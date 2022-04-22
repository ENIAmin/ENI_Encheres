<%@ page import="org.eni.encheres.bo.Article" %>
<%@ page import="java.util.List" %>
<%@  page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%List<Article> listeArticles = (List<Article>) request.getAttribute("listeArticles"); %>
<!DOCTYPE html>
<html>
</head>
<body>
	<h1>ENI-Enchères</h1>
<%=session.getAttribute("pseudo")%>
Welcome ${sessionScope.pseudo }


	<a href="<%=request.getContextPath()%>/register">S'inscrire</a>
	</br>
	<a href="<%=request.getContextPath()%>/Connexion">Se connecter</a>
	</br>
	<h2>Filtres :</h2>
	<select name="categorie" id="categorie">
		<option value="">Toutes</option>
		<option value="test">test</option>
		<option value="wsh">wsh</option>
		<option value="peeka">peeka</option>
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