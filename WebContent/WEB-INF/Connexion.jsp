<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page de connexion</title>
</head>
<body>
	<h1>ENI-Enchères</h1>

	<form action="<%=request.getContextPath()%>/Connexion" method="post" class=>
		<table>
			<tr>
				<td>Identifiant:</td>
				<td><input type="text" name="login" id="login"></td>
			</tr>
			<tr>
				<td>Mot de passe:</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Connexion"></td>
			</tr>
		</table>
		<a href="/ForgotPassword" class=""> Mot de passe oublié ? </a>
		
	</form>
</body>
</html> 