package com.jiang.background.controller;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.jiang.pojo.MultiUploadResultBean;
import com.jiang.pojo.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


//上传文件的controller
@Controller
@RequestMapping("file")
public class FileController {

    @Value("${image.server}")
    private String image_server;

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("upload")
    @ResponseBody
    public ResultBean upload(MultipartFile file){
        System.out.println(file+"!!!");
        //获取到客户端上传的文件，将文件上传到fastDFS
        String originalFileName=file.getOriginalFilename();
        System.out.println(originalFileName);
        String ext=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        try {
            StorePath storePath=fastFileStorageClient.uploadFile(file.getInputStream(),file.getSize(),ext,null);
            //把服务器文件保存的地址再返回给客户端
            String fullPath=storePath.getFullPath();
            String path=new StringBuilder(image_server).append(fullPath).toString();
            return new ResultBean("200",path);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultBean("500","当前服务器繁忙");
        }
        //客户端将这个地址回显到服务器
    }

    @RequestMapping("multiUpload")
    @ResponseBody
    public MultiUploadResultBean multiUpload(MultipartFile[] files){
        MultiUploadResultBean resultBean=new MultiUploadResultBean();
        String[] data =new String[files.length];
        for (int i=0;i<files.length;i++){
            String originalFileName=files[i].getOriginalFilename();
            System.out.println(originalFileName);
            String ext=originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            try {
                StorePath storePath=fastFileStorageClient.uploadFile(files[i].getInputStream(),files[i].getSize(),ext,null);
                //把服务器文件保存的地址再返回给客户端
                String fullPath=storePath.getFullPath();
                String path=new StringBuilder(image_server).append(fullPath).toString();
                data[i]=path;
            } catch (IOException e) {
                e.printStackTrace();
                resultBean.setErrno("-1");
                return resultBean;
            }
        }
        resultBean.setErrno("0");
        resultBean.setData(data);
        return resultBean;
    }
}
