package com.example.apache.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author caogq
 * @create 2021/8/9 9:48
 */
@RestController
public class StudentController {


    @PostMapping("/export")
    public void te(HttpServletResponse response) throws IOException {

//        List<ArtCrowd> dataList = this.getDataList();
//        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class)
//                .build(dataList);
//        AttachmentExportUtil.export(workbook, "艺术生信息.txt", response);


//        List<ArtCrowd> dataList = this.getDataList();
//        Workbook workbook = DefaultExcelBuilder.of(ArtCrowd.class)
//                .build(dataList);
//        FileExportUtil.export(workbook, new File("D:\\down\\demo.xlsx"));

//        try (DefaultStreamExcelBuilder<ArtCrowd> streamExcelBuilder = DefaultStreamExcelBuilder
//                .of(ArtCrowd.class)
//                .threadPool(Executors.newFixedThreadPool(10))
//                .capacity(10_000)
//                .start()) {
//
//            Path zip = streamExcelBuilder.buildAsZip("test");
//            AttachmentExportUtil.export(zip, "finalName.zip", response);
//        }

//        ImportInfoFromText.writeToTxt("C:\\Users\\EDY\\Downloads","学生数据");


        //获取日志
        List<Student> list = new ArrayList<>();
        list.add(new Student().setId("100").setName("李白").setAddress("大唐"));
        list.add(new Student().setId("101").setName("杜甫").setAddress("大唐"));
        //拼接字符串
        StringBuilder text = new StringBuilder();
        for(Student log:list){
            text.append(log.getId());
            text.append("|");
            text.append(log.getName());
            text.append("|");
            text.append(log.getAddress());
            text.append("\r\n");    //换行字符
        }
        exportTxt(response,text.toString());
    }



    private List<ArtCrowd> getDataList() {
        List<ArtCrowd> dataList = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            ArtCrowd artCrowd = new ArtCrowd();
            artCrowd.setName("李四");
            artCrowd.setAge(18);

            dataList.add(artCrowd);
        }
        return dataList;
    }




    /** 导出txt文件
     * @author
     * @param	response
     * @param	text 导出的字符串
     * @return
     */
    public void exportTxt(HttpServletResponse response,String text) {
        response.setCharacterEncoding("utf-8");
        //设置响应的内容类型
        response.setContentType("text/plain");
        //设置文件的名称和格式
        response.addHeader("Content-Disposition", "attachment;filename="
//                + genAttachmentFileName( "文件名称", "JSON_FOR_UCC_")//设置名称格式，没有这个中文名称无法显示
                + "sss"
                + ".txt");
        BufferedOutputStream buff = null;
        ServletOutputStream outStr = null;
        try {
            outStr = response.getOutputStream();
            buff = new BufferedOutputStream(outStr);
            buff.write(text.getBytes("UTF-8"));
            buff.flush();
            buff.close();
        } catch (Exception e) {
            //LOGGER.error("导出文件文件出错:{}",e);
        } finally {
            try {
                buff.close();
                outStr.close();
            } catch (Exception e) {
                //LOGGER.error("关闭流对象出错 e:{}",e);
            }
        }
    }


    @GetMapping("/get")
    public Student g(){
        Student student = new Student().setId("1");
        return student;
    }

    @GetMapping("/getDate")
    public SSS gfsafsa(){
        SSS sss = new SSS();
        sss.setDate(LocalDate.now());
        sss.setDateTime(LocalDateTime.now());
        return sss;
    }

    @Data
    static public class SSS {
        private LocalDateTime dateTime;

        private LocalDate date;
    }
}
