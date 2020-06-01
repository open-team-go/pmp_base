package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.api.bo.room.RoomDataResp;
import com.arz.pmp.base.api.bo.room.RoomSearchReq;
import com.arz.pmp.base.entity.PmpTeachingRoomEntity;
import com.arz.pmp.base.entity.PmpTeachingTypeEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpRoomExMapper {

    List<RoomDataResp> selectRoomList(RoomSearchReq search);

    Long selectRoomByName(@Param("roomName") String roomName);

    List<PmpTeachingTypeEntity> selectTypeList();

    List<PmpTeachingRoomEntity> selectRoomSimpleList(RoomSearchReq search);

    List<Long> selectRoomIdsByAdminId(@Param("adminId") Long adminId);

}