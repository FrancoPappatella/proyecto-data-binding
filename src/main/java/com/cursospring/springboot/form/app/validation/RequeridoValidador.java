package com.cursospring.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	/*
	 * Valida que el atributo anotado con la notación creada "Requerido" no sea nulo
	 * ni que esté vacío o con espacios en blanco
	 */

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (value == null || !StringUtils.hasText(value)) {
			return false;
		}
		return true;
	}

}
