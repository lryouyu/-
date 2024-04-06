package com.ysu.controller;

import com.ysu.common.FileUploadUtils;
import com.ysu.common.ImageInfo;
import com.ysu.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("upload")
public class UploadController {
    @Autowired
    private ImageInfo imageInfo;

    @RequestMapping("file")
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file) {
        //使用配置的上传的文件存储基础路径
        String basePath = "d:/temp";
        return FileUploadUtils.upload(file, basePath,"file");
    }

    @RequestMapping("image")
    public JsonResult uploadImage(@RequestParam("imageFile") MultipartFile imageFile){
        //使用配置图片的文件存储基础路径
        String basePath = "d:/temp";
        return FileUploadUtils.upload(imageFile, imageInfo.getBasePath(), "image");
    }


    @RequestMapping("video")
    public JsonResult uploadVideo(@RequestParam("videoFile") MultipartFile videoFile){
        //使用配置视频的文件存储基础路径
        String basePath = "d:/temp";
        return FileUploadUtils.upload(videoFile, basePath,"video");
    }

}
