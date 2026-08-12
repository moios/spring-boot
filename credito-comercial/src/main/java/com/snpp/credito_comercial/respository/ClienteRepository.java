package com.snpp.credito_comercial.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.snpp.credito_comercial.crud.ClienteCrudRepository;
import com.snpp.credito_comercial.entity.Cliente;


@Repository
public class ClienteRepository {
	@Autowired
	private ClienteCrudRepository clienteCrud;
	
	public List<Cliente> listarTodos(){
		return (List<Cliente>) this.clienteCrud.findAll();
	}
	
	public Optional<Cliente> buscarPorId(Long id) {
		return (Optional<Cliente>) this.clienteCrud.findById(id);
	}
	
	public Cliente guardar(Cliente cliente) {
		return this.clienteCrud.save(cliente);
	}
	
	public void eliminar(Long id) {
		this.clienteCrud.deleteById(id);
	}
	
	
}
