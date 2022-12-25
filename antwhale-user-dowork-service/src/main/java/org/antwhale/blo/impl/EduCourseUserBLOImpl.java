package org.antwhale.blo.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.antwhale.blo.EduCourseUserBLO;
import org.antwhale.entity.EduCourseUser;
import org.antwhale.mapper.course.EduCourseUserMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: 何欢
 * @Date: 2022/12/2517:49
 * @Description:
 */
@Service
public class EduCourseUserBLOImpl extends ServiceImpl<EduCourseUserMapper, EduCourseUser> implements EduCourseUserBLO {
}
