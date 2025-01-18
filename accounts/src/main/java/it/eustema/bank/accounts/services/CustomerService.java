package it.eustema.bank.accounts.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import it.eustema.bank.accounts.dto.CardDto;
import it.eustema.bank.accounts.dto.CustomerDetailsResponseDto;
import it.eustema.bank.accounts.entities.Customer;
import it.eustema.bank.accounts.exceptions.ResourceNotFoundException;
import it.eustema.bank.accounts.repositories.CustomerRepository;
import it.eustema.bank.accounts.services.clients.CardFeignClient;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	private CardFeignClient cardFeignClient;
	
	public CustomerService(
		CustomerRepository customerRepository,
		CardFeignClient cardFeignClient
	)
	{
		this.customerRepository = customerRepository;
		this.cardFeignClient = cardFeignClient;
	}
	
	
	public List<Customer> getAll()
	{
		return this.customerRepository.findAll();
	}
	
	public List<Customer> getBySex(String sex)
	{
		return this.customerRepository.findBySex(sex);
	}
	
	public Customer getByMobileNumber(String mobileNumber)
	{
		// return this.customerRepository.findByMobileNumber(mobileNumber);
		return this.customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow( () -> new ResourceNotFoundException("Customer", "mobile number", mobileNumber.toString() ) );
	}
	
	public Optional<Customer> getByEmailOrMobileNumber(String email, String mobileNumber)
	{
		return this.customerRepository.findByEmailOrMobileNumber(email, mobileNumber);
	}
	
	
	public Customer getById(Long id)
	{
		Customer customer = this.customerRepository.findById(id)
			.orElseThrow( () -> new ResourceNotFoundException("Customer", "id", id.toString() ) );
				
		return customer;
	}
	
	
	public Customer store(Customer customerRequest)
	{
		Customer customerAdded = this.customerRepository.save(customerRequest);
		return customerAdded;
	}
	
	
	public void deleteById(Long id)
	{
		this.customerRepository.deleteById(id);
	}
	
	
	public Customer update(Customer customerRequest)
	{
		Customer customerUpdated = this.customerRepository.save(customerRequest);
		return customerUpdated;
	}

	
	
	
	public CustomerDetailsResponseDto fetchCustomerDetails(String mobileNumber)
	{
		// info sul cliente
		Customer customer = this.getByMobileNumber(mobileNumber);
			
		// info su account
		
		
		
		// info sulle cards
		ResponseEntity<ArrayList<CardDto>> cardsDto = this.cardFeignClient.fetchCard(mobileNumber);
		
		
		
		
		
		// info su loans
		
		
		// impacchettare tutto in una risposta e renderla al controller
		CustomerDetailsResponseDto customerDetailsResponseDto = new CustomerDetailsResponseDto();
		customerDetailsResponseDto.setId(customer.getCustomerId());
		customerDetailsResponseDto.setFirstName(customer.getName());
		customerDetailsResponseDto.setEmail(customer.getEmail());
		customerDetailsResponseDto.setCardsDto(cardsDto.getBody());
		
		return customerDetailsResponseDto;
		
		
	}
	
}
