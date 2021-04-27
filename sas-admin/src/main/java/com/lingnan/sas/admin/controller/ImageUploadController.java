package com.lingnan.sas.admin.controller;
import com.baomidou.mybatisplus.extension.api.R;
import com.lingnan.sas.core.util.CommonResponse;
import com.lingnan.sas.core.util.TencentCOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 图片上传Controller
 */
@Controller
@RequestMapping("/api")
public class ImageUploadController {

    @Value("${tencent.path}")
    private String IMAGE_PATH;

    @Value("${tencent.qianzui}")
    private String qianzui;


    /**
     * 上传图片
     */
    @RequestMapping("/upload")
    @ResponseBody
            public CommonResponse upload(@RequestParam("upload") MultipartFile multipartFile, @RequestParam("uId") String username, Model model) throws Exception {
        //获取文件的名称
        String fileName = multipartFile.getOriginalFilename();

        //判断有无后缀
        if (fileName.lastIndexOf(".") < 0)
            return CommonResponse.error("上传图片格式不正确");

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        //如果不是图片
        if (!prefix.equalsIgnoreCase(".jpg") && !prefix.equalsIgnoreCase(".jpeg") && !prefix.equalsIgnoreCase(".svg") && !prefix.equalsIgnoreCase(".gif") && !prefix.equalsIgnoreCase(".png")) {
            return CommonResponse.error("上传图片格式不正确");
        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("imagesFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String imageName = TencentCOS.uploadfile(excelFile, "img/newsInfo");

        //程序结束时，删除临时文件
        deleteFile(excelFile);

        //存入图片名称，用于网页显示
        model.addAttribute("imageName", imageName);

        System.out.println(IMAGE_PATH+imageName);

        //插入数据库记录
        //TODO
        //uploadService.insert(imageName, uId);

        //返回成功信息
        return  CommonResponse.success(IMAGE_PATH+imageName);
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
