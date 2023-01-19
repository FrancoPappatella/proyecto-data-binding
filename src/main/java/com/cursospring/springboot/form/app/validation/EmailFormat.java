package com.cursospring.springboot.form.app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EmailFormatValidador.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface EmailFormat {

	String message() default "El campo no tiene un formato de email";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
