package it.eustema.bank.accounts.mappers;

import it.eustema.bank.accounts.dto.CustomerRequestDto;
import it.eustema.bank.accounts.dto.CustomerResponseDto;
import it.eustema.bank.accounts.entities.Customer;

public class CustomerMapper {

	public static Customer toEntity(CustomerRequestDto customerRequestDto)
	{
		Customer customer = new Customer();
		customer.setName(customerRequestDto.getName());
		customer.setEmail(customerRequestDto.getEmail());
		customer.setMobileNumber(customerRequestDto.getMobileNumber());
		customer.setSex(customerRequestDto.getSex());
		return customer;
	}
	
	
	public static CustomerResponseDto toResponseDto(Customer customer)
	{
		CustomerResponseDto customerResponseDto = new CustomerResponseDto();
		customerResponseDto.setId(customer.getCustomerId());
		customerResponseDto.setFirstName(customer.getName());
		return customerResponseDto;
	}
}
