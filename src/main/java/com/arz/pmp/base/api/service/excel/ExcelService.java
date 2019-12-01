package com.arz.pmp.base.api.service.excel;

import java.util.List;

import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * description excel业务
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/12/1 17:31
 */
public interface ExcelService {

    List<UserDataImport> importUser(MultipartFile multipartFile) throws Exception;
}
