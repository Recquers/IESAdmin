package in.vasanth.utility;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPzwd {
	public static String generateRandomPwd() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String pwd = RandomStringUtils.random( 8, characters );
		return pwd;
	}

}
