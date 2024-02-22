package com.onlineexhibition.repository;

import com.onlineexhibition.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.lang.Long;
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query("select ur from user_roles  ur WHERE ur.user_role = :userRole")
    UserRole findByUserRole(@Param("userRole") String userRole);
    @Query("select ur.user_role from user_roles  ur WHERE ur.id = :user_type_id")
    String getRoleById(@Param("user_type_id") Long user_type_id);

}
