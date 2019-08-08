package com.example.crud.spark;

import com.example.crud.bean.TrafficData;
import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.*;

/**
 * @author: lcb
 * @Date: 2019  7/8/19  8:04 PM
 */
public class SparkStreaming_kafka_consumer implements Serializable{
    private TrafficData trafficData;
    private static GoEasy goEasy;
    public  static void main(String[] args) throws Exception{
        //配置spark
        SparkConf conf = new SparkConf().setAppName("myTraffic_consumer").setMaster("local");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaStreamingContext ssc = new JavaStreamingContext(sc, Durations.seconds(5));
        //检查点
//        ssc.checkpoint("test-consumer-group");
        //配置kafka
        String brokers = "localhost:9092";
        String topics = "traffic_event";
        Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", brokers);
        kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        kafkaParams.put("group.id","test-consumer-group");
        //创建consumer
        JavaInputDStream<ConsumerRecord<String, String>> messages =
                KafkaUtils.createDirectStream(
                        ssc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topicsSet, kafkaParams));

        //收集数据
        JavaDStream<String> words = messages.flatMap((FlatMapFunction<ConsumerRecord<String, String>, String>) s -> {
            List<String> list = new ArrayList<>();
            //获取到kafka的每条数据 进行操作
//            System.out.print("***************************" + s.value() + "***************************");
            list.add(s.value());
            return list.iterator();
        });
        //将数据转换成json字符串
        JavaDStream<JSONObject> jsons = words.flatMap(new FlatMapFunction<String, JSONObject>() {
            @Override
            public Iterator<JSONObject> call(String s) throws Exception {
//                System.out.println(s);
                List<JSONObject> list = new ArrayList<>();
                JSONObject data = JSONObject.fromObject(s);
//                System.out.println("收到的数据" + s);
                list.add(data);
                return list.iterator();
            }
        });

//        jsons.print();
       goEasy = new GoEasy("rest-hangzhou.goeasy.io","BC-1fd590eb9aef46a6809486626b4199a2");
        jsons.foreachRDD(rdd ->{
            rdd.foreach(x ->{
//                trafficData.setGCSJ(x.getString("GCSJ"));
//                trafficData.setGCZMC(x.getString("GCZMC"));
//                trafficData.setBQ1(x.getInt("BQ1"));
//                trafficData.setBQ2(x.getInt("BQ2"));
//                trafficData.setBQ3(x.getInt("BQ3"));
//                trafficData.setBQ4(x.getInt("BQ4"));
//                trafficData.setBQ5(x.getInt("BQ5"));
//                trafficData.setBQ6(x.getInt("BQ6"));
//                trafficData.setBQ7(x.getInt("BQ7"));
//                trafficData.setMTC(x.getInt("MTC"));
//                trafficData.setTLJ(x.getInt("TLJ"));
//                trafficData.setTotal(x.getDouble("total"));
                System.out.println("字符串x==="+x);
                System.out.println("String.valueof(x)==="+String.valueOf(x));
                goEasy.publish("trafficFlow", String.valueOf(x),new PublishListener(){
                    @Override
                    public void onSuccess() {
                        System.out.print("消息发布成功 ");
                    }
                    @Override
                    public void onFailed(GoEasyError error) {
                        System.out.print("消息发布失败 , 错误编码：" + error.getCode());
                    }
                });
            });
        });
        //打印数据
//        messages.foreachRDD(rdd -> {
//          rdd.foreach(x -> {
//              System.out.println("nihao");
//              System.out.println(x.value());
//          });
//        });
        //数据预处理

        //存储数据
        ssc.start();
        try {
            ssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
