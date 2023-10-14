package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.mapper.RolesMapper;
import com.example.demo.model.RoleModel;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired(required = false)
	private RolesMapper roleMapper;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public Role createRole(RoleModel request) {
		Role role = new Role();
		role.setRole(request.getRole());
//		role.setId(id);
		return roleRepository.save(roleMapper.toEntity(request));
	}
}
