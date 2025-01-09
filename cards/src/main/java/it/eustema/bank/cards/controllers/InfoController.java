package it.eustema.bank.cards.controllers;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	private Environment environment;
	
	public InfoController(Environment environment)
	{
		this.environment = environment;
	}
	
	@GetMapping("/environment")
	public String env()
	{
		return this.environment.getProperty("environment");
	}
	
	@GetMapping("/version")
	public String version()
	{
		return this.environment.getProperty("version");
	}
	
	@GetMapping("/apikey")
	public String apikey()
	{
		return this.environment.getProperty("api_key");
	}
}
