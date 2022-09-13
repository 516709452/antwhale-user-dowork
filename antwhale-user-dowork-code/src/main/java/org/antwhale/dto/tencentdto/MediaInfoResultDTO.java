package org.antwhale.dto.tencentdto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * @Author: 何欢
 * @Date: 2022/9/521:15
 * @Description:
 */
@Data
public class MediaInfoResultDTO {
    @ApiModelProperty(value = "唯一请求ID，每次请求都会返回。定位问题时需要提供该次请求的 RequestId")
    private String RequestId;

    @ApiModelProperty(value = "不存在的文件ID列表")
    private String NotExistFileIdSet;

    @ApiModelProperty(value = "媒体文件信息列表")
    private Set<MediaInfo> MediaInfoSet;
}
