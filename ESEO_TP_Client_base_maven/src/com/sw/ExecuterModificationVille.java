package com.sw;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.config.ConfigRead;

/**
 * Servlet implementation class ExecuterModificationVille
 */
@WebServlet("/ExecuterModificationVille")
public class ExecuterModificationVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());

	private static String error = "Error";
	private static final String PASSWORD = ConfigRead.getString("PASSWORD");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuterModificationVille() {
		super();

	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codeCommuneINSEE = request.getParameter("codeCommuneINSEE");
		String nomCommune = request.getParameter("nomCommune");
		String codePostal = request.getParameter("codePostal");
		String libelleAcheminement = request.getParameter("libelleAcheminement");
		String ligne5 = request.getParameter("ligne5");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		String jdbcdriver = "com.mysql.cj.jdbc.Driver";
		String user = "admin";
		String password = PASSWORD;
		String addressServer = "localhost";
		String sqlUrl = "jdbc:mysql://" + addressServer + "/ville_france?user=" + user + "&password=" + password;

		try {
			Class.forName(jdbcdriver);
		} catch (ClassNotFoundException e1) {
			logger.error(error, e1);
		}
		try (Connection connection = DriverManager.getConnection("" + sqlUrl);
				Statement stmt = connection.createStatement();) {

			stmt.executeUpdate("UPDATE `ville_france` SET `Nom_commune` = '" + nomCommune + "', `Code_postal` = '"
					+ codePostal + "', `Libelle_acheminement` = '" + libelleAcheminement + "', `Ligne_5` = '" + ligne5
					+ "', `Latitude` = '" + latitude + "', `Longitude` = '" + longitude
					+ "' WHERE `ville_france`.`Code_commune_INSEE` = '" + codeCommuneINSEE + "'");

		} catch (

		SQLException e) {
			logger.error(error, e);
		}

		RequestDispatcher dispat = request.getRequestDispatcher("modificationReussie.jsp");
		dispat.forward(request, response);

	}

}
