package com.hcmsouza.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcmsouza.cursomc.domain.Pedido;
import com.hcmsouza.cursomc.repositories.PedidoRepository;
import com.hcmsouza.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);
		
		if (obj == null) {
			throw new ObjectNotFoundException("Objeto não econtrado - Id: " + id + " - Tipo: " + Pedido.class.getName());
		}
		return obj;
	}
}
