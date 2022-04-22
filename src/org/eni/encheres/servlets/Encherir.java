package org.eni.encheres.servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.BLLException;
import org.eni.encheres.bll.EnchereManager;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Enchere;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class Encherir
 */
@WebServlet("/Enchere")
public class Encherir extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pseudo = (String) session.getAttribute("pseudo");
		System.out.println(pseudo);
		UtilisateurManager utilisateurManager;
		EnchereManager enchereManager;
		try {
			if(request.getParameter("montant") != null) {
				utilisateurManager = new UtilisateurManager();
				enchereManager = new EnchereManager();
				Utilisateur utilisateur;
				Enchere enchere;
				utilisateur = utilisateurManager.getUtilisateurByPseudo(pseudo);
				if(Integer.parseInt((String) request.getParameter("montant")) <= utilisateur.getCredit()) {
					enchere = new Enchere(utilisateur.getNoUtilisateur(), Integer.parseInt((String) request.getParameter("articleId")), LocalDateTime.now(), Integer.parseInt((String) request.getParameter("montant")));
					System.out.println("Enchere a supprimer" + Integer.parseInt((String) request.getParameter("articleId")));
					enchereManager.removeEnchere(Integer.parseInt((String) request.getParameter("articleId")));
					enchereManager.addEnchere(enchere);
					int credit = utilisateur.getCredit() - enchere.getMontantEnchere();
					utilisateurManager.updateCredit(utilisateur.getNoUtilisateur(), credit);
				}
				request.setAttribute("articleID", Integer.parseInt((String) request.getParameter("articleId")));
				request.getRequestDispatcher("/Article").forward(request, response);
			} else {
				request.getRequestDispatcher("/Accueil").forward(request, response);
			}
		} catch (BLLException e){
			e.printStackTrace();
		}
	}

}
