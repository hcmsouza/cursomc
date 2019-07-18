package com.hcmsouza.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcmsouza.cursomc.domain.Produto;

@Repository
public interface ProdutoRespoitory extends JpaRepository<Produto, Integer>{

}
