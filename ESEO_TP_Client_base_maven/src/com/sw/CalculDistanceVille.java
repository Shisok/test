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
 * Servlet implementation class CalculDistanceVille
 */
@WebServlet("/CalculDistanceVille")
public class CalculDistanceVille extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
	private static final String USER_AGENT = "Mozilla/5.0";
	private static String error = "Error";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalculDistanceVille() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String ville1 = request.getParameter("ville1");
		String ville2 = request.getParameter("ville2");
		session.setAttribute("ville1", ville1);
		session.setAttribute("ville2", ville2);
		double lat1 = 0;
		double long1 = 0;
		double lat2 = 0;
		double long2 = 0;
//		String url = "http://localhost:8181/ville";
//
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//
//		// optional default is GET
//		con.setRequestMethod("GET");
//
//		// add request header
//		con.setRequestProperty("User-Agent", USER_AGENT);
//
//		int responseCode = con.getResponseCode();
//		logger.info("\nSending 'GET' request to URL : {}", url);
//
//		logger.info("Response Code : {}", responseCode);
//
//		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuilder responseHttp = new StringBuilder();
//
//		while ((inputLine = in.readLine()) != null) {
//			responseHttp.append(inputLine);
//		}
//		in.close();
//		String resultat = responseHttp.toString();
//		resultat = resultat.replace("[", "{\"tableau\":[");
//		resultat = resultat.replace("]", "]}");
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
				if (nomCommune.equals(ville1)) {
					lat1 = array.getJSONObject(i).getDouble("latitude");
					long1 = array.getJSONObject(i).getDouble("longitude");
				} else if (nomCommune.equals(ville2)) {
					lat2 = array.getJSONObject(i).getDouble("latitude");
					long2 = array.getJSONObject(i).getDouble("longitude");
				}
				listeNomCommune.add(nomCommune);

			}

			double distance = 6371 * Math.acos(
					Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1))
							* Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(Math.abs(long2 - long1))));
			session.setAttribute("villes", listeNomCommune);
			session.setAttribute("distance", distance);
		} catch (JSONException e) {

			logger.error(error, e);
		}
		RequestDispatcher dispat = request.getRequestDispatcher("distanceResultat.jsp");
		dispat.forward(request, response);

		//
	}

}
