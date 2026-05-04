package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoItemEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventDTOMapper;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.PedidoJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = "queue.order-events.enabled=false")
class PedidoEventIntegrationTest {

    @Autowired
    private PedidoServicePort pedidoServicePort;

    @Autowired
    private PedidoJpaRepository pedidoJpaRepository;

    @Test
    void devePersistirPedidoRecebidoComoEvento() {
        long totalAntes = pedidoJpaRepository.count();
        PedidoEventDTO evento = criarEvento();

        PedidoBO pedidoCriado = pedidoServicePort.criarPedido(PedidoEventDTOMapper.toBO(evento));

        assertNotNull(pedidoCriado.getId());
        assertEquals("80010000", pedidoCriado.getCep());
        assertEquals(110.0, pedidoCriado.getValorTotal(), 0.0001);
        assertEquals(totalAntes + 1, pedidoJpaRepository.count());
    }

    private PedidoEventDTO criarEvento() {
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
        return evento;
    }
}
