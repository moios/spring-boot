package com.snpp.credito_comercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpp.credito_comercial.domain.CreditoRequest;
import com.snpp.credito_comercial.domain.CreditoResponse;
import com.snpp.credito_comercial.service.CreditoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/creditos")
@Tag(name = "Créditos", description = "Gestión de créditos financieros")
public class CreditoController {

    @Autowired
    private CreditoService creditoService;

    @GetMapping
    @Operation(summary = "Listar todos los créditos")
    public ResponseEntity<List<CreditoResponse>> listar() {
        return ResponseEntity.ok(creditoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un crédito por ID")
    public ResponseEntity<CreditoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(creditoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo crédito")
    public ResponseEntity<CreditoResponse> crear(@RequestBody CreditoRequest request) {
        return ResponseEntity.status(201).body(creditoService.crear(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un crédito")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        creditoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}