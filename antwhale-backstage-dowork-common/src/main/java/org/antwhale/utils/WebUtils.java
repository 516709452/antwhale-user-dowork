package org.antwhale.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: 何欢
 * @Date: 2022/7/3010:55
 * @Description: 向response响应体中写数据工具类
 */
public class WebUtils {
    /**
     * @author 何欢
     * @Date 10:57 2022/7/30
     * @Description 将字符串渲染到客户端
     **/
    public static void renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
