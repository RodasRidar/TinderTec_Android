package com.example.tindertec.controller;

import com.example.tindertec.models.*;
import com.example.tindertec.repository.*;

import antlr.collections.List;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Usuario")
public class UsuarioController {

	@Autowired
	ICarrerasRepository repoCar;
	@Autowired
	ISedesRepository repoSed;
	@Autowired
	IGeneroUsuarioRepository repoGenusu;
	@Autowired
	IInteresGeneroRepository repoInte;
	@Autowired
	IUsuarioRepository repoUsu;

//

	@GetMapping("/ListaSedes")
	public ArrayList<Sedes> lstSedes() {
		ArrayList<Sedes> lst = (ArrayList<Sedes>) repoSed.findAll();
		return lst;
	}

	@GetMapping("/ListaCarreras")
	public ArrayList<Carreras> lstCarreras() {
		ArrayList<Carreras> lst = (ArrayList<Carreras>) repoCar.findAll();
		return lst;
	}

	@GetMapping("/ListaGenero")
	public ArrayList<GeneroUsuario> lstGenero() {
		ArrayList<GeneroUsuario> lst = (ArrayList<GeneroUsuario>) repoGenusu.findAll();
		return lst;
	}

	@GetMapping("/ListaGeneroInteres")
	public ArrayList<InteresGenero> lstGeneroInteres() {
		ArrayList<InteresGenero> lst = (ArrayList<InteresGenero>) repoInte.findAll();
		return lst;
	}

	@Transactional
	@PostMapping("/Registrar")
	public String registrarUsuario(@RequestParam Map<String, String> usuario) throws ParseException {

		try {
			repoUsu.USP_USUARIO_REGISTRAR(usuario.get("Nombres"), usuario.get("Email"), usuario.get("FechaNacimiento"),
					usuario.get("Clave"), Integer.parseInt(usuario.get("CodSede")),
					Integer.parseInt(usuario.get("CodCarrera")), Integer.parseInt(usuario.get("CodGenero")),
					Integer.parseInt(usuario.get("CodInteres")), usuario.get("Descripcion"), usuario.get("Foto1"));

			return "¡Se registro el usuario " + usuario.get("Nombres") + " correctamente!";

		} catch (Exception e) {

			return "Ups!,Ocurrio un problema en el registro";

		}
	}

	@PostMapping("/Guardar")
	public String GUARDARUsuario(@RequestParam Map<String, String> usuario) throws ParseException {

		try {
			repoUsu.USP_EDITAR_PERFIL(Integer.parseInt(usuario.get("Cod_usu")), usuario.get("Nombres"),
					usuario.get("Descripcion"), Integer.parseInt(usuario.get("CodInteres")),
					Integer.parseInt(usuario.get("CodCarrera")), Integer.parseInt(usuario.get("CodSede")));

			return "¡Cambios guardados exitosamente.";

		} catch (Exception e) {

			return "Error al guardar cambios.";

		}
	}

	@PostMapping("/AgregarFoto")
	public String AgregarFoto(@RequestBody Usuario usuario) throws ParseException {

		try {
			int CodUsuInSession = SeguridadController.CodUsuInSession;
			repoUsu.USP_USUARIO_INSERTAR_FOTO(CodUsuInSession, 2, usuario.getFoto2());
			return "¡Foto agregada exitosamente!";

		} catch (Exception e) {

			return "Error al guardar cambios.";

		}
	}

	@Transactional
	@PostMapping("/Eliminar")
	public String Eliminar() throws ParseException {

		try {
			int CodUsuInSession = SeguridadController.CodUsuInSession;
			//repoUsu.USP_USUARIO_ELIMINAR(CodUsuInSession)
			System.out.println("¡Usuario eliminado Correctamente!");
			return "¡Usuario eliminado Correctamente!";
			
		} catch (Exception e) {

			
			System.out.println("Error al eliminar usuario");
			return "Error al eliminar usuario";
		}
	}

	public String obtenerEdad(String fecna) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
		// fecna= repoUsua.findById(1).get().getFecha_naci();
		Date fechaNacimiento = sdf.parse(fecna);
		Date secondDate = sdf.parse("2022-01-01");

		long diff = (secondDate.getTime() - fechaNacimiento.getTime()) / 365;

		TimeUnit time = TimeUnit.DAYS;
		long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		String age;
		age = diffrence + "";

		return age;
	}
}
