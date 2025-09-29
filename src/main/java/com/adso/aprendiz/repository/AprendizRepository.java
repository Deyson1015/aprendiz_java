package com.adso.aprendiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.adso.aprendiz.entity.Aprendiz;


@Repository
public interface AprendizRepository extends JpaRepository<Aprendiz, Long> {
}
