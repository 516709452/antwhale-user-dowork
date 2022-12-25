package org.antwhale.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @Author: 何欢
 * @Date: 2022/12/2420:29
 * @Description: 用户、课程信息映射表
 */
@Data
@TableName("edu_course_user")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="EduCourseUser对象", description="用户、课程信息映射表")
public class EduCourseUser {

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "公共信息表_用户id")
    private String commonUserinfoId;

    @ApiModelProperty(value = "课程ID")
    private String courseId;

    @TableField("validflag")
    @ApiModelProperty(value = "数据有效标识")
    private String validflag = "1";

    @TableField(value = "createtime", fill = FieldFill.INSERT)
    @ApiModelProperty(value = "数据新增时间")
    private LocalDateTime createtime;

    @TableField(value = "updatetime", fill = FieldFill.UPDATE)
    @ApiModelProperty(value = "数据修改时间")
    private LocalDateTime updatetime;
}
