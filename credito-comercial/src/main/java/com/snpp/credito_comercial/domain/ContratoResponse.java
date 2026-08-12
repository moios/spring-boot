package com.snpp.credito_comercial.domain;

import java.time.LocalDate;

public record ContratoResponse(
    Long id,
    String condiciones,
    LocalDate fechaFirma,
    Long idCredito
) {}