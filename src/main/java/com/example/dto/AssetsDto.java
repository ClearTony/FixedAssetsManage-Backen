package com.example.dto;

import java.math.BigDecimal;

/**
 * 资产信息
 */
public class AssetsDto {
    /** ID */
    private Integer id;
    /** 资产名称 */
    private String name;
    /** 资产分类 */
    private String category;
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
    private Integer departmentId;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDepreciate() {
        return depreciate;
    }

    public void setDepreciate(String depreciate) {
        this.depreciate = depreciate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}