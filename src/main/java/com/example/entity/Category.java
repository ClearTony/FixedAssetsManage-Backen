package com.example.entity;


import java.math.BigDecimal;

/**
 * 资产分类
 */
public class Category {
    /** ID */
    private Integer id;
    /** 分类名称 */
    private String name;
    /** 使用年限 */
    private Integer durableYears;
    /** 净残值率 */
    private BigDecimal residualRate;
    /** 折旧方法 */
    private String depreciationMethod;

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

    public Integer getDurableYears() {
        return durableYears;
    }

    public void setDurableYears(Integer durableYears) {
        this.durableYears = durableYears;
    }

    public BigDecimal getResidualRate() {
        return residualRate;
    }

    public void setResidualRate(BigDecimal residualRate) {
        this.residualRate = residualRate;
    }

    public String getDepreciationMethod() {
        return depreciationMethod;
    }

    public void setDepreciationMethod(String depreciationMethod) {
        this.depreciationMethod = depreciationMethod;
    }
}