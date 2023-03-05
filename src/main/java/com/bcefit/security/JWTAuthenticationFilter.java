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
import java.util.Date;
import java.util.List;

import static org.springframework.util.CollectionUtils.toArray;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager=authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        UserLoginData userLoginData = null;

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

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginData.getEmail(),userLoginData.getPasswordHash()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User springUser = (User) authResult.getPrincipal();

        // Gestion des rôles
        List<String> roles = new ArrayList<>();
        roles.add("USER");

        String monToken = JWT.create().
                withSubject(springUser.getUsername()).
                withArrayClaim("claims",roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME)).
                sign(Algorithm.HMAC256(SecurityConstants.SECRET));


        // Réponse renvoyée dans la requête
        response.getWriter();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("jwt", monToken);
        jsonObject.addProperty("email", springUser.getUsername());

        response.getWriter().print(jsonObject);
        response.getWriter().flush(); // commit la réponse

    }
}