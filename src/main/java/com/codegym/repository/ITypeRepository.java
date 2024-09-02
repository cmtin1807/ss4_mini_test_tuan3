package com.codegym.repository;

import com.codegym.model.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ITypeRepository extends JpaRepository<Type, Long> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "call deleteType(:id)")
    void deleteType(@Param("id") Long id);

}

