package com.example.demo.config;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.autoconfigure.mongo.MongoPropertiesClientSettingsBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClientSettings;

@Configuration
public class MongoConfiguration {

	private final MongoProperties mongoProperties;

	public MongoConfiguration(MongoProperties mongoProperties) {
		super();
		this.mongoProperties = mongoProperties;
	}

	@Bean
	public MongoClientSettings mongoClientSettings() {
		return MongoClientSettings.builder()

				.applyToSslSettings(builder -> builder.enabled(true).invalidHostNameAllowed(true)).build();
	}

	@Bean
	public MongoPropertiesClientSettingsBuilderCustomizer mongoPropertiesCustomizer(MongoProperties properties,
			Environment environment) {
		return new MongoPropertiesClientSettingsBuilderCustomizer(properties, environment);
	}
}
