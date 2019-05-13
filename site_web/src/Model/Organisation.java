package Model;

import java.net.URL;

public class Organisation {
	private String name;
	private Adresse adresse;
	private String phone;
	private String mail;
	private URL website;
	
	
	public Organisation(String name, Adresse adresse, String phone, String mail) {
		super();
		this.name = name;
		this.adresse = adresse;
		this.phone = phone;
		this.mail = mail;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public URL getWebsite() {
		return website;
	}
	public void setWebsite(URL website) {
		this.website = website;
	}
	
	
}
