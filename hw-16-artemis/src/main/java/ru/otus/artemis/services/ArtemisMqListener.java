package ru.otus.artemis.services;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.otus.artemis.config.ArtemisMqConfig;


@Service
@RequiredArgsConstructor
@Slf4j
public class ArtemisMqListener {

    private static void onTextMessage(TextMessage message) throws JMSException {
        String msg = message.getText();
        log.info("Received message: {}", msg);
    }

    @JmsListener(destination = "test_queue",
            containerFactory = ArtemisMqConfig.JMS_LISTENER_CONTAINER_FACTORY)
    public void onMessage(Message message) {

        try {
            onTextMessage((TextMessage) message);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
