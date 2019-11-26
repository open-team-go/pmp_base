package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.place.PlaceSearchReq;
import com.arz.pmp.base.entity.PmpTeachingPlaceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpPlaceExMapper {

    List<PmpTeachingPlaceEntity> getPlaceList(PlaceSearchReq search);

    Long selectPlaceByName(@Param("placeName") String placeName);
}