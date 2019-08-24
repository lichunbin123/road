package com.example.crud.service.serviceImpl;

import com.example.crud.service.DataService;
import com.example.crud.spark.SparkStreaming_kafka_consumer;
import com.example.crud.spark.SparkStreaming_kafka_producer;
import com.example.crud.utils.ResponseMessage;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author: lcb
 * @Date: 2019  8/4/19  12:41 PM
 */
@Component
public class DataServiceImpl implements DataService {
    private SparkStreaming_kafka_producer sparkStreaming_kafka_producer;
    private SparkStreaming_kafka_consumer sparkStreaming_kafka_consumer;
//    @Autowired
//    public DataServiceImpl(SparkStreaming_kafka_producer sparkStreaming_kafka_producer,SparkStreaming_kafka_consumer sparkStreaming_kafka_consumer){
//        this.sparkStreaming_kafka_producer = sparkStreaming_kafka_producer;
//        this.sparkStreaming_kafka_consumer = sparkStreaming_kafka_consumer;
//    }
    @Override
    public ResponseMessage producer() {
        try {
//            sparkStreaming_kafka_producer.producer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("成功").build();
    }

    @Override
    public ResponseMessage consumer() {
        try {
//            sparkStreaming_kafka_consumer.consumer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseMessage.builder().successStatus(true).httpStatus(HttpStatus.OK).messageContent("成功").build();
    }
}
