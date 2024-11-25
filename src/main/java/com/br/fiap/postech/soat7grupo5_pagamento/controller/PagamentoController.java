package com.br.fiap.postech.soat7grupo5_pagamento.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pagamentos", description = "Recursos relacionados aos pagamentos.")
@RequestMapping(path="pagamentos", produces=MediaType.APPLICATION_JSON_VALUE)
public class PagamentoController {

	@PostMapping(path="/registrar")
	@Operation(summary = "Registrar solicitação de pagamento.")
	String registrarSolicitacaoPagamento() {
		return null;
	}
	
	@PostMapping(path="/receberRetornoPagamento")
	@Operation(summary = "Receber retorno do processador de pagamento.")
	String receberRetornoPagamento() {
		return null;
	}
	
	@PutMapping(path="{idPedido}/status/{idStatus}")
	@Operation(summary = "Atualizar status de pagamento no pedido")
	String atualizarStatusPagamentoNoPedido(@Parameter(description = "ID do pedido.", example = "1") @PathVariable int idPedido, @Parameter(description = "ID do status do pagamento (1: Solicitado, 2: Aguardando validação, 3: Pago, 4: Cancelado).", example = "1") @PathVariable int idStatus) {
		return null;
	}
	
}
