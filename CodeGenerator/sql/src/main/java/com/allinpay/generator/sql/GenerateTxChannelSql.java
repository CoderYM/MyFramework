/**
 * @(#) GenerateTxChannelSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2012-3-23
 * name    : nilomiao
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
 * 1.	2012-3-23	nilomiao	created this class.
 * </pre>
 */
public class GenerateTxChannelSql {

	public static final String paraFile = "E:/dev-workspace/frame/IPP-env/change/transaction_parameters.xls";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));
			HSSFSheet sheet = workbook.getSheet("channel");
			int rowCount = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell1 = row.getCell(1);
				if (null != cell1) {
					String channelId = String.valueOf((int) row.getCell(1).getNumericCellValue());
					String channelName = row.getCell(2).toString();
					String orgCode = row.getCell(3).toString();
					String merchantId = null == row.getCell(4) ? null : row.getCell(4).toString();
					String merchantAcct = null == row.getCell(5) ? null : row.getCell(5).toString();
					String publicKeyPath = null == row.getCell(6) ? null : row.getCell(6)
							.toString();
					String privateKeyPath = null == row.getCell(7) ? null : row.getCell(7)
							.toString();
					String privateKeyPassword = null == row.getCell(8) ? null : row.getCell(8)
							.toString();
					String confPath = null == row.getCell(9) ? null : row.getCell(9).toString();
					String orgPublicKeyPath = null == row.getCell(10) ? null : row.getCell(10)
							.toString();
					String remark = null == row.getCell(11) ? null : row.getCell(11)
							.toString();

					StringBuffer buf = new StringBuffer();
					buf.append("INSERT INTO gw_transaction_channel(");
					buf.append("channel_id,name,org_id,");
					buf.append("state,merchant_id, merchant_acct, public_key_path,");
					buf.append("private_key_path, private_key_password, org_public_key_path,");
					buf.append("conf_path, description,");
					buf.append("create_datetime,create_operator,");
					buf.append("last_update_datetime,last_update_operator");
					buf.append(") VALUES(");
					buf.append(channelId).append(",'").append(channelName).append("','");
					buf.append(orgCode).append("',1,");
					appendPara(merchantId, buf);
					appendPara(merchantAcct, buf);
					appendPara(publicKeyPath, buf);
					appendPara(privateKeyPath, buf);
					appendPara(privateKeyPassword, buf);
					appendPara(orgPublicKeyPath, buf);
					appendPara(confPath, buf);
					appendPara(remark, buf);
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
