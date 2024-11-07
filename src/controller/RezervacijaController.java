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
import model.Korisnik;
import model.Rezervacija;
import model.Role;
import model.User;

/**
 * Servlet implementation class RezervacijaController
 */
@WebServlet("/rezervacija/*")
public class RezervacijaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RezervacijaController() {
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
				getRezervacije(request, response);
			} catch (SQLException | ServletException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	 
	     }
	     if(route.contains("otkazivanje")) {
	    	 try {
				getOtkazivanje(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     if(route.contains("zakazivanje")) {
	    	 try {
				getZakazivanje(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	     }
	     if(route.contains("zakazMenadzer")) {
	    	 try {
				getZakazivanjeMenadzer(request, response);
			} catch (ServletException | IOException | SQLException e) {
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

		
		 if(route.equals("zakazivanje")) {
			 
			 try {
				postZakazivanje(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }

		 if(route.equals("zakazivanjeMenadzer")) {
			 
			 try {
				postZakazivanjeMenadzer(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
	}
	
	protected void getRezervacije(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		User user= (User) request.getSession().getAttribute("user");
		if(user == null) {
		    response.sendRedirect(request.getContextPath() + "/authentication/login");
		    return; //prazan retrun prekida funk
		}
		List<Rezervacija> rezervacije = null;
		if(user.getRole() == Role.KORISNIK) {
			
			rezervacije = RezervacijaDAO.findByKorisnik(user.getUsername());
		}
		else if(user.getRole() == Role.MENADZER) {
			rezervacije = RezervacijaDAO.findAll();
		}
		
		request.setAttribute("rezervacije", rezervacije);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/rezervacije.jsp"); 
	    dispatcher.forward (request, response);
	}
	protected void getOtkazivanje(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		RezervacijaDAO.delete(id);
	    response.sendRedirect(request.getContextPath() + "/rezervacija/pregled");

		
	}
	
	protected void getZakazivanje(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		 Korisnik korisnik = (Korisnik) request.getSession().getAttribute("user");

		String poruka = request.getParameter("poruka");
		if(poruka != null) {
			request.setAttribute("poruka", poruka);
		}
		int trenutniPoeni = UserDAO.getBrojPoena(korisnik.getUsername());
		List<Agencija> agencije = AgencijaDAO.findAll();
		request.setAttribute("agencije", agencije);
		request.setAttribute("trenutniPoeni", trenutniPoeni);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/zakazivanje.jsp"); 
	    dispatcher.forward (request, response);
	}
	protected void getZakazivanjeMenadzer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String poruka = request.getParameter("poruka");
		if(poruka != null) {
			request.setAttribute("poruka", poruka);
		}
		
		List<Agencija> agencije = AgencijaDAO.findAll();
		List<Korisnik> korisnici = UserDAO.findAllKorisnik();
		request.setAttribute("agencije", agencije);
		request.setAttribute("korisnici", korisnici);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/menadzer_zakazivanje.jsp"); 
	    dispatcher.forward (request, response);
	}
	
	protected void postZakazivanjeMenadzer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		
		 
		 int agencijaId = Integer.parseInt(request.getParameter("agencijaId"));
		
		 String username = request.getParameter("username");
		 Korisnik korisnik = (Korisnik)UserDAO.getByUsername(username);
		 
		 Agencija agencija = null;
		 try {
			agencija = AgencijaDAO.findById(agencijaId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String vreme = request.getParameter("vreme");
		 String brojTelefona = request.getParameter("brojTelefona");
		 int kolicinaGaziranogSoka = Integer.parseInt(request.getParameter("kolicinaGaziranogSoka"));
		 int kolicinaNegaziranogSoka = Integer.parseInt(request.getParameter("kolicinaNegaziranogSoka"));
		 boolean klovn = false;
		 if(request.getParameter("klovn") != null) {
			 klovn = true;
		 }
		 boolean diskoteka = false;
		 if(request.getParameter("diskoteka") != null) {
			 diskoteka = true;
		 }
		 boolean sportskeIgre = false;
		 if(request.getParameter("sportskeIgre") != null) {
			 sportskeIgre = true;
		 }
		 boolean igraonica = false;
		 if(request.getParameter("igraonica") != null) {
			 igraonica = true;
		 }
		 
		 
		 Rezervacija rezervacija = new Rezervacija(korisnik, brojTelefona, 
				 vreme, kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, agencija );
		 
	     RezervacijaDAO.save(rezervacija);
	     response.sendRedirect(request.getContextPath() + "/rezervacija/zakazMenadzer?poruka=Uspesno zakazano.");


	
	}
	
	
	protected void postZakazivanje(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		 Korisnik korisnik = (Korisnik) request.getSession().getAttribute("user");
		
		 if(korisnik == null) {
			 response.sendRedirect(request.getContextPath() + "/authentication/login");
			 return;

		 }
		 int agencijaId = Integer.parseInt(request.getParameter("agencijaId"));
		 int poeni = Integer.parseInt(request.getParameter("poeni"));
		 if(poeni < 0) {
			 response.sendRedirect(request.getContextPath() + "/rezervacija/zakazivanje?poruka=Broj poena mora da bude veci ili jednak 0");
			 return;
			 
		 }
		 if(poeni > UserDAO.getBrojPoena(korisnik.getUsername())) {
			 response.sendRedirect(request.getContextPath() + "/rezervacija/zakazivanje?poruka=Nemate dovoljno poena");
			 return;
		 }
		 
		 
		 Agencija agencija = null;
		 try {
			agencija = AgencijaDAO.findById(agencijaId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 String vreme = request.getParameter("vreme");
		 String brojTelefona = request.getParameter("brojTelefona");
		 int kolicinaGaziranogSoka = Integer.parseInt(request.getParameter("kolicinaGaziranogSoka"));
		 int kolicinaNegaziranogSoka = Integer.parseInt(request.getParameter("kolicinaNegaziranogSoka"));
		 boolean klovn = false;
		 if(request.getParameter("klovn") != null) {
			 klovn = true;
		 }
		 boolean diskoteka = false;
		 if(request.getParameter("diskoteka") != null) {
			 diskoteka = true;
		 }
		 boolean sportskeIgre = false;
		 if(request.getParameter("sportskeIgre") != null) {
			 sportskeIgre = true;
		 }
		 boolean igraonica = false;
		 if(request.getParameter("igraonica") != null) {
			 igraonica = true;
		 }
		 
		 
		 Rezervacija rezervacija = new Rezervacija(korisnik, brojTelefona, 
				 vreme, kolicinaGaziranogSoka, kolicinaNegaziranogSoka, klovn, diskoteka, sportskeIgre, igraonica, agencija );
		 
		 if(rezervacija.procenatSnizenja(poeni) <= 0.3 && rezervacija.procenatSnizenja(poeni) >= 0.0 ) {
			 int korisnikoviPoeni = UserDAO.getBrojPoena(korisnik.getUsername());
			 korisnikoviPoeni -= poeni;
			 int noviPoeni = korisnikoviPoeni + rezervacija.ukupnaCena()/3000;
			 UserDAO.postaviNoviBrojPoena(korisnik.getUsername(), noviPoeni);
			 RezervacijaDAO.save(rezervacija);
			 response.sendRedirect(request.getContextPath() + "/rezervacija/zakazivanje?poruka=Uspesno zakazano.");

		 }
		 else {
			 response.sendRedirect(request.getContextPath() + "/rezervacija/zakazivanje?poruka=Snizenje ne sme da premasi trideset posto!");
		 }
		 

	
	}
}
