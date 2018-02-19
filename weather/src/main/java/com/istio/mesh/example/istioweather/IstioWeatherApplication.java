package com.istio.mesh.example.istioweather;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IstioWeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(IstioWeatherApplication.class, args);
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	IconService iconService(RestTemplate restTemplate,
							@Value("${backend.icon-service.url:localhost:8083}") String endpoint) {
		return new IconService(restTemplate, endpoint);
	}
}
