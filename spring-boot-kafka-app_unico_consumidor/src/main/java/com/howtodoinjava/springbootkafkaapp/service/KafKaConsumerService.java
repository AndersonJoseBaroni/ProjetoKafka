package com.howtodoinjava.springbootkafkaapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.howtodoinjava.springbootkafkaapp.common.AppConstants;
import com.howtodoinjava.springbootkafkaapp.model.User;

 
@Service
public class KafKaConsumerService
{
    private final Logger logger = 
            LoggerFactory.getLogger(KafKaConsumerService. class);
 
    @KafkaListener(topics = AppConstants.TOPIC_NAME, 
            groupId = AppConstants.GROUP_ID)
    public void consume(String message) 
    {
        logger.info(String.format("Message recieved -> %s", message));
    }
    
//    @KafkaListener(topics = AppConstants.TOPIC_NAME_USER_LOG, 
//            groupId = AppConstants.GROUP_ID)
//    public void consume(User user) 
//    {
//        logger.info(String.format("User create -> %s", user));
//    }
}