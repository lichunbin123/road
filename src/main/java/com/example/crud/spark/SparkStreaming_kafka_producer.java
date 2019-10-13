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
import org.springframework.stereotype.Component;

import java.util.*;
/**
 * @author: lcb
 * @Date: 2019  7/5/19  5:06 PM
 * 使用kafka和sparkStreaming实现实时处理数据
 */
public class SparkStreaming_kafka_producer {
    public static void main(String[] args) throws Exception{
        //配置kafka，初始化kafka
        String brokers = "localhost:9092";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        ProducerConfig kafkaConfig = new ProducerConfig(props);
        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        KafkaProducer kafkaProducer = new KafkaProducer(props);
        //配置spark
        SparkConf conf = new SparkConf().setAppName("myTraffic").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        //读取文件
        SQLContext sqlContext = new SQLContext(sc);
        Dataset<Row> df = sqlContext.read().format("csv").option("header","true").load("/home/lcb/Downloads/temp.csv");
        Dataset<Row> f=df.groupBy("GCSJ","GCZMC").agg(functions.sum(df.col("BQ1")),functions.sum(df.col("BQ2")),functions.sum(df.col("BQ3")),functions.sum(df.col("BQ4")),functions.sum(df.col("BQ5")),functions.sum(df.col("BQ6")),functions.sum(df.col("BQ7")),functions.sum(df.col("MTC")),functions.sum(df.col("TLJ"))).sort("GCSJ");
        JavaRDD<Row> rdd = f.toJavaRDD();
        List<Row> rows = rdd.collect();
        System.out.println("rows的第一行数据是"+rows.get(0).get(0));
        //数据发送
        for (Row row : rows){
            Double total = Double.valueOf(String.valueOf(row.get(2)))+Double.valueOf(String.valueOf(row.get(3)))+
                    Double.valueOf(String.valueOf(row.get(4)))+Double.valueOf(String.valueOf(row.get(5)))+
                    Double.valueOf(String.valueOf(row.get(6)))+Double.valueOf(String.valueOf(row.get(7)))+
                    Double.valueOf(String.valueOf(row.get(8)))+Double.valueOf(String.valueOf(row.get(9)))+
                    Double.valueOf(String.valueOf(row.get(10)));
            System.out.println("total"+total);
            //数据准备
            JSONObject event = new JSONObject();
            event.put("GCSJ",row.get(0)).put("GCZMC",row.get(1)).put("BQ1",row.get(2)).put("BQ2",row.get(3)).put("BQ3",row.get(4)).put("BQ4",row.get(5)).put("BQ5",row.get(6)).put("BQ6",row.get(7)).put("BQ7",row.get(8)).put("MTC",row.get(9)).put("TLJ",row.get(10)).put("total",total);
            //数据发送
            String topic = "traffic_event";
            producer.send(new ProducerRecord<>(topic,event.toString()));
            System.out.println("message"+event);
            Thread.sleep(5000);
        }
        producer.close();
    }
}
