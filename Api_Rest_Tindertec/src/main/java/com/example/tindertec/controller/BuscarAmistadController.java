package com.example.tindertec.controller;

import com.example.tindertec.models.*;
import com.example.tindertec.repository.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/BuscarAmistad")
public class BuscarAmistadController {

	@Autowired
	private IUsuarioRepository repoUsua;
	@Autowired
	private ILikesRepository repoLike;
	@Autowired
	private IDislikesRepository repoDislike;
	@Autowired
	private IMatchRepository repoMatch;
	@Autowired
	private IChatRepository repoChat;

	public String obtenerEdad(String fecna) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD", Locale.ENGLISH);
		// fecna= repoUsua.findById(CodUsuInSession).get().getFecha_naci();
		Date fechaNacimiento = sdf.parse(fecna);
		Date secondDate = sdf.parse("2022-01-01");

		long diff = (secondDate.getTime() - fechaNacimiento.getTime()) / 365;

		TimeUnit time = TimeUnit.DAYS;
		long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
		String age;
		age = diffrence + "";

		return age;
	}


	@GetMapping("/Inicio")
	public List<Usuario> cargarInicio()throws ParseException {
		
		int CodUsuInSession = 2;
		List<Usuario>  lstUsuarios = repoUsua.listaBuscarAmistad(CodUsuInSession);

		return lstUsuarios;

	}

	@PostMapping("/Like")
	public String like(@RequestParam Map<String, String> body) {

		int CodUsuInSession = SeguridadController.CodUsuInSession;

		String mensaje = repoLike.USP_INSERTAR_LIKE(CodUsuInSession,Integer.parseInt(body.get("codUsuarioLike")) );

		if (mensaje == "MATCH") {
			return "MATCH";
		}
		else{
			return "";
		}

	}


	@PostMapping("/BuscarAmistad/disLike")
	public void dislike(@RequestParam Map<String, String> body) {

		int CodUsuInSession = SeguridadController.CodUsuInSession;

		repoDislike.USP_INSERTAR_DISLIKE(CodUsuInSession,Integer.parseInt(body.get("codUsuarioDisLike")) );

	}	
		
