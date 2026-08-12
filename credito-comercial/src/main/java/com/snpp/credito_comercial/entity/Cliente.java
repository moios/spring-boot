package com.snpp.credito_comercial.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "clientes")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long id;
	private String nombre;
	private String documento;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Credito> creditos;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Pago> pagos;
	
	
	
}
