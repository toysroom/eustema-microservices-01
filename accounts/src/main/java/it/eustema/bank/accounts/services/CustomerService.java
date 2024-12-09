package it.eustema.bank.accounts.services;

import java.util.List;

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
}
