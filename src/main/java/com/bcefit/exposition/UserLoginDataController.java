package com.bcefit.exposition;

import com.bcefit.application.IUserLoginDataService;
import com.bcefit.dto.UserLoginDataDto;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/iam")
public class UserLoginDataController {

    @Autowired
    IUserLoginDataService userLoginDataService;

    @GetMapping("health")
    public ResponseEntity<String> health_check(){

        return new ResponseEntity<>("IAM - HEALTH CHECK STATUS: OK", HttpStatus.OK);
    }

    @PostMapping("createuser")
    public ResponseEntity<String> createUser(@RequestBody UserLoginDataDto userLoginDataDto) {

        //Appel du service pour insérer en BDD
        userLoginDataService.save(userLoginDataDto);

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("email", userLoginDataDto.getEmail());
        jsonResponse.addProperty("pseudo", userLoginDataDto.getLoginName());

        return new ResponseEntity<>(jsonResponse.toString(), HttpStatus.CREATED);
    }

}
