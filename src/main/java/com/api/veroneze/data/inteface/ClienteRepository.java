package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    @Query("SELECT MAX(c.id) FROM ClienteEntity c")
    Integer findLastId();
}
