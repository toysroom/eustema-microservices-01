package it.eustema.bank.accounts.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CustomerRequestDto {

	private Long customerId;
	
	@NotEmpty(message = "Campo nome vuoto")
	@Size(min=3, message = "Campo nome è corto")
	private String name;
	
	@NotEmpty(message = "Campo email vuoto")
	@Email(message = "Campo email non corretto")
	private String email;
	
	@NotEmpty(message = "Campo telefono vuoto")
	private String mobileNumber;
	
	@NotEmpty(message = "Campo genere vuoto")
	private String sex;
}
