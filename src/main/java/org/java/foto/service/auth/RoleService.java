package org.java.foto.service.auth;

import java.util.List;
import java.util.Optional;

import org.java.foto.pojo.auth.Role;
import org.java.foto.repository.auth.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public List<Role> findAll(){
		return roleRepository.findAll();
	}
	
	public Optional<Role> findById(Integer id) {
		return roleRepository.findById(id);
	}
	
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}
