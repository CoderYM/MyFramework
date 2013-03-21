/**
 * @(#) GenerateTableCreateSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2012-2-29
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

import com.allinpay.util.StringUtil;

/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容. 
 * 序号 	时间 		作者 		修改内容 
 * 1. 	2012-2-29 	nilomiao 	created this class.
 * </pre>
 */
public class GenerateTableCreateSql {
	public static final String paraFile = "D:/ITS非交易表数据库字典.xls";

	private static String tableName = "SYS_CODES"; // 可在此指定表名生成脚本;为空时表示按目录生成所有表的脚本

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));

			if (StringUtil.isEmpty(tableName)) {
				// 读取所有表
				// Map map = new HashMap();
				HSSFSheet sheet = workbook.getSheet("目录");
				int rowCount = sheet.getLastRowNum() + 1; // 总行数
				HSSFRow row = null;// 表头
				for (int i = 3; i < rowCount; i++) {
					row = sheet.getRow(i);
					HSSFCell cell1 = row.getCell(1);
					if (null == cell1) {
						break;
					}
					// map.put(cell1.toString(), row.getCell(2).toString());
					// System.out.println("generateSql(workbook.getSheet(\"" +
					// cell1.toString() +"\"));");
					generateSql(workbook.getSheet(cell1.toString()));
				}
			} else {
				generateSql(workbook.getSheet(tableName));
			}

			// generateSql(workbook.getSheet("GW_PAYMENT_REQUEST"));
			//generateSql(workbook.getSheet("GW_TRANSACTION_ROUTER"));
			// generateSql(workbook.getSheet("GW_PAYMENT_ORDER"));
			// generateSql(workbook.getSheet("GW_GATEWAY_ORDER"));
			// generateSql(workbook.getSheet("GW_ACCTINFO_ORDER"));
			// generateSql(workbook.getSheet("GW_CONTRACT"));
			// generateSql(workbook.getSheet("GW_ACCOUNT_BALANCE"));
			// generateSql(workbook.getSheet("GW_ACCOUNT_TX_DETAIL"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_REQUEST"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_RESPONSE"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_CHANNEL"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_CONNECTION"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_CONN_PARA"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_ROUTER"));
			// generateSql(workbook.getSheet("GW_PAY_TYPE_CONFIG"));
			// generateSql(workbook.getSheet("GW_PAY_TRANS_CONFIG"));
			// generateSql(workbook.getSheet("GW_PAY_ISSUER_CONFIG"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_ROUTER_MAP"));
			// generateSql(workbook.getSheet("GW_TRANSACTION_ROUTER_MAP_APPLY"));
			// generateSql(workbook.getSheet("GW_PAYMENT_CONFIG"));
			// generateSql(workbook.getSheet("GW_PAGE_CONFIG"));
			// generateSql(workbook.getSheet("SYS_ORGNIZATION"));
			// generateSql(workbook.getSheet("SYS_MESSAGE_REQUEST"));
			// generateSql(workbook.getSheet("SYS_EXCHANGE_RATE"));
			// generateSql(workbook.getSheet("SYS_MCHT_EXCHANGE_RATE"));
			// generateSql(workbook.getSheet("SYS_CATEGORY"));
			// generateSql(workbook.getSheet("SYS_CODES"));
			// generateSql(workbook.getSheet("SYS_RISK_RULE"));
			// generateSql(workbook.getSheet("SYS_RISK_LOG"));
			// generateSql(workbook.getSheet("SYS_BLACKLIST"));
			// generateSql(workbook.getSheet("SYS_PROPERTIES"));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateSql(HSSFSheet sheet) {
		try {
			int rowCount = sheet.getLastRowNum() + 1;

			// 表头
			HSSFRow row = sheet.getRow(1);
			String[] tableInfo = row.getCell(1).toString().split(":");
			String tableName = tableInfo[1].trim().toUpperCase();
			String tableDesc = tableInfo[0].trim();

			StringBuffer buf = new StringBuffer();
			buf.append("--==============================================================").append(
					"\n");
			buf.append("-- TABLE: \"").append(tableName).append("\"").append("\n");
			buf.append("-- DESC: ").append(tableDesc).append("\n");
			buf.append("--==============================================================").append(
					"\n");
			buf.append("CREATE TABLE ").append(tableName).append(" (").append("\n");

			StringBuffer dbuf = new StringBuffer(); // 描述
			dbuf.append("COMMENT ON TABLE ").append(tableName).append(" IS '").append(tableDesc)
					.append("';").append("\n");

			// 表字段
			for (int i = 3; i < rowCount; i++) {
				row = sheet.getRow(i);
				HSSFCell cell1 = row.getCell(1);
				if (null == cell1) {
					continue;
				}
				String field = row.getCell(1).toString().toUpperCase();
				String type = row.getCell(2).toString().toUpperCase();
				String desc = row.getCell(3).toString();

				buf.append("	");
				buf.append(field);
				buf.append("	");
				buf.append(type);
				if (i == 3) { // 首字段为主键
					buf.append("	NOT NULL");
				}
				buf.append(",").append("\n");

				dbuf.append("COMMENT ON COLUMN ").append(tableName).append(".").append(field)
						.append(" IS '").append(desc).append("';").append("\n");
			}
			String pkColumn = sheet.getRow(3).getCell(1).toString().toUpperCase();
			String pkType = sheet.getRow(3).getCell(2).toString().toUpperCase();
			String pkLable = sheet.getRow(3).getCell(3).toString();

			buf.append("	CONSTRAINT PK_").append(tableName).append("_ID PRIMARY KEY (")
					.append(pkColumn).append(")").append("\n");
			buf.append("); ").append("\n");
			/**
			 * DB2的索引空间
			 
			buf.append("	IN \"DATSPACE02\" INDEX IN \"IDXSPACE01\";").append("\n \n");
			 */
			buf.append(dbuf.toString()).append("\n");

			if (pkLable.indexOf("<seq>") != -1) {
				/**  db2 的序列
				 
				buf.append("CREATE SEQUENCE SEQ_").append(tableName).append("_").append(pkColumn)
						.append(" AS ").append(pkType)
						.append(" START WITH 1 INCREMENT BY 1 CACHE 20 NO CYCLE NO ORDER; \n");
						* 
				 */
				//  oracle 的序列
				 
				buf.append("CREATE SEQUENCE ").append("SEQ_").append(tableName)
						.append(" minvalue 1 maxvalue 9999999999999999999999999999 start with 100 increment by 1 cache 20; \n");
			}
			System.out.println(buf.toString());
			System.out.println("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
