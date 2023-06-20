package in.vasanth.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Entity
@Data
public class AccountDtls {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer accountId;
	private String fullName;
	private String email;
	private String pazzword;
	private Integer status=0;
	private Boolean active=true;
	private Long mobile;
	private String gender;
	private String dob;
	private String ssn;
	private String encryptionKey;
	private String initialVector;
	private String role="USER";
	
	
	

}
