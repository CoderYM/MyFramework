/**
 * @(#) GenerateOrgCodeSql.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2012-5-10
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
 * 根据EXCEL表，生成机构的INSERT脚本.
 * 
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号	时间			作者			修改内容
 * 1.	2012-5-10	nilomiao	created this class.
 * </pre>
 */
public class GenerateOrgCodeSql {
	public static final String paraFile = "E:/dev-workspace/frame/IPP-env/change/transaction_parameters.xls";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));
			HSSFSheet sheet = workbook.getSheet("organization");
			int rowCount = sheet.getLastRowNum() + 1;
			for (int i = 1; i < rowCount; i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFCell cell1 = row.getCell(1);
				if (null != cell1) {
					String orgCode = row.getCell(2).toString();
					String orgName = StringUtil.isEmpty(row.getCell(1).toString()) ? orgCode : row.getCell(1)
							.toString();
					String description = null == row.getCell(3) ? "" : row.getCell(3).toString();
					String orgType = String.valueOf((int) row.getCell(4).getNumericCellValue());
					String level = String.valueOf((int) row.getCell(5).getNumericCellValue());

					StringBuffer buf = new StringBuffer();
					buf.append("INSERT INTO sys_organization(");
					buf.append("org_id,org_type,name,description,level,");
					buf.append("create_datetime,create_operator,");
					buf.append("last_update_datetime,last_update_operator");
					buf.append(") VALUES('");
					buf.append(orgCode).append("',");
					buf.append(orgType).append(",'");
					buf.append(orgName).append("','");
					buf.append(description).append("',");
					buf.append(level).append(",");
					buf.append("current timestamp,null,current timestamp, null");
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
}
