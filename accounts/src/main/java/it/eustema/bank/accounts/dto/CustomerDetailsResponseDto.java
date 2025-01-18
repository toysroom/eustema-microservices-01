package it.eustema.bank.accounts.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerDetailsResponseDto {

	private Long id;
	private String firstName;
	private String email;
	private String mobileNumber;
	
	private ArrayList<CardDto> cardsDto;
}
