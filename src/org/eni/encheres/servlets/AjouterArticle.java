package org.eni.encheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.BLLException;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class AjouterArticle
 */
@WebServlet("/Ajout")
public class AjouterArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		CategorieManager categorieManager;
		UtilisateurManager utilisateurManager;
		try {
			categorieManager = new CategorieManager();
			utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur;
			utilisateur = utilisateurManager.getUtilisateurByPseudo(pseudo);
			List<Categorie> listeCategories = new ArrayList<>();
			listeCategories = categorieManager.getAllCategories();
			request.setAttribute("listeCategories", listeCategories);
			request.setAttribute("utilisateur", utilisateur);
			request.getRequestDispatcher("/WEB-INF/NouvelleVente.jsp").forward(request, response);
		} catch (BLLException e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		UtilisateurManager utilisateurManager;
		RetraitManager retraitManager;
		ArticleManager articleManager;
		try {
			utilisateurManager = new UtilisateurManager();
			retraitManager = new RetraitManager();
			articleManager = new ArticleManager();
			Utilisateur utilisateur;
			Retrait retrait;
			Article article;
			utilisateur = utilisateurManager.getUtilisateurByPseudo(pseudo);
			Date debutEnchere = new SimpleDateFormat("dd/MM/yyyy").parse(request.getAttribute("debutEnchere").toString());  
			Date finEnchere = new SimpleDateFormat("dd/MM/yyyy").parse(request.getAttribute("finEnchere").toString()); 
			article = new Article(utilisateur.getNoUtilisateur(), request.getAttribute("nomArticle").toString(), request.getAttribute("description").toString(), debutEnchere, finEnchere, Integer.parseInt((String) request.getAttribute("prixArticle")), Integer.parseInt((String) request.getAttribute("categorieArticle")));
			articleManager.addArticle(article);
			retrait = new Retrait(utilisateur.getRue(), utilisateur.getCodePostal(), utilisateur.getVille(), article.getNoArticle());
			retraitManager.addRetrait(retrait);
			request.getRequestDispatcher("/Accueil").forward(request, response);
		} catch (BLLException e){
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
