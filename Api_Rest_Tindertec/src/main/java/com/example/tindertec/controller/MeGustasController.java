package com.example.tindertec.controller;

import com.example.tindertec.models.Usuario;
import com.example.tindertec.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/MeGustas")
public class MeGustasController {

	@Autowired
	IUsuarioRepository usuRepo;
	@Autowired
	ILikesRepository likesRepo;
	@Autowired
	IDislikesRepository disLikesRepo;
	// private IUsuarioRepository repoUsua;
	// @GetMapping
	// @PostMapping

	@GetMapping("/Lista")
	public List<Usuario> lstusu () {
		int CodUsuInSession = SeguridadController.CodUsuInSession;

	//List<Usuario> lstusu = usuRepo.USP_Listar_Usuarios_Likes(CodUsuInSession);
		List<Usuario> lstusu = usuRepo.USP_Listar_Usuarios_Likes(1);
		if (lstusu.isEmpty()) {
			lstusu= null;
			return lstusu;
		} else {
			
			return lstusu;
		}
	}
	@PostMapping("/Eliminar")
	public String Eliminar(@RequestBody Usuario usuario) throws ParseException {

		try {
			int CodUsuInSession = SeguridadController.CodUsuInSession;
			disLikesRepo.USP_ELIMINAR_LIKE(CodUsuInSession, usuario.getCod_usu());
			return	"¡No me gusta!";
			 
		} catch (Exception e) {

			return "Error al eliminar like";
			 
		}
	}

}
