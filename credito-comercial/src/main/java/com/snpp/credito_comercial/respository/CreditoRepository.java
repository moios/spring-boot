package com.snpp.credito_comercial.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snpp.credito_comercial.crud.CreditoCrudRepository;
import com.snpp.credito_comercial.entity.Credito;

@Repository
public class CreditoRepository {
	
	@Autowired
	private CreditoCrudRepository creditoCrud;
	
	public List<Credito> listarTodos(){
		return (List<Credito>) this.creditoCrud.findAll();
	}
	
	public Optional<Credito> buscarPorId(Long id) {
		return (Optional<Credito>) this.creditoCrud.findById(id);
	}
	
	public Credito guardar(Credito credito) {
		return this.creditoCrud.save(credito);
	}
	
	public void eliminar(Long id) {
		this.creditoCrud.deleteById(id);
	}

}
