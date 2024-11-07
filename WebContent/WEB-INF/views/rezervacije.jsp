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
                     <a href="${pageContext.request.contextPath}/menadzer/pregled"> Menadzeri </a> <br><br>   
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

  				<h3>Rezervacije</h3>
			<hr>
				<c:forEach items="${rezervacije}" var="rezervacija">
        			<h4>Broj telefona: ${rezervacija.brojTelefona}</h4>
        			<h4>Vreme: ${rezervacija.vremeRodjendana}</h4>
        			<h4>Naziv agencije: ${rezervacija.agencija.naziv}</h4>
        			<a href="${pageContext.request.contextPath}/rezervacija/otkazivanje/?id=${rezervacija.id}">Otkaži rezervaciju</a>
        	<hr>
				</c:forEach>            
		</div>
    <footer style="height: 65px; display: flex; align-items: center;">
            Copyright &copy; Proslava decijih rodjendana    
    </footer>

</body>

</html>





       