package org.antwhale.service;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import org.antwhale.code.TencentCosAccountEnum;
import org.antwhale.utils.TencentCOSUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: 何欢
 * @Date: 2022/8/2920:50
 * @Description:
 */
@Component
public class TencentCOSHandler {

    /**
     * @author 何欢
     * @Date 20:03 2022/8/30
     * @Description 创建存储桶：
     * ①在指定账号下创建一个存储桶。
     * ②同一用户账号下，可以创建多个存储桶，数量上限是200个（不区分地域），存储桶中的对象数量没有限制。
     * ③创建存储桶是低频操作，一般建议在控制台创建 Bucket，在 SDK 进行 Object 的操作。
     * ④存储同命名规则：BucketName-APPID 如：examplebucket-1250000000
     * 成功： Bucket 类，包含有关 Bucket 的描述（Bucket 的名称，owner 和创建日期）。
     * 失败： 发生错误（如身份认证失败），抛出异常 CosClientException 或者 CosServiceException。
     **/
    public void createBucket(String bucketName) {
        COSClient cosClient = TencentCOSUtils.getCOSClient();
        String bucket = bucketName; //存储桶名称，格式：BucketName-APPID
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
        createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
        Bucket bucketResult = cosClient.createBucket(createBucketRequest);
        cosClient.shutdown();
    }

    /**
     * @author 何欢
     * @Date 19:57 2022/8/30
     * @Description 查询存储桶列表 - 查询指定账号下所有的存储桶列表。
     * 成功：返回一个 所有 Bucket 类的列表，Bucket 类包含了 bucket 成员，location 等信息。
     * 失败：发生错误（如 Bucket 不存在），抛出异常 CosClientException 或者 CosServiceException。
     **/
    public List<Bucket> queryBucket() {
        COSClient cosClient = TencentCOSUtils.getCOSClient();
        // 如果只调用 listBuckets 方法，则创建 cosClient 时指定 region 为 new Region("") 即可
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
        }
        cosClient.shutdown();
        return buckets;
    }

    /**
     * @author 何欢
     * @Date 20:09 2022/8/30
     * @Description 检索存储桶是否存在且是否有权限访问。
     * 成功：存在返回 true，否则 false。
     * 失败：发生错误（如身份认证失败），抛出异常 CosClientException 或者 CosServiceException
     **/
    public Boolean doesBucketExist(String bucketName) {
        // bucket的命名规则为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        COSClient cosClient = TencentCOSUtils.getCOSClient();
        Boolean bucketExistFlag = cosClient.doesBucketExist(bucketName);
        cosClient.shutdown();
        return bucketExistFlag;
    }


    /**
     *@author 何欢
     *@Date 20:03 2022/8/30
     *@Description
     **/

    /**
     * @author 何欢
     * @Date 20:53 2022/8/29
     * @Description 查询存储桶中对象列表
     **/
    public List<COSObjectSummary> queryTencentCOSFromBucket(String FilePath) {
        COSClient cosClient = TencentCOSUtils.getCOSClient();
        // Bucket的命名格式为 BucketName-APPID ，此处填写的存储桶名称必须为此格式
        String bucketName = TencentCosAccountEnum.getCourseBucketName.getValue();
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        // 设置bucket名称
        listObjectsRequest.setBucketName(bucketName);
        // prefix表示列出的object的key以prefix开始
        listObjectsRequest.setPrefix(FilePath);
        // deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
        // 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;
        List<COSObjectSummary> cosObjectSummaryList = new ArrayList<>();
        do {
            objectListing = cosClient.listObjects(listObjectsRequest);
            // common prefix表示表示被delimiter截断的路径, 如delimter设置为/, common prefix则表示所有子目录的路径
            List<String> commonPrefixs = objectListing.getCommonPrefixes();

            // object summary表示所有列出的object列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            cosObjectSummaryList.addAll(cosObjectSummaries);
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                // 文件的路径key
                String key = cosObjectSummary.getKey();
                // 文件的etag
                String etag = cosObjectSummary.getETag();
                // 文件的长度
                long fileSize = cosObjectSummary.getSize();
                // 文件的存储类型
                String storageClasses = cosObjectSummary.getStorageClass();
            }
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
        // 关闭客户端(关闭后台线程)
        cosClient.shutdown();
        return cosObjectSummaryList;
    }
}
