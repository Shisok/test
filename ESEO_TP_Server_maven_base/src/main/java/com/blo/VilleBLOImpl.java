package com.blo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.VilleDAO;
import com.dto.Ville;

@Service
public class VilleBLOImpl implements VilleBLO {

	@Autowired
	private VilleDAO villeDAO;

	public List<Ville> getInfoVille() {
		List<Ville> ville;

		ville = villeDAO.getInfoVille();
		return ville;
	}

	@Override
	public List<Ville> getInfoVilleParam(int code) {
		List<Ville> ville;

		ville = villeDAO.getInfoVilleParam(code);
		return ville;
	}

	public void insertVille(Ville ville) {
		villeDAO.insertVille(ville);
	}

}
