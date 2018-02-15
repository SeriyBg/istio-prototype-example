
package com.istio.mesh.example.istiolocation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class IstioForecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(IstioForecastApplication.class, args);
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

	@Bean
	PhaseService phaseService(RestTemplate restTemplate,
							  @Value("${backend.phase-service.url:localhost:8082}") String endpoint) {
        return new PhaseService(restTemplate, endpoint);
	}
}
