/**
 * @(#) GenerateTxConnectionSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2009-8-28
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

/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号	时间			作者			修改内容
 * 1.	2009-8-28	 马仁配		created this class.
 * </pre>
 */
public class GenerateTxConnectionSql {

	public static final String paraFile = "E:/dev-workspace/frame/IPP-env/change/transaction_parameters.xls";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));
			HSSFSheet sheet = workbook.getSheet("connection");
			int rowCount = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell1 = row.getCell(1);
				if (null != cell1) {
					String connId = String.valueOf((int) row.getCell(1).getNumericCellValue());
					String connName = row.getCell(2).toString();
					String channelId = String.valueOf((int) row.getCell(3).getNumericCellValue());
					String orgCode = row.getCell(4).toString();
					String txType = String.valueOf((int) row.getCell(5).getNumericCellValue());
					String interactType = String
							.valueOf((int) row.getCell(6).getNumericCellValue());
					String timeout = String.valueOf((int) row.getCell(7).getNumericCellValue());
					String msgFormat = String.valueOf((int) row.getCell(8).getNumericCellValue());
					String msgType = String.valueOf((int) row.getCell(9).getNumericCellValue());
					String connUrl = null == row.getCell(10) ? null : row.getCell(10).toString();

					StringBuffer buf = new StringBuffer();
					buf.append("INSERT INTO gw_transaction_connection(");
					buf.append("connection_id,name,channel_id,org_id,transaction_type,interact_type,timeout,msg_format,msg_type,transaction_url,state,");
					buf.append("create_datetime,create_operator,");
					buf.append("last_update_datetime,last_update_operator");
					buf.append(") VALUES(");
					buf.append(connId).append(",'").append(connName).append("',");
					buf.append(channelId).append(",'").append(orgCode).append("',");
					buf.append(txType).append(",").append(interactType).append(",");
					buf.append(timeout).append(",").append(msgFormat).append(",");
					buf.append(msgType).append(",'").append(connUrl).append("',1,");
					String datetime = DateUtil.formatCurrDateTime(DateUtil.DF_Y_M_D_HMS);
					appendPara(datetime, buf);
					appendPara(null, buf);
					appendPara(datetime, buf);
					buf.append("null");
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

	private static void appendPara(String para, StringBuffer buf) {
		if (null == para) {
			buf.append(para).append(",");
		} else {
			buf.append("'").append(para).append("',");
		}
	}
}
