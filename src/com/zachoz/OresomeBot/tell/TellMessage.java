package com.zachoz.OresomeBot.tell;

import java.io.Serializable;

public class TellMessage implements Serializable {

    private String sender, recipient, message;
    private MessageType type;

    public TellMessage(String sender, String recipient, String message, MessageType type) {
        this.sender = sender;
        this.recipient = recipient;
        this.message= message;
        this.type = type;
    }

    public String getSender() {
        return this.sender;
    }

    public String getRecipient() {
        return this.recipient;
    }

    public String getMessage() {
        return this.message;
    }

    public MessageType getType() {
        return this.type;
    }

}
