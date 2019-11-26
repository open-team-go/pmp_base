package com.arz.pmp.base.api.service.course;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.course.CourseEditorReq;
import com.arz.pmp.base.api.bo.course.CourseSearchReq;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpCourseEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.mapper.PmpCourseEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpCourseExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ma.glasnost.orika.MapperFacade;

/**
 * description 课程业务实现
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private PmpCourseEntityMapper pmpCourseEntityMapper;
    @Autowired
    private PmpCourseExMapper pmpCourseExMapper;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private RedisService redisService;

    @Override
    public PageInfo<List<PmpCourseEntity>> getCourseListPage(RestRequest<CourseSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        CourseSearchReq search = req.getBody();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpCourseExMapper.getCourseList(search);
            }).toPageInfo();
        return pageInfo;

    }

    @Override
    public List<PmpCourseEntity> getCourseAll(CourseSearchReq search) {

        return pmpCourseExMapper.getCourseList(search);
    }

    @Override
    public Long addOrUpCourse(CourseEditorReq data, boolean addOn, String authentication) {

        String name = data.getCourseName();
        Long courseId = data.getCourseId();
        if (StringUtils.isNotBlank(name)) {

            Long id = pmpCourseExMapper.selectCourseByName(name);
            boolean flag = id == null || (!addOn && id.equals(courseId));
            Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        }
        PmpCourseEntity courseEntity = mapperFacade.map(data, PmpCourseEntity.class);
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);

        // 新增
        if (addOn) {
            Assert.isTrue(StringUtils.isNotBlank(name), CommonCodeEnum.PARAM_ERROR);
            courseEntity.setCreateTime(curTimeSec);
            courseEntity.setCreateManager(operatorId);
            courseEntity.setDelOn(false);
            pmpCourseEntityMapper.insert(courseEntity);
            courseId = courseEntity.getCourseId();

        } else {
            courseEntity.setUpdateTime(curTimeSec);
            courseEntity.setUpdateManager(operatorId);
            pmpCourseEntityMapper.updateByPrimaryKeySelective(courseEntity);
        }

        return courseId;
    }

    @Override
    public void deleteCourse(Long courseId, String authentication) {
        PmpCourseEntity courseEntity = new PmpCourseEntity();
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        courseEntity.setCourseId(courseId);
        courseEntity.setUpdateTime(curTimeSec);
        courseEntity.setUpdateManager(operatorId);
        courseEntity.setDelOn(true);
        pmpCourseEntityMapper.updateByPrimaryKeySelective(courseEntity);
    }
}
