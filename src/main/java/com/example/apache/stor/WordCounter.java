package com.example.apache.stor;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author caogq
 * @description
 * @date 2021/10/27 17:45
 */
public class WordCounter implements IRichBolt {

     Integer id;

     String name;

     Map<String,Integer> counters;

     private OutputCollector collector;

    /**
     * 初始化
     */
    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counters = new HashMap<>();
        this.collector = outputCollector;
        this.name = topologyContext.getThisComponentId();
        this.id = topologyContext.getThisTaskId();
    }

    /**
     * 为每个单词计数
     */
    @Override
    public void execute(Tuple tuple) {
        String str = tuple.getString(0);
        // 如果单词尚不存在于map，我们就创建一个，如果已在，我们就为它加1
        if (!counters.containsKey(str)) {
            counters.put(str,1);
        }else {
            Integer c = counters.get(str) + 1;
            counters.put(str,c);
        }
        // 对元组作为应答
        collector.ack(tuple);
    }

    /**
     * 这个spout结束时（集群关闭的时候），我们会显示单词数量
     */
    @Override
    public void cleanup() {
        System.out.println("-- 单词数 【"+name+"-"+id+"】 --");
        for (Map.Entry<String, Integer> entry : counters.entrySet()) {
            System.out.println(entry.getKey()+": "+entry.getValue());
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
