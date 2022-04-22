<%@ page import="org.eni.encheres.bo.Article" %>
<%@ page import="org.eni.encheres.bo.Categorie" %>
<%@ page import="java.util.List" %>
<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles"); 
List<Categorie> listeCategories = (List<Categorie>) request.getAttribute("listeCategories"); 
%>
<!DOCTYPE html>
<html>
</head>
<body>
	<h1>ENI-Enchères</h1>


	<a href="<%=request.getContextPath()%>/Inscription">S'inscrire</a>
	</br>
	
	
	<%if(session.getAttribute("pseudo") != null){ %>
		<a href="<%=request.getContextPath()%>/Connexion">Se Déconnecter</a>
		<a href="<%=request.getContextPath()%>/Ajout">Nouvelle Vente</a>
	<%} else {%>
	<a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
	<%}%>
	</br>
	<h2>Filtres :</h2>
	<select name="categorie" id="categorie"><option value="all">Toutes</option>
		<%for(Categorie categorie : listeCategories){ %>
		<option value="<%=categorie.getNoCategorie() %>"><%=categorie.getLibelle() %></option>
		<%} %>
	</select>
	</br>
	<input placeholder="Le nom de l'article contient " type="search"
		id="search" name="search">
	<button>Rechercher</button>
	<%for(Article article : listeArticles){%>
	<div>
		Article: <a href="<%=request.getContextPath()%>/Article?articleID=<%=article.getNoArticle()%>"><%=article.getNomArticle() %></a></br>
		Description: <%=article.getDescription() %></br>
		Mise à prix: <%=article.getMiseAPrix() %></br>
		Date de début de l'enchère: <%=article.getDateDebutEncheres() %></br></br></br>
		</div>
	<%} %>
</body>
</html>