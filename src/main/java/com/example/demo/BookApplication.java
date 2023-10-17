package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;

@SpringBootApplication
public class BookApplication {

	public static void main(String[] args) {
		SSLContextHelper.setSslProperties();
		SpringApplication.run(BookApplication.class, args);
	}

	 @Bean
	    CommandLineRunner init(RoleRepository roleRepository) {

	        return args -> {

	            Role adminRole = roleRepository.findByRole("ADMIN");
	            if (adminRole == null) {
	                Role newAdminRole = new Role();
	                newAdminRole.setRole("ADMIN");
	                roleRepository.save(newAdminRole);
	            }
	            
	            Role userRole = roleRepository.findByRole("USER");
	            if (userRole == null) {
	                Role newUserRole = new Role();
	                newUserRole.setRole("USER");
	                roleRepository.save(newUserRole);
	            }
	        };

	    }
	 

	 protected static class SSLContextHelper {
		private static final String KEY_STORE_TYPE = "JKS";
		private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";
		private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
		private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
		private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";

		
		

		private static void setSslProperties() {

			try {
				System.setProperty(SSL_TRUST_STORE, "tmp/certs/rds-truststore.jks");
				System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
				System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}
}
