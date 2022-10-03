package org.antwhale.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.antwhale.utils.SimpleDateUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

/**
 * @Author: 何欢
 * @Date: 2022/8/2621:18
 * @Description:
 */
@Configuration
public class MybatisPlusConfig implements MetaObjectHandler {
    /**
     * 分页插件。如果你不配置，分页插件将不生效
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 指定数据库方言为 MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        strictInsertFill(metaObject, "createtime", String.class, SimpleDateUtils.getNowDate("yyyyMMdd"));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        strictUpdateFill(metaObject, "updatetime", String.class, SimpleDateUtils.getNowDate("yyyyMMdd"));
    }
}
