package com.cursospring.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cursospring.springboot.form.app.models.domain.Usuario;

@Component
public class UsuarioValidador implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		/*
		 * Las validaciones de los atributos IDENTIFICADOR y APELLIDO se desarrollaron
		 * de manera independiente con anotaciones y clases validadoras propias
		 */

		/*
		 * Las validaciones de los atributos se desarrollaron de manera independiente
		 * con anotaciones y clases validadoras propias
		 * 
		 * ---------------------------- CÓDIGO ANTIGUO ----------------------------
		 * Usuario usuario = (Usuario) target; ValidationUtils.rejectIfEmpty(errors,
		 * "nombre", "NotEmpty.usuario.nombre");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username",
		 * "NotEmpty.usuario.username"); ValidationUtils.rejectIfEmpty(errors,
		 * "password", "NotEmpty.usuario.password");
		 * ValidationUtils.rejectIfEmpty(errors, "email", "NotEmpty.usuario.email");
		 * 
		 * if (!usuario.getEmail().contains("@")) { errors.rejectValue("email",
		 * "Email.usuario.email"); } ---------------------------- CÓDIGO ANTIGUO
		 * ----------------------------
		 */
	}

}
