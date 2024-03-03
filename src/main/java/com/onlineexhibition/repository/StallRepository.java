package com.onlineexhibition.repository;

import com.onlineexhibition.model.Stall;
import com.onlineexhibition.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StallRepository extends JpaRepository<Stall, Long> {

    @Query("SELECT s FROM Stall s WHERE s.user.id = :id")
    Stall findByRoleId(@Param("id") Long id);

}
