package com.snpp.credito_comercial.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snpp.credito_comercial.domain.ClienteRequest;
import com.snpp.credito_comercial.domain.ClienteResponse;
import com.snpp.credito_comercial.entity.Cliente;
import com.snpp.credito_comercial.respository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public List<ClienteResponse> listar() {
        return clienteRepository.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
	public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
        return toResponse(cliente);
    }

    @Transactional
    public ClienteResponse crear(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());
        cliente.setDocumento(request.documento());
        return toResponse(clienteRepository.guardar(cliente));
    }

    @Transactional
    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + id));
        cliente.setNombre(request.nombre());
        cliente.setDocumento(request.documento());
        return toResponse(clienteRepository.guardar(cliente));
    }

    public void eliminar(Long id) {
        clienteRepository.eliminar(id);
    }

	private ClienteResponse toResponse(Cliente c) {
        return new ClienteResponse(c.getId(), c.getNombre(), c.getDocumento());
    }
}
