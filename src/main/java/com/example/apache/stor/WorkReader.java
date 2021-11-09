package com.example.apache.stor;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

/**
 * @author caogq
 * @description
 * @date 2021/10/27 17:11
 */
public class WorkReader implements IRichSpout {

    private SpoutOutputCollector collector;

    private FileReader fileReader;

    private boolean completed = false;

    private TopologyContext context;

    public boolean isDistributed() {
        return false;
    }

    /**
     * 我们将创建一个文件并维持一个collector对象
     */
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        try {
            context = topologyContext;
            fileReader = new FileReader(map.get("wordsFile").toString());
        }catch (FileNotFoundException e) {
            throw new RuntimeException("Error reading file {"+map.get("wordFile") + "}");
        }
        collector = spoutOutputCollector;
    }

    @Override
    public void close() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void deactivate() {

    }

    /**
     * 这个方法做的惟一一件事情就是分发文件中的文本行
     */
    @Override
    public void nextTuple() {
        // 这个方法会不断的被调用，直到整个文件都读完了，我们将等待并返回。
        if (completed) {
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                //
            }
            return;
        }
        String str;
        // 创建reader
        BufferedReader reader = new BufferedReader(fileReader);
        try {
            // 读所有文本行
            while ((str = reader.readLine()) != null) {
                // 按行发布一个新值
                Values values = new Values(str);
                collector.emit(values,str);
            }
        }catch (Exception e) {
            throw new RuntimeException("Error reading tuple",e);
        }finally {
            completed = true;
        }

    }

    @Override
    public void ack(Object msgId) {
        System.out.println("OK:" + msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("FAIL:" + msgId);
    }

    /**
     * 声明输入域"word"
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("line"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
