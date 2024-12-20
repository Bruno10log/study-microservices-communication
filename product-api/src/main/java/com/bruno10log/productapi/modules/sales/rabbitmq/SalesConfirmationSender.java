package com.bruno10log.productapi.modules.sales.rabbitmq;

import com.bruno10log.productapi.modules.sales.dto.SalesConfirmationDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SalesConfirmationSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${app-config.rabbit.exchange.product}")
    private String productTopicExchange;

    @Value("${app-config.rabbit.routingKey.sales-confirmation}")
    private String salesConfirmationKey;

    public void sendSalesConfirmationMessage(SalesConfirmationDto message) {
       try {
           log.info("Sending message: {}", new ObjectMapper().writeValueAsString(message));
           rabbitTemplate.convertAndSend(productTopicExchange, salesConfirmationKey, message);
       } catch (JsonProcessingException e) {
           log.info("Error while trying to send sales confirmation message: {}", e);
           throw new RuntimeException(e);
       }
    }
}
