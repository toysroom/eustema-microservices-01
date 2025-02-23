package it.eustema.bank.accounts.services.clients;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.eustema.bank.accounts.dto.CardDto;

@FeignClient("cards")
public interface CardFeignClient {
	
	@GetMapping("/api/cards/search/params")
	public ResponseEntity<ArrayList<CardDto>> fetchCard(@RequestParam String mobileNumber);
}
