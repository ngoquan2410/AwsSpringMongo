package com.example.demo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ObjectUtils;

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
		private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
		private static final String DEFAULT_SSL_CERTIFICATE = "ssl/global-bundle.pem";
		private static final String SSL_CERTIFICATE = "sslCertificate";
		private static final String KEY_STORE_TYPE = "JKS";
		private static final String DEFAULT_KEY_STORE_PASSWORD = "changeit";
		private static final String SSL_TRUST_STORE = "javax.net.ssl.trustStore";
		private static final String SSL_TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
		private static final String SSL_TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";

		
		

		private static void setSslProperties() {

			try {
				String sslCertificate= System.getProperty(SSL_CERTIFICATE);
				if(ObjectUtils.isEmpty(sslCertificate)) {
					sslCertificate= DEFAULT_SSL_CERTIFICATE;
				}
				logger.info(" ssl certificate path {}",sslCertificate);
				System.setProperty(SSL_TRUST_STORE, "home/ec2-user/tmp/certs/rds-truststore.jks");
				System.setProperty(SSL_TRUST_STORE_TYPE, KEY_STORE_TYPE);
				System.setProperty(SSL_TRUST_STORE_PASSWORD, DEFAULT_KEY_STORE_PASSWORD);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}
}
