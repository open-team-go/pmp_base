package com.arz.pmp.base.api.controller.back;

import com.arz.pmp.base.api.service.redis.RedisService;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.framework.commons.response.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "缓存管理API集", tags = "缓存管理API集")
@RestController
@RequestMapping("/cache")
public class CacheCleanRestController {

    @Resource
    private RedisService redisService;

    @ApiOperation(value = "清楚系統配置緩存", notes = "清楚系統配置緩存")
    @PostMapping("/clean/conf")
    public RestResponse cleanConf(@RequestBody RestRequest data) {

        redisService.cleanAllCache();

        return RestResponse.success();
    }
}
