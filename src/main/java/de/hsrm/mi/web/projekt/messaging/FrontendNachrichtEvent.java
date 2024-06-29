package de.hsrm.mi.web.projekt.messaging;

public class FrontendNachrichtEvent {
    MessageType messageType;
    Operation operation;
    long id;

    public FrontendNachrichtEvent(MessageType messageType, Operation operation, long id) {
        this.messageType = messageType;
        this.operation = operation;
        this.id = id;
    }

}
