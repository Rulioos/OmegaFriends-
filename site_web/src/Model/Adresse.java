package Model;

public class Adresse {
	private String street;
	private String num;
	private String city;
	private String country;
	private String code;
	
	
	public Adresse(String street, String num, String city, String country, String code) {
		super();
		this.street = street;
		this.num = num;
		this.city = city;
		this.country = country;
		this.code = code;
	}
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
