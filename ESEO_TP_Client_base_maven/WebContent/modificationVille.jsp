<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>Insert title here</title>
</head>
<body style= " text-align:center ; toppadding-top: 200px ">

<div class="container-central">
		<form method="POST" action="ExecuterModificationVille">
			<label for="codeCommuneINSEE"><b>code Commune INSEE :</b></label>
			<input class="form-control form-perso" name="codeCommuneINSEE"
				id="codeCommuneINSEE" type="text" style="margin: auto;"
				value="<%=session.getAttribute("codeCommuneINSEE")%>" required>
			<br> <label for="nomCommune" ><b>nom Commune :
				</b></label> <input class="form-control form-perso"
				name="nomCommune" id="nomCommune" type="text"
				style="margin: auto;"
				value="<%=session.getAttribute("nomCommune")%>" required>
			<br> <label for="codePostal" id="java"><b>code Postal :</b></label> <input
				class="form-control form-perso" name="codePostal" id="codePostal"
				style="margin: auto;" type="text"
				value="<%=session.getAttribute("codePostal")%>" required>
			<br>  <label for="libelleAcheminement"><b>Libelle Acheminement :</b></label> <input class="form-control form-perso"
				name="libelleAcheminement" id="libelleAcheminement" style="margin: auto;"
				type="text" value="<%=session.getAttribute("libelleAcheminement")%>"
				required> <br> <br> <label for="ligne5"><b>ligne5  :</b></label> <input class="form-control form-perso" name="ligne5"
				id="ligne5" style="margin: auto;" type="text"
				value="<%=session.getAttribute("ligne5")%>" required> <br>
			<br> <br>
			<label for="latitude"><b>Latitude:</b></label> <input class="form-control form-perso"
				name="latitude" id="latitude" style="margin: auto;"
				type="text" value="<%=session.getAttribute("latitude")%>"
				required> <br> <br> <label for="longitude"><b>Longitude  :</b></label> <input class="form-control form-perso" name="ligne5"
				id="longitude" style="margin: auto;" type="text"
				value="<%=session.getAttribute("longitude")%>" required> <br>
			<br>
			<button type="submit"  id="boutonValider"
				name="outil" >
				Valider
				les modifications</button>
		</form>
	</div>

</body>
</html>