package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.BLLException;
import org.eni.encheres.bll.CategorieManager;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.RetraitManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Retrait;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Article
 */
@WebServlet("/Article")
public class ArticleDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager;
		CategorieManager categorieManager;
		EnchereManager enchereManager;
		UtilisateurManager utilisateurManager;
		RetraitManager retraitManager;
		try {
			articleManager = new ArticleManager();
			categorieManager = new CategorieManager();
			enchereManager = new EnchereManager();
			utilisateurManager = new UtilisateurManager();
			retraitManager = new RetraitManager();
			Article article;
			Categorie categorie;
			Enchere enchere;
			Utilisateur acheteur, vendeur;
			Retrait retrait;
			System.out.println("id" + Integer.parseInt(request.getParameter("articleID")));
			article = articleManager.getArticle(Integer.parseInt(request.getParameter("articleID")));
			categorie = categorieManager.getCategorie(article.getNoCategorie());
			enchere = enchereManager.getEnchereByArticle(article.getNoArticle());
			if(enchere != null) {
				acheteur = utilisateurManager.getUtilisateur(enchere.getNoUtilisateur());
				request.setAttribute("acheteur", acheteur);
			}
			System.out.println(article.getNoUtilisateur());
			vendeur = utilisateurManager.getUtilisateur(article.getNoUtilisateur());
			retrait = retraitManager.getRetrait(article.getNoArticle());
			request.setAttribute("article", article);
			request.setAttribute("categorie", categorie);
			request.setAttribute("enchere", enchere);
			
			request.setAttribute("vendeur", vendeur);
			request.setAttribute("retrait", retrait);
			request.getRequestDispatcher("/WEB-INF/Article.jsp").forward(request, response);
		} catch (BLLException e){
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
