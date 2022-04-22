<%@ page import="org.eni.encheres.bo.Article"%>
<%@ page import="org.eni.encheres.bo.Categorie"%>
<%@ page import="org.eni.encheres.bo.Enchere"%>
<%@ page import="org.eni.encheres.bo.Utilisateur"%>
<%@ page import="org.eni.encheres.bo.Retrait"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profil</title>
</head>
<body>
<h1>ENI-Enchères</h1>
<h2><%=utilisateur.getPseudo()%></h2>
    <table>
        <tr>
            <td>Pseudo: <%=utilisateur.getPseudo()%></td>
        </tr>
        <tr>
            <td>Nom: <%=utilisateur.getNom()%></td>
        </tr>
        <tr>
            <td>Prenom: <%=utilisateur.getPrenom()%></td>
        </tr>
        <tr>
            <td>Crédit: <%=utilisateur.getCredit()%></td>
        </tr>
        <tr>
            <td>Email: <%=utilisateur.getEmail()%></td>
        </tr>
        <tr>
            <td>Téléphone: <%=utilisateur.getTelephone()%></td>
        </tr>
        <tr>
            <td>Rue: <%=utilisateur.getRue()%></td>
        </tr>
        <tr>
            <td>Code Postal: <%=utilisateur.getCodePostal()%></td>
        </tr>
        <tr>
            <td>Ville: <%=utilisateur.getVille()%></td>
        </tr>
    </table>
    <%if (session.getAttribute("pseudo") == utilisateur.getPseudo()) {%>
	    <form method="POST" action="<%=request.getContextPath()%>/Profil">
	    	<input type="submit" value="Supprimer mon compte">
    	</form>
    <%} %>
    <a href="<%=request.getContextPath()%>/Accueil"><input type="button" value="Retour"/></a>
</body>
</html>