/*
	@GetMapping("/Chat")
	public String cargarChat(Model model) throws ParseException {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;

		Chat chat = new Chat();

		List<Match> match;
		match = repoMatch.USP_LISTAR_MATCH_POR_USUARIO(CodUsuInSession);

		if (match.isEmpty()) {
			model.addAttribute("msjNULLMatch", 0);
			model.addAttribute("msjNULLMensajes", 0);
		} else {
			// enviar Match
			model.addAttribute("lstMatch", match);

			// enviar chat vacio
			model.addAttribute("lstMensajes", chat);
			model.addAttribute("msjNULLMatch", 1); // post tambien
			model.addAttribute("msjNULLMensajes", 0);
			model.addAttribute("MSJdeleteMatchAndMsj", "");
		}

		// enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);

		return "Chat/Chat";
	}

	@PostMapping("/BuscarAmistad/Chat")
	public String SeleccionarChat(@RequestParam(name = "id", required = false) int id, Model model) {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		List<Match> match;
		Optional<Usuario> usu2;
		List<Chat> chat;

		chat = repoChat.USP_LISTAR_CHAT_POR_USUARIO(CodUsuInSession, id);
		usu2 = repoUsua.findById(id);
		match = repoMatch.USP_LISTAR_MATCH_POR_USUARIO(CodUsuInSession);

		if (match.isEmpty()) {
			model.addAttribute("msjNULLMatch", 0);
			model.addAttribute("msjNULLMensajes", 0);
		} else {
			model.addAttribute("msjNULLMatch", 1);
			//model.addAttribute("msjNULLMensajes", 1);

			// enviar Match
			model.addAttribute("lstMatch", match);
			model.addAttribute("foto1", usu2.get().getFoto1());
			model.addAttribute("nombres", usu2.get().getNombres());

			if (chat.isEmpty()) {
				model.addAttribute("msjNULLMensajes", 1);
				// body
				model.addAttribute("msjNULLMensajes_2", "¡Tu match esta esperando, enviale un mensaje 📨!");
			
				// head
				String ChatconNombre = "Chat con " + usu2.get().getNombres();
				model.addAttribute("Chatfoto", usu2.get().getFoto1());
				model.addAttribute("ChatconNombre", ChatconNombre);
				model.addAttribute("ChatUserid", id);
				// footer
				model.addAttribute("cod_usu_enviarmsj", id);
				model.addAttribute("MSJdeleteMatchAndMsj", "");
			} else {
				model.addAttribute("msjNULLMensajes", 1);
				//model.addAttribute("msjNULLMensajes_2", "¡Tu match esta esperando, enviale un mensaje 📨!");
				// enviar chat
				// head
				String ChatconNombre = "Chat con " + usu2.get().getNombres();
				model.addAttribute("Chatfoto", usu2.get().getFoto1());
				model.addAttribute("ChatconNombre", ChatconNombre);
				model.addAttribute("ChatUserid", id);
				// body
				model.addAttribute("lstMensajes", chat);
				model.addAttribute("cod_usu_now", CodUsuInSession);
				model.addAttribute("cod_usu_1_msj", CodUsuInSession);
				model.addAttribute("cod_usu_2_msj", id);
				model.addAttribute("foto1_msj ", usu2.get().getFoto1());
				// footer
				model.addAttribute("cod_usu_enviarmsj", id);
				model.addAttribute("MSJdeleteMatchAndMsj", "");
			}
		}

		// enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "Chat/Chat";
	}

	@PostMapping("/BuscarAmistad/EnviarMensaje")
	public String sendMensaje(@RequestParam(name = "msj_enviar", required = false) String msj_enviar,
			@RequestParam(name = "cod_usu_enviarmsj", required = false) int cod_usu_enviarmsj, Model model) {
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;
		// enviar msj
		repoChat.USP_REGISTRAR_CHAT(CodUsuInSession, cod_usu_enviarmsj, msj_enviar);

		List<Match> match;
		Optional<Usuario> usu2;
		List<Chat> chat;

		match = repoMatch.USP_LISTAR_MATCH_POR_USUARIO(CodUsuInSession);
		usu2 = repoUsua.findById(cod_usu_enviarmsj);
		chat = repoChat.USP_LISTAR_CHAT_POR_USUARIO(CodUsuInSession, cod_usu_enviarmsj);

		if (match.isEmpty()) {
			model.addAttribute("msjNULLMatch", 0);
			model.addAttribute("msjNULLMensajes", 0);
		} else {
			model.addAttribute("msjNULLMatch", 1);
			model.addAttribute("msjNULLMensajes", 1);

			// enviar Match
			model.addAttribute("lstMatch", match);
			model.addAttribute("foto1", usu2.get().getFoto1());
			model.addAttribute("nombres", usu2.get().getNombres());

			if (chat == null) {
				model.addAttribute("msjNULLMensajes", 0);
			} else {
				model.addAttribute("msjNULLMensajes", 1);
				// enviar chat
				// head
				String ChatconNombre = "Chat con " + usu2.get().getNombres();
				model.addAttribute("Chatfoto", usu2.get().getFoto1());
				model.addAttribute("ChatconNombre", ChatconNombre);
				model.addAttribute("ChatUserid", cod_usu_enviarmsj);
				// body
				model.addAttribute("lstMensajes", chat);
				model.addAttribute("cod_usu_now", CodUsuInSession);
				model.addAttribute("cod_usu_1_msj", CodUsuInSession);
				model.addAttribute(cod_usu_enviarmsj);
				model.addAttribute("foto1_msj ", usu2.get().getFoto1());
				// footer
				model.addAttribute("cod_usu_enviarmsj", cod_usu_enviarmsj);
				model.addAttribute("MSJdeleteMatchAndMsj", "");
			}
		}

		// enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "Chat/Chat";
	}

	@PostMapping("/BuscarAmistad/CancelarMatch")
	public String CancelarMatch(@RequestParam(name = "cod_usu_menu", required = false) int cod_usu_menu, Model model)
			throws ParseException {

		int CodUsuInSession = SeguridadController.CodUsuInSession;
		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		// cancelar match

		repoMatch.USP_ELIMINAR_MATCH_POR_USUARIO(CodUsuInSession, cod_usu_menu);
		Chat chat = new Chat();
		List<Match> match;
		
		match = repoMatch.USP_LISTAR_MATCH_POR_USUARIO(CodUsuInSession);
	
		
		
		if (match.isEmpty()) {
			model.addAttribute("msjNULLMatch", 0);
			model.addAttribute("msjNULLMensajes", 0);
		} else {
			model.addAttribute("msjNULLMatch", 1);
			model.addAttribute("msjNULLMensajes", 1);

			// enviar Match
			model.addAttribute("lstMatch", match);

			
				model.addAttribute("msjNULLMensajes", 0);
			
				//model.addAttribute("msjNULLMensajes", 1);
				model.addAttribute("lstMensajes", chat);
			
		}
		

		model.addAttribute("MSJdeleteMatchAndMsj", "¡Se ha eliminado el match y los mensajes!");

		
		
		// enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "Chat/Chat";
	}

	@PostMapping("/BuscarAmistad/VerPerfil")
	public String VerPerfil(@RequestParam(name = "cod_usu_menu", required = false) int cod_usu_menu, Model model)
			throws ParseException {

		///// REDIRECCIONAR A PAGINA VERPERFIL

		String nombresYedad = SeguridadController.nombresYedad;
		String foto1 = SeguridadController.foto1;
		int CodUsuInSession = SeguridadController.CodUsuInSession;

		// enviarle el usuario que inicio sesion
		model.addAttribute("nombresYedad", nombresYedad);
		model.addAttribute("f1", foto1);
		return "Chat/Chat";
	}*/
}
