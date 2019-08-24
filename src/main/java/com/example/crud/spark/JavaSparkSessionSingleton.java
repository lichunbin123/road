package com.example.crud.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.SparkSession;

/**
 * @author: lcb
 * @Date: 2019  8/9/19  2:33 PM
 */
public class JavaSparkSessionSingleton {
    private static  transient SparkSession instance =null;
    public static SparkSession getInstance(SparkConf sparkConf){

        if(instance ==null){
            instance= SparkSession.builder().config(sparkConf).getOrCreate();
        }
        return  instance;
    }
}
