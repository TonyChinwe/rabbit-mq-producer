package com.phisoft.rabbitmqproducer;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@RestController
@Slf4j
public class MessagePublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final Logger LOGGER= LoggerFactory.getLogger(MessagePublisher.class);

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage customMessage){
     customMessage.setMessageId(UUID.randomUUID().toString());
     customMessage.setMessageDate(new Date());
      rabbitTemplate.convertAndSend(MQConfig.EXCHANGE,MQConfig.ROUTING_KEY,customMessage);
      return "Message published";
}

}
