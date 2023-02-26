package com.bcefit.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.bcefit.domaine.UserLoginData;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserLoginData userLoginData = null;

        System.out.println("==================== JWTAuthenticationFilter - ATTEMPT AUTHENTIFICATION ==================== ");

        try {
            userLoginData= new ObjectMapper().readValue(request.getInputStream(),UserLoginData.class);
        } catch(JsonParseException jpe){
            throw new RuntimeException(jpe);
        }
        catch (JsonMappingException e ) {
            throw new RuntimeException(e);
        }
        catch (IOException e ) {
            throw new RuntimeException(e);
        }
//        System.out.println(userLoginData.getEmail() + " - " + userLoginData.getPasswordHash() + " - " + userLoginData.getLoginName() );

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginData.getEmail(),userLoginData.getPasswordHash()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();

        List<String> roles = new ArrayList<>();

//        springUser.getAuthorities().forEach(au -> {
//            roles.add(au.getAuthority());
//        });

        String monToken = JWT.create().
                withSubject(springUser.getUsername()).
//                withArrayClaim("claims", roles.toArray(new String[roles.size()])).
                sign(Algorithm.HMAC256("monSecret123456789"));

        System.out.println("==================== JWTAuthenticationFilter - SUCCESSFULL AUTHENTIFICATION ====================");
        System.out.println("TOKEN JWT généré: " + monToken);


        // Réponse renvoyée dans la requête
        response.getWriter();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("jwt", monToken);
//        jsonObject.addProperty("access", springUser.getAuthorities().toString());
        jsonObject.addProperty("id", springUser.getUsername());

        response.getWriter().print(jsonObject);
        response.getWriter().flush(); // commit la réponse

    }
}