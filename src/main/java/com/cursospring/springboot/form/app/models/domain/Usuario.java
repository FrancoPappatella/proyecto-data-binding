package com.cursospring.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

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

	@NotNull
	@Min(18)
	@Max(200)
	private Integer edad;

	@NotNull
	@Past
	// @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;

	@NotNull
	private Pais pais;

	@NotEmpty
	private List<Role> roles;

	private Boolean habilitar;
	
	@NotEmpty
	private String genero;

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

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getHabilitar() {
		return habilitar;
	}

	public void setHabilitar(Boolean habilitar) {
		this.habilitar = habilitar;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	
}
