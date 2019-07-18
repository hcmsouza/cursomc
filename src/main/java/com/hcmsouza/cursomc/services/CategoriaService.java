package com.hcmsouza.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmsouza.cursomc.domain.Categoria;
import com.hcmsouza.cursomc.repositories.CategoriaRespoitory;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRespoitory repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		return obj;
	}
}
