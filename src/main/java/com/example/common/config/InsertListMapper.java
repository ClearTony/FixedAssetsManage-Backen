package com.example.common.config;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 通用Mapper接口,特殊方法，批量插入，支持批量插入的数据库都可以使用，例如mysql,h2等
 *
 * @param <T> 不能为空
 * @author
 */
public interface InsertListMapper<T> {
    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等
     * <p>
     * 注意：
     * 1.若实体类中设置了@TableId注解，则需要设置value值  如：@TableId(value = "id")
     * 2.若建表语句中设置了default, 需要在插入时手动设置默认值，否则存储的是null。
     * 如：delete_flag tinyint default 0 comment '删除标志 0-未删除 1-已删除'，这种需要在insert的时候设置实体类改字段值。setDeleteFlag(0)
     * <p>
     *
     * @param recordList
     * @return
     */
    int insertList(@Param("list") List<T> recordList);
}

