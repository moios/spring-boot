package com.snpp.credito_comercial.domain;

import java.time.LocalDate;

public record ContratoRequest(
    String condiciones,
    LocalDate fechaFirma,
    Long idCredito
) {}