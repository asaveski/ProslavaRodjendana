<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Proslava Rođendana</title>

</head>

<body>

        <div style="background-color:#CED2CC; min-height:30px; margin:0; padding:0">
                       
                        <a href="/ProslavaRodjendana" style="float:center"><% if(request.getSession().getAttribute("username")!=null || request.getSession().getAttribute("role")!=null) {%>
                        <%= request.getSession().getAttribute("username") %> - <%= request.getSession().getAttribute("role") %></a>
                    	<%}else { %>
                    	<a href="${pageContext.request.contextPath}/authentication/login" > Prijava </a>
                 		<a href="${pageContext.request.contextPath}/authentication/register" style="float:right"> Registracija </a>
                 		<%} %>
        </div>

			<div style="margin:0; padding:0; min-height:500px; background-color:#f1f1f1;">
                <br>
                <%
                 if(request.getSession().getAttribute("role") == "KORISNIK"){ 
                %>
                    <a href="${pageContext.request.contextPath}/rezervacija/pregled"> Rezervacije </a><br><br>
                    <a href="${pageContext.request.contextPath}/agencija/pregled"> Agencije </a><br><br>
                	<a href="${pageContext.request.contextPath}/rezervacija/zakazivanje"> Zakazivanje </a><br><br>
                	<a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a><br><br>
                <%} 
                 else if(request.getSession().getAttribute("role") == "ADMIN"){
                	 %>
                     <a href="${pageContext.request.contextPath}/agencija/pregled"> Agencije </a><br><br>
                 	 <a href="${pageContext.request.contextPath}/agencija/dodaj"> Dodaj agenciju </a><br><br>
                     <a href="${pageContext.request.contextPath}/menadzer/pregled"> Menadzeri </a><br><br>                     
                     <a href="${pageContext.request.contextPath}/menadzer/dodaj"> Dodaj menadzera </a><br><br>
                     <a href="${pageContext.request.contextPath}/korisnik/pregled"> Korisnici </a><br><br>
                     <a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a><br><br>

                <%
                 }
                 else if(request.getSession().getAttribute("role") == "MENADZER"){
                %>
                  <a href="${pageContext.request.contextPath}/agencija/pregled"> Agencije </a><br><br>                
                  <a href="${pageContext.request.contextPath}/rezervacija/pregled"> Rezervacije </a><br><br>
                  <a href="${pageContext.request.contextPath}/rezervacija/zakazMenadzer"> Zakazivanje </a><br><br>                  
                  <a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a><br><br>
                
                <%
                 }
                %>
                
            <div>
  				<h1>Dodavanje agencije</h1>
			<div>
 
  <hr>
  <div>
    <form action="${pageContext.request.contextPath}/agencija/dodaj" method="post">
         
          <label for="inputAddress">Naziv</label><br>
          <input required="true" type="text" name="naziv" id="inputAddress"><br><br>

          <label for="inputAddress">Adresa</label><br>
          <input required="true" type="text" name="adresa" id="inputAddress"><br><br>

          <label for="inputAddress">Broj telefona</label><br>
          <input required="true" type="text" name="telefon" id="inputAddress"><br><br>

          <label for="inputAddress">Osnovni paket cena</label><br>
          <input required="true" type="number" name="osnovni" id="inputAddress" placeholder="Kolicina gaziranog pica"><br><br>

          <label for="inputAddress">Gazirani sok cena</label><br>
          <input required="true" type="number" name="gaziraniSok" id="inputAddress" placeholder="Kolicina gaziranog pica"><br><br>

          <label for="inputAddress">Negazirani sok cena</label><br>
          <input required="true" type="number" name="negaziraniSok" id="inputAddress" placeholder="Kolicina negaziranog pica"><br><br>

          <label for="klovn">Klovn cena</label><br>
          <input type="number" id="klovn" name="klovn"><br><br>

          <label for="diskoteka">Diskoteka cena</label><br>
          <input type="number" id="diskoteka" name="diskoteka"><br><br>

          <label for="sportskeIgre">Sportske igre cena</label><br>
          <input type="number" id="sportskeIgre" name="sportskeIgre"><br><br>

          <label for="igraonica">Igraonica cena</label><br>      
          <input type="number" id="igraonica" name="igraonica"><br><br>

      <button type="submit">Dodaj agenciju</button>
    </form>
  </div>
  <div>
</div>
</div>
</div>           
</div>

    <footer style="height: 65px; display: flex; align-items: center;">
        <div>
            Copyright &copy; Proslava decijih rodjendana
        </div>
        
    </footer>

</body>

</html>


