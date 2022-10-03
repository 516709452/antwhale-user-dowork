package org.antwhale.bpo.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.antwhale.blo.WeifxUserInfoBLO;
import org.antwhale.bpo.WeifxUserInfoBPO;
import org.antwhale.dto.userinfodto.WeifxUserImportParamDTO;
import org.antwhale.dto.userinfodto.WeifxUserQueryParamDTO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.mapper.WeifxUserInfoMapper;
import org.antwhale.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: 何欢
 * @Date: 2022/8/1816:02
 * @Description:
 */
@Service
public class WeifxUserInfoBPOImpl implements WeifxUserInfoBPO {
    @Autowired
    private WeifxUserInfoBLO weifxUserInfoBLO;

    /**
     * @author 何欢
     * @Date 18:55 2022/8/22
     * @Description 微分销用户信息导入功能
     **/
    @Override
    public List<WeifxUserInfo> importWeifxUser(MultipartFile file) throws IOException {
        List<WeifxUserInfo> weifxUserInfoList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, WeifxUserImportParamDTO.class, new ReadListener<WeifxUserImportParamDTO>() {
            /**
             * 单次缓存的数据量
             */
            public static final int BATCH_COUNT = 1000;
            /**
             *临时存储
             */
            private List<WeifxUserImportParamDTO> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

            /**
             * 这个每一条数据解析都会来调用
             *
             * @param weifxUserImportParamDTO    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
             * @param analysisContext
             */
            @Override
            public void invoke(WeifxUserImportParamDTO weifxUserImportParamDTO, AnalysisContext analysisContext) {
                cachedDataList.add(weifxUserImportParamDTO);
                if (cachedDataList.size() >= BATCH_COUNT) {
                    cachedDataList = cachedDataListFilter(cachedDataList);
                    List<WeifxUserInfo> weifxUserInfos = new ArrayList<>();
                    cachedDataList.stream().forEach(c -> {
                        WeifxUserInfo weifxUserInfo = new WeifxUserInfo();
                        BeanUtils.copyProperties(c, weifxUserInfo);
                        weifxUserInfos.add(weifxUserInfo);
                        weifxUserInfoList.add(weifxUserInfo);//全局List，返回全部用户信息
                    });
                    weifxUserInfoBLO.saveBatch(weifxUserInfos);
                    // 存储完成清理 list
                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
                }
            }

            /**
             * 所有数据解析完成了 都会来调用
             *
             * @param analysisContext
             */
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                List<WeifxUserInfo> weifxUserInfos = new ArrayList<>();
                cachedDataList = cachedDataListFilter(cachedDataList);
                cachedDataList.stream().forEach(c -> {
                    WeifxUserInfo weifxUserInfo = new WeifxUserInfo();
                    BeanUtils.copyProperties(c, weifxUserInfo);
                    weifxUserInfos.add(weifxUserInfo);
                });
                weifxUserInfoBLO.saveBatch(weifxUserInfos);
            }
        }).excelType(ExcelTypeEnum.CSV).sheet().doRead();
        return weifxUserInfoList;
    }

    /**
     * @author 何欢
     * @Date 18:56 2022/8/22
     * @Description 查询微分销用户信息
     **/
    @Override
    public IPage<WeifxUserInfo> queryWeifxUser(WeifxUserQueryParamDTO weifxUserQueryParamDTO) {
        QueryWrapper<WeifxUserInfo> queryWrapper = new QueryWrapper<>();

        Optional.ofNullable(weifxUserQueryParamDTO.getWeifxUserinfoId())
                .ifPresent(weifxUserinfoId -> queryWrapper.eq("weifx_userinfo_id", weifxUserinfoId));
        Optional.ofNullable(weifxUserQueryParamDTO.getWeifxUserinfoName())
                .ifPresent(weifxUserinfoName -> queryWrapper.eq("weifx_userinfo_name", weifxUserinfoName));
        Optional.ofNullable(weifxUserQueryParamDTO.getWeifxUserinfoPhone())
                .ifPresent(weifxUserinfoPhone -> queryWrapper.eq("weifx_userinfo_phone", weifxUserinfoPhone));
        Optional.ofNullable(weifxUserQueryParamDTO.getWeifxUserinfoWxname())
                .ifPresent(weifxUserinfoWxname -> queryWrapper.eq("weifx_userinfo_wxname", weifxUserinfoWxname));
        Optional.ofNullable(weifxUserQueryParamDTO.getWeifxUserinfoLevel())
                .ifPresent(weifxUserinfoLevel -> queryWrapper.eq("weifx_userinfo_level", weifxUserinfoLevel));
        Optional.ofNullable(weifxUserQueryParamDTO.getCommonUserinfoId())
                .ifPresent(commonUserinfoId -> queryWrapper.eq("common_userinfo_id", commonUserinfoId));
        if (CommonUtils.isNotNull(weifxUserQueryParamDTO.getWeifxUserinfoRegistertime())) {
            String endDate;
            String startDate;
            List<String> registerTimeList = weifxUserQueryParamDTO.getWeifxUserinfoRegistertime();
            if (CommonUtils.isNull(registerTimeList.get(0))) {
                throw new RuntimeException("请填写开始日期");
            }
            if (CommonUtils.isNull(registerTimeList.get(1))) {
                throw new RuntimeException("请填写终止日期");
            }
            startDate = weifxUserQueryParamDTO.getWeifxUserinfoRegistertime().get(0);
            endDate = weifxUserQueryParamDTO.getWeifxUserinfoRegistertime().get(1);
            queryWrapper.apply("date_format(weifx_userinfo_registertime,'%Y-%m-%d') between {0} and {1}", startDate, endDate);
        }
        Long currentPage = weifxUserQueryParamDTO.getCurrentPage();
        Long pageSize = weifxUserQueryParamDTO.getPageSize();
        IPage<WeifxUserInfo> WeifxUserInfoPage = weifxUserInfoBLO.page(new Page(currentPage, pageSize), queryWrapper);
        if (CommonUtils.isNull(WeifxUserInfoPage.getRecords())) {
            throw new RuntimeException("根据条件为查询到用户信息");
        }
        return WeifxUserInfoPage;
    }

    /**
     * @author 何欢
     * @Date 17:46 2022/8/21
     * @Description 根据id过滤导入的微分销重复用户
     **/
    private List<WeifxUserImportParamDTO> cachedDataListFilter(List<WeifxUserImportParamDTO> cachedDataList) {
        List<String> weifxUserinfoId = cachedDataList
                .stream()
                .map(WeifxUserImportParamDTO::getWeifxUserinfoId)
                .map(String::valueOf)
                .collect(Collectors.toList());
        List<WeifxUserInfo> weifxUserInfos = weifxUserInfoBLO.list(
                new QueryWrapper<WeifxUserInfo>().in("weifx_userinfo_id", weifxUserinfoId)
        );
        if (CommonUtils.isNull(weifxUserInfos)) {
            return cachedDataList;
        }
        String weifxUserinfoIdFilter = weifxUserInfos
                .stream()
                .map(WeifxUserInfo::getWeifxUserinfoId)
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        cachedDataList = cachedDataList
                .stream()
                .filter(c -> !weifxUserinfoIdFilter.contains(c.getWeifxUserinfoId().toString()))
                .collect(Collectors.toList());
        return cachedDataList;
    }
}
