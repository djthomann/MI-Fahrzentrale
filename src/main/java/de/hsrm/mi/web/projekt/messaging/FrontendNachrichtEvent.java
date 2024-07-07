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

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "FrontendNachrichtEvent{" +
                "messageType=" + messageType +
                ", operation=" + operation +
                ", id=" + id +
                '}';
    }

}
