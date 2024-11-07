package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.util.DatabaseConnection;
import model.Ponuda;

public class PonudaDAO {
	public static List<Ponuda> findAll() throws SQLException{
		String sql = "Select * from Ponuda";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rs = pstm.executeQuery();
		ArrayList<Ponuda> ponude = new ArrayList<>();

		while (rs.next()) {
			int id = rs.getInt("ponudaID");
			int osnovni = rs.getInt("osnovni");
			int gaziraniSok = rs.getInt("gaziraniSok");
			int negaziraniSok = rs.getInt("negaziraniSok");
			int klovn = rs.getInt("klovn");
			int diskoteka = rs.getInt("diskoteka");
			int sportskeIgre = rs.getInt("sportskeIgre");
			int igraonica = rs.getInt("igraonica");
			Ponuda ponuda = new Ponuda(id, osnovni, gaziraniSok, negaziraniSok, klovn, diskoteka, sportskeIgre, igraonica);
			ponude.add(ponuda);

		}
		conn.close();
		return ponude;
	}
	public static Ponuda findById(int id) throws SQLException {
		String sql = "Select * from Ponuda where ponudaID=?";
		Connection conn = DatabaseConnection.getMySQLConnection();

		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, id);
		ResultSet rs = pstm.executeQuery();
		

		if(!rs.next()) {
			return null;
		}
		int osnovni = rs.getInt("osnovni");
		int gaziraniSok = rs.getInt("gaziraniSok");
		int negaziraniSok = rs.getInt("negaziraniSok");
		int klovn = rs.getInt("klovn");
		int diskoteka = rs.getInt("diskoteka");
		int sportskeIgre = rs.getInt("sportskeIgre");
		int igraonica = rs.getInt("igraonica");
		Ponuda ponuda = new Ponuda(id, osnovni, gaziraniSok, negaziraniSok, klovn, diskoteka, sportskeIgre, igraonica);
		conn.close();
		return ponuda;
	}
	
	public static int save(Ponuda ponuda) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();
		String sql = "insert into Ponuda(osnovni, gaziraniSok, negaziraniSok, klovn, diskoteka, sportskeIgre, igraonica) values (?,?,?,?,?,?,?)";
		

		PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //???

		pstm.setInt(1, ponuda.getOsnovni());
		pstm.setInt(2, ponuda.getGaziraniSok());
		pstm.setInt(3, ponuda.getNegaziraniSok());
		pstm.setInt(4, ponuda.getKlovn());
		pstm.setInt(5, ponuda.getDiskoteka());
		pstm.setInt(6, ponuda.getSportskeIgre());
		pstm.setInt(7, ponuda.getIgraonica());		


		pstm.executeUpdate();
		
		ResultSet rs = pstm.getGeneratedKeys();
	
		rs.next();
		int id = rs.getInt(1);
		conn.close();
		return id;
			
		
		
	}
	
	public static boolean update(Ponuda ponuda) throws SQLException {
		Connection conn = DatabaseConnection.getMySQLConnection();

		String sql = "update Ponuda set osnovni=?,gaziraniSok=?, negaziraniSok=?, klovn=?, diskoteka=?, sportskeIgre=?, igraonica=? where ponudaID=?";

		PreparedStatement pstm = conn.prepareStatement(sql);

		pstm.setInt(1, ponuda.getOsnovni());
		pstm.setInt(2, ponuda.getGaziraniSok());
		pstm.setInt(3, ponuda.getNegaziraniSok());
		pstm.setInt(4, ponuda.getKlovn());
		pstm.setInt(5, ponuda.getDiskoteka());
		pstm.setInt(6, ponuda.getSportskeIgre());
		pstm.setInt(7, ponuda.getIgraonica());		
		pstm.setInt(8, ponuda.getId());		




		pstm.executeUpdate();
		conn.close();
		return true;
	}
 
}
