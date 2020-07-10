package com.howtodoinjava.springbootkafkaapp.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.howtodoinjava.springbootkafkaapp.model.User;

 
@Service
public class KafKaProducerService
{
    private static final Logger logger =
            LoggerFactory.getLogger(KafKaProducerService. class);
     
    //1. General topic with a string payload
     
    @Value(value = "${general.topic.name}")
    private String topicName;
     
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
 
    //2. Topic with user object payload
     
    @Value(value = "${user.topic.name}")
    private String userTopicName;
     
    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;
     
    public void sendMessage(String message)
    {
        ListenableFuture<SendResult<String, String>> future
            = this.kafkaTemplate.send(topicName, message);
         
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Sent message: " +  message
                        + " with offset: " + result.getRecordMetadata().offset());
            }
 
            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message : " +  message, ex);
            }
       });
    }
     
    public void saveCreateUserLog(User user)
    {
        ListenableFuture<SendResult<String, User>> future
            = this.userKafkaTemplate.send(userTopicName, user);
         
        future.addCallback(new ListenableFutureCallback<SendResult<String, User>>() {
            @Override
            public void onSuccess(SendResult<String, User> result) {
                logger.info("User created: "
                        + user + " with offset: " + + result.getRecordMetadata().offset());
            }
 
            @Override
            public void onFailure(Throwable ex) {
                logger.error("User created : " + user, ex);
            }
       });
    }
}
/*
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate; 
import org.springframework.stereotype.Service;

import com.howtodoinjava.springbootkafkaapp.common.AppConstants;
import com.howtodoinjava.springbootkafkaapp.model.User;

 
@Service
public class KafKaProducerService
{
    private static final Logger logger = 
            LoggerFactory.getLogger(KafKaProducerService. class);
     
    @Autowired
    // Quando estava usando apenas com o metodo da message
    //private KafkaTemplate<String, String> kafkaTemplate;
    // Quando passei a usar o metodo usando o objeto User
    private KafkaTemplate<String, Object> kafkaTemplate;
    
    public void sendMessage(String message) 
    {
        logger.info(String.format("Message sent -> %s", message));
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
    }
    
    public void saveCreateUserLog(User user) {
    	logger.info(String.format("Message sent -> %s", user));
        this.kafkaTemplate.send(AppConstants.TOPIC_NAME_USER_LOG, user);
    }
}
*/