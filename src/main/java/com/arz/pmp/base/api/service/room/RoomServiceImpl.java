package com.arz.pmp.base.api.service.room;

import java.util.List;

import com.arz.pmp.base.api.bo.room.RoomDataResp;
import com.arz.pmp.base.api.service.admin.AdminService;
import com.arz.pmp.base.framework.core.enums.SysPermEnumClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.room.RoomEditorReq;
import com.arz.pmp.base.api.bo.room.RoomSearchReq;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;
import com.arz.pmp.base.entity.PmpTeachingTypeEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.mapper.PmpTeachingRoomEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpRoomExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ma.glasnost.orika.MapperFacade;

/**
 * description 班级业务实现
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 17:31
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private PmpRoomExMapper pmpRoomExMapper;
    @Autowired
    private PmpTeachingRoomEntityMapper pmpTeachingRoomEntityMapper;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private RedisService redisService;
    @Autowired
    private AdminService adminService;

    @Override
    public PageInfo<List<RoomDataResp>> getRoomListPage(RestRequest<RoomSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        RoomSearchReq search = req.getBody();
        Long adminId = adminService.getRoleAdminId(req.getHeader().getAuthentication(), SysPermEnumClass.RoleEnum.EDUCATION);
        if(adminId!=null){
            search.setAdminId(adminId);
        }
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
                .doSelectPage(() -> {
                    pmpRoomExMapper.selectRoomList(search);
                }).toPageInfo();
        return pageInfo;

    }

    @Override
    public List<PmpTeachingRoomEntity> getRoomAll(RestRequest<RoomSearchReq> req) {
        RoomSearchReq search = req.getBody();
        search.setAdminId(adminService.getRoleAdminId(req.getHeader().getAuthentication(), SysPermEnumClass.RoleEnum.EDUCATION));
        return pmpRoomExMapper.selectRoomSimpleList(search);
    }

    @Override
    public Long addOrUpRoom(RoomEditorReq data, boolean addOn, String authentication) {
        String name = data.getRoomName();
        Long roomId = data.getRoomId();
        if (StringUtils.isNotBlank(name)) {

            Long id = pmpRoomExMapper.selectRoomByName(name);
            boolean flag = id == null || (!addOn && id.equals(roomId));
            Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        }
        PmpTeachingRoomEntity entity = mapperFacade.map(data, PmpTeachingRoomEntity.class);
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);

        // 新增
        if (addOn) {
            Assert.isTrue(StringUtils.isNotBlank(name), CommonCodeEnum.PARAM_ERROR);
            entity.setCreateTime(curTimeSec);
            entity.setCreateManager(operatorId);
            entity.setDelOn(false);
            pmpTeachingRoomEntityMapper.insertSelective(entity);
            roomId = entity.getRoomId();

        } else {
            entity.setUpdateTime(curTimeSec);
            entity.setUpdateManager(operatorId);
            pmpTeachingRoomEntityMapper.updateByPrimaryKeySelective(entity);
        }

        return roomId;
    }

    @Override
    public void deleteRoom(Long roomId, String authentication) {
        PmpTeachingRoomEntity entity = new PmpTeachingRoomEntity();
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        entity.setRoomId(roomId);
        entity.setUpdateTime(curTimeSec);
        entity.setUpdateManager(operatorId);
        entity.setDelOn(true);
        pmpTeachingRoomEntityMapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<PmpTeachingTypeEntity> getTypeList() {

        return pmpRoomExMapper.selectTypeList();
    }
}
