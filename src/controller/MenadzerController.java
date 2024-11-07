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

import dao.AgencijaDAO;
import dao.RezervacijaDAO;
import dao.UserDAO;
import model.Agencija;
import model.Menadzer;
import model.Role;

/**
 * Servlet implementation class MenadzerController
 */
@WebServlet("/menadzer/*")
public class MenadzerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenadzerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String route = request.getPathInfo().substring(1);
        if(route.contains("dodaj")) {
        	try {
				getDodajMenadzer(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(route.equals("pregled")) {
        	
        	try {
				pregledMenadzera(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        }
        if(route.contains("obrisi")) {
        	try {
				obrisiMenadzera(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(route.contains("izmeni")) {
        	try {
				getIzmeniMenadzera(request, response);
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
        if(route.equals("dodaj")) {
        	try {
				postDodajMenadzer(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        if(route.contains("izmena")) {
        	try {
				postIzmeniMenadzera(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	protected static void getDodajMenadzer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		List<Agencija> agencije = AgencijaDAO.findAll();
		if(request.getParameter("poruka") != null) {//gde je ova poruka? 
			request.setAttribute("poruka", request.getParameter("poruka"));
		}
		request.setAttribute("agencije", agencije);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dodavanje_menadzera.jsp"); 
	    dispatcher.forward (request, response);
	    
	}
	
	protected static void postDodajMenadzer(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		 String username = request.getParameter("korisnickoIme");
		 if(UserDAO.getByUsername(username) != null) {
			    response.sendRedirect(request.getContextPath() + "/menadzer/dodaj/?poruka="+"Menadzer sa tim korisnickom imenom vec postoji!");
			    return;
			 
		 }
			String password = request.getParameter("sifra");
			Role role = Role.MENADZER;
			String imePrezime = request.getParameter("imePrezime");
			String brTel = request.getParameter("brTel");
			int agencijaId= Integer.parseInt(request.getParameter("agencijaId"));
			Agencija agencija = AgencijaDAO.findById(agencijaId);
			Menadzer menadzer = new Menadzer(username, password, role, imePrezime, brTel, agencija);
			UserDAO.saveMenadzer(menadzer);
		    response.sendRedirect(request.getContextPath() + "/menadzer/dodaj/?poruka="+"Uspesno dodat menadzer");


			
	}
	
	protected static void pregledMenadzera(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Menadzer> menadzeri = UserDAO.findAllMenadzers();
		request.setAttribute("menadzeri", menadzeri);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/menadzeri.jsp"); 
	    dispatcher.forward (request, response);
	    	
	}
	
	protected static void obrisiMenadzera(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		UserDAO.deleteMenadzer(username);
	    response.sendRedirect(request.getContextPath() + "/menadzer/pregled");

	}
	
	protected static void getIzmeniMenadzera(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		List<Agencija> agencije = AgencijaDAO.findAll();
		String username = request.getParameter("username");
		Menadzer menadzer = (Menadzer)UserDAO.getByUsername(username);
		if(request.getParameter("poruka") != null) {
			request.setAttribute("poruka", request.getParameter("poruka"));
		}
		request.setAttribute("agencije", agencije);
		request.setAttribute("menadzer", menadzer);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/izmena_menadzera.jsp"); 
	    dispatcher.forward (request, response);
	    
	}
	
	protected static void postIzmeniMenadzera(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 String username = request.getParameter("korisnickoIme");
		Role role = Role.MENADZER;
		String imePrezime = request.getParameter("imePrezime");
		String brTel = request.getParameter("brTel");
		int agencijaId= Integer.parseInt(request.getParameter("agencijaId"));
		Agencija agencija = AgencijaDAO.findById(agencijaId);
		Menadzer menadzer = new Menadzer(username, role, imePrezime, brTel, agencija);
		UserDAO.updateMenadzer(menadzer);
	    response.sendRedirect(request.getContextPath() + "/menadzer/izmeni/?poruka="+"Uspesno izmenjen menadzer"+"&username="+username);

		
		
	}

}
