package com.hcmsouza.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmsouza.cursomc.domain.Cliente;
import com.hcmsouza.cursomc.repositories.ClienteRepository;
import com.hcmsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Cliente obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não econtrado - Id: " + id + " - Tipo: " + Cliente.class.getName());
		}
		return obj;
	}
	
	
}
