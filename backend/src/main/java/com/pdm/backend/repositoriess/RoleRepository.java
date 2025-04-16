package com.pdm.backend.repositoriess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pdm.backend.models.Role;

@Repository
public interface RoleRepository  extends CrudRepository<Role , Long >{
      
    
}


