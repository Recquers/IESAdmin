package in.vasanth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.vasanth.entity.AccountDtls;

public interface AccountRepo extends JpaRepository<AccountDtls, Integer>{
	
	public AccountDtls findByEmail(String email);
	
	public AccountDtls findByEmailAndPazzword(String email,String pazzword);
		
	

}
