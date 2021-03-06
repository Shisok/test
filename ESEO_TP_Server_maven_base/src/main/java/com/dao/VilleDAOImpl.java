package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.config.JDBCConfiguration;
import com.dto.Ville;

@Repository
public class VilleDAOImpl implements VilleDAO {
	private static final Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());

	private String error = "Error";

	public List<Ville> getInfoVille() {

		logger.info("Appel GET");
		String sql = "SELECT * FROM ville_france ";
		return requete(sql);

	}

	public List<Ville> getInfoVilleParam(int code) {
		logger.info("Appel GET");
		String sql = "SELECT * FROM ville_france WHERE Code_postal=" + code;
		return requete(sql);
	}

	public List<Ville> requete(String sql) {

		int codeCommuneINSEE = 0;
		String nomcommune = "";
		int codePostal = 0;
		String libelleAcheminement = "";
		String ligne5 = "";
		float latitude = 0;
		float longitude = 0;
		Ville ville;

		List<Ville> listeVille = new ArrayList<>();

		try (Connection connection = JDBCConfiguration.getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {
			while (rs.next()) {
				codeCommuneINSEE = rs.getInt("Code_commune_INSEE");
				nomcommune = rs.getString("Nom_commune");
				libelleAcheminement = rs.getString("Libelle_acheminement");
				ligne5 = rs.getString("Ligne_5");
				latitude = rs.getFloat("Latitude");
				codePostal = rs.getInt("Code_postal");
				longitude = rs.getFloat("Longitude");

				ville = new Ville(codeCommuneINSEE, nomcommune, codePostal, libelleAcheminement, ligne5, latitude,
						longitude);
				listeVille.add(ville);
			}
		} catch (SQLException e) {
			logger.error(error, e);
		}

		return listeVille;
	}

	public void insertVille(Ville ville) {
		logger.info("Appel POST");
		try (Connection connection = JDBCConfiguration.getConnection();
				Statement stmt = connection.createStatement();) {
			stmt.executeUpdate(
					"Insert into ville_france(Code_commune_INSEE,Nom_commune,Libelle_acheminement,Ligne_5,Latitude,Code_postal,Longitude)"
							+ " values(" + ville.getCodeCommune() + ",'" + ville.getNomCommune() + "','"
							+ ville.getLibelleAcheminement() + "','" + ville.getLigne() + "'," + ville.getLatitude()
							+ "," + ville.getCodePostal() + "," + ville.getLongitude() + ")");
		} catch (SQLException e) {
			logger.error(error, e);
		}

	}

	public void insertVillePut(Ville ville) {
		logger.info("Appel PUT");
		String sql = "SELECT * FROM ville_france WHERE Code_commune_INSE=" + ville.getCodeCommune();
		List<Ville> listeVille = requete(sql);
		if (listeVille.isEmpty()) {
			try (Connection connection = JDBCConfiguration.getConnection();
					Statement stmt = connection.createStatement();) {
				stmt.executeUpdate(
						"Insert into ville_france(Code_commune_INSEE,Nom_commune,Libelle_acheminement,Ligne_5,Latitude,Code_postal,Longitude)"
								+ " values(" + ville.getCodeCommune() + ",'" + ville.getNomCommune() + "','"
								+ ville.getLibelleAcheminement() + "','" + ville.getLigne() + "'," + ville.getLatitude()
								+ "," + ville.getCodePostal() + "," + ville.getLongitude() + ")");
			} catch (SQLException e) {
				logger.error(error, e);
			}
		}

	}

	public void deleteVille(Ville ville) {
		logger.info("Appel DELETE");
		try (Connection connection = JDBCConfiguration.getConnection();
				Statement stmt = connection.createStatement();) {
			stmt.executeUpdate(
					"UPDATE ville_france SET Flag_Delete = 1 WHERE Code_commune_INSE" + ville.getCodeCommune());
		} catch (SQLException e) {
			logger.error(error, e);
		}

	}

}
