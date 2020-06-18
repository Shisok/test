<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Distance entre 2 villes</title>
</head>
<body style= " text-align:center ; toppadding-top: 200px " >

<h1> Distance entre :</h1>
		<form action="CalculDistanceVille" method="POST">
			<label for="ville1"><strong>Ville 1 :</strong></label> <select
				 name="ville1" id="ville1" required>
				<c:forEach items="${villes}" var="v">
					<option id="outil,<c:out value="${v}"></c:out>"><c:out
							value="${v
							}"></c:out></option>
				</c:forEach>
			</select>
			<br>
				<br>
			<label for="ville2"><strong>Ville 2 :</strong></label> <select
				 name="ville2" id="ville2" required>
				<c:forEach items="${villes}" var="v">
					<option id="ville2,<c:out value="${v}"></c:out>"><c:out
							value="${v
							}"></c:out></option>
				</c:forEach>
			</select>
			<br>
				<br>
			<button type="submit"  id="Envoyer"
			name="Envoyer" 
				>Calculer</button>
		</form>
	<div>
	La distance entre ${ville1} et ${ville2} est de ${distance} km.
	</div>

</body>
</html>