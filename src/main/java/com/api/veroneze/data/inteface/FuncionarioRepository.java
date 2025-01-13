package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity, Integer> {

    UserDetails findByLogin(String login);

    @Query("SELECT MAX(f.id) FROM FuncionarioEntity f")
    Integer findLastId();
}
