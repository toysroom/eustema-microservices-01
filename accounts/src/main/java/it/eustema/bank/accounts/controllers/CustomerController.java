package it.eustema.bank.accounts.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import it.eustema.bank.accounts.entities.Customer;
import it.eustema.bank.accounts.services.CustomerService;

@RestController
public class CustomerController {
	
	private CustomerService customerService;
	
	public CustomerController(
		CustomerService customerService
	)
	{
		this.customerService = customerService;
	}
	
	
	@GetMapping("/customers")
	public List<Customer> index()
	{
		return this.customerService.getAll();
	}
}
