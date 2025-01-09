package it.eustema.bank.cards.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import it.eustema.bank.cards.models.Card;

public interface CardRepository extends JpaRepository<Card, Long>{

	ArrayList<Card> findByMobileNumber(String mobileNumber);
}
