package ru.otus.artemis.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Service
public class ArtemisMqProducer {

    private final JmsTemplate jmsTemplate;
    private final AtomicInteger atomicInteger = new AtomicInteger();


    @Autowired
    public ArtemisMqProducer(@Qualifier("jmsTemplate") JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage() {
        try {
            log.info("\nStarting message send operation");
            String messageText = "test msg " + atomicInteger.incrementAndGet();
            jmsTemplate.convertAndSend("test_queue", messageText, message -> {
                message.setJMSExpiration(0);
                message.setJMSCorrelationID("1");
                log.info("Sending message: {}", messageText);
                return message;
            });

            log.info("\nMessage sent to test_queue");
        } catch (Throwable e) {
            log.error("\nError sending message", e);
        }
    }
}

