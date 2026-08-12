package com.snpp.credito_comercial.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "creditos")
public class Credito {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_credito")
	private Long id;
	private Double monto;
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@OneToOne(mappedBy = "credito", cascade = CascadeType.ALL)
	private Contrato contrato;

	@OneToMany(mappedBy = "credito", cascade = CascadeType.ALL)
	private List<Pago> pagos;
	
	@ManyToMany
	@JoinTable(name = "credito_producto",
	joinColumns = @JoinColumn(name = "id_credito"),
	inverseJoinColumns = @JoinColumn(name = "id_producto"))
	private List<Producto> productos;
	
}






















