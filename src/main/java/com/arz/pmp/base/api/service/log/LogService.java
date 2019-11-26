package com.arz.pmp.base.api.service.log;

import com.arz.pmp.base.api.bo.course.CourseEditorReq;
import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 日志业务
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
public interface LogService {

    PageInfo<List<LogDataResp>> getLogListPage(RestRequest<LogSearchReq> req);
}
