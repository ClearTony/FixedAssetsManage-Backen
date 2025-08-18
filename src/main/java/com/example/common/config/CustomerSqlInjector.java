package com.example.common.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 添加Sql注入方法,支持空字段更新
 */
@Component
public class CustomerSqlInjector extends DefaultSqlInjector {
    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo){
        List<AbstractMethod> methodList=super.getMethodList(mapperClass, tableInfo);
        //注册自定义方法
        //注意：InsertList中的name需要与xxxMapper中的方法名一致，即insertList
        methodList.add(new InsertList("insertList"));

        return methodList;
    }
}
