package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JDBCConfiguration {
	private static final String PASSWORD = ConfigRead.getString("PASSWORD");

	public static Connection getConnection() {
		Logger logger = LoggerFactory.getLogger(new Throwable().getStackTrace()[0].getClassName());
		String error = "Error";

		String jdbcdriver = "com.mysql.cj.jdbc.Driver";
		String user = "admin";
		String password = PASSWORD;
		String addressServer = "localhost";
		String sqlUrl = "jdbc:mysql://" + addressServer + "/ville_france?user=" + user + "&password=" + password;

		Connection connection = null;
		// L'essaie de connexion à votre base de donées
		try {
			Class.forName(jdbcdriver);
			// création de la connexion

			connection = DriverManager.getConnection("" + sqlUrl);

		} catch (ClassNotFoundException e) {
			logger.error(error, e);
		} catch (SQLException e1) {
			logger.error(error, e1);
		}
		return connection;
	}

}
