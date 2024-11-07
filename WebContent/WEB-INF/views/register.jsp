<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                     <a href="${pageContext.request.contextPath}/menadzer/pregled"> Menadzeri </a>  <br><br>  
                     <a href="${pageContext.request.contextPath}/menadzer/dodaj"> Dodaj menadzera </a><br><br>
                     <a href="${pageContext.request.contextPath}/korisnik/pregled"> Korisnici </a><br><br>
                     <a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a><br><br>
                <%
                 }
                 else if(request.getSession().getAttribute("role") == "MENADZER"){
                %>
                  <a href="${pageContext.request.contextPath}/agencija/pregled"> Agencije </a> <br><br>          
                  <a href="${pageContext.request.contextPath}/rezervacija/pregled"> Rezervacije </a><br><br>
                  <a href="${pageContext.request.contextPath}/rezervacija/zakazMenadzer"> Zakazivanje </a>   <br><br>       
                  <a href="${pageContext.request.contextPath}/authentication/logout"> Odjava </a> <br><br>
                <%
                 }
                %>
                
  				<h1>Registracija</h1>
 
  <hr>
    <form action="${pageContext.request.contextPath}/authentication/register" method="post">

          <label for="inputEmail4">Korisnicko ime</label><br>
          <input required="true" type="text" name="korisnickoIme" id="inputEmail4" placeholder="Korisničko ime"><br><br>

          <label for="inputAddress">Sifra</label><br>
          <input required="true" type="password" name="sifra" id="inputAddress" placeholder="Šifra"><br><br>

          <label for="inputAddress">Ime i prezime</label><br>
          <input required="true" type="text" name="imePrezime" id="inputAddress" placeholder="Ime i prezime"><br><br>

          <label for="inputAddress">Broj telefona</label><br>
          <input required="true" type="text" name="brTel" id="inputAddress" placeholder="Broj telefona"><br><br>

      <button type="submit">Registracija</button>
    </form>

  </div>

    <footer style="height: 65px; display: flex; align-items: center;">
            Copyright &copy; Proslava decijih rodjendana    
    </footer>

</body>

</html>



