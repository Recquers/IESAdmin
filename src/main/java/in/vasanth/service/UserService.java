package in.vasanth.service;

import java.util.List;

import in.vasanth.binding.CreateUsers;
import in.vasanth.entity.AccountDtls;

public interface UserService {
	
	public String createUser(CreateUsers user);
	
	public List<AccountDtls> getAllUsers();
	
	public AccountDtls editUser(Integer userId);
	
	public Boolean deleteUser(Integer userId);

}
