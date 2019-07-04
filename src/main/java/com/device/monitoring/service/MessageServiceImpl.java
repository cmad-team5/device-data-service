package com.device.monitoring.service;

import com.device.monitoring.exception.GenericException;
import com.device.monitoring.model.Message;
import com.device.monitoring.model.MessageStatistics;
import com.device.monitoring.model.MessageType;
import com.device.monitoring.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;


    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public List<Message> getMessages(Date to, Date from, List<String> messageTypes) throws GenericException {
        return messageRepository.findMessagesByMessageTimestampBeforeAndMessageTimestampAfterAndMessageTypeIsIn
                (
                        to,
                        from,
                        !CollectionUtils.isEmpty(messageTypes) ? messageTypes.stream()
                                .filter(m -> m != null)
                                .map(m -> MessageType.valueOf(m))
                                .collect(Collectors.toList()
                                ) : null
                );
    }

    @Override
    public Message addMessage(Message message) throws GenericException {
        try {
            return messageRepository.save(message);
        } catch (Exception e) {
            throw new GenericException();
        }
    }

    @Override
    public MessageStatistics getStatistics() {
        return null;
    }
}
