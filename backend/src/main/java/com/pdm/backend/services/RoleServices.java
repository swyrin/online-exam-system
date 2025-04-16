package com.pdm.backend.services;

import java.util.List;
import java.util.Optional;

import com.pdm.backend.models.Role;

public interface RoleServices {

  Role saveRole(long role_id , Role role);
  Optional<Role> findOne(long role_id);
  List<Role> findAll();
  boolean isExist(long role_id);
  Role partialUpdated(long role_id , Role role);
  void delete(long role_id);


}
