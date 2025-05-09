package com.pdm.backend.services;

import com.pdm.backend.models.Role;

import java.util.List;
import java.util.Optional;

public interface RoleServices {

    Role saveRole(long role_id, Role role);

    Optional<Role> findOne(long role_id);

    List<Role> findAll();

    boolean isExist(long role_id);

    Role partialUpdated(long role_id, Role role);

    void delete(long role_id);


}
