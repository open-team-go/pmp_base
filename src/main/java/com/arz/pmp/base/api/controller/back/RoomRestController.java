package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.aop.annotation.RequirePermissions;
import com.arz.pmp.base.api.bo.CommonDataReq;
import com.arz.pmp.base.api.bo.room.RoomEditorReq;
import com.arz.pmp.base.api.bo.room.RoomSearchReq;
import com.arz.pmp.base.api.service.room.RoomService;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;
import com.arz.pmp.base.entity.PmpTeachingTypeEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import com.arz.pmp.base.framework.core.annotation.SysLog;
import com.arz.pmp.base.framework.core.enums.SysLogEnumClass;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static com.arz.pmp.base.framework.core.enums.SysPermEnumClass.PermissionEnum.*;

/**
 * description 班级操作
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 班级操作API集", tags = "后端 班级操作API集")
@RestController
@RequestMapping("/back/room")
public class RoomRestController {

    @Resource
    private RoomService roomService;

    @ApiOperation(value = "班级 列表查看", notes = "分页查看班级")
    @PostMapping("/index")
    @RequirePermissions({ROOM_READ})
    public RestResponse<PageInfo<List<PmpTeachingRoomEntity>>>
    getRoomListPage(@RequestBody @Valid RestRequest<RoomSearchReq> data) {

        PageInfo pageInfo = roomService.getRoomListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "班级 所有班级", notes = "获取所有班级")
    @PostMapping("/all")
    public RestResponse<List<PmpTeachingRoomEntity>> getRoomAll(@RequestBody @Valid RestRequest<RoomSearchReq> data) {

        List<PmpTeachingRoomEntity> list = roomService.getRoomAll(data);

        return RestResponse.success(list);
    }

    @ApiOperation(value = "班级 班级添加", notes = "添加新的班级")
    @PostMapping("/add")
    @RequirePermissions({ROOM_ADD})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.ADD, module = SysLogEnumClass.OptionModuleEnum.SYS_ROOM,
            describe = "添加班级信息")
    public RestResponse<Long> addRoom(@RequestBody @Valid RestRequest<RoomEditorReq> data) {

        Long id = roomService.addOrUpRoom(data.getBody(), true, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "班级 班级更新", notes = "更新班级")
    @PostMapping("/update")
    @RequirePermissions({ROOM_UPDATE})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_ROOM,
            describe = "更新班级信息")
    public RestResponse<Long> updateRoom(@RequestBody @Valid RestRequest<RoomEditorReq> data) {

        Long id = roomService.addOrUpRoom(data.getBody(), false, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "班级 班级删除", notes = "班级删除")
    @PostMapping("/delete")
    @RequirePermissions({ROOM_DEL})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.DELETE, module = SysLogEnumClass.OptionModuleEnum.SYS_ROOM,
            describe = "删除班级信息")
    public RestResponse deleteRoom(@RequestBody @Valid RestRequest<CommonDataReq> data) {

        roomService.deleteRoom(data.getBody().getId(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }

    @ApiOperation(value = "班级类型 所有班级类型", notes = "获取所有班级类型")
    @PostMapping("/type")
    public RestResponse<List<PmpTeachingTypeEntity>> getRoomType(@RequestBody @Valid RestRequest data) {

        List<PmpTeachingTypeEntity> list = roomService.getTypeList();

        return RestResponse.success(list);
    }
}
