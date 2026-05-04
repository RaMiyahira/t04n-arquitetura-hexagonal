package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.listener;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PedidoServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PedidoBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.dto.PedidoEventDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.messaging.pedido.mapper.PedidoEventDTOMapper;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "queue.order-events.enabled", havingValue = "true", matchIfMissing = true)
public class PedidoSqsAdapter {

    private static final Logger log = LoggerFactory.getLogger(PedidoSqsAdapter.class);

    private final PedidoServicePort pedidoServicePort;

    public PedidoSqsAdapter(PedidoServicePort pedidoServicePort) {
        this.pedidoServicePort = pedidoServicePort;
    }

    @SqsListener(value = "${queue.order-events}")
    public void receberMensagem(PedidoEventDTO evento) {
        try {
            log.info(
                    "Evento de pedido recebido via SQS. customerId={}, origin={}, occurredAt={}",
                    evento.getCustomerId(),
                    evento.getOrigin(),
                    evento.getOccurredAt()
            );

            final PedidoBO pedidoBO = PedidoEventDTOMapper.toBO(evento);
            final PedidoBO pedidoCriado = pedidoServicePort.criarPedido(pedidoBO);

            log.info(
                    "Pedido consumido com sucesso via SQS. pedidoId={}, customerId={}, total={}",
                    pedidoCriado.getId(),
                    evento.getCustomerId(),
                    pedidoCriado.getValorTotal()
            );
        } catch (Exception e) {
            log.error("Erro ao processar evento de pedido via SQS. customerId={}", evento.getCustomerId(), e);
            throw e;
        }
    }
}
