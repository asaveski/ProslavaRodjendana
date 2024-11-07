package model;

public class Korisnik extends User {
	private String imePrezime;
	int poeni;
	private String brojTelefona;
	public Korisnik(String username, String imePrezime, String brojTelefona, Role role) {
		super(username, role);
		this.imePrezime = imePrezime;
		this.brojTelefona = brojTelefona;


	}
	public Korisnik(String username, String pass, Role role, String imePrezime, int poeni, String brojTelefona) {
		super(username, pass, role);
		this.imePrezime = imePrezime;
		this.poeni = poeni;
		this.brojTelefona = brojTelefona;
	}
	
	public String getImePrezime() {
		return imePrezime;
	}
	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}
	public int getPoeni() {
		return poeni;
	}
	public void setPoeni(int poeni) {
		this.poeni = poeni;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	

}
