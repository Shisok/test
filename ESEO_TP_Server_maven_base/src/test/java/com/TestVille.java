package com;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestVille {
	private static final String USER_AGENT = "Mozilla/5.0";
	static Logger logger = LoggerFactory.getLogger(Application.class.getName());

	@Test
	public void testBLO() throws IOException {
		String url = "http://localhost:8181/ville";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request headers
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		logger.info("{}", responseCode);
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
		assertEquals("", resultat);
	}
}
