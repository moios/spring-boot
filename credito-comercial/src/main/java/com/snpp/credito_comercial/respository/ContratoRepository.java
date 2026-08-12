package com.snpp.credito_comercial.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snpp.credito_comercial.crud.ClienteCrudRepository;
import com.snpp.credito_comercial.crud.ContratoCrudRepository;
import com.snpp.credito_comercial.entity.Cliente;
import com.snpp.credito_comercial.entity.Contrato;

@Repository
public class ContratoRepository {

	@Autowired
	private ContratoCrudRepository contratoCrud;
	
	public List<Contrato> listarTodos(){
		return (List<Contrato>) this.contratoCrud.findAll();
	}
	
	public Optional<Contrato> buscarPorId(Long id) {
		return (Optional<Contrato>) this.contratoCrud.findById(id);
	}
	
	public Contrato guardar(Contrato contrato) {
		return this.contratoCrud.save(contrato);
	}
	
	public void eliminar(Long id) {
		this.contratoCrud.deleteById(id);
	}
}
