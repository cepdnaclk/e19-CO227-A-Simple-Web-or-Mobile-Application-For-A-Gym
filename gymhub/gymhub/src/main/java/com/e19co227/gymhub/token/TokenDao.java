package com.e19co227.gymhub.token;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenDao extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from Token t inner join AppUser u\s
      on t.appUser.userId = u.userId\s
      where u.userId = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String jwt);
}