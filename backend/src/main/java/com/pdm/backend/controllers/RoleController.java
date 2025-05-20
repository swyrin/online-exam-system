package com.pdm.backend.controllers;


import com.pdm.backend.mappers.Mapper;
import com.pdm.backend.models.Role;
import com.pdm.backend.models.dto.RoleDto;
import com.pdm.backend.services.RoleServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class RoleController {


    private final Mapper<Role, RoleDto> RoleMapper;
    private final RoleServices roleServices;

    public RoleController(Mapper<Role, RoleDto> RoleMapper, RoleServices roleServices) {
        this.RoleMapper = RoleMapper;
        this.roleServices = roleServices;
    }

    @PutMapping(path = "/roles/{role_id}")
    public ResponseEntity<RoleDto> saveRoleEntity(@PathVariable("role_id") long role_id, @RequestBody RoleDto roleDto) {

        Role role = RoleMapper.mapfrom(roleDto);
        boolean roleExisted = roleServices.isExist(role_id);
        Role savedRole = roleServices.saveRole(role_id, role);
        RoleDto savedRoleDto = RoleMapper.mapto(savedRole);

        if (roleExisted) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {

            return new ResponseEntity<>(savedRoleDto, HttpStatus.CREATED);
        }


    }

    @GetMapping(path = "/roles")
    public List<RoleDto> RoleList() {
        List<Role> roles = roleServices.findAll();
        return roles.stream().map(RoleMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path = "/roles/{role_id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("role_id") long role_id, RoleDto roleDto) {

        Optional<Role> ExistedRole = roleServices.findOne(role_id);
        if (ExistedRole.isPresent()) {

            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PatchMapping(path = "/roles/{role_id}")
    public ResponseEntity<RoleDto> partialUpdated(@PathVariable("role_id") long role_id, @RequestBody RoleDto roleDto) {


        Optional<Role> roleEntity = roleServices.findOne(role_id);
        if (!roleEntity.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleDto.setRoleID(role_id);
        Role role = RoleMapper.mapfrom(roleDto);
        Role savedRoleEntity = roleServices.saveRole(role_id, role);
        return new ResponseEntity<>(RoleMapper.mapto(savedRoleEntity), HttpStatus.OK);


    }

    @DeleteMapping(path = "/roles/{roles_id}")
    public ResponseEntity<RoleDto> delete(@PathVariable("roles_id") long roles_id) {

        roleServices.delete(roles_id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
