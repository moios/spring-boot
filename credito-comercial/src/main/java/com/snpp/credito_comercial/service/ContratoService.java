package com.snpp.credito_comercial.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpp.credito_comercial.domain.ContratoRequest;
import com.snpp.credito_comercial.domain.ContratoResponse;
import com.snpp.credito_comercial.entity.Contrato;
import com.snpp.credito_comercial.entity.Credito;
import com.snpp.credito_comercial.respository.ContratoRepository;
import com.snpp.credito_comercial.respository.CreditoRepository;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    @Autowired
    private CreditoRepository creditoRepository;

    public List<ContratoResponse> listar() {
        return contratoRepository.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public ContratoResponse buscarPorId(Long id) {
        Contrato contrato = contratoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado: " + id));
        return toResponse(contrato);
    }

    @Transactional
    public ContratoResponse crear(ContratoRequest request) {
        Credito credito = creditoRepository.buscarPorId(request.idCredito())
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + request.idCredito()));

        Contrato contrato = new Contrato();
        contrato.setCondiciones(request.condiciones());
        contrato.setFechaFirma(request.fechaFirma());
        contrato.setCredito(credito);

        return toResponse(contratoRepository.guardar(contrato));
    }

    public void eliminar(Long id) {
        contratoRepository.eliminar(id);
    }

    private ContratoResponse toResponse(Contrato c) {
        return new ContratoResponse(
                c.getId(), c.getCondiciones(), c.getFechaFirma(), c.getCredito().getId()
        );
    }
}