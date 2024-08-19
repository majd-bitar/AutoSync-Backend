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

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Dotenv dotenv = Dotenv.load();
    private final String JWTSecret = dotenv.get("JWT_SECRET_KEY");

    // Method to generate a JWT token
    private String generateJwtToken(LoginModel login) {
        return Jwts.builder()
                .setSubject(login.getUsername())
                .claim("id",login.getLoginId())
                .claim("role", login.getRole())
                .claim("status", login.getStatus())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour expiration
                .signWith(SignatureAlgorithm.HS256, JWTSecret)
                .compact();
    }

    public String authenticateUser(String username, String password) {
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


}
