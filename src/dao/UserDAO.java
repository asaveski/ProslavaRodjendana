package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.util.DatabaseConnection;
import model.Admin;
import model.Agencija;
import model.Korisnik;
import model.Menadzer;
import model.Role;
import model.User;

public class UserDAO {
	
	public static User getByUsername(String username) throws SQLException  {
		String sql = "Select * from users where username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		
		if(!rs.next()) {
			return null;
		}
		String password = rs.getString("pass");
		Role role = Role.valueOf(rs.getString("role"));//string u rolu
		User user = new User(username, password, role);
		conn.close();
		if(user.getRole() == Role.ADMIN) {
			return getAdminByUsernameAndPassword(username, password);

		}
		else if(user.getRole() == Role.MENADZER) {
			return getMenadzerByUsernameAndPassword(username, password);

		}
		else if(user.getRole() == Role.KORISNIK) {
			return getKorisnikByUsernameAndPassword(username, password);

			
		}
		return user;
		
	}
	
	public static User getByUsernameAndPassword(String username, String password) throws SQLException, ClassNotFoundException {
		String sql = "Select * from users where username=? and pass=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		pstm.setString(2, password);
		ResultSet rs = pstm.executeQuery();
		
		if(!rs.next()) {
			return null;
		}
		
		Role role = Role.valueOf(rs.getString("role"));
		User user = new User(username, password, role);
		if(user.getRole() == Role.ADMIN) {
			return getAdminByUsernameAndPassword(username, password);

		}
		else if(user.getRole() == Role.MENADZER) {
			return getMenadzerByUsernameAndPassword(username, password);
		}
		else if(user.getRole() == Role.KORISNIK) {
			return getKorisnikByUsernameAndPassword(username, password);

			
		}
		conn.close();
		return user;
	}
	
	public static Korisnik getKorisnikByUsernameAndPassword(String username, String password) throws SQLException {
		String sql = "Select * from Korisnik where users_username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		if(!rs.next()) {
			return null;
		}
		String imePrezime = rs.getString("imePrezime");
		String brojTelefona = rs.getString("brTel");
		int poeni = rs.getInt("poeni");
		
		Korisnik korisnik = new Korisnik(username, password, Role.KORISNIK, imePrezime, poeni, brojTelefona);
		conn.close();
		return korisnik;
		
		
		
		
		
	}
	
	public static Admin getAdminByUsernameAndPassword(String username, String password) throws SQLException {
		String sql = "Select * from Admin where users_username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		if(!rs.next()) {
			return null;
		}
		
		Admin admin = new Admin(username, password, Role.ADMIN);
		conn.close();
		return admin;
		
	}
	
	public static Menadzer getMenadzerByUsernameAndPassword(String username, String password) throws SQLException{
		String sql = "Select * from Menadzer where users_username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		if(!rs.next()) {
			return null;
		}
		String imePrezime = rs.getString("imePrezime");
		String brojTelefona = rs.getString("brTel");
		int agencijaId = rs.getInt("Agencija_agencijaID");
		Agencija agencija = AgencijaDAO.findById(agencijaId);
		
		Menadzer menadzer = new Menadzer(username, password, Role.MENADZER, imePrezime, brojTelefona, agencija);
		conn.close();
		return menadzer;
		
	}
	
	
	public static boolean save(User user) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into users(username, pass, role) values (?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, user.getUsername());
		pstm.setString(2, user.getPass());
		pstm.setString(3, user.getRole().toString());
		

		pstm.executeUpdate();
		conn.close();
		return true;
	}
	public static boolean saveKorisnik(Korisnik korisnik) throws SQLException {
		save(korisnik);
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into Korisnik(imePrezime, poeni, brTel, users_username) values (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, korisnik.getImePrezime());
		pstm.setInt(2, korisnik.getPoeni());
		pstm.setString(3, korisnik.getBrojTelefona());
		pstm.setString(4, korisnik.getUsername());
		
		

		pstm.executeUpdate();
		conn.close();
		
		return true;
	}
	public boolean saveAdmin() {
		return true;
		
	}
	public static boolean saveMenadzer(Menadzer menadzer) throws SQLException {
		save(menadzer);
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into Menadzer(imePrezime, brTel, users_username, Agencija_agencijaID) values (?,?,?,?)";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, menadzer.getImePrezime());
		pstm.setString(2, menadzer.getBrojTelefona());
		pstm.setString(3, menadzer.getUsername());

		pstm.setInt(4, menadzer.getAgencija().getId());
		
		

		pstm.executeUpdate();
		conn.close();
		
		return true;
	}
	
	public static List<Menadzer> findAllMenadzers() throws SQLException  {
		String sql = "Select * from users where role=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, Role.MENADZER.toString());
		ResultSet rs = pstm.executeQuery();
		List<Menadzer> menadzeri = new ArrayList<>();
		while(rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("pass");
			
			Menadzer menadzer = getMenadzerByUsernameAndPassword(username, password);
			menadzeri.add(menadzer);
				
		}
		conn.close();

		return menadzeri;
		
	}
	public static List<Korisnik> findAllKorisnik() throws SQLException{
		String sql = "Select * from users where role=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, Role.KORISNIK.toString());
		ResultSet rs = pstm.executeQuery();
		List<Korisnik> korisnici = new ArrayList<>();
		while(rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("pass");
			
			Korisnik korisnik = getKorisnikByUsernameAndPassword(username, password);
			korisnici.add(korisnik);
				
		}
		conn.close();

		return korisnici;	
	}
	
	public static boolean delete(String username) throws SQLException {
		String sql = "delete from users  where username = ?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, username);

		pstm.executeUpdate();
		
		return true;
	}
	public static boolean deleteMenadzer(String username) throws SQLException {
		String sql = "delete from Menadzer  where users_username = ?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, username);

		pstm.executeUpdate();
		delete(username);
		
		return true;
	}
	public static boolean deleteKorisnik(String username) throws SQLException {
		String sql = "delete from Korisnik  where users_username = ?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, username);

		pstm.executeUpdate();
		delete(username);
		
		return true;
	}
	
	
	public static boolean updateMenadzer(Menadzer menadzer) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();

		String sql = "update Menadzer set imePrezime=?,brTel=?, Agencija_agencijaID=? where users_username=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, menadzer.getImePrezime());
		pstm.setString(2, menadzer.getBrojTelefona());
		pstm.setInt(3, menadzer.getAgencija().getId());
		pstm.setString(4, menadzer.getUsername());


		pstm.executeUpdate();
		conn.close();
		return true;
	}
	public static boolean updateKorisnik(Korisnik korisnik) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();

		String sql = "update Korisnik set imePrezime=?,brTel=? where users_username=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, korisnik.getImePrezime());
		pstm.setString(2, korisnik.getBrojTelefona());
		pstm.setString(3, korisnik.getUsername());


		pstm.executeUpdate();
		conn.close();
		return true;
	}
	
	public static int getBrojPoena(String username) throws SQLException{
		String sql = "Select * from Korisnik where users_username=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, username);
		ResultSet rs = pstm.executeQuery();
		if(!rs.next()) {
			return 0;
		}
	
		int poeni = rs.getInt("poeni");
		conn.close();
		return poeni;
		
	}
	public static boolean postaviNoviBrojPoena(String username, int poeni) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();

		String sql = "update Korisnik set poeni=? where users_username=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, poeni);
		pstm.setString(2, username);


		pstm.executeUpdate();
		conn.close();
		return true;
	}


}
