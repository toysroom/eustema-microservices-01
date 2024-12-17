package it.eustema.bank.accounts.services;

import java.util.List;

import org.springframework.stereotype.Service;

import it.eustema.bank.accounts.entities.Customer;
import it.eustema.bank.accounts.exceptions.ResourceNotFoundException;
import it.eustema.bank.accounts.repositories.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository customerRepository;
	
	public CustomerService(
		CustomerRepository customerRepository
	)
	{
		this.customerRepository = customerRepository;
	}
	
	
	public List<Customer> getAll()
	{
		return this.customerRepository.findAll();
	}
	
	public List<Customer> getBySex(String sex)
	{
		return this.customerRepository.findBySex(sex);
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
	
}
