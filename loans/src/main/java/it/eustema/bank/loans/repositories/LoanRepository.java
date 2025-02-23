package it.eustema.bank.loans.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eustema.bank.loans.models.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>{

	ArrayList<Loan> findByMobileNumber(String mobileNumber);
}
