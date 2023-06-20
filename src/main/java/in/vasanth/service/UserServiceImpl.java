package in.vasanth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vasanth.binding.CreateUsers;
import in.vasanth.entity.AccountDtls;
import in.vasanth.entity.PlansDtls;
import in.vasanth.repository.AccountRepo;
import in.vasanth.utility.Mail;
import in.vasanth.utility.RandomPzwd;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private AccountRepo accRepo;
	@Autowired
	private Mail email;

	@Override
	public String createUser(CreateUsers user) {
		AccountDtls accUser = accRepo.findByEmail(user.getEmail());
		if(accUser!=null) {
			return "Choose Unique Email";
		}
		
		AccountDtls accDtl = new AccountDtls();
		BeanUtils.copyProperties(user, accDtl);
		String pzzwd = RandomPzwd.generateRandomPwd();
		accDtl.setPazzword(pzzwd);
		
		String to=user.getEmail();
		String subject="Unlock your Account";
		StringBuilder body=new StringBuilder();
		body.append("<h1>Use below password to unlock account</h1>");
		body.append("TempPwd = "+pzzwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click here to unlock account</a>");
		
		email.sendEmail(to,subject,body.toString());
		
		
		AccountDtls save = accRepo.save(accDtl);
		if(save.getAccountId()!=null) {
			return "Action Completed";
		}else {
			return "Error Occured, Try Again";
		}
		
	}

	@Override
	public List<AccountDtls> getAllUsers() {
		List<AccountDtls> findAll = accRepo.findAll();
		boolean removeIf = findAll.removeIf(e->e.getRole().equals("ADMIN"));
		if(removeIf) {
			return findAll;
		}else {
			return null;
		}
		
		
		
	}

	@Override
	public AccountDtls editUser(Integer userId) {
		Optional<AccountDtls> findById = accRepo.findById(userId);
		if(findById.isPresent()) {
			return findById.get();
		}else {
			return null;
		}
		
	}

	@Override
	public Boolean deleteUser(Integer userId) {
		
		Optional<AccountDtls> findById = accRepo.findById(userId);
		if(findById.isPresent()) {
			 AccountDtls accountDtls = findById.get();
			 Boolean active = accountDtls.getActive();
			if(active) {
				accountDtls.setActive(false);
				accRepo.save(accountDtls);
			}else {
				accountDtls.setActive(true);;
				accRepo.save(accountDtls);
			}
			return true;
		}else {
			return false;
		}
		
	}
	
	

}
