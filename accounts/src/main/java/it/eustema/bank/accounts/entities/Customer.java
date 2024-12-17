package it.eustema.bank.accounts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor 
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	
	@Column(name = "first_name")
	// @NotEmpty(message = "Campo nome vuoto")
	// @Size(min=3, message = "Campo nome è corto")
	private String name;
	
//	@NotEmpty(message = "Campo email vuoto")
//	@Email(message = "Campo email non corretto")
	private String email;
	
	// @NotEmpty(message = "Campo telefono vuoto")
	private String mobileNumber;
	
	// @NotEmpty(message = "Campo genere vuoto")
	private String sex;
	
}
