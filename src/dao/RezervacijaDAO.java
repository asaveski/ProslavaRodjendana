package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.util.DatabaseConnection;
import model.Agencija;
import model.Korisnik;
import model.Ponuda;
import model.Rezervacija;

public class RezervacijaDAO {
	public static List<Rezervacija> findAll() throws SQLException{
		String sql = "Select * from Rezervacija";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Rezervacija> rezervacije = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt("RezervacijaID");
			String brTel = rs.getString("brTel");
			String vremeRodjendana = rs.getString("vremeRodjendana");
			int kolicinaGaziranogSoka = rs.getInt("kolicinaGaziranogSoka");
			int kolicinaNegaziranogSoka = rs.getInt("kolicinaNegaziranogSoka");
			boolean klovn = rs.getBoolean("klovn");
			boolean diskoteka = rs.getBoolean("diskoteka");
			boolean sportskeIgre = rs.getBoolean("sportskeIgre");
			boolean igraonica = rs.getBoolean("igraonica");
			int agencijaId = rs.getInt("Agencija_agencijaID");
			Agencija agencija = AgencijaDAO.findById(agencijaId);
			String username = rs.getString("Korisnik_users_username");
			Korisnik korisnik = (Korisnik) UserDAO.getByUsername(username);
			Rezervacija rezervacija = new Rezervacija(id, korisnik, brTel, vremeRodjendana,
					kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, agencija);
			rezervacije.add(rezervacija);

		}
		conn.close();
		return rezervacije;
	}
	
	public static Rezervacija findById(int id) throws SQLException, ClassNotFoundException {
		String sql = "Select * from Rezervacija where RezervacijaID=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		

		if(!rs.next()) {
			return null;
		}
		String brTel = rs.getString("brTel");
		String vremeRodjendana = rs.getString("vremeRodjendana");
		int kolicinaGaziranogSoka = rs.getInt("kolicinaGaziranogSoka");
		int kolicinaNegaziranogSoka = rs.getInt("kolicinaNegaziranogSoka");
		boolean klovn = rs.getBoolean("klovn");
		boolean diskoteka = rs.getBoolean("diskoteka");
		boolean sportskeIgre = rs.getBoolean("sportskeIgre");
		boolean igraonica = rs.getBoolean("igraonica");
		int agencijaId = rs.getInt("Agencija_agencijaID");
		Agencija agencija = AgencijaDAO.findById(agencijaId);
		String username = rs.getString("Korisnik_users_username");
		Korisnik korisnik = (Korisnik) UserDAO.getByUsername(username);
		Rezervacija rezervacija = new Rezervacija(id, korisnik, brTel, vremeRodjendana,
				kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, agencija);
		return rezervacija;
		
	}
	
	public static List<Rezervacija> findByKorisnik(String username) throws SQLException {
		String sql = "Select * from Rezervacija where Korisnik_users_username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		

		ArrayList<Rezervacija> rezervacije = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt("RezervacijaID");
			String brTel = rs.getString("brTel");
			String vremeRodjendana = rs.getString("vremeRodjendana");
			int kolicinaGaziranogSoka = rs.getInt("kolicinaGaziranogSoka");
			int kolicinaNegaziranogSoka = rs.getInt("kolicinaNegaziranogSoka");
			boolean klovn = rs.getBoolean("klovn");
			boolean diskoteka = rs.getBoolean("diskoteka");
			boolean sportskeIgre = rs.getBoolean("sportskeIgre");
			boolean igraonica = rs.getBoolean("igraonica");
			int agencijaId = rs.getInt("Agencija_agencijaID");
			Agencija agencija = AgencijaDAO.findById(agencijaId);
			Korisnik korisnik = (Korisnik) UserDAO.getByUsername(username);
			Rezervacija rezervacija = new Rezervacija(id, korisnik, brTel, vremeRodjendana,
					kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, agencija);
			rezervacije.add(rezervacija);

		}
		conn.close();
		return rezervacije;
		
	}
	public static boolean save(Rezervacija rezervacija) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into Rezervacija(Korisnik_users_username, brTel, vremeRodjendana, kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, Agencija_agencijaID) values (?,?,?,?,?,?,?,?,?,?)";
		

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, rezervacija.getKorisnik().getUsername());
		pstm.setString(2, rezervacija.getBrojTelefona());
		pstm.setString(3, rezervacija.getVremeRodjendana());
		pstm.setInt(4, rezervacija.getKolicinaGaziranogSoka());
		pstm.setInt(5, rezervacija.getKolicinaNegaziranogSoka());
		pstm.setBoolean(6, rezervacija.isKlovn());
		pstm.setBoolean(7, rezervacija.isDiskoteka());
		pstm.setBoolean(8, rezervacija.isSportskeIgre());
		pstm.setBoolean(9, rezervacija.isIgraonica());
		pstm.setInt(10, rezervacija.getAgencija().getId());
		
		

		pstm.executeUpdate();
		conn.close();
		
		return true;
	}
	
	public static boolean delete(int id) throws SQLException {
		String sql = "delete from Rezervacija where RezervacijaID = ?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
		
		return true;
	}
	
	
}
