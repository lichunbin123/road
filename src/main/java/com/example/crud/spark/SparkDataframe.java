package com.example.crud.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author: lcb
 * @Date: 2019  7/5/19  5:03 PM
 * 基于spark的dataframe对数据进行的离线批量处理
 */
public class SparkDataframe {
    public static void main(String[] args){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("TrafficFlow-analysis")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        SQLContext sqlContext = new SQLContext(sc);
        System.out.println("hello");
        Dataset<Row> df = sqlContext.read().format("csv").option("header","true").load("/home/lcb/Downloads/2016_.csv");
        System.out.println("nihao");
        Dataset<Row> newdf = df.select(df.col("GCSJ"),df.col("GCZMC"),df.col("BQ1"),df.col("BQ2"),df.col("BQ3"),df.col("BQ4"),df.col("BQ5"),df.col("BQ6"),df.col("BQ7"),df.col("MTC"),df.col("TLJ"), functions.concat(df.col("GCSJ"), df.col("GCZMC")).as("ID"));
        Dataset<Row> f=newdf.groupBy("GCSJ","GCZMC").agg(functions.sum(newdf.col("BQ1")),functions.sum(newdf.col("BQ2")),functions.sum(newdf.col("BQ3")),functions.sum(newdf.col("BQ4")),functions.sum(newdf.col("BQ5")),functions.sum(newdf.col("BQ6")),functions.sum(newdf.col("BQ7")),functions.sum(newdf.col("MTC")),functions.sum(newdf.col("TLJ"))).sort("GCSJ");
        //数据库内容
        String url = "jdbc:mysql://127.0.0.1:3306/sparkdata?characterEncoding=utf8";
        Properties connectionProperties = new Properties();
        connectionProperties.put("user","root");
        connectionProperties.put("password","123456");
        connectionProperties.put("driver","com.mysql.jdbc.Driver");

        f.write().mode(SaveMode.Append).jdbc(url,"Info",connectionProperties);
        f.show();
        sc.close();
    }
}
