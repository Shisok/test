package com.blo;

import java.util.List;

import com.dto.Ville;

public interface VilleBLO {

	public List<Ville> getInfoVille();

	public List<Ville> getInfoVilleParam(int code);

	public void insertVille(Ville ville);
}
