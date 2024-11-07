package model;

public class Ponuda {
	private int id;
	private int osnovni;
	private int gaziraniSok;
	private int negaziraniSok;
	private int klovn;
	private int diskoteka;
	private int sportskeIgre;
	private int igraonica;
	public Ponuda(int id, int osnovni, int gaziraniSok, int negaziraniSok, int klovn, int diskoteka, int sportskeIgre,
			int igraonica) {
		super();
		this.id = id;
		this.osnovni = osnovni;
		this.gaziraniSok = gaziraniSok;
		this.negaziraniSok = negaziraniSok;
		this.klovn = klovn;
		this.diskoteka = diskoteka;
		this.sportskeIgre = sportskeIgre;
		this.igraonica = igraonica;
	}
	
	public Ponuda(int osnovni, int gaziraniSok, int negaziraniSok, int klovn, int diskoteka, int sportskeIgre,
			int igraonica) {
		super();
		this.osnovni = osnovni;
		this.gaziraniSok = gaziraniSok;
		this.negaziraniSok = negaziraniSok;
		this.klovn = klovn;
		this.diskoteka = diskoteka;
		this.sportskeIgre = sportskeIgre;
		this.igraonica = igraonica;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOsnovni() {
		return osnovni;
	}
	public void setOsnovni(int osnovni) {
		this.osnovni = osnovni;
	}
	public int getGaziraniSok() {
		return gaziraniSok;
	}
	public void setGaziraniSok(int gaziraniSok) {
		this.gaziraniSok = gaziraniSok;
	}
	public int getNegaziraniSok() {
		return negaziraniSok;
	}
	public void setNegaziraniSok(int negaziraniSok) {
		this.negaziraniSok = negaziraniSok;
	}
	public int getKlovn() {
		return klovn;
	}
	public void setKlovn(int klovn) {
		this.klovn = klovn;
	}
	public int getDiskoteka() {
		return diskoteka;
	}
	public void setDiskoteka(int diskoteka) {
		this.diskoteka = diskoteka;
	}
	public int getSportskeIgre() {
		return sportskeIgre;
	}
	public void setSportskeIgre(int sportskeIgre) {
		this.sportskeIgre = sportskeIgre;
	}
	public int getIgraonica() {
		return igraonica;
	}
	public void setIgraonica(int igraonica) {
		this.igraonica = igraonica;
	}
	
	

}
