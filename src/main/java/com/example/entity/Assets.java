package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 资产信息
 */
@Data
public class Assets {
    /** ID */
    @Alias("ID")
    @TableId(type = IdType.AUTO)
    private Integer id;
    /** 资产名称 */
    @Alias("资产名称")
    private String name;
    /** 资产分类 */
    @Alias("资产分类")
    private String category;
    @Alias("资产二级分类")
    private String secondCategory;
    /** 资产编号 */
    @Alias("资产编号")
    private String no;
    /** 资产图片 */
    @Alias("资产图片")
    private String img;
    /** 资产型号 */
    @Alias("资产型号")
    private String model;
    /** 数量 */
    @Alias("数量")
    private Integer num;
    /** 购置日期 */
    @Alias("购置日期")
    private String date;
    /** 初始价值 */
    @Alias("初始价值")
    private BigDecimal money;
    /** 折旧方法 */
    @Alias("折旧方法")
    private String depreciate;
    /** 使用部门ID */
    @Alias("使用部门ID")
    private String departmentId;
//    @Alias("使用部门名称")
//    private String departmentName;
    /** 责任人 */
    @Alias("责任人ID")
    private Integer staffId;
    @Alias("责任人名称")
    private String staffName;
    /** 存放地点 */
    @Alias("存放地点")
    private String location;
    /** 状态 */
    @Alias("状态")
    private String status;
    /** 备注 */
    @Alias("备注")
    private String comment;
    @Alias("操作人ID")
    private Integer operator;
}