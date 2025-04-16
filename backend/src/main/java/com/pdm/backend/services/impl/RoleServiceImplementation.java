package com.pdm.backend.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.pdm.backend.models.Role;
import com.pdm.backend.repositoriess.RoleRepository;
import com.pdm.backend.services.RoleServices;

@Service
public class RoleServiceImplementation implements RoleServices {

    private RoleRepository roleRepository;

    public RoleServiceImplementation(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role saveRole(long role_id , Role role){
       role.setRoleID(role_id);
       return roleRepository.save(role);
    }

    @Override
    public Optional<Role> findOne(long role_id){
        return roleRepository.findById(role_id);
    }

    @Override
    public boolean isExist(long role_id){
            return roleRepository.existsById(role_id);
    }

    @Override
    public List<Role> findAll(){
         return StreamSupport.stream(roleRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }
    

    @Override
    public Role partialUpdated(long role_id , Role role){
           
        role.setRoleID(role_id);

        return roleRepository.findById(role_id).map(existingRole -> {
            Optional.ofNullable(role.getName()).ifPresent(existingRole::setName);
            return roleRepository.save(existingRole);
        }).orElseThrow(() -> new RuntimeException("Role does not exist"));
    }

    @Override
    public void delete(long role_id){
         roleRepository.deleteById(role_id);
    }
}
