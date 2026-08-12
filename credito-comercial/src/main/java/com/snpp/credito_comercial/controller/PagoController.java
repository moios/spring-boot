package com.snpp.credito_comercial.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.snpp.credito_comercial.domain.PagoRequest;
import com.snpp.credito_comercial.domain.PagoResponse;
import com.snpp.credito_comercial.service.PagoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/pagos")
@Tag(name = "Pagos", description = "Gestión de pagos de créditos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    @Operation(summary = "Listar todos los pagos")
    public ResponseEntity<List<PagoResponse>> listar() {
        return ResponseEntity.ok(pagoService.listar());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar un pago por ID")
    public ResponseEntity<PagoResponse> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(pagoService.buscarPorId(id));
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo pago")
    public ResponseEntity<PagoResponse> crear(@RequestBody PagoRequest request) {
        return ResponseEntity.status(201).body(pagoService.crear(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un pago")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pagoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}