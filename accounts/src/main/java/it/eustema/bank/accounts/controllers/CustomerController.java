package it.eustema.bank.accounts.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.eustema.bank.accounts.entities.Customer;
import it.eustema.bank.accounts.services.CustomerService;
import jakarta.validation.Valid;

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
	public ResponseEntity<List<Customer>> index()
	{
		return ResponseEntity.status(HttpStatus.OK).body(this.customerService.getAll());
	}	
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> show(
		@PathVariable Long id
	)
	{
		Optional<Customer> customerOptional = this.customerService.getById(id);
		
		if (customerOptional.isEmpty())
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("customer non esistente");
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(customerOptional.get());
	}
	
	
	
	@PostMapping("/customers")
	public ResponseEntity<?> store(
		@Valid @RequestBody Customer customerRequest,
		BindingResult result
	)
	{		
		if (result.hasErrors())
		{
			ArrayList<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach( error -> errors.add(error.getDefaultMessage()) );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		Customer customerAdded = this.customerService.store(customerRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(customerAdded);
	}
	
}
