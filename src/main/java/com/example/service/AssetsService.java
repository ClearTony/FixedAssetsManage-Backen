package com.example.service;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.example.common.Result;
import com.example.dto.AssetsDto;
import com.example.dto.AssetsImportDto;
import com.example.entity.Account;
import com.example.entity.Assets;
import com.example.entity.Staff;
import com.example.mapper.AssetsMapper;
import com.example.mapper.DepartmentMapper;
import com.example.mapper.StaffMapper;
import com.example.utils.TokenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
    public void add(AssetsDto assetsDto) {
        Assets assets = new Assets();
        BeanUtils.copyProperties(assetsDto, assets);
        if (assetsDto.getDepartmentIds() != null) {
            List<String> departmentIds = assetsDto.getDepartmentIds();
            assets.setDepartmentId(String.join("/", departmentIds));
        }
        Account currentUser = TokenUtils.getCurrentUser();
        assets.setOperator(currentUser.getId());
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
    public void updateById(AssetsDto assetsDto) {
        Assets assets = assetsMapper.selectById(assetsDto.getId());
        BeanUtils.copyProperties(assetsDto, assets);
        if (assetsDto.getDepartmentIds() != null) {
            List<String> departmentIds = assetsDto.getDepartmentIds();
            assets.setDepartmentId(String.join("/", departmentIds));
        }
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
        Account currentUser = TokenUtils.getCurrentUser();
        assets.setOperator(currentUser.getId());
        return assetsMapper.selectAll(assets);
    }

    /**
     * 分页查询
     */
    public PageInfo<AssetsDto> selectPage(Assets assets, Integer pageNum, Integer pageSize) {
        Account currentUser = TokenUtils.getCurrentUser();
        assets.setOperator(currentUser.getId());
        PageHelper.startPage(pageNum, pageSize);
        List<AssetsDto> list = assetsMapper.selectAll(assets);
        list.forEach(assetsDto -> {
            if (assetsDto.getDepartmentId() != null) {
                List<String> departmentId = Arrays.asList(assetsDto.getDepartmentId().split("/"));
                assetsDto.setDepartmentIds(departmentId);
                if (!departmentId.isEmpty()) {
                    List<String> nameList =  departmentMapper.selectByIdList(departmentId);
                    assetsDto.setDepartmentNames(nameList);
                    assetsDto.setDepartmentName(String.join("/", nameList));
                }
            }
        });
        return PageInfo.of(list);
    }


    public int selectCountByCategory(String category) {
        return assetsMapper.selectCountByCategory(category);
    }

    public Result importData(MultipartFile file) {
        Account currentUser = TokenUtils.getCurrentUser();
        List<Assets> assetsList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream()) {
            EasyExcel.read(inputStream, AssetsImportDto.class, new PageReadListener<AssetsImportDto>(dataList -> {
                for (AssetsImportDto assetsImportDto : dataList) {
                    Assets assets = new Assets();
                    assets.setOperator(currentUser.getId());
                    BeanUtils.copyProperties(assetsImportDto, assets);
                    String departmentName = assetsImportDto.getDepartmentName();
                    if (departmentName!=null) {
                        List<String> departmentArr = Arrays.asList(departmentName.split("/"));
                        List<Integer> departmentIdList = departmentMapper.selectByName(departmentArr);
                        if (!departmentIdList.isEmpty()) {
                            String result = String.join("/", departmentIdList.stream().map(String::valueOf).toArray(String[]::new));
                            assets.setDepartmentId(result);
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