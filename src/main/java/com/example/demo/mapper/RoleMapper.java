package com.example.demo.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Role;
import com.example.demo.model.RoleModel;

@Component
@Mapper(componentModel = "spring")
public class RoleMapper implements IMapper<Role, RoleModel>{

	@Override
	public RoleModel toDto(Role entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role toEntity(RoleModel dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RoleModel> toDtos(List<Role> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> toEntities(List<RoleModel> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
