package com.onlineexhibition.services;

import com.onlineexhibition.model.UserRole;
import jakarta.validation.constraints.NotNull;

public interface UserRoleService {

    UserRole createRole(@NotNull String userRole);

    UserRole get(String role);

    UserRole findRoleById(Long user_type_id);

}
