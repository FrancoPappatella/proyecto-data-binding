package com.cursospring.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
		Usuario usuario = (Usuario) target;
		
		ValidationUtils.rejectIfEmpty(errors, "identificador", "NotEmpty.usuario.identificador");
		ValidationUtils.rejectIfEmpty(errors, "nombre", "NotEmpty.usuario.nombre");
		ValidationUtils.rejectIfEmpty(errors, "apellido", "NotEmpty.usuario.apellido");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty.usuario.username");
		ValidationUtils.rejectIfEmpty(errors, "password", "NotEmpty.usuario.password");
		ValidationUtils.rejectIfEmpty(errors, "email", "NotEmpty.usuario.email");
	
		
		if(!usuario.getIdentificador().matches("[0-9]{2}[.][0-9]{3}[.][0-9]{3}[-][a-zA-Z]{1}")) {
			errors.rejectValue("identificador", "Pattern.usuario.identificador");
		}
		if(!usuario.getEmail().contains("@")) {
			errors.rejectValue("email", "Email.usuario.email");
		}
	}

}
