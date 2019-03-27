package com.learning.learning.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.learning.learning.dao.domain.Provincia;

public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    @Query(value = "select * from db.localidad where id_provincia = :prov", nativeQuery = true)
    List<Object[]> findByProvincia(@Param("prov") Long provincia);

}
