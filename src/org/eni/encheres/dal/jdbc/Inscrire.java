package org.eni.encheres.dal.jdbc;

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
 * Servlet implementation class Inscrire
 */
@WebServlet("/Inscription")
public class Inscrire extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager utilisateurManager;
		try {
			utilisateurManager = new UtilisateurManager();
			Utilisateur utilisateur;
			//if(request.getParameter("password").trim() == request.getParameter("passwordConfirm").trim()){
				utilisateur = new Utilisateur(request.getParameter("pseudo"), request.getParameter("nom"), request.getParameter("prenom"), request.getParameter("email"), request.getParameter("telephone"), request.getParameter("rue"), request.getParameter("codePostal"), request.getParameter("ville"), request.getParameter("password"));
				utilisateurManager.addUtilisateur(utilisateur);
				HttpSession session = request.getSession();
				session.setAttribute("pseudo", utilisateur.getPseudo());
				request.getRequestDispatcher("/Accueil").forward(request, response);
			/*} else {
				request.getRequestDispatcher("/WEB-INF/Inscription.jsp").forward(request, response);
			}*/
			} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
