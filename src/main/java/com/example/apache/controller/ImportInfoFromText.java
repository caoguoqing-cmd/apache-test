package com.example.apache.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caogq
 * @create 2021/8/9 10:43
 */
public class ImportInfoFromText {

    public static void writeToTxt(String path, String fileName) throws IOException {
        List<Student> stus = new ArrayList<>();
        stus.add(new Student().setId("100").setName("李白"));
        stus.add(new Student().setId("101").setName("杜甫"));

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(path + fileName, true), "UTF-8");
        try {
            out.write("编号  "+ "姓名");
            for (Student student : stus) {
                out.write(student.getId()+"  "+student.getName());
            }
        } catch (Exception e) {
            System.out.println("writeToTxt error");
        } finally {
            out.flush();
            out.close();
        }
    }
}
