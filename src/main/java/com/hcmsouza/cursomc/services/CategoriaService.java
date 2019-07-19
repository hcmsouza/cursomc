package com.hcmsouza.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmsouza.cursomc.domain.Categoria;
import com.hcmsouza.cursomc.repositories.CategoriaRepository;
import com.hcmsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Categoria obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não econtrado - Id: " + id + " - Tipo: " + Categoria.class.getName());
		}
		return obj;
	}
}
