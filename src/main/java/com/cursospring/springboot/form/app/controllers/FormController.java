package com.cursospring.springboot.form.app.controllers;

import java.text.SimpleDateFormat;
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
	 * @Autowired 
	 * private UsuarioValidador validador;
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

	/*
	 * @PostMapping("/subir") public String procesar(Model model, @RequestParam
	 * String username, @RequestParam String password, @RequestParam String email) {
	 * Usuario usuario = new Usuario(); usuario.setUsername(username);
	 * usuario.setEmail(email); usuario.setPassword(password);
	 * model.addAttribute("titulo", "Resultado form: ");
	 * model.addAttribute("usuario",usuario); return "resultado"; }
	 * 
	 * Version más limpia. Se puede indicar para recibir un objeto ya que el nombre
	 * de los parámetros (username, password) en HTML es igual a las propiedades del
	 * objeto
	 */

	@PostMapping("/subir") // Versión más limpia
	public String procesar(@Valid Usuario usuario, BindingResult result, Model model, SessionStatus status) {
		model.addAttribute("titulo", "Resultado form: ");
		if (result.hasErrors()) {
			return "form";
		}
		model.addAttribute("usuario", usuario);
		status.setComplete();
		return "resultado";
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
