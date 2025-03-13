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
        RefundRequest refundRequest = new RefundRequest();
        refundRequest.setTransactionId("12345");
        refundRequest.setAmount(100.0);
        refundRequest.setReason("Canceled order");

        String response = refundController.requestRefund(refundRequest);

        assertEquals("Refund request received and queued for processing.", response);

        verify(rabbitTemplate).convertAndSend("refund_requests", refundRequest);
    }
}