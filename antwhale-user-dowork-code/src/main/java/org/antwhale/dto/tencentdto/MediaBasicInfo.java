package org.antwhale.dto.tencentdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/9/521:23
 * @Description:
 */
@Data
public class MediaBasicInfo {
    @ApiModelProperty(value = "媒体文件名称")
    private String Name;

    @ApiModelProperty(value = "媒体文件描述")
    private String Description;

    @ApiModelProperty(value = "媒体文件的创建时间，使用ISO日期格式")
    private String CreateTime;

    @ApiModelProperty(
            value = "媒体文件的最近更新时间（如修改视频属性、发起视频处理等会触发更新媒体文件信息的操作）" +
                    "使用 ISO 日期格式"
    )
    private String UpdateTime;

    @ApiModelProperty(
            value = "媒体文件的过期时间，" +
                    "使用 ISO 日期格式。过期后该媒体文件及其相关资源（转码结果、雪碧图等）将被永久删除。" +
                    "“9999-12-31T23:59:59Z”表示永不过期"
    )
    private String ExpireTime;

    @ApiModelProperty(value = "媒体文件的分类ID")
    private Integer ClassId;

    @ApiModelProperty(value = "媒体文件的分类名称")
    private String ClassName;

    @ApiModelProperty(value = "媒体文件的分类路径，分类间以“-”分隔，如“新的一级分类 - 新的二级分类”")
    private String ClassPath;

    @ApiModelProperty(value = "媒体文件的封面图片地址")
    private String CoverUrl;

    @ApiModelProperty(value = "媒体文件的封装格式，例如 mp4、flv 等")
    private String Type;

    @ApiModelProperty(value = "原始媒体文件的 URL 地址")
    private String MediaUrl;
    //由于里面还有一个实体，暂时用不到先不处理
//    @ApiModelProperty(
//            value = "该媒体文件的来源信息。" +
//            "注意：此字段可能返回 null，表示取不到有效值"
//    )
//    private MediaSourceData SourceInfo;

    @ApiModelProperty(value = "媒体文件存储地区，如 ap-chongqing")
    private String StorageRegion;

    @ApiModelProperty(value = "媒体文件的标签信息")
    private List<String> TagSet;

    @ApiModelProperty(value = "直播录制文件的唯一标识")
    private String Vid;

    @ApiModelProperty(
            value = "文件类型：" +
                    "Video: 视频文件" +
                    "Audio: 音频文件" +
                    "Image: 图片文件"
    )
    private String Category;

    @ApiModelProperty(
            value = "文件状态：Normal：正常，Forbidden：封禁。" +
                    "*注意：此字段暂不支持"
    )
    private String Status;

    @ApiModelProperty(value = "媒体文件的存储类别：" +
            "STANDARD：标准存储。" +
            "STANDARD_IA：低频存储。" +
            "ARCHIVE：归档存储。" +
            "DEEP_ARCHIVE：深度归档存储。")
    private String StorageClass;
}
