package com.autosync.autosync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.autosync.autosync")
public class AutosyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutosyncApplication.class, args);
	}

}
