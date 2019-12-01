package com.arz.pmp.base.api.service.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.framework.core.utils.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arz.pmp.base.api.bo.log.LogDataResp;
import com.arz.pmp.base.api.bo.log.LogSearchReq;
import com.arz.pmp.base.framework.commons.RequestHeader;
import com.arz.pmp.base.framework.commons.RestRequest;
import com.arz.pmp.base.mapper.ex.PmpLogExMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * description excel业务实现
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/11/14 17:31
 */
@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public List<UserDataImport> importUser(MultipartFile multipartFile) throws Exception {
        InputStream is = multipartFile.getInputStream();
        String name = multipartFile.getOriginalFilename();
        if (is != null) {
            return ExcelUtil.parseExcelToObjList(is, name);
        }
        return null;
    }

}
