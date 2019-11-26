package com.arz.pmp.base.mapper.ex;

import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PmpPermissionExMapper {

    List<PmpPermissionEntity> selectPermListByRoleId(@Param("roleId") Long roleId);

    List<PmpPermissionEntity> selectPermsListPage();

    List<PmpRoleEntity> selectRolesListPage();

    List<Long> selectPermIds(@Param("roleId") Long roleId, @Param("permIdList") List<Long> permIdList);

    void deleteRolePerm(@Param("roleId") Long roleId, @Param("permIdList") List<Long> permIdList,
        @Param("operationId") Long operationId, @Param("curTime") Long curTime);

    void insertRolePermsButch(@Param("roleId") Long roleId, @Param("permIdList") List<Long> permIdList,
        @Param("operationId") Long operationId, @Param("curTime") Long curTime);
}