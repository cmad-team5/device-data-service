package com.device.monitoring.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int messageId;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column
    private String message;

    @Column
    private String deviceId;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date messageTimestamp;

    public Message() {

    }

    @JsonCreator
    public Message(@JsonProperty("messageId") int messageId,
                   @JsonProperty("messageType") MessageType messageType,
                   @JsonProperty("message") String message,
                   @JsonProperty("deviceId") String deviceId,
                   @JsonProperty("messageTimestamp") Date messageTimestamp) {
        this.messageId = messageId;
        this.messageType = messageType;
        this.deviceId = deviceId;
        this.message = message;
        this.messageTimestamp = messageTimestamp;

    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int mesageId) {
        this.messageId = mesageId;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getMessageTimestamp() {
        return messageTimestamp;
    }

    public void setMessageTimestamp(Date messageTimestamp) {
        this.messageTimestamp = messageTimestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;
        Message message1 = (Message) o;
        return messageId == message1.messageId &&
                messageType == message1.messageType &&
                Objects.equals(message, message1.message) &&
                Objects.equals(deviceId, message1.deviceId) &&
                Objects.equals(messageTimestamp, message1.messageTimestamp);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, messageType, message, deviceId, messageTimestamp);
    }
}
