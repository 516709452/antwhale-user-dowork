package org.antwhale.dto.tencentdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: 何欢
 * @Date: 2022/9/521:23
 * @Description:
 */
@Data
public class MediaInfo {
    @ApiModelProperty(value = "媒体文件唯一标识ID")
    private String FileId;

    @ApiModelProperty(
            value = "基础信息。" +
                    "包括视频名称、分类、播放地址、封面图片等。" +
                    "注意：此字段可能返回 null，表示取不到有效值。"
    )
    private MediaBasicInfo mediaBasicInfo;

}
