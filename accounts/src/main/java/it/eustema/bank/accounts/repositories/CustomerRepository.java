package it.eustema.bank.accounts.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eustema.bank.accounts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findBySex(String sex);
	
	
	Optional<Customer> findByMobileNumber(String mobileNumber);
	
	
	Optional<Customer> findByEmailOrMobileNumber(String email, String mobileNumber);
	
}
