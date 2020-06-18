package com.sw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpURLConnectionExample {
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
	private static final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionExample http = new HttpURLConnectionExample();
		logger.info("Testing 1 - Send Http GET request");

		http.sendGet();

	}

	// HTTP GET request
	private void sendGet() throws IOException, JSONException {

		String url = "http://localhost:8181/ville";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		logger.info("\nSending 'GET' request to URL : {}", url);

		logger.info("Response Code : {}", responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		String resultat = response.toString();
		resultat = resultat.replace("[", "{\"tableau\":[");
		resultat = resultat.replace("]", "]}");

		logger.info(resultat);
		JSONObject jsonObj = new JSONObject(resultat);
		JSONArray array = jsonObj.getJSONArray("tableau");
		String nomCommune;

		for (int i = 0; i < array.length(); i++) {
			nomCommune = array.getJSONObject(i).getString("nomCommune");
			logger.info(nomCommune);
		}
	}

}