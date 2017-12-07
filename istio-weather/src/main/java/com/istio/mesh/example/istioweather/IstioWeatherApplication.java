package com.istio.mesh.example.istioweather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
public class IstioWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(IstioWeatherApplication.class, args);
	}

	@Bean
	@Primary
	@ConditionalOnEnvironmentVariable
	public MongoProperties mongoProperties() {
		final MongoProperties mongoProperties = new MongoProperties();
		mongoProperties.setHost("localhost");
		mongoProperties.setPort(27017);
		return mongoProperties;
	}
}
