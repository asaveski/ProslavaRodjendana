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

  				<h1>Izmena menadzera</h1>

    <form action="${pageContext.request.contextPath}/menadzer/izmena" method="post">
      <p>${poruka}</p>
      <input type="hidden" value="${menadzer.username}" name="korisnickoIme">

          <label for="inputAddress">Ime i prezime</label><br>
          <input required="true" type="text" name="imePrezime" value="${menadzer.imePrezime}" id="inputAddress" placeholder="Ime i prezime"><br><br>

          <label for="inputAddress">Broj telefona</label><br>
          <input required="true" type="text" name="brTel" value="${menadzer.brojTelefona}" id="inputAddress" placeholder="Broj telefona"><br><br>

        Agencija <br>
        <select name="agencijaId">
	        <c:forEach items="${agencije}" var="agencija">
	        		<option value="${agencija.id}">${agencija.naziv}</option>
			        
			</c:forEach>
		</select>
		
     <br><br><button type="submit">Izmeni menadzera</button>
    </form><br>
  </div>


    <footer style="height: 65px; display: flex; align-items: center;">
            Copyright &copy; Proslava decijih rodjendana
        
    </footer>
 
</body>

</html>

