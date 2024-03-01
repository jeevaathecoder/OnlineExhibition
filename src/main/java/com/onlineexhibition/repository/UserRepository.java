package com.onlineexhibition.repository;

import com.onlineexhibition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);


    @Query("SELECT u FROM User u WHERE u.status = :status AND u.user_type_id = :typeId")
    List<User> getBendingExhibitors(@Param("status") String status, @Param("typeId") Long userTypeId);

    @Query("SELECT u FROM User u WHERE u.status = :status AND u.user_type_id = :typeId")
    List<User> getApprovedExhibitors(@Param("status") String status, @Param("typeId") Long userTypeId);
}

