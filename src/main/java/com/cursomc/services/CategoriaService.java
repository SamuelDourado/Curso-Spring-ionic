package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.ropositories.CategoriaRepository;
import com.cursomc.services.exceptions.DataIntegrityException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository RCategoria;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = this.RCategoria.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
		id + "classe " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return RCategoria.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		this.find(obj.getId());
		return RCategoria.save(obj);
	}
	
	public void delete(Integer id) {
		try {
			this.RCategoria.deleteById(id);
		}
		catch (DataIntegrityException e) {
			throw new DataIntegrityException("Nao é possivel excluir uma categoria referenciada por produtos ");
		}	
	}
	
	public List<Categoria> findAll(){
		return this.RCategoria.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.RCategoria.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
	
}
