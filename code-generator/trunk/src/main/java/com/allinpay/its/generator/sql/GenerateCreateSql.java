/**
 * @(#) GenerateTableCreateSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2012-2-29
 * name    : nilomiao
 */
package com.allinpay.its.generator.sql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容. 
 * 序号 	时间 		作者 		修改内容 
 * 1. 	2012-2-29 	nilomiao 	created this class.
 * </pre>
 */
public class GenerateCreateSql {
	
	public static final String paraFile = "D:/repository/ITS/docs/03.系统设计/数据库设计/ITS交易表数据库字典.xls";

	private static String tableName = "GW_MAPPING_VARIABLE,GW_MAPPING_RET_CODE";//"GW_TRANSACTION_VIRTUAL_CNL,GW_PARA_VALUE_MAPPING"; // 可在此指定表名生成脚本;为空时表示按目录生成所有表的脚本

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));

			if (StringUtils.isBlank(tableName)) {
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
					generateSql(workbook.getSheet(cell1.toString()));
				}
			} else {
				for(String tn:tableName.split(",")) {
					generateSql(workbook.getSheet(tn.trim()));
				}
			}
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
			buf.append("DROP TABLE ").append(tableName).append(";\n");
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

				buf.append(" ");
				buf.append(field);
				buf.append(" ");
				buf.append(type);
				if (i == 3) { // 首字段为主键
					buf.append(" NOT NULL");
				}
				buf.append(",").append("\n");

				dbuf.append("COMMENT ON COLUMN ").append(tableName).append(".").append(field)
						.append(" IS '").append(desc).append("';").append("\n");
			}
			String pkColumn = sheet.getRow(3).getCell(1).toString().toUpperCase();
			String pkType = sheet.getRow(3).getCell(2).toString().toUpperCase();
			String pkLable = sheet.getRow(3).getCell(3).toString();

			buf.append(" CONSTRAINT PK_").append(tableName).append("_ID PRIMARY KEY (")
					.append(pkColumn).append(")").append("\n");
			buf.append(")").append(";\n");
			//buf.append("	IN \"DATSPACE02\" INDEX IN \"IDXSPACE01\";").append("\n \n");

			buf.append(dbuf.toString()).append("\n");

			if (pkLable.indexOf("<seq>") != -1) {
				buf.append("DROP SEQUENCE SEQ_").append(tableName).append(";\n");
				buf.append("CREATE SEQUENCE SEQ_").append(tableName)
						//.append("_").append(pkColumn)
						//.append(" AS ").append(pkType)
						.append(" START WITH 1 INCREMENT BY 1 CACHE 20 NOCYCLE NOORDER; \n");
			}
			System.out.println(buf.toString());
			System.out.println("");
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
