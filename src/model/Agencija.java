package model;

public class Agencija {
	
	private int id;
	private String naziv;
	private String adresa;
	private String telefon;
	private Ponuda ponuda;
	private String imgUrl;
	public Agencija(int id, String naziv, String adresa, String telefon, Ponuda ponuda, String imgUrl) {//za izvl iz baze
		super();
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.ponuda = ponuda;
		this.imgUrl = imgUrl;
	}
	
	public Agencija(String naziv, String adresa, String telefon, Ponuda ponuda, String imgUrl) {//za stavljanje u 
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.ponuda = ponuda;
		this.imgUrl = imgUrl;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public Ponuda getPonuda() {
		return ponuda;
	}
	public void setPonuda(Ponuda ponuda) {
		this.ponuda = ponuda;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	

}
