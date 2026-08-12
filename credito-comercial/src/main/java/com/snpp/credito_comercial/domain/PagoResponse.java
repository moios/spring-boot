package com.snpp.credito_comercial.domain;

import java.time.LocalDate;

public record PagoResponse(
	    Long id,
	    Double monto,
	    LocalDate fecha,
	    String nombreCliente,
	    Long idCredito
	) {}