package com.device.monitoring.repository;

import com.device.monitoring.model.Message;
import com.device.monitoring.model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByMessageTimestampBeforeAndMessageTimestampAfterAndMessageTypeIsIn(Date to, Date From, List<MessageType> types);
}
