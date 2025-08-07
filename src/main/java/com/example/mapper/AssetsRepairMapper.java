package com.example.mapper;

import com.example.entity.AssetsRepair;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 操作assets_repair相关数据接口
*/
public interface AssetsRepairMapper {

    /**
      * 新增
    */
    int insert(AssetsRepair assetsRepair);

    /**
      * 删除
    */
    int deleteById(Integer id);

    /**
      * 修改
    */
    int updateById(AssetsRepair assetsRepair);

    /**
      * 根据ID查询
    */
    AssetsRepair selectById(Integer id);

    /**
      * 查询所有
    */
    List<AssetsRepair> selectAll(AssetsRepair assetsRepair);

    @Select("select * from assets_repair where receive_id=#{receive_id} and status='待审核'or status='维修中'")
    AssetsRepair selectInProcessRepair(Integer receiveId);
}