package com.arz.pmp.base.api.service.place;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.place.PlaceEditorReq;
import com.arz.pmp.base.api.bo.place.PlaceSearchReq;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpTeachingPlaceEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.mapper.PmpTeachingPlaceEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpPlaceExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ma.glasnost.orika.MapperFacade;

/**
 * description 教学点业务实现
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Autowired
    private PmpPlaceExMapper pmpPlaceExMapper;
    @Autowired
    private PmpTeachingPlaceEntityMapper pmpTeachingPlaceEntityMapper;
    @Autowired
    private MapperFacade mapperFacade;
    @Autowired
    private RedisService redisService;

    @Override
    public PageInfo<List<PmpTeachingPlaceEntity>> getPlaceListPage(RestRequest<PlaceSearchReq> req) {

        RequestHeader requestHeader = req.getHeader();
        PlaceSearchReq search = req.getBody();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpPlaceExMapper.getPlaceList(search);
            }).toPageInfo();
        return pageInfo;

    }

    @Override
    public List<PmpTeachingPlaceEntity> getPlaceAll(PlaceSearchReq search) {

        return pmpPlaceExMapper.getPlaceList(search);
    }

    @Override
    public Long addOrUpPlace(PlaceEditorReq data, boolean addOn, String authentication) {

        String name = data.getPlaceName();
        Long placeId = data.getPlaceId();
        if (StringUtils.isNotBlank(name)) {

            Long id = pmpPlaceExMapper.selectPlaceByName(name);
            boolean flag = id == null || (!addOn && id.equals(placeId));
            Assert.isTrue(flag, CommonCodeEnum.PARAM_ERROR_USERNAME_MULTI);
        }
        PmpTeachingPlaceEntity entity = mapperFacade.map(data, PmpTeachingPlaceEntity.class);
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);

        // 新增
        if (addOn) {
            Assert.isTrue(StringUtils.isNotBlank(name), CommonCodeEnum.PARAM_ERROR);
            entity.setCreateTime(curTimeSec);
            entity.setCreateManager(operatorId);
            entity.setDelOn(false);
            pmpTeachingPlaceEntityMapper.insert(entity);
            placeId = entity.getPlaceId();

        } else {
            entity.setUpdateTime(curTimeSec);
            entity.setUpdateManager(operatorId);
            pmpTeachingPlaceEntityMapper.updateByPrimaryKeySelective(entity);
        }

        return placeId;
    }

    @Override
    public void deletePlace(Long placeId, String authentication) {
        PmpTeachingPlaceEntity entity = new PmpTeachingPlaceEntity();
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(authentication);
        entity.setPlaceId(placeId);
        entity.setUpdateTime(curTimeSec);
        entity.setUpdateManager(operatorId);
        entity.setDelOn(true);
        pmpTeachingPlaceEntityMapper.updateByPrimaryKeySelective(entity);
    }
}
