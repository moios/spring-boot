package com.snpp.credito_comercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpp.credito_comercial.domain.ClienteRequest;
import com.snpp.credito_comercial.domain.ClienteResponse;
import com.snpp.credito_comercial.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gestión de clientes del sistema de crédito")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Listar todos los clientes")
    public ResponseEntity<List<ClienteResponse>> listar() {
        return ResponseEntity.ok(clienteService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un cliente por ID")
    public ResponseEntity<ClienteResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo cliente")
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest request) {
        return ResponseEntity.status(201).body(clienteService.crear(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de un cliente")
    public ResponseEntity<ClienteResponse> actualizar(@PathVariable Long id, @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        clienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}