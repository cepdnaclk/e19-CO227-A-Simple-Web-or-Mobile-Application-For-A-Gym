package com.e19co227.gymhub.appuser;

// Import necessary dependencies and classes
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

// Define a repository interface for accessing and managing 'AppUser' entities
@Repository
@Transactional(readOnly = true)
public interface AppUserDao extends JpaRepository<AppUser,Integer> {

    // Define methods for querying user information

    // Find a user by their email
    Optional<AppUser> findByEmail(String email);

    // Find a user by their user ID
    Optional<AppUser> findByUserId(Integer userId);

    // Define a method for enabling an app user by updating the 'enabled' field
    @Modifying
    @Query("UPDATE AppUser a SET a.enabled = true WHERE a.email = :email")
    void enableAppUser(@Param("email") String email);


}
