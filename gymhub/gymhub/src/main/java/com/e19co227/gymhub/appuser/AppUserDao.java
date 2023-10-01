package com.e19co227.gymhub.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
@Transactional(readOnly = true)
public interface AppUserDao extends JpaRepository<AppUser,Integer> {

    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByUserId(Integer userId);

    @Modifying
    @Query("UPDATE AppUser a SET a.enabled = true WHERE a.email = :email")
    int enableAppUser(@Param("email") String email);


}
