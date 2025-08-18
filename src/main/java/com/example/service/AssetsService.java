package com.example.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.common.Result;
import com.example.dto.AssetsDto;
import com.example.dto.AssetsImportDto;
import com.example.entity.Assets;
import com.example.entity.Staff;
import com.example.mapper.AssetsMapper;
import com.example.mapper.DepartmentMapper;
import com.example.mapper.StaffMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产信息业务处理
 **/
@Service
public class AssetsService {

    @Resource
    private AssetsMapper assetsMapper;
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private StaffMapper staffMapper;


    private static final Logger log = LoggerFactory.getLogger(AssetsService.class);

    /**
     * 新增
     */
    public void add(Assets assets) {
        assetsMapper.insert(assets);
    }

    /**
     * 删除
     */
    public void deleteById(Integer id) {
        assetsMapper.deleteById(id);
    }

    /**
     * 批量删除
     */
    public void deleteBatch(List<Integer> ids) {
        for (Integer id : ids) {
            assetsMapper.deleteById(id);
        }
    }

    /**
     * 修改
     */
    public void updateById(Assets assets) {
        assetsMapper.updateById(assets);
    }

    /**
     * 根据ID查询
     */
    public Assets selectById(Integer id) {
        return assetsMapper.selectById(id);
    }

    /**
     * 查询所有
     */
    public List<AssetsDto> selectAll(Assets assets) {
        return assetsMapper.selectAll(assets);
    }

    /**
     * 分页查询
     */
    public PageInfo<AssetsDto> selectPage(Assets assets, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsDto> list = assetsMapper.selectAll(assets);
        return PageInfo.of(list);
    }


    public int selectCountByCategory(String category) {
        return assetsMapper.selectCountByCategory(category);
    }

    public Result importData(MultipartFile file) {
        List<Assets> assetsList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            EasyExcel.read(inputStream, AssetsImportDto.class, new PageReadListener<AssetsImportDto>(dataList -> {
                for (AssetsImportDto assetsImportDto : dataList) {
                    Assets assets = new Assets();
                    BeanUtils.copyProperties(assetsImportDto, assets);
                    if (assetsImportDto.getDepartmentName()!=null) {
                        Integer departmentId = departmentMapper.selectByName(assetsImportDto.getDepartmentName());
                        assets.setDepartmentId(departmentId);
                    }
                    if (assetsImportDto.getStaffName()!=null) {
                        Staff staff = staffMapper.selectByUsername(assetsImportDto.getStaffName());
                        if (staff!=null) {
                            assets.setStaffId(staff.getId());
                        }
                    }
                    assetsList.add(assets);
                }
            })).sheet().doRead();
            assetsMapper.insertList(assetsList);
            return Result.success();
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.error();
        }
    }
}