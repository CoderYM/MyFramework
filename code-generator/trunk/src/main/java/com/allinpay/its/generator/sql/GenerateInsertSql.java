package com.allinpay.its.generator.sql;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * <pre>
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号	时间			作者			修改内容
 * 1.	2012-3-23	zl	created this class.
 * </pre>
 */
public class GenerateInsertSql {
	
	public static final String paraFile = "e://Trans_Parameter.xls";
	public static final String rn = "\n";//File.separator;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//due to poi version limit, not support excel file xlsx
		
		List<String> metas = new ArrayList<String>();
		//meta should be formated as: sheetName:tableName#columnName:columnType,columnName1:columnType2
		//columnType default is string if you don't give
		
		//channel meta
		metas.add("Channel:gw_trans_channel#CHANNEL_ID:int,NAME,ALIAS_NAME,ORG_ID,MERCHANT_ID,MERCHANT_ACCT,PUBLIC_KEY_PATH,PRIVATE_KEY_PATH,PRIVATE_KEY_PASSWORD,ORG_PUBLIC_KEY_PATH,CONF_PATH,CHECK_FILE_PATH,STATE,TYPE,DESCRIPTION,CREATE_DATETIME:datetime,CREATE_OPERATOR,LAST_UPDATE_DATETIME:datetime,LAST_UPDATE_OPERATOR");
		//connect meta
		metas.add("Connect:gw_trans_connect#CONNECT_ID:int,NAME,STATE,CHANNEL_ID:int,TRANSACTION_TYPE:int,INTERACT_TYPE,TIMEOUT:int,MSG_FORMAT,MSG_TYPE,TRANSACTION_URL,MSG_TEMPLATE,MSG_CHARSET,SEND_METHOD,TARGET_TYPE,CREATE_DATETIME:datetime,CREATE_OPERATOR,LAST_UPDATE_DATETIME:datetime,LAST_UPDATE_OPERATOR");
		//parameter meta
		metas.add("Parameter:gw_trans_conn_para#PARAMETER_ID:int,CONNECT_ID:int,TRANSACTION_STAGE,,PARAMETER_NAME,DEFAULT_PARAMETER_VALUE,VARIABLE_NAME,MAPPING_TYPE,ENCRYPTION_SEQUENCE:int,CREATE_DATETIME:datetime,CREATE_OPERATOR,LAST_UPDATE_DATETIME:datetime,LAST_UPDATE_OPERATOR");
		//variable_map meta
		metas.add("VarMap:gw_mapping_variable#ROW_ID:int,CHANNEL_ID,TRANSACTION_TYPE:int,VAR_NAME,CNL_VAR_VALUE,ITS_VAR_VALUE,CREATE_DATETIME:datetime,CREATE_OPERATOR,LAST_UPDATE_DATETIME:datetime,LAST_UPDATE_OPERATOR");
		//retcode_map meta
		metas.add("RetCodeMap:gw_mapping_ret_code#ROW_ID:int,CHANNEL_ID,TRANSACTION_TYPE:int,CNL_RET_CODE,CNL_RET_MSG,ITS_RET_CODE,ITS_RET_MSG,TRANS_STATE:int,TYPE,CREATE_DATETIME:datetime,CREATE_OPERATOR,LAST_UPDATE_DATETIME:datetime,LAST_UPDATE_OPERATOR");
		
		//store meta data
		Map<String,String[]> metaData = new LinkedHashMap<String,String[]>();
		for(String meta:metas) {
			metaData.put(meta.split("#")[0], meta.split("#")[1].split(","));
		}
		
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(paraFile));//load excel
			for(String key: metaData.keySet()) {//loop to process
				String[] tmp = key.split(":");//sheetName:tableName
				HSSFSheet sheet = workbook.getSheet(tmp[0]);//get sheet according sheetName
				System.out.print(
						"-- Insert SQL for table " + tmp[1] + rn +
						//"delete from " + tmp[1] + ";" + rn +
						gen(tmp[1],sheet, metaData.get(key)));//generate SQL and print
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * generate inset sql from excel sheet
	 * @param tableName
	 * @param sheet
	 * @param meta
	 * @return
	 */
	private static String gen(String tableName, HSSFSheet sheet, String[] meta) {
		StringBuilder buf = new StringBuilder();
		buf.append("insert into "+tableName+"(");
		
		int rowCount = sheet.getLastRowNum() + 1;
		String[] columnNames = new String[meta.length];
		String[] columnTypes = new String[meta.length];
		String[] tmp;
		
		//get table columns' names and types
		for(int i=0; i<meta.length; i++) {
			if("".equals(meta[i].trim())) {
				columnNames[i] = null;
				columnTypes[i] = null;
				continue;
			}
			tmp = meta[i].split(":");
			buf.append(tmp[0]).append(",");
			columnNames[i] = tmp[0];
			columnTypes[i] = tmp.length>1?tmp[1]:null;
		}
		
		buf.replace(buf.length()-1, buf.length(), ") values(");
		
		String sqltmp = buf.toString();
		buf.setLength(0);
		
		for (int i = 1; i < rowCount; i++) {
			HSSFRow row = sheet.getRow(i);
			buf.append(sqltmp);
			for(int j=0; j<meta.length; j++) {
				if(columnNames[j] == null) {
					continue;
				}
				HSSFCell cell = row.getCell(j);
				if (null != cell) {
					if("int".equals(columnTypes[j])) {
						buf.append(cell.toString().split("\\.")[0]+",");
					} else {
						//System.out.println(columnNames[j]+" "+(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC));
						buf.append("'"+cell.toString()+"',");
					}
				} else {
					if("datetime".equals(columnTypes[j])){
						buf.append("sysdate,");
					} else {
						buf.append("null,");
					}
				}
			}
			buf.replace(buf.length()-1, buf.length(), ");"+rn);
		}
		return buf.toString();
	}
}
