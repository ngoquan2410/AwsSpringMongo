package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Role;
import com.example.demo.model.RoleModel;
import com.example.demo.service.RoleService;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;
	
//	@Autowired(required = false)
//	private RolesMapper roleMapper;
	
	@PostMapping("/role/createRole")
	public ResponseEntity<RoleModel> createRole(@RequestBody RoleModel request){
		Role role = roleService.createRole(request);
		RoleModel roleModel = new RoleModel();
		roleModel.setId(role.getId());
		roleModel.setRole(role.getRole());
		return new ResponseEntity(roleModel, HttpStatus.OK);
	}
	
}
