package com.example.common.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import java.util.Collections;

public class InsertList extends AbstractMethod {

    public InsertList(String name) {
        super(name);
    }

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        KeyGenerator keyGenerator = new NoKeyGenerator();
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, getBatchInsertSql(tableInfo,modelClass), Collections.class);
        return this.addInsertMappedStatement(mapperClass,modelClass,"insertList",sqlSource,keyGenerator,null,null);

    }

    private String getBatchInsertSql(TableInfo tableInfo, Class<?> modelClass){
        String batchInsertSql="<script> INSERT INTO %s (%s) values %s</script>";

        //要插入的字段 即insert into table(要插入的字段) values
        StringBuilder insertColumnSql=new StringBuilder();
        insertColumnSql.append(tableInfo.getKeyColumn()).append(",");

        StringBuilder valueSql=new StringBuilder();
        valueSql.append("<foreach collection='list' item='item' open='(' separator='),(' close=')' >\n");
        valueSql.append("#{item."+tableInfo.getKeyProperty()+"},");

        tableInfo.getFieldList().forEach(x->{
            insertColumnSql.append(x.getColumn()).append(",");
            valueSql.append("#{item."+x.getProperty()+"},");
        });

        insertColumnSql.delete(insertColumnSql.length()-1,insertColumnSql.length());
        valueSql.delete(valueSql.length()-1,valueSql.length());
        valueSql.append("</foreach>");

        return  String.format(batchInsertSql,tableInfo.getTableName(),insertColumnSql,valueSql);
    }

}
