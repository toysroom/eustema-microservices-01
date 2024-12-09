package it.eustema.bank.accounts.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eustema.bank.accounts.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
