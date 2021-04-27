package com.lingnan.sas.client.controller;


import com.lingnan.sas.client.commonutil.R;
import com.lingnan.sas.client.service.UploadService;
import com.lingnan.sas.client.util.TencentCOS;
import com.lingnan.sas.core.entity.UploadLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 图片上传Controller
 */
@Controller
@RequestMapping("/api")
public class ImageUploadController {

    @Value("${tencent.path}")
    private String IMAGE_PATH;

    @Autowired
    private UploadService uploadService;


    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    @ResponseBody
    public R upload(@RequestParam("file") MultipartFile multipartFile, @RequestParam("username") String username, Model model) throws Exception {
        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0)
            return R.error().put("result", "上传图片格式不正确");

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if (!prefix.equalsIgnoreCase(".jpg") && !prefix.equalsIgnoreCase(".jpeg") && !prefix.equalsIgnoreCase(".svg") && !prefix.equalsIgnoreCase(".gif") && !prefix.equalsIgnoreCase(".png")) {
            return R.error().put("result", "上传图片格式不正确");
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String imageName = TencentCOS.uploadfile(excelFile, "avatar");

        //程序结束时，删除临时文件
        deleteFile(excelFile);

        //存入图片名称，用于网页显示
        model.addAttribute("imageName", imageName);

        //插入数据库记录
        //TODO
//        uploadService.insert(imageName, username);

        //返回成功信息
        return  R.error().put("result", "图片上传成功");
    }

    /**
     * 删除临时文件
     *
     * @param files 临时文件，可变参数
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
