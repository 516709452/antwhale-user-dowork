package org.antwhale.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import com.tencentcloudapi.common.Credential;
import org.antwhale.code.TencentCosAccountEnum;

/**
 * @Author: 何欢
 * @Date: 2022/8/2920:36
 * @Description:腾讯云COS操作对象工具类
 */
public class TencentCOSUtils {

    /**
     * @author 何欢
     * @Date 20:43 2022/8/29
     * @Description 获取COSClient实例【存储桶用】
     **/
    public static COSClient getCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = TencentCosAccountEnum.getSecretId.getValue();
        String secretKey = TencentCosAccountEnum.getSecretKey.getValue();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-beijing");
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    /**
    *@author 何欢
    *@Date 21:53 2022/9/5
    *@Description 获取Credential实例【视频点播用】
    **/
    public static Credential getCredential(){
        String secretId = TencentCosAccountEnum.getCredentialSecretId.getValue();
        String secretKey = TencentCosAccountEnum.getCredentialSecretKey.getValue();
        Credential cred = new Credential(secretId, secretKey);
        return cred;
    }
}
