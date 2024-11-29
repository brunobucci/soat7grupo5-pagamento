package com.br.fiap.postech.soat7grupo5_pagamento.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.br.fiap.postech.soat7grupo5_pagamento.dto.PagamentoDto;
import com.br.fiap.postech.soat7grupo5_pagamento.entity.PagamentoEntity;
import com.br.fiap.postech.soat7grupo5_pagamento.repository.PagamentoRepository;
import com.google.gson.Gson;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Value("${url.api.pedido}")
    String urlApiPedidos;
	
	@Value("${url.api.producao}")
    String urlApiProducao;
	
	public PagamentoDto registarPagamento(PagamentoDto pagamentoDto) {
		PagamentoEntity pagamentoEntity = new PagamentoEntity(pagamentoDto);
    	return pagamentoRepository.save(pagamentoEntity).toPagamentoDto();
    }

	public PagamentoDto atualizarStatusPagamento(PagamentoDto pagamentoDto) {
		PagamentoEntity pagamentoEntity = pagamentoRepository.findByIdPedidoAndIdPagamentoAndIdStatusPagamento(pagamentoDto.getIdPedido(), pagamentoDto.getIdPagamento(), 0);
		pagamentoRepository.save(pagamentoEntity).toPagamentoDto();
		
		atualizaStatusNoPedidoAPI(pagamentoDto);
		
		// Pagamento aprovado
		if(pagamentoDto.getIdStatusPagamento() == 1) {
			registraPedidoNaProducaoAPI(pagamentoDto);
		}
		
		return pagamentoEntity.toPagamentoDto();
	}
	
	private void registraPedidoNaProducaoAPI(PagamentoDto pagamentoDto) {
		try {
			Gson gson = new Gson();
			RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            String pagamentoDtoJson = gson.toJson(pagamentoDto);
            
            HttpEntity<String> request = new HttpEntity<>(pagamentoDtoJson, headers);

			restTemplate.postForObject(
					urlApiProducao + "/registrar",
                    request,
                    String.class
            );
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

	private void atualizaStatusNoPedidoAPI(PagamentoDto pagamentoDto) {
		try {
			RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(headers);

			restTemplate.exchange(
					urlApiPedidos + pagamentoDto.getIdPedido() + "/status-pagamento/" + pagamentoDto.getIdStatusPagamento(),
                    HttpMethod.PUT,
                    request,
                    String.class
            );
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}