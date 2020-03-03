package com.arz.pmp.base.framework.core.utils.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.TypeUtil;
import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.framework.commons.enums.CommonCodeEnum;
import com.arz.pmp.base.framework.commons.utils.Assert;
import com.arz.pmp.base.framework.commons.utils.Util;
import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * description: java类作用描述
 *
 * @author chen wei
 * @date 2019/7/13 14:52
 * @version: 1.0
 *           <p>
 *           Copyright: Copyright (c) 2019
 *           </p>
 */
public class ExcelUserListener extends AnalysisEventListener {

    /**
     * 自定义用于暂时存储data,可以通过实例获取该值
     */
    private List<UserDataImport> datas = new ArrayList<UserDataImport>();
    private List<String> rowHeader = null;// 表头
    private int headerNum = 29;

    @Override
    public void invoke(Object obj, AnalysisContext context) {

        int rowNum = context.getCurrentRowNum();
        if (Util.isEmpty(obj) || !(obj instanceof List) || rowNum < 0) {
            Assert.isTrue(true, CommonCodeEnum.PARAM_ERROR, "列数错误，请参照导出文档列名");
        }
        List<String> cellList = (List)obj;
        // 判断是否表头
        if (rowNum == 0) {
            cellList.remove(null);
            Assert.isTrue(cellList.size() == headerNum, CommonCodeEnum.PARAM_ERROR, "列数错误，请参照导出文档列名");
            rowHeader = cellList;
            return;
        }
        // 表数据
        // 循环处理每一行数据
        UserDataImport userData = new UserDataImport();
        boolean flag = false;
        for (int i = 0; i < rowHeader.size(); i++) {
            // 先查表头,根据表头查询字段意义
            String headerName = rowHeader.get(i);
            // 实际对应数据
            String cellData = cellList.get(i);
            Field[] fields = UserDataImport.class.getDeclaredFields();
            boolean headerFlag = false;
            for (Field field : fields) {
                PayrollProperty payRollProperty = field.getDeclaredAnnotation(PayrollProperty.class);
                if (payRollProperty == null) {
                    continue;
                }
                if (headerName == null || !headerName.equals(payRollProperty.value())) {
                    continue;
                }
                flag = true;
                headerFlag = true;
                try {
                    Object cellObj = null;

                    cellObj = TypeUtil.convert(cellData, field, payRollProperty.format(), false);
                    if (cellObj == null) {
                        break;
                    }
                    if (cellObj instanceof String) {
                        cellObj = ((String)cellObj).trim();
                    }
                    PropertyUtils.setProperty(userData, field.getName(), cellObj);
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            Assert.isTrue(headerFlag, CommonCodeEnum.PARAM_ERROR, "列名：" + headerName + "不正确，请参照导出文档列名");
        }
        // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
        if (flag) {
            datas.add(userData);
        }
        // 根据自己业务做处理
        doSomething(userData);

    }

    private void doSomething(Object object) {
        // 1、入库调用接口
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        datas.clear();// 解析结束销毁不用的资源
    }

    public List<UserDataImport> getDatas() {
        return datas;
    }

    public void setDatas(List<UserDataImport> datas) {
        this.datas = datas;
    }
}
