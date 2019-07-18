package com.hcmsouza.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcmsouza.cursomc.domain.Categoria;

@Repository
public interface CategoriaRespoitory extends JpaRepository<Categoria, Integer>{

}
