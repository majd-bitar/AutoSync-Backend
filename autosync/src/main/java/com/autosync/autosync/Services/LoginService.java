package com.autosync.autosync.Services;


import com.autosync.autosync.Models.LoginModel;
import com.autosync.autosync.Repositories.LoginRepository;
import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.autosync.autosync.ExceptionHandling.CustomExceptions;

import java.util.*;
import java.util.logging.Logger;
import java.util.logging.Logger;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Dotenv dotenv = Dotenv.load();
    private static final Logger log = Logger.getLogger(LoginService.class.getName());
    private final String JWTSecret = dotenv.get("JWT_SECRET_KEY");

    // Method to generate a JWT token
    private Map<String ,String> generateJwtToken(LoginModel login) {
        //log.info("Generating JWT token for user: " + login.getUsername());
        try {
            //log.info("Adding claim: id");
            String id = login.getLoginId().toString(); // Convert UUID to string if needed
            //log.info("Adding claim: role");
            String role = login.getRole().name(); // Assuming it's an enum
            //log.info("Adding claim: status");
            String status = login.getStatus().name(); // Assuming it's an enum
            //log.info("JWT Secret (length): " + (JWTSecret != null ? JWTSecret : "Not initialized"));
            String token = Jwts.builder()
                    .setSubject(login.getUsername())
                    .claim("id", id)
                    .claim("role", role)
                    .claim("status", status)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                    .signWith(SignatureAlgorithm.HS256, JWTSecret)
                    .compact();
            //log.info("JWT token generated successfully for user: " + login.getUsername());
            Map<String,String> returnedToken = new HashMap<>();
            returnedToken.put("token",token);
            returnedToken.put("username",login.getUsername());
            returnedToken.put("role",login.getRole().name());
            returnedToken.put("loginId",login.getLoginId().toString());
            return returnedToken;
        } catch (Exception e) {
            log.severe("Error generating JWT token: " + e.getMessage());
            throw e;
        }
    }



    public Map<String ,String> authenticateUser(String username, String password) {
        Optional<LoginModel> optionalLogin = loginRepository.findByUsername(username);

        if (optionalLogin.isPresent()) {
            LoginModel login = optionalLogin.get();

            if (bCryptPasswordEncoder.matches(password, login.getPassword())) {
                return generateJwtToken(login);
            } else {
                throw new CustomExceptions.InvalidCredentialsException("Invalid username or password");
            }
        } else {
            throw new CustomExceptions.UserNotFoundException("User not found");
        }
    }

    public LoginModel updateStatus(UUID loginId, LoginModel.Status status) {
        Optional<LoginModel> optionalLogin = loginRepository.findById(loginId);
        if(optionalLogin.isPresent()){
            optionalLogin.get().setStatus(status);
            return loginRepository.save(optionalLogin.get());
        }
        else{
            throw new CustomExceptions.UserNotFoundException("User not found");
        }
    }

    // Method to handle user signup
    public LoginModel signup(LoginModel loginModel) {
        // Check if the username or email already exists
        if (loginRepository.existsByUsername(loginModel.getUsername())) {
            throw new CustomExceptions.UsernameAlreadyExistsException("Username already exists");
        }

        loginModel.setPassword(bCryptPasswordEncoder.encode(loginModel.getPassword()));
        if (loginModel.getStatus() == null) {
            loginModel.setStatus(LoginModel.Status.INACTIVE);
        }
        return loginRepository.save(loginModel);
    }


}
