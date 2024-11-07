package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.util.DatabaseConnection;
import model.Agencija;
import model.Ponuda;

public class AgencijaDAO {
	
	
	public static List<Agencija> findAll() throws SQLException{
		String sql = "Select * from Agencija";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Agencija> agencije = new ArrayList<>();

		while (rs.next()) {// ???
			int id = rs.getInt("agencijaID");
			String naziv = rs.getString("naziv");
			String adresa = rs.getString("adresa");
			String brojTelefona = rs.getString("telefon");
			String imgUrl = rs.getString("imgUrl");
			int ponudaId = rs.getInt("Ponuda_ponudaID");
			Ponuda ponuda = PonudaDAO.findById(ponudaId);
	        Agencija agencija =  new Agencija(id, naziv, adresa, brojTelefona, ponuda, imgUrl);

			agencije.add(agencija);

		}
		conn.close();
		return agencije;
	}
	
	public static Agencija findById(int id) throws SQLException {
		String sql = "Select * from Agencija where agencijaID=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		

		if(!rs.next()) {
			return null;
		}
		String naziv = rs.getString("naziv");
		String adresa = rs.getString("adresa");
		String brojTelefona = rs.getString("telefon");
		String imgUrl = rs.getString("imgUrl");
		int ponudaId = rs.getInt("Ponuda_ponudaID");
		Ponuda ponuda = PonudaDAO.findById(ponudaId);
		Agencija agencija =  new Agencija(id, naziv, adresa, brojTelefona, ponuda, imgUrl);

		
		
		conn.close();
		return agencija;
	}
	
	public static boolean save(Agencija agencija) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into Agencija(naziv, adresa, telefon, Ponuda_ponudaID, imgUrl) values (?,?,?,?,?)";
		

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1, agencija.getNaziv());
		pstm.setString(2, agencija.getAdresa());
		pstm.setString(3, agencija.getTelefon());
		pstm.setInt(4, agencija.getPonuda().getId());
		pstm.setString(5, agencija.getImgUrl());
	

		pstm.executeUpdate();
		conn.close();
		
		return true;
		
	}
	public static boolean delete(int id) throws SQLException {
		String sql = "delete from Agencija where agencijaID = ?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, id);

		pstm.executeUpdate();
		
		return true;
	}
	
	public static boolean update(Agencija agencija) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();

		String sql = "update Agencija set naziv=?,adresa=?, telefon=?, Ponuda_ponudaID=?, imgUrl =? where agencijaID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setString(1,agencija.getNaziv());
		pstm.setString(2, agencija.getAdresa());
		pstm.setString(3, agencija.getTelefon());
		pstm.setInt(4, agencija.getPonuda().getId());
		pstm.setString(5, agencija.getImgUrl());
		pstm.setInt(6, agencija.getId());


		pstm.executeUpdate();
		conn.close();
		return true;

	}

	

}
