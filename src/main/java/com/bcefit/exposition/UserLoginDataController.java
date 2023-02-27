package com.bcefit.exposition;

import com.bcefit.application.IUserLoginDataService;
import com.bcefit.dto.UserLoginDataDto;
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
        return new ResponseEntity<>("IAM HEALTH CHECK: OK", HttpStatus.OK);
    }

    @PostMapping("createuser")
    public ResponseEntity<String> createUser(@RequestBody UserLoginDataDto userLoginDataDto) {

        //Appel du service pour insérer en BDD
        userLoginDataService.save(userLoginDataDto);

        return new ResponseEntity<>("USER CREATED", HttpStatus.CREATED);
    }

}
