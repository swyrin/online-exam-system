package com.pdm.backend.mappers.imple;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Role;
import com.pdm.backend.models.dto.RoleDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class RoleMapperImple implements Mapper<Role, RoleDto> {

    private ModelMapper modelMapper;

    @Override
    public RoleDto mapto(Role role) {
        return modelMapper.map(role, RoleDto.class);
    }

    @Override
    public Role mapfrom(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }

}
