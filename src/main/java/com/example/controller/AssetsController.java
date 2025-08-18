package com.example.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.example.common.Result;
import com.example.common.enums.ResultCodeEnum;
import com.example.dto.AssetsDto;
import com.example.entity.Assets;
import com.example.service.AssetsService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * 资产信息前端操作接口
 **/
@RestController
@RequestMapping("/assets")
public class AssetsController {

    @Resource
    private AssetsService assetsService;
    private static final Logger logger = LoggerFactory.getLogger(AssetsController.class);

    /**
     * 新增
     */
    @PostMapping("/add")
    public Result add(@RequestBody Assets assets) {
        assetsService.add(assets);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable Integer id) {
        assetsService.deleteById(id);
        return Result.success();
    }

    /**
     * 批量删除
     */
    @DeleteMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        assetsService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public Result updateById(@RequestBody Assets assets) {
        assetsService.updateById(assets);
        return Result.success();
    }

    /**
     * 根据ID查询
     */
    @GetMapping("/selectById/{id}")
    public Result selectById(@PathVariable Integer id) {
        Assets assets = assetsService.selectById(id);
        return Result.success(assets);
    }

    /**
     * 查询所有
     */
    @GetMapping("/selectAll")
    public Result selectAll(Assets assets) {
        List<AssetsDto> list = assetsService.selectAll(assets);
        return Result.success(list);
    }

    /**
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(Assets assets,
                             @RequestParam(defaultValue = "1") Integer pageNum,
                             @RequestParam(defaultValue = "10") Integer pageSize) {
        PageInfo<AssetsDto> page = assetsService.selectPage(assets, pageNum, pageSize);
        return Result.success(page);
    }

    /**
     * 批量导出数据
     */
    @GetMapping("/export")
    public void exportData(HttpServletResponse response) throws IOException {
        ExcelWriter writer = ExcelUtil.getWriter(true);

        List<AssetsDto> list = assetsService.selectAll(null);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("资产信息表", "UTF-8") + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        writer.flush(outputStream, true);
        outputStream.flush();
        writer.close();
        outputStream.close();
    }

    /**
     * 批量导出数据
     */
    @PostMapping("/import")
    public Result importData(@RequestParam("file") MultipartFile file){
        if (file.isEmpty()) {
            logger.error("导入文件为空");
            return Result.error(ResultCodeEnum.valueOf("导入文件不能为空"));
        }
        try {
            return assetsService.importData(file);
        } catch (Exception e) {
            logger.error("文件导入失败", e);
            return Result.error(ResultCodeEnum.valueOf("文件导入失败: " + e.getMessage()));
        }
    }

    @GetMapping("/import/template")
    public void template(HttpServletResponse response) throws IOException {
        String templateFileName = "template.xlsx";
        ClassPathResource resource = new ClassPathResource("importFile/" + templateFileName);
        if (!resource.exists()) {
            throw new RuntimeException("模板文件不存在");
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        String encodedFileName = URLEncoder.encode(templateFileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
        try (InputStream is = resource.getInputStream();
             OutputStream os = response.getOutputStream()) {
            FileCopyUtils.copy(is, os);
        }
    }
}