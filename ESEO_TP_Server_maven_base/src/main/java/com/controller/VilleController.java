package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blo.VilleBLO;
import com.dto.Ville;

@RestController
//@RequestMapping("/path")
class VilleController {
	@Autowired
	VilleBLO villeService;

	// Methode GET
	// ?codePostal=abc apres ville pour parametre
	@GetMapping(value = "/ville")
	@ResponseBody
	public List<Ville> appelGet(
			@RequestParam(required = false, value = "codePostal", defaultValue = "0") String monParam) {
		System.out.println("Appel GET");
		System.out.println("param = " + monParam);
		List<Ville> ville;
		if (monParam.contentEquals("0")) {
			ville = villeService.getInfoVille();
		} else {
			ville = villeService.getInfoVilleParam(Integer.parseInt(monParam));
		}
		return ville;
	}

	@GetMapping(value = "/ville/{codePostal}")
	public List<Ville> appelGetParam(@PathVariable("codePostal") String codePostal) {
		System.out.println("Appel GET");
		List<Ville> ville;
		ville = villeService.getInfoVilleParam(Integer.parseInt(codePostal));
		return ville;
	}

	// Methode POST
	@PostMapping(value = "/ville")
	@ResponseBody
	public List<Ville> appelPost(@RequestBody Ville ville) {
		System.out.println("Appel POST");
		villeService.insertVille(ville);
		List<Ville> ville1 = new ArrayList<>();
		ville1.add(ville);
		return ville1;
	}

	@PutMapping(value = "/ville")
	@ResponseBody
	public List<Ville> appelPut(@RequestBody Ville ville) {
		System.out.println("Appel POST");
		villeService.insertVillePut(ville);
		List<Ville> ville1 = new ArrayList<>();
		ville1.add(ville);
		return ville1;
	}

}