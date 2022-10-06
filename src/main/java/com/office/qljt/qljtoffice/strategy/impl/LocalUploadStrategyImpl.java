package com.office.qljt.qljtoffice.strategy.impl;

import com.office.qljt.qljtoffice.exception.BizException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 本地上传策略
 *
 * @author lib
 * @date 2021/07/28
 */
@Service("localUploadStrategyImpl")
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl {

    /**
     * 本地路径
     */
    @Value("${upload.local.path}")
    private String rootPath;

    /**
     * 访问url
     */
    @Value("${upload.local.domain}")
    private String domain;

    @Override
    public Boolean exists(String fileName) {
        return new File(rootPath + fileName).exists();
    }


    @Override
    public void upload(String path, String fileName, MultipartFile file) throws IOException {


        File targetFile = new File(rootPath +path+"/"+fileName);

        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        try {
            //上传成功
            file.transferTo(targetFile);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("上传失败");
        }

    }

    @Override
    public String getFileAccessUrl(String filePath) {
        return domain + filePath;
    }

}
