package com.cursospring.springboot.form.app.services;

import java.util.List;

import com.cursospring.springboot.form.app.models.domain.Role;

public interface RoleService {

		public List<Role> listar();
		public Role obtenerPorId(Integer id);
}
