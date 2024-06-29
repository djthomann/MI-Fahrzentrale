package de.hsrm.mi.web.projekt.messaging;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FrontendNachrichtService {

    Logger logger = LoggerFactory.getLogger(FrontendNachrichtService.class);

    @MessageMapping("/topic")
    public void sendEvent(FrontendNachrichtEvent ev) {
        logger.info("Sending event" + ev);
    }

}
