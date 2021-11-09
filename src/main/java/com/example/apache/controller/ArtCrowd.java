package com.example.apache.controller;

import com.github.liaochong.myexcel.core.annotation.ExcelColumn;
import com.github.liaochong.myexcel.core.annotation.ExcelModel;
import lombok.Data;

/**
 * @author caogq
 * @create 2021/8/9 9:59
 */
@ExcelModel(sheetName = "艺术生")
@Data
public class ArtCrowd {

    @ExcelColumn(order = 0, title = "姓名")
    private String name;

    @ExcelColumn(order = 1, title = "年龄")
    private Integer age;
}
