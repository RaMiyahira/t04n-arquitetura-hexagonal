package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoItemEventDTO;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PedidoEventDTOMapperTest {

    @Test
    void toBO_deveConverterEventoDePedidoParaDominio() {
        PedidoItemEventDTO primeiroItem = new PedidoItemEventDTO();
        primeiroItem.setSku(1);
        primeiroItem.setAmount(5);

        PedidoItemEventDTO segundoItem = new PedidoItemEventDTO();
        segundoItem.setSku(2);
        segundoItem.setAmount(3);

        PedidoEventDTO evento = new PedidoEventDTO();
        evento.setZipCode("80010000");
        evento.setCustomerId(1);
        evento.setOrderItems(Arrays.asList(primeiroItem, segundoItem));
        evento.setOrigin("SQS_QUEUE");
        evento.setOccurredAt("2024-05-20T14:30:00Z");

        PedidoBO pedidoBO = PedidoEventDTOMapper.toBO(evento);

        assertEquals("80010000", pedidoBO.getCep());
        assertEquals(1, pedidoBO.getPessoa().getId());
        assertEquals(2, pedidoBO.getItens().size());
        assertEquals(1, pedidoBO.getItens().get(0).getProduto().getId());
        assertEquals(5, pedidoBO.getItens().get(0).getQuantidade());
        assertEquals(2, pedidoBO.getItens().get(1).getProduto().getId());
        assertEquals(3, pedidoBO.getItens().get(1).getQuantidade());
    }
}
