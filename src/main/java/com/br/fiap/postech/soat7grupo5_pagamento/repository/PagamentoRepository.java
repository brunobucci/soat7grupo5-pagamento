package com.br.fiap.postech.soat7grupo5_pagamento.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.br.fiap.postech.soat7grupo5_pagamento.entity.PagamentoEntity;

@Repository
public interface PagamentoRepository extends CrudRepository<PagamentoEntity, Integer>{

	PagamentoEntity findByIdPedidoAndIdPagamentoAndIdStatusPagamento(int idPedido, int idPagamento, int i);

}
