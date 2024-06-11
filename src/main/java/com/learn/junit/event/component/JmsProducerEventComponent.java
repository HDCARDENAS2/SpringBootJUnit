package com.learn.junit.event.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import com.learn.junit.event.UserCreatedEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class JmsProducerEventComponent {

    private final JmsMessagingTemplate jmsMessagingTemplate;
    @Value("${queue.new.user}")
    private String newUserQueue;
    
    @EventListener
    public void createNewUserCRM(UserCreatedEvent userCreatedEvent) {
        log.info("Entering createNewUserCRM method with userCreatedEvent: {}", userCreatedEvent);
    	jmsMessagingTemplate.convertAndSend(newUserQueue, userCreatedEvent.getUser().getId());
    	log.info("Entering Exiting createNewUserCRM method");	
    }
}
