package in.vasanth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IesAdminApiApplication {

	public static void main(String[] args) throws Exception {
		//ConfigurableApplicationContext run = 
				SpringApplication.run(IesAdminApiApplication.class, args);
		/*AccountRepo accountRepo = run.getBean(AccountRepo.class);

		AccountDtls userAcc = new AccountDtls();
		userAcc.setEmail("vasanth0110@hotmail.com");
		userAcc.setPazzword("0101010101");
		userAcc.setFullName("VasanthKumar");
		SecretKey generateKey = Encryption.generateKey(256);
		String keyString = Encryption.convertSecretKeyToString(generateKey);
		IvParameterSpec generateIv = Encryption.generateIv();
		byte[] iv = generateIv.getIV();
		String ivString = Base64.getEncoder().encodeToString(iv);

		String algorithm = "AES/CBC/PKCS5Padding";
		String encrypt = Encryption.encrypt(algorithm, userAcc.getPazzword(), generateKey, generateIv);
		userAcc.setPazzword(encrypt);
		userAcc.setEncryptionKey(keyString);
		userAcc.setInitialVector(ivString);
		accountRepo.save(userAcc);

		AccountDtls accountDtls = accountRepo.findById(1).get();
		String password = accountDtls.getPazzword();
		String encryptKey = accountDtls.getEncryptionKey();
		String initialVector = accountDtls.getInitialVector();
		SecretKey decryptKey = Encryption.convertStringToSecretKeyto(encryptKey);
		byte[] decode = Base64.getDecoder().decode(initialVector);
		IvParameterSpec decodeIv = new IvParameterSpec(decode);
	
		String decrypt = Encryption.decrypt(algorithm, password, decryptKey, decodeIv);
		System.out.println(decrypt);*/

	}

}
