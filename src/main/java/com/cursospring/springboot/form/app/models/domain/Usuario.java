package com.cursospring.springboot.form.app.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.cursospring.springboot.form.app.validation.EmailFormat;
import com.cursospring.springboot.form.app.validation.IdentificadorRegex;
import com.cursospring.springboot.form.app.validation.Requerido;

public class Usuario {
	@IdentificadorRegex()
	@Requerido
	private String identificador;
	@Requerido
	private String nombre;
	@Requerido
	private String apellido;
	@Requerido
	private String username;
	@Requerido
	private String password;
	@EmailFormat
	@Requerido
	private String email;

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
