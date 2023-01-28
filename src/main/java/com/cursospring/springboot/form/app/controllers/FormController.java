package com.cursospring.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.cursospring.springboot.form.app.editors.NombreMayusculaEditor;
import com.cursospring.springboot.form.app.editors.PaisPropertyEditor;
import com.cursospring.springboot.form.app.editors.RoleEditor;
import com.cursospring.springboot.form.app.models.domain.Usuario;
import com.cursospring.springboot.form.app.services.PaisService;
import com.cursospring.springboot.form.app.services.RoleService;
import com.cursospring.springboot.form.app.models.domain.Pais;
import com.cursospring.springboot.form.app.models.domain.Role;

@Controller
@RequestMapping("/app")
@SessionAttributes("usuario") // --> ampliar scope, para persistir lo que no pasa por el formulario (el
								// identificador por ejemplo)
public class FormController {

	/*
	 * @Autowired private UsuarioValidador validador;
	 */

	@Autowired
	private RoleEditor roleEditor;

	@Autowired
	private PaisService paisService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PaisPropertyEditor paisEditor;

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		// Se settea el atributo habilitar en true por defecto
		usuario.setHabilitar(true);
		// Se settea el role de usuario por defecto
		usuario.setRoles(Arrays.asList(new Role(2, "Usuario", "ROLE_USER")));

		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	/*
	 * Esta porción de código se ejecutará cuando comiencen a poblarse los datos del
	 * formulario a la clase de doiminio
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// binder.addValidators(validador);

		/*
		 * Formateando el atributo fechaNacimiento al tipo Date Debido a que el dato
		 * viene como un tipo String del formulario
		 */
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		binder.registerCustomEditor(String.class, "nombre", new NombreMayusculaEditor());
		binder.registerCustomEditor(String.class, "apellido", new NombreMayusculaEditor());

		binder.registerCustomEditor(Pais.class, "pais", paisEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);

	}

	@PostMapping("/subir") // Versión más limpia
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Resultado form: ");
			return "form";
		}
		model.addAttribute("usuario", usuario);

		return "redirect:/app/ver";
	}

	/*
	 * Se hace uso del redirect para hacer uso de una nueva petición, para impedir
	 * que los datos enviados al formulario se envíen 2 veces al refrescar la vista de resultado
	 */

	@GetMapping("/ver")
	public String ver(@SessionAttribute(name="usuario", required = false) Usuario usuario,Model model, SessionStatus status) {
		if(usuario == null) {
			return "redirect:/app/form";
		}
		model.addAttribute("titulo", "Resultado form: ");

		status.setComplete();
		return "resultado";
	}

	@ModelAttribute("genero")
	public List<String> genero() {
		return Arrays.asList("Hombre", "Mujer");
	}

	/* Se agrega como atributo al Model, va a estar presente en todas las vistas */
	@ModelAttribute("paises")
	public List<Pais> listaPaises() {
		return this.paisService.listar();
	}

	@ModelAttribute("roles")
	public List<Role> listaRoles() {
		return this.roleService.listar();
	}
}
