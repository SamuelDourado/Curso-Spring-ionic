package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Pedido;
import com.cursomc.ropositories.PedidoRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository RPedido;
	
	public Pedido busca(Integer id) {
		Optional<Pedido> obj = this.RPedido.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
		id + "classe " + Pedido.class.getName()));
	}
	
}
