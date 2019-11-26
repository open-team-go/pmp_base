package com.arz.pmp.base.framework.commons;

import com.arz.pmp.base.framework.commons.utils.NumberUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * description 请求体头信息
 * 
 * @author chen wei
 * @date 2019/11/11
 */
@Data
@ApiModel
public class RequestHeader implements Serializable {

    @ApiModelProperty("平台信息")
    private String authentication;

    @ApiModelProperty("页码")
    private Integer pageNum;

    @ApiModelProperty("页数大小")
    private Integer pageSize;

    public int confirmCurrentPage() {

        if (NumberUtil.isPositive(pageNum)) {
            return pageNum.intValue();
        }
        return 1;
    }

    public int confirmShowNum() {

        if (NumberUtil.isPositive(pageSize) && pageSize < 100) {
            return pageSize.intValue();
        }
        return 10;
    }

}
