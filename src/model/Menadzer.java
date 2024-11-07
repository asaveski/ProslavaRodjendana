package model;

public class Menadzer extends User {
	private String imePrezime;
	private String brojTelefona;
	private Agencija agencija;
	public Menadzer(String username, String pass, Role role, String imePrezime, String brojTelefona,
			Agencija agencija) {
		super(username, pass, role);
		this.imePrezime = imePrezime;
		this.brojTelefona = brojTelefona;
		this.agencija = agencija;
	}
	public Menadzer(String username, Role role, String imePrezime, String brojTelefona,
			Agencija agencija) {
		super(username, role);
		this.imePrezime = imePrezime;
		this.brojTelefona = brojTelefona;
		this.agencija = agencija;
	}
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public Agencija getAgencija() {
		return agencija;
	}
	public void setAgencija(Agencija agencija) {
		this.agencija = agencija;
	}
	
	
	

}
