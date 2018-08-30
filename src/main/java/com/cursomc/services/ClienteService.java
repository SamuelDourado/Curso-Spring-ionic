package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cliente;
import com.cursomc.ropositories.ClienteRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository RCliente;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = this.RCliente.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
				id + "classe " + Cliente.class.getName()));
	}

}
