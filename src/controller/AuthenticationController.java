package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.Korisnik;
import model.Role;
import model.User;

/**
 * Servlet implementation class AuthenticationController
 */
@WebServlet("/authentication/*")
public class AuthenticationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AuthenticationController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

        String route = request.getPathInfo().substring(1);
        if(route.equals("login")) {
        	getLogin(request, response);
        }
        else if(route.equals("register")) {
        	getRegister(request, response);
        }
        else if(route.equals("logout")){
        	getLogout(request, response);
        }
 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        String route = request.getPathInfo().substring(1);
        if(route.equals("login")) {
        	try {
				postLogin(request, response);
			} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        else if(route.equals("register")) {
        	try {
				postRegister(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	
	private void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/login.jsp"); 
	    dispatcher.forward (request, response);
	}
	private void postLogin(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, ServletException, IOException {
		String username = request.getParameter("korisnickoIme");
		String password = request.getParameter("sifra");
		User user = UserDAO.getByUsernameAndPassword(username, password);
		HttpSession session = request.getSession();
		if(user != null) {
			session.setAttribute("user", user);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("role", user.getRole().toString());

		    response.sendRedirect(request.getContextPath() + "/agencija/pregled"); 

		}
		else {
			request.setAttribute("message", "Pogresno korisnicko ime i sifra.");

		    RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
		    rd.forward(request, response);
			
		}
	}
	private void postRegister(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String username = request.getParameter("korisnickoIme");
		String password = request.getParameter("sifra");
		
		Role role = Role.KORISNIK;
		String imePrezime = request.getParameter("imePrezime");
		int poeni = 0;
		String brTel = request.getParameter("brTel");
		
		Korisnik korisnik = new Korisnik(username, password, role, imePrezime, poeni, brTel);
		UserDAO.saveKorisnik(korisnik);
		request.setAttribute("message", "Uspesna registracija.");

	    RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp");
	    try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void getRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/register.jsp"); 
	    dispatcher.forward (request, response);
	}
	private void getLogout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();//invalidate?
	    try {
			response.sendRedirect(request.getContextPath() + "/authentication/login");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	}


}
