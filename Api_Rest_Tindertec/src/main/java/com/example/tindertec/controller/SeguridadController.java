package com.example.tindertec.controller;

import com.example.tindertec.models.Usuario;
import com.example.tindertec.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Seguridad")
public class SeguridadController {
	public static String nombresYedad;
	public static String foto1;
	public static String edad;
	public static int CodUsuInSession;

	@Autowired
	private IUsuarioRepository repoUsua;

	@Transactional
	@PostMapping("/Login")
	
	/*
	 @RequestMapping(value = "/{email}/authenticate", method = RequestMethod.POST,
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
        produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody  Representation authenticate(@PathVariable("email") String anEmailAddress, MultiValueMap paramMap) throws Exception {
		   if(paramMap == null && paramMap.get("password") == null) {
		        throw new IllegalArgumentException("Password not provided");
		    }
		    return null;
		}
	  */
	public Usuario validarUsuario(@RequestParam Map<String, String> body ) throws ParseException {

		String msj = repoUsua.usp_usuario_acceso(body.get("email"), body.get("clave"));
		
		System.out.println(body.get("email")+ body.get("clave"));
		if (msj.equals("OK")) {

			Usuario u = repoUsua.findByEmailAndClave(body.get("email"), body.get("clave"));
			
			CodUsuInSession = u.getCod_usu();
			edad = obtenerEdad(repoUsua.findById(u.getCod_usu()).get().getFecha_naci());
			nombresYedad = repoUsua.findById(u.getCod_usu()).get().getNombres() + "," + edad;
			foto1 = repoUsua.findById(u.getCod_usu()).get().getFoto1();

			return u;
		} else {
			Usuario usuempty = new Usuario();
			return usuempty;
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
