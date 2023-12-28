package com.vere.assign_online.utils;

import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * ClassName:QNYTokenUtil
 * Package:com.vere.assign_online.utils
 * Description:
 *
 * @Date:2022/5/5 16:00
 * @Author:3046990030@qq.com
 */

@Slf4j
@ConfigurationProperties(prefix = "qny")
@Component
public class QiNiuUtil {
    @Value("${qny.accessKey}")
    private String accessKey;

    @Value("${qny.secretKey}")
    private String secretKey;

    @Value("${qny.bucket}")
    private String bucket;

    @Value("${qny.domain}")
    private String domain;

    /**
     * @param key
     * @return 生成上传覆盖token，方便学生修改上传的作业
     */
    public String getToken(String key) {
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.uploadToken(bucket, key);
    }


    public String getPrivateUrl(String fileName) {
        String encodedFileName = fileName;
        try {
            encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s%s", domain, encodedFileName);
        System.out.println(publicUrl);
        long expireInSeconds = 3600;
        Auth auth = Auth.create(accessKey, secretKey);
        return auth.privateDownloadUrl(publicUrl, expireInSeconds);
    }
}
