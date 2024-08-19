package com.autosync.autosync.Controllers;


import com.autosync.autosync.Models.LoginModel;
import com.autosync.autosync.Services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class LoginController {

    @Autowired
    LoginService loginService;


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestBody LoginModel loginRequest) {
        Map<String ,String> token = loginService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        return new ResponseEntity<Map<String ,String>>(token,HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<LoginModel> signupUser(@RequestBody LoginModel loginRequest){
        LoginModel signedUp = loginService.signup(loginRequest);
        return new ResponseEntity<>(signedUp, HttpStatus.CREATED);
    }
}
