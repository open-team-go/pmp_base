package com.arz.pmp.base.api.service.log;

import com.arz.pmp.base.api.bo.course.CourseEditorReq;
import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.mapper.PmpCourseEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpCourseExMapper;
import com.arz.pmp.base.mapper.ex.PmpLogExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ma.glasnost.orika.MapperFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description 日志业务实现
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 17:31
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private PmpLogExMapper pmpLogExMapper;

    @Override
    public PageInfo<List<LogDataResp>> getLogListPage(RestRequest<LogSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        LogSearchReq search = req.getBody();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
                .doSelectPage(() -> {
                    pmpLogExMapper.selectLogList(search);
                }).toPageInfo();
        return pageInfo;

    }
}
