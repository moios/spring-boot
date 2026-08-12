package com.snpp.credito_comercial.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.snpp.credito_comercial.crud.ProductoCrudRepository;
import com.snpp.credito_comercial.entity.Producto;

@Repository
public class ProductoRepository {

	@Autowired
	private ProductoCrudRepository productoCrud;
	
	public List<Producto> listarTodos(){
		return (List<Producto>) this.productoCrud.findAll();
	}
	
	public Optional<Producto> buscarPorId(Long id) {
		return (Optional<Producto>) this.productoCrud.findById(id);
	}
	
	public Producto guardar(Producto producto) {
		return this.productoCrud.save(producto);
	}
	
	public void eliminar(Long id) {
		this.productoCrud.deleteById(id);
	}
}
