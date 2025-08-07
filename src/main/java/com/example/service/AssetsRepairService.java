package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.example.common.enums.ResultCodeEnum;
import com.example.common.enums.RoleEnum;
import com.example.entity.Account;
import com.example.entity.AssetsRepair;
import com.example.exception.CustomException;
import com.example.mapper.AssetsRepairMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 资产报修业务处理
 **/
@Service
public class AssetsRepairService {

    @Resource
    private AssetsRepairMapper assetsRepairMapper;

    /**
     * 新增
     */
    public void add(AssetsRepair assetsRepair) {
        Account currentUser = TokenUtils.getCurrentUser();
        // 校验当前是否有同样的报修单
        AssetsRepair dbRepair = assetsRepairMapper.selectInProcessRepair(assetsRepair.getReceiveId());
        if (ObjectUtil.isNotNull(dbRepair)) {
            throw new CustomException(ResultCodeEnum.REPAIR_ERROR);
        }
        assetsRepair.setStaffId(currentUser.getId());
        assetsRepair.setStatus("待审核");
        assetsRepair.setDate(DateUtil.now());
        assetsRepairMapper.insert(assetsRepair);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        assetsRepairMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsRepairMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(AssetsRepair assetsRepair) {
        assetsRepairMapper.updateById(assetsRepair);
    }

    /**
     * 根据ID查询
     */
    public AssetsRepair selectById(Integer id) {
        return assetsRepairMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<AssetsRepair> selectAll(AssetsRepair assetsRepair) {
        return assetsRepairMapper.selectAll(assetsRepair);
    }

    /**
     * 分页查询
     */
    public PageInfo<AssetsRepair> selectPage(AssetsRepair assetsRepair, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        if(RoleEnum.STAFF.name().equals(currentUser.getRole())){
            assetsRepair.setStaffId(currentUser.getId());
        }
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsRepair> list = assetsRepairMapper.selectAll(assetsRepair);
        return PageInfo.of(list);
    }

}