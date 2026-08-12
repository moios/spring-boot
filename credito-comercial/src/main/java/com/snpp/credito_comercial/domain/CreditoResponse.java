package com.snpp.credito_comercial.domain;

import java.time.LocalDate;
import java.util.List;

public record CreditoResponse(
    Long id,
    Double monto,
    LocalDate fecha,
    String nombreCliente,
    List<String> productos
) {}