package it.eustema.bank.gatewayserver;

import java.time.LocalDateTime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	
	@Bean
	public RouteLocator eustemaRouteConfig(RouteLocatorBuilder routeLocatorBuilder)
	{
		
		return routeLocatorBuilder.routes()
			.route( p -> p.path("/eustema/accounts/**")
					.filters( 
							f -> f.rewritePath("/eustema/accounts/(?<segment>.*)","/${segment}")
							.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
					).uri("lb://ACCOUNTS"))
			
			.route( p -> p.path("/eustema/cards/**")
					.filters( 
							f -> f.rewritePath("/eustema/cards/(?<segment>.*)","/${segment}")		
							.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
					).uri("lb://CARDS"))
			
			.route( p -> p.path("/eustema/loans/**")
					.filters( 
							f -> f.rewritePath("/eustema/loans/(?<segment>.*)","/${segment}")
							.addResponseHeader("X-Response-Time", LocalDateTime.now().toString())
					).uri("lb://LOANS"))
						
			.build();
	}
}
