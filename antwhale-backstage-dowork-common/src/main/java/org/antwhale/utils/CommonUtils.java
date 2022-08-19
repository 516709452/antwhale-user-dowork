package org.antwhale.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;


import java.util.Collection;
import java.util.Map;

/**
 * @Author: 何欢
 * @Date: 2022/7/2623:59
 * @Description: 公共工具类
 */
public class CommonUtils {
    /**
     * @author 何欢
     * @Date 0:44 2022/7/27
     * @Description 判断参数是否为空【可判断任何对象】
     **/
    public static Boolean IsNull(Object object) {
        if (null == object) {
            return null == object;
        }
        if (object instanceof String) {
            return StringUtils.isBlank((String) object);
        }
        if (object instanceof Collection) {
            return CollectionUtils.isEmpty((Collection) object);
        }
        if (object instanceof Map) {
            return CollectionUtils.isEmpty((Map) object);
        }
        if (object instanceof Object[]) {
            return MapUtils.isEmpty((Map) object);
        }
        return false;
    }

    /**
     * @author 何欢
     * @Date 0:44 2022/7/27
     * @Description 判断参数是否不为空【可判断任何对象】
     **/
    public static Boolean IsNotNull(Object object) {
        return !IsNull(object);
    }
}
