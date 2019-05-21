package model;

import org.apache.commons.codec.digest.Crypt;

public class Authentification {
	public static boolean auhtenticate(String mail, String pwd) {
		Database.datastore.ensureIndexes();

		User user = Database.datastore.createQuery(User.class).field("email").equal(mail).first();
		System.out.println(user.getBirthDate());
		System.out.println(user.getPassword());
		

		String input_pwd = Crypt.crypt(pwd, "$6$xxxx");
		System.out.println(user.getPassword());
		System.out.println(input_pwd);

		if (user.getPassword().equals(input_pwd)) {
			return true;
		}

		return false;
	}
}
