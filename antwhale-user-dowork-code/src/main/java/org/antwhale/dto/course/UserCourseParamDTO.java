package org.antwhale.dto.course;

import lombok.Data;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/12/2517:32
 * @Description:
 */
@Data
public class UserCourseParamDTO {
    //用户id
    private List<String> commonUserinfoIdList;

    //课程id
    private List<String> courseIdList;
}
