package com.br.fiap.postech.soat7grupo5_pagamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.fiap.postech.soat7grupo5_pagamento.dto.PagamentoDto;
import com.br.fiap.postech.soat7grupo5_pagamento.service.PagamentoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Pagamentos", description = "Recursos relacionados aos pagamentos.")
@RequestMapping(path="pagamentos", produces=MediaType.APPLICATION_JSON_VALUE)
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping(path="/registrar")
	@Operation(summary = "Registrar solicitação de pagamento.")
	ResponseEntity<PagamentoDto> registrarSolicitacaoPagamento(@RequestBody PagamentoDto pagamentoDto) {
		return new ResponseEntity<>(pagamentoService.registarPagamento(pagamentoDto), HttpStatus.CREATED);
	}
	
	@PutMapping(path="/receberRetornoPagamento/{idPedido}/{idStatusPagamento}")
	@Operation(summary = "Receber retorno do processador de pagamento.")
	ResponseEntity<PagamentoDto> receberRetornoPagamento(@Parameter(description = "ID do pedido.", example = "1") @PathVariable int idPedido, @Parameter(description = "ID do status do pgto. do pedido (0: Pendente Pagamento, 1: Aprovado, 2: Reprovado).", example = "1") @PathVariable int idStatusPagamento) {
		PagamentoDto pagamentoDto = new PagamentoDto();
		pagamentoDto.setIdPedido(idPedido);
		pagamentoDto.setIdStatusPagamento(idStatusPagamento);
		return new ResponseEntity<>(pagamentoService.atualizarStatusPagamento(pagamentoDto), HttpStatus.OK);
	}
	
//	@PutMapping(path="{idPedido}/status/{idStatus}")
//	@Operation(summary = "Atualizar status de pagamento no pedido")
//	String atualizarStatusPagamentoNoPedido(@Parameter(description = "ID do pedido.", example = "1") @PathVariable int idPedido, @Parameter(description = "ID do status do pagamento (1: Solicitado, 2: Aguardando validação, 3: Pago, 4: Cancelado).", example = "1") @PathVariable int idStatus) {
//		return null;
//	}
	
}
