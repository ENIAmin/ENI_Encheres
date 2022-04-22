<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	<h1>ENI-Enchères</h1>
	<h2>Créer un compte</h2>

	<form action="<%=request.getContextPath()%>/Inscription" method="post">
		<table>
			<tr>
				<td>Pseudo :</td>
				<td><input type="text" name="pseudo"></td>
			</tr>
			<tr>
				<td>Nom :</td>
				<td><input type="text" name="nom"></td>
			</tr>
			<tr>
				<td>Prénom</td>
				<td><input type="text" name="prenom"></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>Telephone :</td>
				<td><input type="text" name="telephone"></td>
			</tr>
			<tr> 
				<td>Rue :</td>
				<td><input type="text" name="rue"></td>
			</tr>
			<tr>
				<td>Code Postal :</td>
				<td><input type="text" name="codePostal"></td>
			</tr>
			<tr>
				<td>Ville :</td>
				<td><input type="text" name="ville"></td>
			</tr>
			<tr>
				<td>Mot de passe :</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td>Confirmation :</td>
				<td><input type="password" name="passwordConfirm"></td>
			</tr>
		</table>
		<input type="submit" value="S'inscrire"></input>


	</form>
	<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Retour"/></a>
</body>
</html>