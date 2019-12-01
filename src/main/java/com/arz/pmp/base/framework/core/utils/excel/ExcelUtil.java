package com.arz.pmp.base.framework.core.utils.excel;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.arz.pmp.base.framework.core.bean.TestModel;

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

    public static void writeExcelResponse(String fileName, ServletOutputStream out, List<? extends BaseRowModel> list,Class cla) {
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet1 = new Sheet(1, 0, cla);
        sheet1.setSheetName(fileName);
        writer.write(list, sheet1);
        writer.finish();

    }

    public static List<? extends BaseRowModel> getModelList(int size) {

        List<TestModel> list = new ArrayList<TestModel>();

        for (int i = 0; i < size; i++) {
            TestModel data = new TestModel();
            data.setName("name_" + i);
            data.setGender("男");
            list.add(data);
        }
        return list;
    }

}
