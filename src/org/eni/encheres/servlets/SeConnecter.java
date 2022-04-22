package org.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.BLLException;
import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class SeConnecter
 */
@WebServlet("/Connexion")
public class SeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("pseudo") != null) {
			session.removeAttribute("pseudo");
			request.getRequestDispatcher("/Accueil").forward(request, response);
		} else {
			request.getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
        HttpSession session = request.getSession();
        UtilisateurManager utilisateurManager;
        Utilisateur utilisateur;
        try {
        	utilisateurManager = new UtilisateurManager();
			utilisateur = utilisateurManager.getUtilisateurByLogin(login, password);
			if(utilisateur != null) {
		        session.setAttribute("pseudo", utilisateur.getPseudo());
		        request.getRequestDispatcher("/Accueil").forward(request, response);
			} else {
				request.getRequestDispatcher("/WEB-INF/Connexion.jsp").forward(request, response);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
        
	        
	}

}
