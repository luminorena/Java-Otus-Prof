package ru.otus.artemis.config;


import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Session;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;


@Slf4j
@Configuration
public class ArtemisMqConfig {

    public static final String JMS_TEMPLATE = "jmsTemplate";
    public static final String JMS_LISTENER_CONTAINER_FACTORY = "artemisJmsListenerContainerFactory";

    private static final String ACTIVE_MQ_CONNECTION_FACTORY = "activeMQConnectionFactory";

    @Value("${spring.artemis.broker-url}")
    private String BROKER_URL;

    @Value("${spring.artemis.user}")
    private String userName;

    @Value("${spring.artemis.password}")
    private String password;

    @Bean(JMS_LISTENER_CONTAINER_FACTORY)
    public JmsListenerContainerFactory<?> jmsListenerContainerFactory(
            @Qualifier(ACTIVE_MQ_CONNECTION_FACTORY) ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("1");
        factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return factory;
    }


    @Bean(JMS_TEMPLATE)
    public JmsTemplate jmsTemplate(@Qualifier(ACTIVE_MQ_CONNECTION_FACTORY) ConnectionFactory connectionFactory) {
        return new JmsTemplate(connectionFactory);
    }


    @Bean(ACTIVE_MQ_CONNECTION_FACTORY)
    public ConnectionFactory connectionFactory() throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setUser(userName);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}


