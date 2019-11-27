package com.arz.pmp.base.api.service.room;

import com.arz.pmp.base.api.bo.room.RoomEditorReq;
import com.arz.pmp.base.api.bo.room.RoomSearchReq;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;
import com.arz.pmp.base.entity.PmpTeachingTypeEntity;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * description 班级业务
 *
 * @author chen wei
 * @version 1.0
 * <p>
 * Copyright: Copyright (c) 2019
 * </p>
 * @date 2019/11/14 17:31
 */
public interface RoomService {

    PageInfo<List<PmpTeachingRoomEntity>> getRoomListPage(RestRequest<RoomSearchReq> req);

    List<PmpTeachingRoomEntity> getRoomAll(RestRequest<RoomSearchReq> req);

    Long addOrUpRoom(RoomEditorReq data, boolean addOn, String authentication);

    void deleteRoom(Long RoomId, String authentication);

    List<PmpTeachingTypeEntity> getTypeList();

}
