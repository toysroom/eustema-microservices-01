package it.eustema.bank.accounts.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eustema.bank.accounts.dto.CustomerRequestDto;
import it.eustema.bank.accounts.dto.CustomerResponseDto;
import it.eustema.bank.accounts.dto.ResponseErrorDto;
import it.eustema.bank.accounts.dto.ResponseSuccessDto;
import it.eustema.bank.accounts.entities.Customer;
import it.eustema.bank.accounts.mappers.CustomerMapper;
import it.eustema.bank.accounts.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
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
	public ResponseEntity<List<Customer>> index(@RequestParam(defaultValue = "") String sex)
	{
		List<Customer> customers = null;
		if (sex.equals("M") || sex.equals("F"))
		{
			customers = this.customerService.getBySex(sex);
		}
		else {
			customers = this.customerService.getAll();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(customers);
	}	
	
	
	@GetMapping("/customers/{id}")
	public ResponseEntity<?> show(
		@PathVariable Long id
	)
	{
		Customer customer = this.customerService.getById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(
			new ResponseSuccessDto<Customer>(HttpStatus.OK, "ok", customer)
		);	
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<?> store(
		@Valid @RequestBody CustomerRequestDto customerRequest,
		BindingResult result,
		HttpServletRequest request
	)
	{		
		if (result.hasErrors())
		{
			ArrayList<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach( error -> errors.add(error.getDefaultMessage()) );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				new ResponseErrorDto<ArrayList<String>>(
					request.getRequestURI(), 
					request.getMethod(), 
					HttpStatus.BAD_REQUEST, 
					errors
				)
			);
		}
		
		// mapper request dto -> entity
		Customer customer = CustomerMapper.toEntity(customerRequest);
		
		
		Customer customerAdded = this.customerService.store(customer);
		
		// mapper entity -> response dto 
		CustomerResponseDto customerResponseDto = CustomerMapper.toResponseDto(customerAdded);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
			new ResponseSuccessDto<CustomerResponseDto>(
				HttpStatus.CREATED, 
				"Customer creato correttamente", 
				customerResponseDto
			)
		);
	}
	
	
	@PutMapping("/customers/{id}")
	public ResponseEntity<?> update(
		@PathVariable Long id,
		@Valid @RequestBody CustomerRequestDto customerRequest,
		BindingResult result
	)
	{
		this.customerService.getById(id);
		
		
		if (result.hasErrors())
		{
			ArrayList<String> errors = new ArrayList<String>();
			result.getAllErrors().forEach( error -> errors.add(error.getDefaultMessage()) );
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
		}
		
		// mapper request dto -> entity
		Customer customer = CustomerMapper.toEntity(customerRequest);
		customer.setCustomerId(customerRequest.getCustomerId());
		
		
		Customer customerUpdated = this.customerService.update(customer);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerUpdated);
	}
	
	
	@PatchMapping("/customers/{id}")
	public ResponseEntity<?> updateFields(
		@PathVariable Long id,
		@RequestBody Map<String, String> updateFields
	)
	{
		
		// aggiungere validazione
		
		Customer customer = this.customerService.getById(id);
		
		if (updateFields.containsKey("sex"))
		{
			customer.setSex(updateFields.get("sex"));
		}
		
		// ... 
		
		Customer customerUpdated = this.customerService.update(customer);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerUpdated);
	}
	
	
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<?> destroy(
		@PathVariable Long id
	)
	{
		this.customerService.getById(id);
		
		this.customerService.deleteById(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
	
}
