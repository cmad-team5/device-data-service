package com.device.monitoring.service;

import com.device.monitoring.exception.GenericException;
import com.device.monitoring.model.Message;
import com.device.monitoring.model.MessageStatistics;

import java.util.Date;
import java.util.List;

public interface MessageService {
    List<Message> getMessages(Date to, Date from, List<String> messageTypes) throws GenericException;
    Message addMessage(Message message) throws GenericException;
    MessageStatistics getStatistics();
}
