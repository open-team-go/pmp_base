package com.arz.pmp.base.framework.core.utils.excel;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.arz.pmp.base.api.bo.excel.UserDataExport;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.exception.BaseException;
import com.arz.pmp.base.framework.commons.exception.BusinessException;
import com.arz.pmp.base.framework.commons.exception.ParamException;
import com.arz.pmp.base.framework.core.bean.TestModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * description 使用ali easyExcel工具包
 *
 * @author chen wei
 * @version 1.0
 *          <p>
 *          Copyright: Copyright (c) 2019
 *          </p>
 * @date 2019/7/13 14:45
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * description
     *
     * @param filePath
     *            path
     * @return java.io.InputStream
     * @author chen wei
     * @date 2019/7/13
     */
    public static InputStream getInputStream(String filePath) throws FileNotFoundException {

        return new BufferedInputStream(new FileInputStream(new File(filePath)));
    }

    /**
     * description
     *
     * @param filePath
     *            path
     * @return java.io.OutputStream
     * @author chen wei
     * @date 2019/7/13
     */
    public static OutputStream getOutputStream(String filePath) throws FileNotFoundException {

        return new BufferedOutputStream(new FileOutputStream(new File(filePath)));
    }

    public static void writeExcelResponse(String fileName, ServletOutputStream out, List<? extends BaseRowModel> list,
        Class cla) {
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet1 = new Sheet(1, 0, cla);
        sheet1.setSheetName(fileName);
        writer.write(list, sheet1);
        writer.finish();

    }

    /**
     *
     * @return List<Object>
     */
    public static List<UserDataImport> parseExcelToObjList(InputStream inputStream, String fileName)
        throws BaseException {
        try {
            if (inputStream != null) {
                FileInputStream fis = (FileInputStream)inputStream;
                ExcelUserListener listener = new ExcelUserListener();
                ExcelReader excelReader = null;
                if (fileName != null) {
                    if (StringUtils.endsWithIgnoreCase(fileName, ExcelTypeEnum.XLSX.toString())) {

                        excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
                    }
                    if (StringUtils.endsWithIgnoreCase(fileName, ExcelTypeEnum.XLS.toString())) {
                        excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, null, listener);
                    }
                }
                if (excelReader != null) {
                    excelReader.read();
                    return listener.getDatas();
                }
            }
        } catch (Exception e) {
            String message = e.getMessage();
            logger.info("parseExcelToObjList  Exception : {} ", e);
            throw new BusinessException(CommonCodeEnum.SYSTEM_ERROR_EXCEL_PARSING_ERROR.getCode(), message);
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
