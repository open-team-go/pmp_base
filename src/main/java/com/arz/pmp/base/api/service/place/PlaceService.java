package com.arz.pmp.base.api.service.place;

import com.arz.pmp.base.api.bo.place.PlaceEditorReq;
import com.arz.pmp.base.api.bo.place.PlaceSearchReq;
import com.arz.pmp.base.entity.PmpTeachingPlaceEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 教学点业务
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
public interface PlaceService {

    PageInfo<List<PmpTeachingPlaceEntity>> getPlaceListPage(RestRequest<PlaceSearchReq> req);

    List<PmpTeachingPlaceEntity> getPlaceAll(PlaceSearchReq search);

    Long addOrUpPlace(PlaceEditorReq data, boolean addOn, String authentication);

    void deletePlace(Long placeId, String authentication);

}
