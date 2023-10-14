package com.example.demo.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.model.RoleModel;

@Component
@Mapper(componentModel = "spring")
public interface RolesMapper extends IMapper<Role, RoleModel>{

	@Override
	RoleModel toDto(Role entity);
	
	@Override
	Role toEntity(RoleModel dto);
}
