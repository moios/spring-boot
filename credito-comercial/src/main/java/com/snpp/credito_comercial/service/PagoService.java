package com.snpp.credito_comercial.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.snpp.credito_comercial.domain.PagoRequest;
import com.snpp.credito_comercial.domain.PagoResponse;
import com.snpp.credito_comercial.entity.Cliente;
import com.snpp.credito_comercial.entity.Credito;
import com.snpp.credito_comercial.entity.Pago;
import com.snpp.credito_comercial.respository.ClienteRepository;
import com.snpp.credito_comercial.respository.CreditoRepository;
import com.snpp.credito_comercial.respository.PagoRepository;


@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CreditoRepository creditoRepository;

    public List<PagoResponse> listar() {
        return pagoRepository.listarTodos().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public PagoResponse buscarPorId(Long id) {
        Pago pago = pagoRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado: " + id));
        return toResponse(pago);
    }

    @Transactional
    public PagoResponse crear(PagoRequest request) {
        Cliente cliente = clienteRepository.buscarPorId(request.idCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado: " + request.idCliente()));
        Credito credito = creditoRepository.buscarPorId(request.idCredito())
                .orElseThrow(() -> new RuntimeException("Crédito no encontrado: " + request.idCredito()));

        Pago pago = new Pago();
        pago.setMonto(request.monto());
        pago.setFecha(request.fecha());
        pago.setCliente(cliente);
        pago.setCredito(credito);

        return toResponse(pagoRepository.guardar(pago));
    }

    public void eliminar(Long id) {
        pagoRepository.eliminar(id);
    }

    private PagoResponse toResponse(Pago p) {
        return new PagoResponse(
                p.getId(), p.getMonto(), p.getFecha(),
                p.getCliente().getNombre(), p.getCredito().getId()
        );
    }
}
