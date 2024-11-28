package com.br.fiap.postech.soat7grupo5_pagamento.entity;

import com.br.fiap.postech.soat7grupo5_pagamento.dto.PagamentoDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pagamento")
public class PagamentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int idPedido;
	private int idPagamento;
	private int idStatusPagamento;

	public PagamentoEntity(PagamentoDto pagamentoDto) {
		this.id = pagamentoDto.getId();
		this.idPedido = pagamentoDto.getIdPedido();
		this.idPagamento = pagamentoDto.getIdPagamento();
		this.idStatusPagamento = pagamentoDto.getIdStatusPagamento();
	}

	public PagamentoDto toPagamentoDto() {
		return new PagamentoDto(this.id, this.idPedido, this.idPagamento, this.idStatusPagamento);
	}
	
}
