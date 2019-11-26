package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.bo.place.PlaceEditorReq;
import com.arz.pmp.base.api.bo.place.PlaceSearchReq;
import com.arz.pmp.base.api.service.place.PlaceService;
import com.arz.pmp.base.entity.PmpTeachingPlaceEntity;
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

/**
 * description 教学点操作
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/11 21:42
 */
@Api(value = "后端 教学点操作API集", tags = "后端 教学点操作API集")
@RestController
@RequestMapping("/back/place")
public class PlaceRestController {

    @Resource
    private PlaceService placeService;

    @ApiOperation(value = "教学点 列表查看", notes = "分页查看教学点")
    @PostMapping("/index")
    @RequiresPermissions({"place:read"})
    public RestResponse<PageInfo<List<PmpTeachingPlaceEntity>>>
        getPlaceListPage(@RequestBody @Valid RestRequest<PlaceSearchReq> data) {

        PageInfo pageInfo = placeService.getPlaceListPage(data);

        return RestResponse.success(pageInfo);
    }

    @ApiOperation(value = "教学点 所有教学点", notes = "获取所有教学点")
    @PostMapping("/all")
    public RestResponse<List<PmpTeachingPlaceEntity>>
        getPlaceAll(@RequestBody @Valid RestRequest<PlaceSearchReq> data) {

        List<PmpTeachingPlaceEntity> list = placeService.getPlaceAll(data.getBody());

        return RestResponse.success(list);
    }

    @ApiOperation(value = "教学点 教学点添加", notes = "添加新的教学点")
    @PostMapping("/add")
    @RequiresPermissions({"place:add"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.ADD, module = SysLogEnumClass.OptionModuleEnum.SYS_TEACHING_PLACE,
        describe = "添加教学点信息")
    public RestResponse<Long> addPlace(@RequestBody @Valid RestRequest<PlaceEditorReq> data) {

        Long id = placeService.addOrUpPlace(data.getBody(), true, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "教学点 教学点更新", notes = "更新教学点")
    @PostMapping("/update")
    @RequiresPermissions({"place:update"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.UPDATE, module = SysLogEnumClass.OptionModuleEnum.SYS_TEACHING_PLACE,
        describe = "更新教学点信息")
    public RestResponse<Long> updatePlace(@RequestBody @Valid RestRequest<PlaceEditorReq> data) {

        Long id = placeService.addOrUpPlace(data.getBody(), false, data.getHeader().getAuthentication());

        return RestResponse.success(id);
    }

    @ApiOperation(value = "教学点 教学点删除", notes = "删除教学点")
    @PostMapping("/delete")
    @RequiresPermissions({"place:delete"})
    @SysLog(type = SysLogEnumClass.OptionTypeEnum.DELETE, module = SysLogEnumClass.OptionModuleEnum.SYS_TEACHING_PLACE,
        describe = "删除教学点信息")
    public RestResponse deletePlace(@RequestBody @Valid RestRequest<Long> data) {

        placeService.deletePlace(data.getBody(), data.getHeader().getAuthentication());

        return RestResponse.success();
    }
}
