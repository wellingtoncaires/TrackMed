package com.trackmed.tmgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class TmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TmGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder
				.routes()
					.route(r -> r.path("/v1/hospital/**").uri("lb://tm-hospital") )
					.route(r -> r.path("/v1/mock/**").uri("lb://tm-mock") )
					.route(r -> r.path("/v1/cidadao/**").uri("lb://tm-citizen") )
					.route(r -> r.path("/v1/farmacia/**").uri("lb://tm-pharmacy") )
				.build();
	}
}
