package com.device.monitoring.service;

import com.device.monitoring.model.Message;
import com.device.monitoring.model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;

@Component
public class DeviceSimulator {
    Logger log = LoggerFactory.getLogger(DeviceSimulator.class);
    @Value("${device.message.post.url}")
    private String messagePostURI;


    @Scheduled(cron="${device.message.cron}")
    public void addMessage() {
        log.info("Adding Message to the  DB");
        int i=0;
        RestTemplate template = new RestTemplate();
        Message message = template.postForObject(messagePostURI, prepareMessage(i), Message.class);
        log.info("Added message = {} to DB", message);
    }

    private Message prepareMessage(int i) {
        if(i%100 == 0){
            return prepareMessage(MessageType.ERROR);
        }else if(i%50 == 0) {
            return prepareMessage(MessageType.ALERT);
        }else if(i%10 == 0) {
            return prepareMessage(MessageType.WARNING);
        }else{
            return prepareMessage(MessageType.INFO);
        }
    }

    private Message prepareMessage(MessageType info) {
        Message m = new Message();
        m.setDeviceId("1234");
        if(MessageType.ERROR.equals(info)) {
            m.setMessage("Device Error");
        }else if(MessageType.ALERT.equals(info)) {
            m.setMessage("Device Alert");
        }else if(MessageType.INFO.equals(info)) {
            m.setMessage("Device Info");
        }else if(MessageType.WARNING.equals(info)) {
            m.setMessage("Device Warning");
        }
        m.setMessageTimestamp(Calendar.getInstance().getTime());
        m.setMessageType(MessageType.WARNING);
        return m;
    }
}
