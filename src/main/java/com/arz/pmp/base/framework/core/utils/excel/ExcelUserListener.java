package com.arz.pmp.base.framework.core.utils.excel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.TypeUtil;
import com.alibaba.fastjson.JSONObject;
import com.arz.pmp.base.api.aop.annotation.PayrollProperty;
import com.arz.pmp.base.api.bo.excel.UserDataImport;
import com.arz.pmp.base.framework.commons.utils.DateUtil;
import com.arz.pmp.base.framework.commons.utils.Util;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

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
    @Override
    public void invoke(Object obj, AnalysisContext context) {

        int rowNum = context.getCurrentRowNum();
        if (Util.isEmpty(obj) || !(obj instanceof List) || rowNum < 0) {
            return;
        }
        List<String> cellList = (List) obj;
        // 判断是否表头
        if (rowNum == 0) {
            cellList.remove(null);
            rowHeader = cellList;
            return;
        }
        // 表数据
        // 循环处理每一行数据
        UserDataImport userData = new UserDataImport();
        for (int i = 0; i < rowHeader.size(); i++) {
            // 先查表头,根据表头查询字段意义
            String headerName = rowHeader.get(i);
            // 实际对应数据
            String cellData = cellList.get(i);
            Field[] fields = UserDataImport.class.getDeclaredFields();
            for (Field field : fields) {
                PayrollProperty payRollProperty = field.getDeclaredAnnotation(PayrollProperty.class);
                if (headerName == null || payRollProperty == null
                        || !headerName.equals(payRollProperty.value())) {
                    continue;
                }
                try {
                    Object cellObj = null;
                    // 判断数据类型
//                    if (payRollProperty.format().equalsIgnoreCase(UserDataImport.dateFormat)) {
//                        //库中存10位
//                        if(StringUtils.isNotBlank(cellData)){
//                            cellObj = DateUtil.getDateSecond(DateUtil.strToDate(cellData, DateUtil.DateStrFormat.f_1));
//                        }
//                    } else {

                        cellObj = TypeUtil.convert(cellData, field, payRollProperty.format(), false);
//                    }

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
                }
            }

        }
        // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
        datas.add(userData);
        // 根据自己业务做处理
        doSomething(userData);
    }

    private void doSomething(Object object) {
        // 1、入库调用接口
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // datas.clear();//解析结束销毁不用的资源
    }

    public List<UserDataImport> getDatas() {
        return datas;
    }

    public void setDatas(List<UserDataImport> datas) {
        this.datas = datas;
    }
}
