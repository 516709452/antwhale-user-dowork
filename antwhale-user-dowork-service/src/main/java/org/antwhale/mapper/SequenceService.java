package org.antwhale.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: 何欢
 * @Date: 2022/10/121:23
 * @Description:
 */
@Mapper
public interface SequenceService {
    @Select("select nextval('seq_userId')")
    String getUserIdSeq();
}
