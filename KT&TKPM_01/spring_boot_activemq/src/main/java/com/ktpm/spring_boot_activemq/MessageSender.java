package com.ktpm.spring_boot_activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {
//    private static Logger logger = LogManager.getLogger(Listener.class);
    private JmsTemplate jmsTemplate;

    @Autowired
    public MessageSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendMessage(String name) {
        jmsTemplate.convertAndSend("demo", name);
    }
}