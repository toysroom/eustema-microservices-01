package it.eustema.bank.accounts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eustema.bank.accounts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findBySex(String sex);
	
}
