package com.cursospring.springboot.form.app.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cursospring.springboot.form.app.models.domain.Usuario;
import com.cursospring.springboot.form.app.validation.UsuarioValidador;

@Controller
@RequestMapping("/app")
@SessionAttributes("usuario") // --> ampliar scope, para persistir lo que no pasa por el formulario (el
								// identificador por ejemplo)
public class FormController {

	@Autowired
	private UsuarioValidador validador;

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Salchichon");
		usuario.setIdentificador("A0123");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validador);
	}

	/*
	 * @PostMapping("/subir") public String procesar(Model model, @RequestParam
	 * String username, @RequestParam String password, @RequestParam String email) {
	 * Usuario usuario = new Usuario(); usuario.setUsername(username);
	 * usuario.setEmail(email); usuario.setPassword(password);
	 * model.addAttribute("titulo", "Resultado form: ");
	 * model.addAttribute("usuario",usuario); return "resultado"; }
	 */
	/*
	 * Version más limpia // Se puede indicar para recibir un objeto ya que el
	 * nombre de los parámetros // (username, password) en HTML es igual a las
	 * propiedades del objeto
	 */
	@PostMapping("/subir")
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		model.addAttribute("titulo", "Resultado form: ");
		if (result.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * result.getFieldErrors().forEach(err ->{ errores.put(err.getField(),
			 * "El campo: ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage(
			 * ))); }); model.addAttribute("error", errores); ESTO YA LO HACE THYMELEAF
			 * AUTOMATICAMENTE MAPEANDO LOS FIELDS EN EL FORM
			 */
			return "form";
		}
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
	}
}
