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

/**
 * Servlet implementation class DistanceVille
 */
@WebServlet("/DistanceVille")
public class DistanceVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
	private static final String USER_AGENT = "Mozilla/5.0";
	private static String error = "Error";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DistanceVille() {
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

		String resultat = HttpURLConnectionExample.getVilles();

		logger.info(resultat);
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(resultat);
			JSONArray array = jsonObj.getJSONArray("tableau");
			String nomCommune;
			List<String> listeNomCommune = new ArrayList<>();
			for (int i = 0; i < array.length(); i++) {
				nomCommune = array.getJSONObject(i).getString("nomCommune");
				listeNomCommune.add(nomCommune);

			}
			session.setAttribute("villes", listeNomCommune);
		} catch (JSONException e) {

			logger.error(error, e);
		}
		RequestDispatcher dispat = request.getRequestDispatcher("distance.jsp");
		dispat.forward(request, response);
		//
	}

}
