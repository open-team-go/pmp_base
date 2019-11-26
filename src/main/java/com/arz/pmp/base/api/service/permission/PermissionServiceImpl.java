package com.arz.pmp.base.api.service.permission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.arz.pmp.base.api.bo.permission.RolePermsData;
import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.entity.PmpPermissionEntity;
import com.arz.pmp.base.entity.PmpRoleEntity;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.constants.Constants;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.redis.RedisKeys;
import com.arz.pmp.base.framework.redis.TimeConstants;
import com.arz.pmp.base.framework.redis.aop.RedisMethodCache;
import com.arz.pmp.base.mapper.PmpRoleEntityMapper;
import com.arz.pmp.base.mapper.ex.PmpPermissionExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * description Permission
 * 
 * @author chen wei
 * @date 2019/11/12
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PmpPermissionExMapper pmpPermissionExMapper;
    @Autowired
    private PmpRoleEntityMapper pmpRoleEntityMapper;
    @Autowired
    private RedisService redisService;

    @Override
    @RedisMethodCache(keyPre = RedisKeys.SYS_OPERATOR_ROLE, expire = TimeConstants.ONE_HOUR_SECONDS)
    public PmpRoleEntity getRoleById(Long roleId) {

        return pmpRoleEntityMapper.selectByPrimaryKey(roleId);
    }

    @Override
    @RedisMethodCache(keyPre = RedisKeys.SYS_OPERATOR_ROLE_PERMISSION, expire = TimeConstants.ONE_HOUR_SECONDS)
    public List<PmpPermissionEntity> getPermListByRoleId(Long roleId) {
        if (roleId == null) {
            return null;
        }
        // 超级管理员
        if (roleId.longValue() == Constants.ADMIN_DEFAULT_ID) {
            return pmpPermissionExMapper.selectPermsListPage();
        } else {
            return pmpPermissionExMapper.selectPermListByRoleId(roleId);
        }
    }

    @Override
    public PageInfo<List<PmpPermissionEntity>> getPermsListPage(RestRequest data) {

        RequestHeader requestHeader = data.getHeader();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpPermissionExMapper.selectPermsListPage();
            }).toPageInfo();
        return pageInfo;
    }

    @Override
    public List<PmpPermissionEntity> getPermsAll() {
        return pmpPermissionExMapper.selectPermsListPage();
    }

    @Override
    public PageInfo<List<PmpRoleEntity>> getRolesListPage(RestRequest data) {

        RequestHeader requestHeader = data.getHeader();
        PageInfo pageInfo = PageHelper.startPage(requestHeader.confirmCurrentPage(), requestHeader.confirmShowNum())
            .doSelectPage(() -> {
                pmpPermissionExMapper.selectRolesListPage();
            }).toPageInfo();
        return pageInfo;
    }

    @Override
    public List<PmpRoleEntity> getRolesAll() {

        return pmpPermissionExMapper.selectRolesListPage();
    }

    @Override
    public void updateRolePerms(RestRequest<RolePermsData> req) {
        RolePermsData data = req.getBody();
        Long roleId = data.getRoleId();
        PmpRoleEntity role = pmpRoleEntityMapper.selectByPrimaryKey(roleId);

        Assert.isTrue(role != null, CommonCodeEnum.PARAM_ERROR);
        Assert.isTrue(roleId != Constants.ADMIN_DEFAULT_ID, CommonCodeEnum.PARAM_ERROR_ADMIN);

        List<Long> permIdList = data.getPermIdList();
        if (CollectionUtils.isEmpty(permIdList)) {

            permIdList = null;
        }
        long curTimeSec = DateUtil.getCurSecond();
        // 操作员信息
        Long operatorId = redisService.getOperatorIdByToken(req.getHeader().getAuthentication());
        // 获取已存在关系
        List<Long> hasPermIdList = pmpPermissionExMapper.selectPermIds(roleId, permIdList);
        // 清除已删除关系
        pmpPermissionExMapper.deleteRolePerm(roleId, permIdList, operatorId, curTimeSec);
        // 绑定新关系
        List<Long> addPermIdList = null;
        if (CollectionUtils.isEmpty(permIdList)) {
            // 清除缓存
            redisService.delRolePerms(roleId);
            return;
        }
        if (CollectionUtils.isEmpty(hasPermIdList)) {
            addPermIdList = permIdList;
        } else {

            addPermIdList = permIdList.stream().filter(id -> !hasPermIdList.contains(id)).collect(Collectors.toList());
        }

        if (CollectionUtils.isEmpty(addPermIdList)) {
            // 清除缓存
            redisService.delRolePerms(roleId);
            return;
        }
        pmpPermissionExMapper.insertRolePermsButch(roleId, addPermIdList, operatorId, curTimeSec);
        // 清除缓存
        redisService.delRolePerms(roleId);
    }
}
