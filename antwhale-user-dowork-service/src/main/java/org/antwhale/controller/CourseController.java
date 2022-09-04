package org.antwhale.controller;

import lombok.extern.slf4j.Slf4j;
import org.antwhale.bpo.CourseBPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 何欢
 * @Date: 2022/8/2920:16
 * @Description:课程控制器
 */
@Slf4j
@RestController
public class CourseController {
    @Autowired
    private CourseBPO courseBPO;

    @PostMapping("/CourseController/queryCourse")
    public void queryCourse(String filePath){
        courseBPO.queryCourse(filePath);
    }

}
