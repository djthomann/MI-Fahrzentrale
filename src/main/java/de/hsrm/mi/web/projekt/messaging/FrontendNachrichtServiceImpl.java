package de.hsrm.mi.web.projekt.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class FrontendNachrichtServiceImpl implements FrontendNachrichtService {

    Logger logger = LoggerFactory.getLogger(FrontendNachrichtService.class);

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/topic")
    public void sendEvent(FrontendNachrichtEvent ev) {
        messagingTemplate.convertAndSend("/topic", ev);
        logger.info("Sending event: " + ev.toString());
    }

}
