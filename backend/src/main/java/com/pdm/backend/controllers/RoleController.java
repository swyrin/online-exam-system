package com.pdm.backend.controllers;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pdm.backend.models.Role;
import com.pdm.backend.models.dto.RoleDto;
import com.pdm.backend.services.RoleServices;

import com.pdm.backend.mappers.Mapper;

@RestController
public class RoleController {
    

    private Mapper<Role , RoleDto> RoleMapper;
    private RoleServices roleServices;

    public RoleController(Mapper<Role , RoleDto> RoleMapper , RoleServices roleServices){
        this.RoleMapper = RoleMapper;
        this.roleServices = roleServices;
    }

    @PutMapping(path = "/roles/{role_id}")
    public ResponseEntity<RoleDto> saveRoleEntity(@PathVariable("role_id") long role_id ,@RequestBody RoleDto roleDto){
         
        Role role = RoleMapper.mapfrom(roleDto);
        boolean roleExisted = roleServices.isExist(role_id);
        if(roleExisted){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            Role savedRole = roleServices.saveRole(role_id, role);
            RoleDto savedRoleDto = RoleMapper.mapto(savedRole);
            return new ResponseEntity<>(savedRoleDto , HttpStatus.CREATED);
        }


    }

    @GetMapping(path = "/roles")
    public List<RoleDto> RoleList(@RequestBody RoleDto roleDto){
        List<Role> roles = roleServices.findAll();
        return roles.stream().map(RoleMapper::mapto).collect(Collectors.toList());
    }

    @GetMapping(path= "/roles/{role_id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable("role_id") long role_id , RoleDto roleDto){
        
        Optional<Role> ExistedRole = roleServices.findOne(role_id);
        if(ExistedRole.isPresent()){
            
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @PatchMapping(path = "/roles/{role_id}")
    public ResponseEntity<RoleDto> partialUpdated(@PathVariable("role_id") long role_id , @RequestBody RoleDto roleDto ){
        
        
        Optional<Role> roleEntity = roleServices.findOne(role_id);
        if(!roleEntity.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleDto.setRoleID(role_id);
        Role role = RoleMapper.mapfrom(roleDto);
        Role savedRoleEntity = roleServices.saveRole(role_id, role);
        return new ResponseEntity<>(RoleMapper.mapto(savedRoleEntity) , HttpStatus.OK);


    }

    @DeleteMapping(path = "/roles/{roles_id}")
    public ResponseEntity<RoleDto> delete(@PathVariable("roles_id") long roles_id){

       roleServices.delete(roles_id);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    } 
}
