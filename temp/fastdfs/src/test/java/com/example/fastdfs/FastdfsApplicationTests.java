package com.example.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@SpringBootTest
class FastdfsApplicationTests {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Test
    void contextLoads() {
//        File file=new File("D:\\timg.jpg");
//        FileInputStream inputStream = null;
//        try {
//             inputStream=new FileInputStream(file);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String fileName=file.getName();
//        //获取文件的后缀名
//        String extName=fileName.substring(fileName.lastIndexOf(".")+1);
//        StorePath storePath=fastFileStorageClient.uploadFile(inputStream,file.length(),extName,null);
//        System.out.println(storePath.getFullPath());
//        System.out.println(storePath.getGroup());
//        System.out.println(storePath.getPath());
        fastFileStorageClient.deleteFile("group1/M00/00/00/wKi2jF_kYH2AT-l6AABfRqGBmD4306.jpg");
    }

}
