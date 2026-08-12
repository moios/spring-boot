package com.snpp.credito_comercial.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpp.credito_comercial.domain.ProductoRequest;
import com.snpp.credito_comercial.domain.ProductoResponse;
import com.snpp.credito_comercial.entity.Producto;
import com.snpp.credito_comercial.respository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoResponse> listar() {
        return productoRepository.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ProductoResponse buscarPorId(Long id) {
        Producto producto = productoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + id));
        return toResponse(producto);
    }

    @Transactional
    public ProductoResponse crear(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.nombre());
        producto.setPrecio(request.precio());
        return toResponse(productoRepository.guardar(producto));
    }

    public void eliminar(Long id) {
        productoRepository.eliminar(id);
    }

    private ProductoResponse toResponse(Producto p) {
        return new ProductoResponse(p.getId(), p.getNombre(), p.getPrecio());
    }
}
