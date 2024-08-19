package com.autosync.autosync.Services;


import com.autosync.autosync.Repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    LoginRepository loginRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
}
