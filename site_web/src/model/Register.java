package model;

public class Register {
	/**used to register a new_user if this one does not already exists*/
	public static boolean register_user(String email, String name, String birthDate, String surname, String password,
			String password_confirm) {

		//checks if passwords are equals
		if (password.equals(password_confirm)) {
			boolean same_email = Database.datastore.createQuery(User.class).field("email").equals(email);
			//checks if account with same email exists
			if (!same_email) {
				User new_user = new User(password, name, surname, email, birthDate);
				return true;
			}else {
				return false;
			}
		}
		return false;

	}
}
