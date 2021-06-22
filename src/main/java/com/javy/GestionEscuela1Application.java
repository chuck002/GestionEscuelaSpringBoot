package com.javy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GestionEscuela1Application implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("ADMIN:"+encoder.encode("admin"));
		System.out.println("STUDENT:"+encoder.encode("student"));
		
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionEscuela1Application.class, args);
	}

	

}
