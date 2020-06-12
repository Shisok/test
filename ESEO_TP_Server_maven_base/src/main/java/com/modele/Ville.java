package com.modele;

public class Ville {
	
	private  int Code_commune_INSEE;
	private  String Nom_commune;
	private  int Code_postal=0;
	private  String Libelle_acheminement;
	private  String Ligne_5;
	private  float Latitude;
	private  float Longitude;
	
	public int getCode_commune_INSEE() {
		return Code_commune_INSEE;
	}
	public void setCode_commune_INSEE(int code_commune_INSEE) {
		Code_commune_INSEE = code_commune_INSEE;
	}
	public String getNom_commune() {
		return Nom_commune;
	}
	public void setNom_commune(String nom_commune) {
		Nom_commune = nom_commune;
	}
	public int getCode_postal() {
		return Code_postal;
	}
	public void setCode_postal(int code_postal) {
		Code_postal = code_postal;
	}
	public String getLibelle_acheminement() {
		return Libelle_acheminement;
	}
	public void setLibelle_acheminement(String libelle_acheminement) {
		Libelle_acheminement = libelle_acheminement;
	}
	public String getLigne_5() {
		return Ligne_5;
	}
	public void setLigne_5(String ligne_5) {
		Ligne_5 = ligne_5;
	}
	public float getLatitude() {
		return Latitude;
	}
	public void setLatitude(float latitude) {
		Latitude = latitude;
	}
	public float getLongitude() {
		return Longitude;
	}
	public void setLongitude(float longitude) {
		Longitude = longitude;
	}

	public Ville(int code_commune_INSEE, String nom_commune, int code_postal, String libelle_acheminement,
			String ligne_5, float latitude, float longitude) {
		super();
		Code_commune_INSEE = code_commune_INSEE;
		Nom_commune = nom_commune;
		Code_postal = code_postal;
		Libelle_acheminement = libelle_acheminement;
		Ligne_5 = ligne_5;
		Latitude = latitude;
		Longitude = longitude;
	}
	
	
}
