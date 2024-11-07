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

   <div style="margin:0; padding:0; min-height:400px; background-color:#f1f1f1;">
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
                  <a href="${pageContext.request.contextPath}/agencija/pregled"> Agencije </a> <br><br>          
                  <a href="${pageContext.request.contextPath}/rezervacija/pregled"> Rezervacije </a><br><br>
                  <a href="${pageContext.request.contextPath}/rezervacija/zakazMenadzer"> Zakazivanje </a> <br><br>         
                  <a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a> <br><br>
                <%
                 }
                %>
                
  				<h1>Zakazivanje</h1>
 
  <hr>
    <form action="${pageContext.request.contextPath}/rezervacija/zakazivanjeMenadzer" method="post">
      <h3>${poruka}</h3>
      
		Agencija:
        <select name="agencijaId">
	        <c:forEach items="${agencije}" var="agencija">
	        		<option value="${agencija.id}">${agencija.naziv}</option>
			        
			</c:forEach>
		</select><br><br>
		
		Korisnik
        <select name="username">
	        <c:forEach items="${korisnici}" var="korisnik">
	        		<option value="${korisnik.username}">${korisnik.username}</option>
			        
			</c:forEach>
		</select><br><br>

          <label for="inputAddress">Vreme</label><br>
          <input required="true" type="datetime-local" name="vreme" id="inputAddress"><br><br>

          <label for="inputAddress">Broj telefona</label><br>
          <input required="true" type="text" name="brojTelefona"id="inputAddress"><br><br>

          <label for="inputAddress">Kolicina gaziranog soka</label><br>
          <input required="true" type="number" name="kolicinaGaziranogSoka" id="inputAddress" placeholder="Kolicina gaziranog pica"><br><br>
          
          <label for="inputAddress">Kolicina negaziranog soka</label><br>
          <input required="true" type="number" name="kolicinaNegaziranogSoka" id="inputAddress" placeholder="Kolicina negaziranog pica"><br><br>

          <input type="checkbox" id="klovn" name="klovn" value="klovn">
		  <label for="klovn">Klovn</label><br>

          <input type="checkbox" id="diskoteka" name="diskoteka" value="diskoteka">
		  <label for="diskoteka">Diskoteka</label><br>

          <input type="checkbox" id="sportskeIgre" name="sportskeIgre" value="sportskeIgre">
		  <label for="sportskeIgre">Sportske igre</label><br>

          <input type="checkbox" id="igraonica" name="igraonica" value="Igraonica">
		  <label for="igraonica">Igraonica</label><br><br>
           
      <button type="submit">Rezervisi</button><br><br>
    </form>
  </div>

    <footer style="height: 65px; display: flex; align-items: center;">
            Copyright &copy; Proslava decijih rodjendana        
    </footer>

</body>

</html>



