package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.modele.Ville;

//import com.blo.VilleBLO;

@RestController
//@RequestMapping("/path")
class VilleController {

	// @Autowired
	// VilleBLO villeService;

	// Methode GET
	@RequestMapping(value = "/ville", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Ville> appelGet() throws ClassNotFoundException, SQLException {
		System.out.println("Appel GET");
		String jdbcdriver = "com.mysql.cj.jdbc.Driver";
		String user = "admin";
		String password = "network";
		String addressServer = "localhost";
		String sqlUrl = "jdbc:mysql://" + addressServer + "/ville_france?user=" + user + "&password=" + password;

		int codeCommuneINSEE = 0;
		String nomcommune = "";
		int codePostal = 0;
		String libelleAcheminement = "";
		String ligne5 = "";
		float latitude = 0;
		float longitude = 0;
		Ville ville;

		List<Ville> listeVille = new ArrayList<Ville>();

		String sql = "SELECT * FROM ville_france ";
		Class.forName(jdbcdriver);
		Connection connection = DriverManager.getConnection("" + sqlUrl);
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			codeCommuneINSEE = rs.getInt("Code_commune_INSEE");
			nomcommune = rs.getString("Nom_commune");
			libelleAcheminement = rs.getString("Libelle_acheminement");
			ligne5 = rs.getString("Ligne_5");
			latitude = rs.getFloat("Latitude");
			codePostal = rs.getInt("Code_postal");
			longitude = rs.getFloat("Longitude");
			System.out.println(codeCommuneINSEE + "+" + nomcommune + "+" + codePostal + "+" + libelleAcheminement + "+"
					+ ligne5 + "+" + latitude + "+" + longitude);
			ville = new Ville(codeCommuneINSEE, nomcommune, codePostal, libelleAcheminement, ligne5, latitude,
					longitude);
			listeVille.add(ville);

		}

		return listeVille;
	}

	// Methode Post
	@RequestMapping(value = "/villePost", method = RequestMethod.POST)
	@ResponseBody
	public String appelPost() {
		System.out.println("Appel POST");

		// TODO

		return "JSON";
	}
}
