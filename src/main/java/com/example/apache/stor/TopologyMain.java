package com.example.apache.stor;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 * @author caogq
 * @description
 * @date 2021/10/27 17:56
 */
public class TopologyMain {
    public static void main(String[] args) throws InterruptedException{
        // 定义拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader",new WorkReader());
        builder.setBolt("word-normalizer",new WordNormalizer()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter",new WordCounter(),2).fieldsGrouping("word-normalizer",new Fields("word"));


        // 配置
        Config config = new Config();
//        config.put("wordsFile",args[0]);
        config.put("wordsFile","words.txt");
        config.setDebug(false);

        // 运行拓扑
        config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING,1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie",config,builder.createTopology());
        Thread.sleep(2000);
        cluster.shutdown();
    }
}
