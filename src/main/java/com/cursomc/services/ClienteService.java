package com.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cliente;
import com.cursomc.dto.CategoriaDTO;
import com.cursomc.dto.ClienteDTO;
import com.cursomc.ropositories.ClienteRepository;
import com.cursomc.services.exceptions.DataIntegrityException;
import com.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository RCliente;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = this.RCliente.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
				id + "classe " + Cliente.class.getName()));
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = RCliente.findById(obj.getId()).orElseThrow(
				() -> new ObjectNotFoundException("Objeto não encontrado id = "  +
						obj.getId() + " classe " + Cliente.class.getName())		
		);
		updateData(newObj, obj);
		return RCliente.save(newObj);
	}
	
	public void delete(Integer id) {
		try {
			this.RCliente.deleteById(id);
		}
		catch (DataIntegrityException e) {
			throw new DataIntegrityException("Nao é possivel excluir uma cliente com referencias");
		}	
	}
	
	public List<Cliente> findAll(){
		return this.RCliente.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return this.RCliente.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}
	
	public void updateData(Cliente newObj, Cliente obj) {
		newObj.setId(obj.getId());
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}

}
