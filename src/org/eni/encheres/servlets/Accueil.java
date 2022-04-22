package org.eni.encheres.servlets;

import java.io.IOException;
import java.util.ArrayList;
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
import org.eni.encheres.bo.Article;
import org.eni.encheres.bo.Categorie;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/Accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleManager articleManager;
		CategorieManager categorieManager;
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		System.out.println(pseudo);
		try {
			categorieManager = new CategorieManager();
			articleManager = new ArticleManager();
			List<Categorie> listeCategories = new ArrayList<>();
			List<Article> listeArticles = new ArrayList<>();
			listeArticles = articleManager.getAllArticles();
			listeCategories = categorieManager.getAllCategories();
			request.setAttribute("listeArticles", listeArticles);
			request.setAttribute("listeCategories", listeCategories);
			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
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
