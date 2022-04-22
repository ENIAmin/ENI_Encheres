<%@ page import="org.eni.encheres.bo.Article" %>
<%@ page import="org.eni.encheres.bo.Categorie" %>
<%@ page import="org.eni.encheres.bo.Enchere" %>
<%@ page import="org.eni.encheres.bo.Utilisateur" %>
<%@ page import="org.eni.encheres.bo.Retrait" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%Article article = (Article) request.getAttribute("article");  
Categorie categorie = (Categorie) request.getAttribute("categorie");
Enchere enchere = (Enchere) request.getAttribute("enchere");
Utilisateur acheteur = (Utilisateur) request.getAttribute("acheteur");
Utilisateur vendeur = (Utilisateur) request.getAttribute("vendeur");
Retrait retrait = (Retrait) request.getAttribute("retrait");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Article</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h2>Détail vente</h2>
<div>
Article: <%= article.getNomArticle() %></br>
Description: <%= article.getDescription() %></br>
Catégorie: <%= categorie.getLibelle() %></br>
Meilleure offre: <% if(enchere != null) {%><%= enchere.getMontantEnchere()%> pts par <%= acheteur.getPseudo() %> <% } %></br>
Mise à prix: <%= article.getMiseAPrix() %> points</br>
Fin de l'enchère: <%= article.getDateFinEncheres() %></br>
Retrait: <%= retrait.getRue()%> <%= retrait.getCodePostal()%> <%=retrait.getVille() %></br>
Vendeur: <%= vendeur.getPseudo() %></br>
</div>
<form action="<%=request.getContextPath()%>/Enchere" method="POST">
<input type="hidden" id="articleId" name="articleId" value="<%= article.getNoArticle()%>">
Ma proposition: <select id="montant" name="montant"><%if(enchere!= null){for(int i = enchere.getMontantEnchere() +5; i<800; i = i+5){
	%><option value="<%=i%>"><%=i%></option>
	<%}} else {
		for(int i = article.getMiseAPrix() ; i<800; i = i+5){
			%><option value="<%=i%>"><%=i%></option>
			<%}
	}%></select>
<input type="submit" value="Enchérir">
</form>

<a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Retour"/></a>
</body>
</html>