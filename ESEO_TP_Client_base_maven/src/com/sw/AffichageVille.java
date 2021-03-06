package com.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dto.Ville;

/**
 * Servlet implementation class affichageVille
 */
@WebServlet("/AffichageVille")
public class AffichageVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());

	private static String error = "Error";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AffichageVille() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int numeroPage = 0;
		int nombreTotalOutil = 0;
		int nombreParPage = 50;
		int indexDebut = 0;
		int nombrePage = 0;

		String resultat = HttpURLConnectionExample.getVilles();
		logger.info(resultat);
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(resultat);
			JSONArray array = jsonObj.getJSONArray("tableau");

			List<Ville> listeVilles = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {

				listeVilles.add(new Ville(array.getJSONObject(i).getInt("codeCommune"),
						array.getJSONObject(i).getString("nomCommune"), array.getJSONObject(i).getInt("codePostal"),
						array.getJSONObject(i).getString("libelleAcheminement"),
						array.getJSONObject(i).getString("ligne"), array.getJSONObject(i).getDouble("latitude"),
						array.getJSONObject(i).getDouble("longitude")));
			}

			session.setAttribute("listeVilles", listeVilles);
			String sNumeroDePage = request.getParameter("pageno");
			if (sNumeroDePage == null) {
				session.setAttribute("pageno", 1);
				sNumeroDePage = "1";
			}
			numeroPage = Integer.parseInt(sNumeroDePage);
			indexDebut = (numeroPage * nombreParPage) - nombreParPage + 1;

			nombreTotalOutil = array.length();

			nombrePage = nombreTotalOutil / nombreParPage;
			if (nombreTotalOutil > nombrePage * nombreParPage) {
				nombrePage++;
			}

			session.setAttribute("indexDebut", indexDebut - 1);
			session.setAttribute("nombrePage", nombrePage);
		} catch (JSONException e) {

			logger.error(error, e);
		}

		RequestDispatcher dispat = request.getRequestDispatcher("listeVilles.jsp");
		dispat.forward(request, response);
		//
	}

}
