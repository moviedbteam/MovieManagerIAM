package com.bcefit.exposition;

import com.bcefit.application.IUserLoginDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/iam")
public class UserLoginDataController {

    @Autowired
    IUserLoginDataService userLoginDataService;

    @GetMapping("health")
    public ResponseEntity<String> health_check(){
        return new ResponseEntity<>("IAM HEALTH CHECK: OK", HttpStatus.OK);
    }


}
