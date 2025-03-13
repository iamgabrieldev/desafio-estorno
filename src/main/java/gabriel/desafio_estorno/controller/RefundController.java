package gabriel.desafio_estorno.controller;

import gabriel.desafio_estorno.dto.RefundRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refund")
public class RefundController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping
    public String requestRefund(@RequestBody RefundRequest refundRequest) {
        rabbitTemplate.convertAndSend("refund_requests", refundRequest);
        return "Refund request received and queued for processing.";
    }
}