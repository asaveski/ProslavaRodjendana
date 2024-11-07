package model;

public class Rezervacija {
	private int id;
	private Korisnik korisnik;
	private String brojTelefona;
	private String vremeRodjendana;
	private int kolicinaGaziranogSoka;
	private int kolicinaNegaziranogSoka;
	private boolean klovn;
	private boolean diskoteka;
	private boolean sportskeIgre;
	private boolean igraonica;
	private Agencija agencija;
	public Rezervacija(int id, Korisnik korisnik, String brojTelefona, String vremeRodjendana,
			int kolicinaGaziranogSoka, int kolicinaNegaziranogSoka, boolean klovn, boolean diskoteka,
			boolean sportskeIgre, boolean igraonica, Agencija agencija) {
		super();
		this.id = id;
		this.korisnik = korisnik;
		this.brojTelefona = brojTelefona;
		this.vremeRodjendana = vremeRodjendana;
		this.kolicinaGaziranogSoka = kolicinaGaziranogSoka;
		this.kolicinaNegaziranogSoka = kolicinaNegaziranogSoka;
		this.klovn = klovn;
		this.diskoteka = diskoteka;
		this.sportskeIgre = sportskeIgre;
		this.igraonica = igraonica;
		this.agencija = agencija;
	}
	
	public Rezervacija(Korisnik korisnik, String brojTelefona, String vremeRodjendana, int kolicinaGaziranogSoka,
			int kolicinaNegaziranogSoka, boolean klovn, boolean diskoteka, boolean sportskeIgre, boolean igraonica,
			Agencija agencija) {
		super();
		this.korisnik = korisnik;
		this.brojTelefona = brojTelefona;
		this.vremeRodjendana = vremeRodjendana;
		this.kolicinaGaziranogSoka = kolicinaGaziranogSoka;
		this.kolicinaNegaziranogSoka = kolicinaNegaziranogSoka;
		this.klovn = klovn;
		this.diskoteka = diskoteka;
		this.sportskeIgre = sportskeIgre;
		this.igraonica = igraonica;
		this.agencija = agencija;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Korisnik getKorisnik() {
		return korisnik;
	}
	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
	public String getBrojTelefona() {
		return brojTelefona;
	}
	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}
	public String getVremeRodjendana() {
		return vremeRodjendana;
	}
	public void setVremeRodjendana(String vremeRodjendana) {
		this.vremeRodjendana = vremeRodjendana;
	}
	public int getKolicinaGaziranogSoka() {
		return kolicinaGaziranogSoka;
	}
	public void setKolicinaGaziranogSoka(int kolicinaGaziranogSoka) {
		this.kolicinaGaziranogSoka = kolicinaGaziranogSoka;
	}
	public int getKolicinaNegaziranogSoka() {
		return kolicinaNegaziranogSoka;
	}
	public void setKolicinaNegaziranogSoka(int kolicinaNegaziranogSoka) {
		this.kolicinaNegaziranogSoka = kolicinaNegaziranogSoka;
	}
	public boolean isKlovn() {
		return klovn;
	}
	public void setKlovn(boolean klovn) {
		this.klovn = klovn;
	}
	public boolean isDiskoteka() {
		return diskoteka;
	}
	public void setDiskoteka(boolean diskoteka) {
		this.diskoteka = diskoteka;
	}
	public boolean isSportskeIgre() {
		return sportskeIgre;
	}
	public void setSportskeIgre(boolean sportskeIgre) {
		this.sportskeIgre = sportskeIgre;
	}
	public boolean isIgraonica() {
		return igraonica;
	}
	public void setIgraonica(boolean igraonica) {
		this.igraonica = igraonica;
	}
	public Agencija getAgencija() {
		return agencija;
	}
	public void setAgencija(Agencija agencija) {
		this.agencija = agencija;
	}
	
	public int ukupnaCena() {
		int cena = 0;
		cena += agencija.getPonuda().getOsnovni();
		cena += kolicinaGaziranogSoka*agencija.getPonuda().getGaziraniSok();
		cena += kolicinaNegaziranogSoka*agencija.getPonuda().getNegaziraniSok();
		if(klovn) {
			cena += agencija.getPonuda().getKlovn();
		}
		if(diskoteka) {
			cena += agencija.getPonuda().getDiskoteka();
		}
		if(sportskeIgre) {
			cena += agencija.getPonuda().getSportskeIgre();
		}
		if(igraonica) {
			cena += agencija.getPonuda().getSportskeIgre();
		}
		return cena;
		
	}
	public int cenaSaPoenima(int poeni) {
		if((ukupnaCena() - 100*poeni) > 0) {
			return ukupnaCena() - 100*poeni;
		}
		else {
			return 0;
		}
	}
	public double procenatSnizenja(int poeni) {
		return (double)(ukupnaCena() - cenaSaPoenima(poeni))/ukupnaCena();
	}
	
	
}
