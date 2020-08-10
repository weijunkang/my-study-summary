package com.weijunkang.heinonggong515.component.upload;

import cn.hutool.core.util.StrUtil;
import com.weijunkang.heinonggong515.config.MinIoProperties;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author weijunkang
 * @version 1.0
 * @description: TODO
 * @date 2020/01/14 09:32
 */
@Slf4j
@Component
public class MinIoStorageUpload {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinIoProperties minioProperties;

    /**
     *  MultipartFile上传文件
     * @param multipartFile 文件
     * @param fileName  文件名称 需要带有后缀
     * @param folder    文件夹 可以为空
     * @return
     */
    public String uploadFile(MultipartFile multipartFile, String fileName, String folder) {
        String path = "";
        try {
            fileName = this.getFileName(fileName, folder, multipartFile.getContentType());
            minioClient.putObject(minioProperties.getBucketName(), fileName, multipartFile.getInputStream(), multipartFile.getContentType());
            path = minioClient.getObjectUrl(minioProperties.getBucketName(), fileName);
        } catch (Exception e) {
            log.error("minio--文件上传错误，{}"+e.getMessage());
        }
        return path;
    }

    /**
     * 通过流  上传文件
     * @param inputStream 文件流
     * @param fileName  文件名称
     * @param folder    文件夹
     * @param fileType  文件类型 不能为空
     * @return 上传后的 文件路径
     */
    public String uploadFile(InputStream inputStream, String fileName, String folder, String fileType) {
        String path = "";
        try {
            fileName = this.getFileName(fileName, folder, fileType);
            minioClient.putObject(minioProperties.getBucketName(), fileName, inputStream, this.getContextType(fileName));
            path = minioClient.getObjectUrl(minioProperties.getBucketName(), fileName);
        } catch (Exception e) {
            log.error("minio--文件上传错误，{}"+e.getMessage());
        }
        return path;
    }


    /**
     * 删除文件
     */
    private boolean delete(String filePath) {
        try {
            minioClient.removeObject(minioProperties.getBucketName(), filePath);
            return true;
        } catch (Exception e) {
            log.error("minio--删除失败，{}"+e.getMessage());
        }
        return false;
    }

    /**
     * 把文件夹和文件名 拼起来
     * @param fileName
     * @param folder
     * @param fileType
     * @return
     */
    private String getFileName(String fileName, String folder, String fileType){
        if (StrUtil.isEmpty(fileType)) {
            new RuntimeException("文件类型错误");
        }
        if(StrUtil.isEmpty(fileName)){
            fileName = StrUtil.uuid() + "." + fileType;
        }
        if (StrUtil.isEmpty(folder)) {
            return fileName;
        }
        return folder + "/" + fileName;
    }

    /**
     * 通过文件名称得到 文件上下文类型
     * @param fileName
     * @return
     */
    private String getContextType(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1 );
        String type = "application/octet-stream";
        if(suffix.equals("jpg")||suffix.equals("jpeg")||suffix.equals("png")||suffix.equals("bmp")||suffix.equals("gif")){
            type = "image/jpeg";
        }
        return type;
    }



    /**
     * @param bucketName    桶名称
     * @param objectName    文件名称
     * @param stream        文件流
     * @param contentType   文件类型
     * @return
     */
}
