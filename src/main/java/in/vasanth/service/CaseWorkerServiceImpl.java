package in.vasanth.service;

import java.util.Base64;
import java.util.Optional;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.vasanth.binding.CreateUsers;
import in.vasanth.binding.LoginBind;
import in.vasanth.binding.UnlockBind;
import in.vasanth.entity.AccountDtls;
import in.vasanth.repository.AccountRepo;
import in.vasanth.utility.Encryption;
import in.vasanth.utility.Mail;

@Service

public class CaseWorkerServiceImpl implements CaseWorkerService {
	@Autowired
	private AccountRepo accRepo;

	@Autowired
	private Mail emailService;

	@Autowired
	private HttpSession session;

	private String algorithm = "AES/CBC/PKCS5Padding";

	@Override
	public String loginPage(LoginBind login) throws Exception {

		AccountDtls user = accRepo.findByEmail(login.getEmail());
		
		if (user==null) {
			return "Invalid Credentials";
		}

		if(user.getActive().booleanValue()==false) {
			return "Contact Admin, Your account is Inactive";
		}
		if (user.getStatus().equals(0)) {
			return "Account locked,Unlock first";
		}
		String encryptionKey = user.getEncryptionKey();
		SecretKey decryptKey = Encryption.convertStringToSecretKeyto(encryptionKey);
		String initialVector = user.getInitialVector();
		byte[] bytes = initialVector.getBytes();
		IvParameterSpec iv = new IvParameterSpec(bytes);
		String cipherText = user.getPazzword();

		String decrypt = Encryption.decrypt(algorithm, cipherText, decryptKey, iv);

		if (login.getPassword().equals(decrypt)) {
			session.setAttribute("userId", user.getAccountId());
			return "success";
		}else {
			return "Wrong Password";
		}

		

	}

	@Override
	public String unlock(UnlockBind unlock) throws Exception {

		if (unlock.getNewPwd().equals(unlock.getCnfrmPwd())) {
			return "New Password and Confirm password are not same";
		}

		AccountDtls findByEmail = accRepo.findByEmail(unlock.getEmail());
		if(findByEmail==null) {
			return "Check your MailId";
		}
		if (findByEmail.getStatus().equals(0)) {
			if (unlock.getTempPwd().equals(findByEmail.getPazzword())) {
				String plainText = unlock.getCnfrmPwd();
				SecretKey generateKey = Encryption.generateKey(256);
				String keyString = Encryption.convertSecretKeyToString(generateKey);
				IvParameterSpec generateIv = Encryption.generateIv();
				byte[] iv = generateIv.getIV();
				String ivString = Base64.getEncoder().encodeToString(iv);
				String encrypt = Encryption.encrypt(algorithm, plainText, generateKey, generateIv);
				findByEmail.setPazzword(encrypt);
				findByEmail.setEncryptionKey(keyString);
				findByEmail.setInitialVector(ivString);
				findByEmail.setStatus(1);
				accRepo.save(findByEmail);

			} else {
				return "Temp password is wrong";
			}
		} else {
			return "Already Unlocked";
		}

		return "Account Unlocked";
	}

	@Override
	public CreateUsers loadMyProfile() {
		Integer accountId = (Integer) session.getAttribute("userId");
		Optional<AccountDtls> findById = accRepo.findById(accountId);
		if (findById.isPresent()) {
			AccountDtls accountDtls = findById.get();
			CreateUsers user = new CreateUsers();
			BeanUtils.copyProperties(accountDtls, user);
			return user;
		}
		return null;
	}

	@Override
	public String editProfile(CreateUsers user) {
		Integer accountId = (Integer) session.getAttribute("userId");
		Optional<AccountDtls> findById = accRepo.findById(accountId);
		if (findById.isPresent()) {
			AccountDtls accountDtls = findById.get();
			BeanUtils.copyProperties(user, accountDtls);
			AccountDtls save = accRepo.save(accountDtls);
			if (accountId.equals(save.getAccountId())) {
				return "Updated";
			}
		}

		return "Try Again,Some problem";
	}

	@Override
	public String forgotPassword(String email) throws Exception{

		AccountDtls user = accRepo.findByEmail(email);
		if(user==null) {
			return "No Account with given UserName found ";
		}
		if(user.getStatus().equals(0)) {
			return "Unlock your Account";
		}
		String encryptionKey = user.getEncryptionKey();
		SecretKey decryptKey = Encryption.convertStringToSecretKeyto(encryptionKey);
		String initialVector = user.getInitialVector();
		byte[] bytes = Base64.getDecoder().decode(initialVector);
		IvParameterSpec iv = new IvParameterSpec(bytes);
		String cipherText = user.getPazzword();
		String plainText = Encryption.decrypt(algorithm, cipherText, decryptKey, iv);
		
		String to = user.getEmail();
		String subject = "Password for recovery";
		StringBuilder body = new StringBuilder();
		body.append("<h1>Use below password to access your account</h1>");
		body.append("Your Password is = "+plainText);
		body.append("<br/>");

		boolean sendEmail = emailService.sendEmail(to, subject, body.toString());
		if(sendEmail==true) {
			return "Pazzword Sent to your Mail";
		}else {
			return "Some problem Occured ,Try Again...";
		}
		
	}


}
