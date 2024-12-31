package com.api.veroneze.data.inteface;

import com.api.veroneze.data.entity.EstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstoqueRepository extends JpaRepository<EstoqueEntity, Integer> {
}
