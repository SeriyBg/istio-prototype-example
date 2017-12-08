
package com.istio.mesh.example.istiolocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IstioLocationApplication {

	public static void main(String[] args) {
		SpringApplication.run(IstioLocationApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	WeatherService weatherService(RestTemplate restTemplate,
								  @Value("${backend.weather-service.url:localhost:8081}") String endpoint) {
		return new WeatherService(restTemplate,  endpoint);
	}
}
