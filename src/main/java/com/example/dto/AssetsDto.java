package com.example.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资产信息
 */
@Data
public class AssetsDto {
    /** ID */
    private Integer id;
    /** 资产名称 */
    private String name;
    /** 资产分类 */
    private String category;
    /** 资产二级分类 */
    private String secondCategory;
    /** 资产编号 */
    private String no;
    /** 资产图片 */
    private String img;
    /** 资产型号 */
    private String model;
    /** 数量 */
    private Integer num;
    /** 购置日期 */
    private String date;
    /** 初始价值 */
    private BigDecimal money;
    /** 折旧方法 */
    private String depreciate;
    /** 使用部门ID */
    private String departmentId;
    private String departmentName;
    /** 责任人 */
    private Integer staffId;
    private String staffName;
    /** 存放地点 */
    private String location;
    /** 状态 */
    private String status;
    /** 备注 */
    private String comment;
    private List<String> departmentIds;
    private List<String> departmentNames;
}