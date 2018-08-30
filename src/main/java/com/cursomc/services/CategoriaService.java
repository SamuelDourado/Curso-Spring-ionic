package com.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.ropositories.CategoriaRepository;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository RCategoria;
	
	public Categoria busca(Integer id) {
		Optional<Categoria> obj = this.RCategoria.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
		id + "classe " + Categoria.class.getName()));
	}
	
}
