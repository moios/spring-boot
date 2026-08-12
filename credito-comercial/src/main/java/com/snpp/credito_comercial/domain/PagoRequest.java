package com.snpp.credito_comercial.domain;

import java.time.LocalDate;

public record PagoRequest(
    Double monto,
    LocalDate fecha,
    Long idCliente,
    Long idCredito
) {}