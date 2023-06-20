package in.vasanth.service;

import in.vasanth.binding.CreateUsers;
import in.vasanth.binding.LoginBind;
import in.vasanth.binding.UnlockBind;

public interface CaseWorkerService {
	
	public String loginPage(LoginBind login) throws Exception; 
	
	public String unlock(UnlockBind unlock) throws Exception;
	
	public CreateUsers loadMyProfile();
	
	public String editProfile(CreateUsers user);
	
	public String forgotPassword(String email) throws Exception;
		
	

}
