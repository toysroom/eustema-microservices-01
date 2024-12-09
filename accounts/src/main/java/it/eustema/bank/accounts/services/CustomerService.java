package it.eustema.bank.accounts.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import it.eustema.bank.accounts.entities.Customer;
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
	
	
	public Optional<Customer> getById(Long id)
	{
		return this.customerRepository.findById(id);
	}
	
	public Customer store(Customer customerRequest)
	{
		Customer customerAdded = this.customerRepository.save(customerRequest);
		return customerAdded;
	}
}
