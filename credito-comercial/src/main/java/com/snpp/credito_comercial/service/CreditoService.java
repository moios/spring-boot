package com.snpp.credito_comercial.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpp.credito_comercial.domain.CreditoRequest;
import com.snpp.credito_comercial.domain.CreditoResponse;
import com.snpp.credito_comercial.entity.Cliente;
import com.snpp.credito_comercial.entity.Credito;
import com.snpp.credito_comercial.entity.Producto;
import com.snpp.credito_comercial.respository.ClienteRepository;
import com.snpp.credito_comercial.respository.CreditoRepository;
import com.snpp.credito_comercial.respository.ProductoRepository;


@Service
public class CreditoService {

    @Autowired
    private CreditoRepository creditoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<CreditoResponse> listar() {
        return creditoRepository.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public CreditoResponse buscarPorId(Long id) {
        Credito credito = creditoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + id));
        return toResponse(credito);
    }

    @Transactional
    public CreditoResponse crear(CreditoRequest request) {
        Cliente cliente = clienteRepository.buscarPorId(request.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + request.idCliente()));

        Credito credito = new Credito();
        credito.setMonto(request.monto());
        credito.setFecha(request.fecha());
        credito.setCliente(cliente);

        if (request.idsProductos() != null) {
            List<Producto> productos = request.idsProductos().stream()
                    .map(idProd -> productoRepository.buscarPorId(idProd)
                            .orElseThrow(() -> new RuntimeException("Producto no encontrado: " + idProd)))
                    .collect(Collectors.toList());
            credito.setProductos(productos);
        }

        return toResponse(creditoRepository.guardar(credito));
    }

    public void eliminar(Long id) {
        creditoRepository.eliminar(id);
    }

    private CreditoResponse toResponse(Credito c) {
        List<String> nombresProductos = c.getProductos() == null ? List.of() :
                c.getProductos().stream().map(Producto::getNombre).toList();
        return new CreditoResponse(
                c.getId(), c.getMonto(), c.getFecha(),
                c.getCliente().getNombre(), nombresProductos
        );
    }
 }
