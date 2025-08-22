package com.example.dto;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AssetsExportDto {
    /**
     * 资产名称
     */
    @ExcelProperty(value = "资产名称")
    private String name;
    /**
     * 资产分类
     */
    @ExcelProperty(value = "资产分类")
    private String category;

    @ExcelProperty("资产二级分类")
    private String secondCategory;
    /**
     * 资产编号
     */
    @ExcelProperty("资产编号")
    private String no;

    /**
     * 资产型号
     */
    @ExcelProperty("资产型号")
    private String model;
    /**
     * 数量
     */
    @ExcelProperty("数量")
    private Integer num;
    /**
     * 购置日期
     */
    @ExcelProperty("购置日期")
    private String date;
    /**
     * 初始价值
     */
    @ExcelProperty("初始价值")
    private BigDecimal money;
    /**
     * 折旧方法
     */
    @ExcelProperty("折旧方法")
    private String depreciate;
    /**
     * 使用部门ID
     */
    @ExcelIgnore
    private String departmentId;

    @ExcelProperty("使用部门名称")
    private String departmentName;


    @ExcelProperty("责任人名称")
    private String staffName;
    /**
     * 存放地点
     */
    @ExcelProperty("存放地点")
    private String location;
    /**
     * 状态
     */
    @ExcelProperty("状态")
    private String status;
    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String comment;
}
