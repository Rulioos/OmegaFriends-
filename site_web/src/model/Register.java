package model;
import org.apache.commons.codec.digest.Crypt;

public class Register {
	/**used to register a new_user if this one does not already exists*/
	public static boolean register_user(String email, String name, String birthDate, String surname, String password,
			String password_confirm) {
		Database.datastore.ensureIndexes();
		//checks if passwords are equals
		if (password.equals(password_confirm)) {
			User user = Database.datastore.createQuery(User.class).field("email").equal(email).first();
			//checks if account with same email exists
			if (user==null) {
				String pwd=Crypt.crypt(password,"$6$xxxx");//Crypt.crypt(password);
				User new_user = new User(pwd, name, surname, email, birthDate);
				Database.datastore.save(new_user);
				return true;
			}else {
				return false;
			}
		}
		return false;

	}
	
	
}
