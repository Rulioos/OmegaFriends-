package model;

import org.apache.commons.codec.digest.Crypt;

public class Authentification {
	public static boolean auhtenticate(String mail, String pwd) {
		Database.datastore.ensureIndexes();

		User user = Database.datastore.createQuery(User.class).field("email").equal(mail).first();
		if (user == null) {
			return false;
		}

		String input_pwd = Crypt.crypt(pwd, "$6$xxxx");

		if (user.getPassword().equals(input_pwd)) {
			return true;
		}

		return false;
	}
}
