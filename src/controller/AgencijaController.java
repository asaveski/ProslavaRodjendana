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
import dao.PonudaDAO;
import dao.RezervacijaDAO;
import model.Agencija;
import model.Ponuda;

/**
 * Servlet implementation class AgencijaController
 */
@WebServlet("/agencija/*") //putanja, kada se kreira preko new-servlet 
public class AgencijaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AgencijaController() {
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
					getAgencije(request, response); 
				} catch (ClassNotFoundException | ServletException | IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        if(route.equals("dodaj")) {
	        	getDodajAgenciju(request, response);
	        }
	        if(route.contains("obrisi")) {
				 try {
					getDelete(request, response);
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
	        if(route.contains("izmeni")) {
	        	try {
					izmeniAgenciju(request, response);
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
		 String route = request.getPathInfo().substring(1); //zar u postu nema pathinfo? zasto smo stavili post kada ima get? get uzima? post postavlja? kada se koristi processReq
		 if(route.equals("dodaj")) {
			 try {
				postAgencija(request, response);
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 }
		 if(route.equals("izmeni")) {
			 try {
				postIzmeniAgenciju(request, response);
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
	}
	
	protected void getAgencije(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
		List<Agencija> agencije = AgencijaDAO.findAll(); //sta ovaj deo uzima iz dao? sql?
		request.setAttribute("agencije", agencije);
		request.setAttribute("uloga", (String)request.getSession().getAttribute("uloga"));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/agencije.jsp"); //sta tacno radi requestDispatcher
	    dispatcher.forward (request, response);
	}
	protected void postAgencija(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int osnovni = Integer.parseInt(request.getParameter("osnovni")); //odakle on uzima parametre, iz kog view-a? 
		int gaziraniSok = Integer.parseInt(request.getParameter("gaziraniSok"));
		int negaziraniSok = Integer.parseInt(request.getParameter("negaziraniSok"));
		int klovn = Integer.parseInt(request.getParameter("klovn"));
		int diskoteka = Integer.parseInt(request.getParameter("diskoteka"));
		int sportskeIgre = Integer.parseInt(request.getParameter("sportskeIgre"));
		int igraonica = Integer.parseInt(request.getParameter("igraonica"));
		Ponuda ponuda = new Ponuda(osnovni, gaziraniSok, negaziraniSok, klovn, diskoteka, sportskeIgre, igraonica);
		int id = PonudaDAO.save(ponuda); //zasto ovde vraca id ponude, a dole ne kod agencije? 
		ponuda.setId(id);
		
		String naziv = request.getParameter("naziv");
		String adresa = request.getParameter("adresa");
		String brojTelefona = request.getParameter("telefon");
		String imgUrl = request.getParameter("imgUrl");
		
		Agencija agencija = new Agencija(naziv, adresa, brojTelefona, ponuda, imgUrl);
		AgencijaDAO.save(agencija);
	    response.sendRedirect(request.getContextPath() + "/agencija/pregled");//razlika izmedju sendredirect i reqDis? 

	}
	
	protected void getDodajAgenciju(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/dodavanje_agencije.jsp"); 
	    dispatcher.forward (request, response);
	}
	
	protected void getDelete(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id")); //odakle on vadi id? iz servleta ili view-a
		AgencijaDAO.delete(id);
	    response.sendRedirect(request.getContextPath() + "/agencija/pregled");

	}
	
	protected void izmeniAgenciju(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Agencija agencija = AgencijaDAO.findById(id);
		request.setAttribute("agencija", agencija);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/izmena_agencije.jsp"); 
	    dispatcher.forward (request, response);
		

	}
	protected void postIzmeniAgenciju(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		int ponudaId = Integer.parseInt(request.getParameter("ponudaId"));
		int agencijaId = Integer.parseInt(request.getParameter("agencijaId"));


		int osnovni = Integer.parseInt(request.getParameter("osnovni"));
		int gaziraniSok = Integer.parseInt(request.getParameter("gaziraniSok"));
		int negaziraniSok = Integer.parseInt(request.getParameter("negaziraniSok"));
		int klovn = Integer.parseInt(request.getParameter("klovn"));
		int diskoteka = Integer.parseInt(request.getParameter("diskoteka"));
		int sportskeIgre = Integer.parseInt(request.getParameter("sportskeIgre"));
		int igraonica = Integer.parseInt(request.getParameter("igraonica"));
		Ponuda ponuda = new Ponuda(ponudaId, osnovni, gaziraniSok, negaziraniSok, klovn, diskoteka, sportskeIgre, igraonica);
		PonudaDAO.update(ponuda);
		
		String naziv = request.getParameter("naziv");
		String adresa = request.getParameter("adresa");
		String brojTelefona = request.getParameter("telefon");
		String imgUrl = request.getParameter("imgUrl");
		
		Agencija agencija = new Agencija(agencijaId, naziv, adresa, brojTelefona, ponuda, imgUrl);
		AgencijaDAO.update(agencija);
	    response.sendRedirect(request.getContextPath() + "/agencija/pregled");

	}

}
