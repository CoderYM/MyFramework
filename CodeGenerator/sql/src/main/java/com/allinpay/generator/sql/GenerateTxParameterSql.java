/**
 * @(#) GenerateTxParameterSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2009-8-7
 * name    : 马仁配
 */
package com.allinpay.generator.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.allinpay.util.DateUtil;
import com.allinpay.util.StringUtil;

/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容. 
 * 序号 	时间 		作者 		修改内容
 * 1. 	2009-8-7 	马仁配		created this class.
 * 2. 	2009-10-28 	nilomiao 	modified this class. 增加了convert,convertInt方法
 * 如果运行时出现异常，请检查poi包，移到build path的最上层
 * </pre>
 */
public class GenerateTxParameterSql {

	public static final String paraFile = "E:/dev-workspace/frame/IPP-env/change/transaction_parameters.xls";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					paraFile));
			HSSFSheet sheet = workbook.getSheet("parameters");
			int rowCount = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell0 = row.getCell(0);
				if (null != cell0) {
					String paraId = convertInt(row.getCell(0));
					String connId = convertInt(row.getCell(1));
					String txStage = convertInt(row.getCell(2));
					String englishName = convert(row.getCell(4));
					String defaultValue = convert(row.getCell(6));
					String variableName = convert(row.getCell(7));
					String signIndex = convertInt(row.getCell(8));

					StringBuffer buf = new StringBuffer();
					buf.append("INSERT INTO gw_transaction_conn_para(");
					buf.append("parameter_id,connection_id,transaction_stage,");
					buf.append("parameter_name,default_parameter_value,");
					buf
							.append("variable_name,encryption_sequence,create_datetime,");
					buf
							.append("create_operator,last_update_datetime,last_update_operator");
					buf.append(") VALUES(");
					buf.append(paraId).append(",").append(connId);
					buf.append(",").append(txStage).append(",");
					buf.append("'").append(englishName).append("',");
					if (StringUtil.isEmpty(defaultValue)) {
						buf.append("null").append(",");
					} else {
						buf.append("'").append(defaultValue).append("',");
					}
					if (StringUtil.isEmpty(variableName)) {
						buf.append("null").append(",");
					} else {
						buf.append("'").append(variableName).append("',");
					}
					if (StringUtil.isEmpty(signIndex) || "0".equals(signIndex)) {
						buf.append("null").append(",");
					} else {
						buf.append(signIndex).append(",");
					}
					String datetime = DateUtil
							.formatCurrDateTime(DateUtil.DF_Y_M_D_HMS);
					buf.append("'").append(datetime).append("',")
							.append("null").append(",");
					buf.append("'").append(datetime).append("',")
							.append("null");
					buf.append(");");
					System.out.println(buf.toString());
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String convert(HSSFCell ce) {
		if (null != ce) {
			return ce.toString();
		} else {
			return "";
		}
	}

	private static String convertInt(HSSFCell ce) {
		if (null != ce) {
			return String.valueOf((int) ce.getNumericCellValue());
		} else {
			return "";
		}
	}
}
