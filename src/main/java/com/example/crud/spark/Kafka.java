package com.example.crud.spark;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.functions;
import org.codehaus.jettison.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author: lcb
 * @Date: 2019  8/9/19  11:49 AM
 */
public class Kafka {
    public static void main(String[] args) throws IOException, InterruptedException {
        String line = "";
        String topic = "traffic_event";
        //配置kafka，初始化kafka
        String brokers = "localhost:9092";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
//        props.put("metadata.broker.list","localhost:2181");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        ProducerConfig kafkaConfig = new ProducerConfig(props);
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        KafkaProducer kafkaProducer = new KafkaProducer(props);
        //数据准备
        FileReader file = null;
        try {
            file = new FileReader("/home/lcb/Downloads/sample.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader data=new BufferedReader(file);
        //数据发送
        while ((line=data.readLine())!= null){
            System.out.println("line==="+line);
            producer.send(new ProducerRecord<>(topic,line));
            Thread.sleep(1000);
        }
        producer.close();
        data.close();
        file.close();
    }
}
