package com.sw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ConfigRead;

/**
 * Servlet implementation class ModifierVille
 */
@WebServlet("/ModifierVille")
public class ModifierVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
	private static final String PASSWORD = ConfigRead.getString("PASSWORD");
	private String error = "Error";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifierVille() {
		super();

	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String codeCommuneINSEE = "";

		String codePostal = "";
		String libelleAcheminement = "";
		String ligne5 = "";
		float latitude = 0;
		float longitude = 0;

		String nomCommune = request.getParameter("villeAModifier");

		String jdbcdriver = "com.mysql.cj.jdbc.Driver";
		String user = "admin";
		String password = PASSWORD;
		String addressServer = "localhost";
		String sqlUrl = "jdbc:mysql://" + addressServer + "/ville_france?user=" + user + "&password=" + password;

		// L'essaie de connexion à votre base de donées
		try {
			Class.forName(jdbcdriver);
			// création de la connexion

		} catch (ClassNotFoundException e) {
			logger.error(error, e);
		}
		String sql = "SELECT * FROM ville_france WHERE Nom_commune='" + nomCommune + "'";
		try (Connection connection = DriverManager.getConnection("" + sqlUrl);
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				codeCommuneINSEE = rs.getString("Code_commune_INSEE");
				nomCommune = rs.getString("Nom_commune");
				libelleAcheminement = rs.getString("Libelle_acheminement");
				ligne5 = rs.getString("Ligne_5");
				latitude = rs.getFloat("Latitude");
				codePostal = rs.getString("Code_postal");
				longitude = rs.getFloat("Longitude");
				session.setAttribute("codeCommuneINSEE", codeCommuneINSEE);
				session.setAttribute("nomCommune", nomCommune);
				session.setAttribute("libelleAcheminement", libelleAcheminement);
				session.setAttribute("ligne5", ligne5);
				session.setAttribute("latitude", latitude);
				session.setAttribute("codePostal", codePostal);
				session.setAttribute("longitude", longitude);
			}
		} catch (SQLException e) {
			logger.error(error, e);
		}
		RequestDispatcher dispat = request.getRequestDispatcher("modificationVille.jsp");
		dispat.forward(request, response);
	}

}
