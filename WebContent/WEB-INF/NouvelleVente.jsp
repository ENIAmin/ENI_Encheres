<%@ page import="org.eni.encheres.bo.Categorie" %>
<%@ page import="org.eni.encheres.bo.Utilisateur" %>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategories");
Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Nouvelle Vente</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h2>Nouvelle Vente</h2>
<form action="<%=request.getContextPath()%>/Ajout" method="POST">
<label for="nomArticle">Article:</label>
<input id="nomArticle" name="nomArticle" type="text">
<label for="categorieArticle">Catégorie:</label>
<select id="categorieArticle" name="categorieArticle"><%for(Categorie categorie : listeCategories){
	%><option value="<%=categorie.getNoCategorie()%>"><%=categorie.getLibelle()
	%></option>
	<%}%></select>
<label for="descriptionArticle">Description:</label>
<input id="descriptionArticle" name="descriptionArticle" type="text">
<label for="prixArticle">Prix initial:</label>
<select id="prixArticle" name="prixArticle"><%for(int i = 5; i<800; i = i+5){
	%><option value="<%=i%>"><%=i%></option>
	<%}%></select>
<label for="debutEnchere">Début de l'enchère:</label>
<input id="debutEnchere" name="debutEnchere" type="date">
<label for="finEnchere">Fin de l'enchère):</label>
<input id="finEnchere" name="finEnchere" type="date">
Retrait: <%= utilisateur.getRue()%> <%=utilisateur.getCodePostal()%> <%=utilisateur.getVille() %>

<input type="submit" value="Enregistrer">
</form>

<a href="<%=request.getContextPath()%>/Accueil">Annuler</a>
</body>
</html>