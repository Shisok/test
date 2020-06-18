<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<title>Liste des villes</title>
</head>
<body style= " text-align:center ; toppadding-top: 200px ">
	<%
		int indexDebut = (int) session.getAttribute("indexDebut");
	%>
	<%
		int indexFin = indexDebut + 49;
	%>
	<%
		int nombrePage = (int) session.getAttribute("nombrePage");
	%>
	
	<h1>Liste Villes :</h1>
	<div>
		<table class="table table-borderless table-perso">
			<thead>
				<tr>
					<th scope="col" style="text-align: center">Code Commune
					<th scope="col" style="text-align: center">Nom Commune
					<th scope="col" style="text-align: center">Code Postal
					<th scope="col" style="text-align: center">Libelle Acheminement
					<th scope="col" style="text-align: center">Ligne
					<th scope="col" style="text-align: center">Latitude
					<th scope="col" style="text-align: center">Longitude
				</tr>
			</thead>
			<c:forEach items="${listeVilles}" var="v" begin="<%=indexDebut%>"
				end="<%=indexFin%>" step="1">
				<tr>
			        <td><c:out value="${v.getCodeCommune()}"></c:out></td>
			        <td><a href="ModifierVille?villeAModifier=${v.getNomCommune()}" ><c:out value="${v.getNomCommune()}"></c:out></a></td>
			        <td><c:out value="${v.getCodePostal()}"></c:out></td>
			        <td><c:out value="${v.getLibelleAcheminement()}"></c:out></td>
			         <td><c:out value="${v.getLigne()}"></c:out></td>
			          <td><c:out value="${v.getLatitude()}"></c:out></td>
			           <td><c:out value="${v.getLongitude()}"></c:out></td>
	    		<tr>
						
						
						
			</c:forEach>
		</table>
	 </div>
	 
	 <div>
		<button type="submit" id="boutonmenuOutil" 
				onclick="window.location.href = 'http://localhost:8080/ESEO_TP_Client_base_maven/';"
				> Accueil
			</button>
		</div>
	 
	<% if (nombrePage>1){ %>
	<div class="container-central">
		<nav aria-label="Page navigation example">
			<ul class="pagination pagination-sm justify-content-center">
				<c:forEach var="entry" begin="1" end="<%=nombrePage%>" step="1">
					<li class="page-item"><a class="page-link"
						href="AffichageVille?pageno=${entry}">${entry}</a></li>
					
				</c:forEach>
			</ul>
		</nav>
	</div>
	
	<%} %>
</body>
</html>