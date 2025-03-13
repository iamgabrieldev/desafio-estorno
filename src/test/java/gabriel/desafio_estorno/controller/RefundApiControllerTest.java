package gabriel.desafio_estorno.controller;

import gabriel.desafio_estorno.dto.RefundRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RefundApiControllerTest {

    @Autowired
    private RefundController refundController;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testRequestRefund() {
        // Cria um objeto RefundRequest para simular o corpo da requisição
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setTransactionId("12345");
        refundRequest.setAmount(100.0);
        refundRequest.setReason("Canceled order");

        // Chama o método do controller
        String response = refundController.requestRefund(refundRequest);

        // Verifica se a resposta é a esperada
        assertEquals("Refund request received and queued for processing.", response);

        // Verifica se o método convertAndSend foi chamado corretamente
        verify(rabbitTemplate).convertAndSend("refund_requests", refundRequest);
    }
}