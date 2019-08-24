package com.example.crud.spark;

import io.goeasy.GoEasy;
import io.goeasy.publish.GoEasyError;
import io.goeasy.publish.PublishListener;
import net.sf.json.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.io.Serializable;
import java.util.*;

/**
 * @author: lcb
 * @Date: 2019  8/9/19  11:41 AM
 */
public class SparkStreaming implements Serializable {
        private static GoEasy goEasy;
        public static void main (String[]args) throws Exception {

            //配置spark
            SparkConf conf = new SparkConf().setAppName("myTraffic_consumer").setMaster("local");
            JavaSparkContext sc = new JavaSparkContext(conf);
            SQLContext sqlContext = new SQLContext(sc);
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
            kafkaParams.put("group.id", "test-consumer-group");
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
                System.out.print("***************************" + s.value() + "***************************");
                list.add(s.value());
                return list.iterator();
            });
//            words.foreachRDD(rdd-> {
//                SparkSession spark = JavaSparkSessionSingleton.getInstance(conf);
//
//                        JavaRDD<DataEntity> rowRDD = rdd.map(word -> {
//                            String firstData[] = word.split(",");
//                            String reg = "\"([^\"]*)\"";
//
//                            DataEntity dataEntity = new DataEntity();
//
////                            dataEntity.setId(Integer.parseInt(firstData[0].replaceAll(reg, "$1")));
//                            dataEntity.setYEAR(firstData[1].replaceAll(reg, "$1"));
//                            dataEntity.setGCSJ(firstData[2].replaceAll(reg, "$1"));
//                            dataEntity.setGCZBS(firstData[3].replaceAll(reg, "$1"));
//                            dataEntity.setGCZBH(firstData[4].replaceAll(reg, "$1"));
//                            dataEntity.setGCZMC(firstData[5].replaceAll(reg, "$1"));
//                            dataEntity.setGCZLX(firstData[6].replaceAll(reg, "$1"));
//                            dataEntity.setSBSFSBM(firstData[7].replaceAll(reg, "$1"));
//                            dataEntity.setGCZLX1(firstData[8].replaceAll(reg, "$1"));
//                            dataEntity.setCDH(firstData[9].replaceAll(reg, "$1"));
//                            dataEntity.setXSFX(firstData[10].replaceAll(reg, "$1"));
//                            dataEntity.setBQ1(firstData[11].replaceAll(reg, "$1"));
//                            dataEntity.setBQ2(firstData[12].replaceAll(reg, "$1"));
//                            dataEntity.setBQ3(firstData[13].replaceAll(reg, "$1"));
//                            dataEntity.setBQ4(firstData[14].replaceAll(reg, "$1"));
//                            dataEntity.setBQ5(firstData[15].replaceAll(reg, "$1"));
//                            dataEntity.setBQ6(firstData[16].replaceAll(reg, "$1"));
//                            dataEntity.setBQ7(firstData[17].replaceAll(reg, "$1"));
//                            dataEntity.setMTC(firstData[18].replaceAll(reg, "$1"));
//                            dataEntity.setTLJ(firstData[19].replaceAll(reg, "$1"));
//                            return dataEntity;
//                        });
//                  Dataset<Row> dataFrame = spark.createDataFrame(rowRDD, DataEntity.class);
//                  dataFrame.show();
//                    });
           words.foreachRDD(new VoidFunction<JavaRDD<String>>() {
               SparkSession spark = JavaSparkSessionSingleton.getInstance(conf);
                                @Override
                                public void call(JavaRDD<String> rdd) {
                                    JavaRDD<Row> rowRDD = rdd.map(new Function<String, Row>() {
                                        @Override
                                        public Row call(String msg) {
                                            Row row = RowFactory.create(msg);
                                            return row;
                                        }
                                    });
                                    //转换成dataset操作
                                    StructType schema = DataTypes.createStructType(new StructField[] {DataTypes.createStructField("Message", DataTypes.StringType, true)});
                                    Dataset<Row> msgDataFrame = spark.createDataFrame(rowRDD,schema);
                                    msgDataFrame.show();
                                    //对dataset进行处理
                                    //
                                }
                            });

//            goEasy = new GoEasy("rest-hangzhou.goeasy.io", "BC-1fd590eb9aef46a6809486626b4199a2");
//            words.foreachRDD(rdd -> {
//                rdd.foreach(x -> {
//                    goEasy.publish("trafficFlow", String.valueOf(x), new PublishListener() {
//                        @Override
//                        public void onSuccess() {
//                            System.out.print("消息发布成功 ");
//                        }
//
//                        @Override
//                        public void onFailed(GoEasyError error) {
//                            System.out.print("消息发布失败 , 错误编码：" + error.getCode());
//                        }
//                    });
//                });
//            });
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
