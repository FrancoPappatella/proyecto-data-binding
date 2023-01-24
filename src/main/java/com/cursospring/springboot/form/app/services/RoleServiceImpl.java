package com.cursospring.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cursospring.springboot.form.app.models.domain.Role;

@Service 
public class RoleServiceImpl implements RoleService {
	
	private List<Role> roles;
	public RoleServiceImpl() {
		//Se usará una ArrayList para que el código sea legible 
		this.roles = new ArrayList<Role>();
		this.roles.add(new Role(1,"Administrador","ROLE_ADMIN"));
		this.roles.add(new Role(2,"Usuario","ROLE_USER"));
		this.roles.add(new Role(3,"Moderador","ROLE_MODERATOR"));
	}
	@Override
	public List<Role> listar() {
		return this.roles;
	}

	@Override
	public Role obtenerPorId(Integer id) {
		Role resultado = null;
		
		for(Role role : this.roles) {
			if(id == role.getId()) {
				resultado = role;
				break;
			}
		}
		return resultado;
	}

}
