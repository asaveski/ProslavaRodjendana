package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.Korisnik;
import model.Menadzer;
import model.Role;

/**
 * Servlet implementation class KorisnikController
 */
@WebServlet("/korisnik/*")
public class KorisnikController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KorisnikController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String route = request.getPathInfo().substring(1);
        if(route.equals("pregled")) {
        	try {
				getPregledKorisnika(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(route.contains("obrisi")) {
        	try {
				obrisiKorisnika(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(route.contains("izmena")) {
        	try {
				izmenaKorisnika(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String route = request.getPathInfo().substring(1);
        if(route.equals("izmena")){
        	try {
				postIzmenaKorisnika(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}
	
	protected void getPregledKorisnika(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Korisnik> korisnici = UserDAO.findAllKorisnik();//sql inj?
		request.setAttribute("korisnici", korisnici);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/korisnici.jsp"); 
	    dispatcher.forward (request, response);
		
		
	}
	protected static void obrisiKorisnika(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		UserDAO.deleteKorisnik(username);
	    response.sendRedirect(request.getContextPath() + "/korisnik/pregled");
		
	}
	protected static void izmenaKorisnika(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		String username = request.getParameter("username");
		Korisnik korisnik = (Korisnik)UserDAO.getByUsername(username);
		request.setAttribute("korisnik", korisnik);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/izmena_korisnika.jsp"); 
	    dispatcher.forward (request, response);		
	}

	protected static void postIzmenaKorisnika(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String imePrezime = request.getParameter("imePrezime");
		String brojTelefona = request.getParameter("brTel");
		Korisnik korisnik = new Korisnik(username, imePrezime, brojTelefona, Role.KORISNIK);
		UserDAO.updateKorisnik(korisnik);
	    response.sendRedirect(request.getContextPath() + "/korisnik/pregled");

		
	}
}
