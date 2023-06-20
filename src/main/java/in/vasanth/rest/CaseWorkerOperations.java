package in.vasanth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.vasanth.binding.CreateUsers;
import in.vasanth.binding.DashBoard;
import in.vasanth.binding.LoginBind;
import in.vasanth.binding.UnlockBind;
import in.vasanth.service.CaseWorkerService;

@RestController
public class CaseWorkerOperations {
	
	@Autowired
	private CaseWorkerService caseService;
	
	@PostMapping(value="/login",
			consumes= {"application/json"},
			produces= {"application/json"}
			)
	public String accountLogin(@RequestBody LoginBind login) throws Exception {
		String status = caseService.loginPage(login);
		if(status=="success") {
			return "redirect:/dashboard";
		}
		return status;
		
	}
	
	@GetMapping("/unlock")
	public UnlockBind unlockLink(@RequestParam String email) {
		UnlockBind unlock=new UnlockBind();
		unlock.setEmail(email);
		return unlock;
		
	}
	
	@PostMapping(value="/unlockDtls",
			consumes={"application/json"},
			produces= {"application/json"})
	public String unlockAccount(@RequestBody UnlockBind unlock) throws Exception {
		
		return caseService.unlock(unlock);
	}
	@GetMapping(value="/myProfile",
			produces= {"application/json"}
			)
	public CreateUsers loadMyProfile() {
		return caseService.loadMyProfile();
		
	}
	
	@PostMapping(value="/editProfile", 
			consumes={"application/json"},
			produces= {"application/json"}
	)
	public String editProfile(@RequestBody CreateUsers user) {
		return caseService.editProfile(user);
	}
	
	@GetMapping(value="/dashboard", 
			produces= {"application/json"}
			)
	public DashBoard showDashboard() {
		
		DashBoard dash = new DashBoard();
		return dash;
	}
	
	@PostMapping(value="/forgot",  
			consumes={"application/json"},
			produces= {"application/json"}
			)
	public String forgotPwd(@RequestParam String email) throws Exception {
		
		return  caseService.forgotPassword(email);
		
	}
}
