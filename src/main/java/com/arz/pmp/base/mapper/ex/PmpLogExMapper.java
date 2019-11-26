package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.entity.PmpSystemLogEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpLogExMapper {

    List<LogDataResp> selectLogList(LogSearchReq search);
}