package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoProdutoBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.ProdutoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoItemEventDTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoEventDTOMapper {

    private PedidoEventDTOMapper() {
    }

    public static PedidoBO toBO(PedidoEventDTO evento) {
        final PedidoBO pedidoBO = new PedidoBO();
        final PessoaBO pessoaBO = new PessoaBO();

        pessoaBO.setId(evento.getCustomerId());
        pedidoBO.setPessoa(pessoaBO);
        pedidoBO.setCep(evento.getZipCode());
        pedidoBO.setItens(toItensBO(evento.getOrderItems()));

        return pedidoBO;
    }

    private static List<PedidoProdutoBO> toItensBO(List<PedidoItemEventDTO> itensEvento) {
        final List<PedidoProdutoBO> itensBO = new ArrayList<>();

        if (itensEvento == null) {
            return itensBO;
        }

        for (PedidoItemEventDTO itemEvento : itensEvento) {
            final ProdutoBO produtoBO = new ProdutoBO();
            produtoBO.setId(itemEvento.getSku());

            final PedidoProdutoBO itemBO = new PedidoProdutoBO();
            itemBO.setProduto(produtoBO);
            itemBO.setQuantidade(itemEvento.getAmount());

            itensBO.add(itemBO);
        }

        return itensBO;
    }
}
