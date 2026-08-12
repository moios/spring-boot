package com.snpp.credito_comercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpp.credito_comercial.domain.ContratoRequest;
import com.snpp.credito_comercial.domain.ContratoResponse;
import com.snpp.credito_comercial.service.ContratoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/contratos")
@Tag(name = "Contratos", description = "Gestión de contratos de crédito")
public class ContratoController {

    @Autowired
    private ContratoService contratoService;

    @GetMapping
    @Operation(summary = "Listar todos los contratos")
    public ResponseEntity<List<ContratoResponse>> listar() {
        return ResponseEntity.ok(contratoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un contrato por ID")
    public ResponseEntity<ContratoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(contratoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo contrato")
    public ResponseEntity<ContratoResponse> crear(@RequestBody ContratoRequest request) {
        return ResponseEntity.status(201).body(contratoService.crear(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un contrato")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        contratoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}