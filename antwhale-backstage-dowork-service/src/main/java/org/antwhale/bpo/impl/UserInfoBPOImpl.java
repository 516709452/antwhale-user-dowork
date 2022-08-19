package org.antwhale.bpo.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.ListUtils;
import org.antwhale.blo.WeifxUserInfoBLO;
import org.antwhale.bpo.UserInfoBPO;
import org.antwhale.dto.userinfodto.WeifxUserImportParamDTO;
import org.antwhale.entity.WeifxUserInfo;
import org.antwhale.utils.CommonUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 何欢
 * @Date: 2022/8/1816:02
 * @Description:
 */
@Service
public class UserInfoBPOImpl implements UserInfoBPO {
    @Autowired
    private WeifxUserInfoBLO weifxUserInfoBLO;

    @Override
    public List<WeifxUserInfo> importWeifxUser(MultipartFile file) throws IOException {
        List<WeifxUserInfo> weifxUserInfoList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        EasyExcel.read(inputStream, WeifxUserImportParamDTO.class,new ReadListener<WeifxUserImportParamDTO>() {
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
                    List<WeifxUserInfo> weifxUserInfos = new ArrayList<>();
                    cachedDataList.stream().forEach(c->{
                        WeifxUserInfo weifxUserInfo = new WeifxUserInfo();
                        BeanUtils.copyProperties(c,weifxUserInfo);
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
                cachedDataList.stream().forEach(c->{
                    WeifxUserInfo weifxUserInfo = new WeifxUserInfo();
                    BeanUtils.copyProperties(c,weifxUserInfo);
                    weifxUserInfos.add(weifxUserInfo);
                });
                weifxUserInfoBLO.saveBatch(weifxUserInfos);
            }
        }).excelType(ExcelTypeEnum.CSV).sheet().doRead();
        return weifxUserInfoList;
    }
}
