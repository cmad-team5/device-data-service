package com.device.monitoring.controller;

import com.device.monitoring.model.Message;
import com.device.monitoring.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class MessageController {
    private static final Logger log = LoggerFactory.getLogger(MessageController.class);

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/message")
    public ResponseEntity<Message> addMessage(@RequestBody Message message) {
        try {
            Message res = messageService.addMessage(message);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/message")
    public ResponseEntity<List<Message>> getMessages(@RequestParam MultiValueMap<String, String> filter) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss.SSS'Z'");
            Date toDate = sdf.parse(Optional.ofNullable(filter.getFirst("toDate"))
                    .orElse("2099-31-12T23:59:59.999Z"));

            Date fromDate = sdf.parse(Optional.ofNullable(filter.getFirst("fromDate"))
                    .orElse("2019-06-06T23:59:59.999Z"));

            List<String> messageTypes = Optional.ofNullable(filter.get("messageType"))
                    .orElse(Arrays.asList(new String[] {"INFO", "WARNING", "ERROR", "ALERT"}));

            List<Message> messages = messageService.getMessages(
                    toDate,
                    fromDate,
                    messageTypes
            );

            return ResponseEntity.ok(messages);
        } catch (Exception e) {
            log.error("Exception while trying to retrieve messages {e}", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/message/statistics")
    public ResponseEntity<Message> getMessageStatistics(@RequestParam Map<String, String> filter) {
        return null;
    }
}
