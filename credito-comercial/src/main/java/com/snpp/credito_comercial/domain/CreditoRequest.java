package com.snpp.credito_comercial.domain;

import java.time.LocalDate;
import java.util.List;

public record CreditoRequest(
    Double monto,
    LocalDate fecha,
    Long idCliente,
    List<Long> idsProductos
) {}