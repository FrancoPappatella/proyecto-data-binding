package com.cursospring.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailFormatValidador implements ConstraintValidator<EmailFormat, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(!value.contains("@")) {
			return false;
		}
		
		return true;

	}

}
