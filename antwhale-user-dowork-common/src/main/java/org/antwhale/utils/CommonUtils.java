package org.antwhale.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;


import java.math.BigDecimal;
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
    public static Boolean isNull(Object object) {
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
    public static Boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * @author 何欢
     * @Date 21:37 2022/10/1
     * @Description 获取文件后缀
     **/
    public static String getFileSuffix(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } else {
            return " ";
        }
    }

    public static Integer getIntegerValue(Object obj) {
        int value = 0;
        if (obj != null && !"".equals(obj.toString())) {
            value = Integer.parseInt(obj.toString());
        }

        return value;
    }

    public static Long getLongValue(Object obj) {
        long value = 0L;
        if (obj != null && !"".equals(obj.toString())) {
            value = Long.parseLong(obj.toString());
        }

        return value;
    }

    public static Double getDoubleValue(Object obj) {
        double value = 0.0D;
        if (obj != null && !"".equals(obj.toString())) {
            value = Double.parseDouble(obj.toString());
        }

        return value;
    }

    public static BigDecimal getBigDecimalValue(Object obj) {
        BigDecimal value = BigDecimal.ZERO;
        if (obj != null && !"".equals(obj.toString())) {
            value = new BigDecimal(obj.toString());
        }

        return value;
    }

    public static String getStringValue(Object obj) {
        String value = "";
        if (obj != null) {
            value = obj.toString();
        }

        return value;
    }
}
