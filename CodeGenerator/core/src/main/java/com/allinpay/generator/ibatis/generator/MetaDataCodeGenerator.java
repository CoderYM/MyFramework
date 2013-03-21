/**
 * Created at 2008-01-28.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.allinpay.frameworkdao.ibatis.metadata.TableMetaData;
import com.allinpay.util.StringUtil;
import com.ibm.db2.jcc.DB2Driver;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-28	pony created this class.
 */
public class MetaDataCodeGenerator extends AbstractCodeGenerator {
	public MetaDataCodeGenerator() {
		setTemplateFile("metadata.html");
		setModuleFilePath("model/src/main/java/");
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#createEmptyGeneratedFile(java.lang.String)
	 */
	@Override
	public File createEmptyGeneratedFile(String fileName) {
		String metadata = (String) getModel().get("metadata");
		String pkgDir = getPackageDir();
		pkgDir = getGeneratedFilePath() + File.separator + getModuleFilePath() + File.separator  + pkgDir;
		new File(pkgDir).mkdirs();
		String filepath = pkgDir + File.separator + "Meta" + metadata + ".java";
		File file = new File(filepath);
		return file;
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getPackageIdentifier()
	 */
	@Override
	public String getPackageIdentifier() {
		return "metadata";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.ICodeGenerator#getName()
	 */
	public String getName() {
		return "MetaDataCodeGenerator";
	}

	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#generate(java.util.Map)
	 */
	@Override
	public void generate(Map model) {
		TableMetaData table = (TableMetaData) model.get("tmd");
		if (table.getTableName().equals("sys_meta_data_type")) {
			try {
				generateMetaDataType();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#getRealPackageName()
	 */
	@Override
	protected String getRealPackageName() {
		String pkg = (String) getModel().get("prefixPackage") ;
    	if (!StringUtil.isEmpty(getPackageIdentifier())) {
			pkg += "." + getPackageIdentifier();
    	}
    	return pkg;
	}

	private void generateMetaDataType() throws Exception {
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		DriverManager.registerDriver(new DB2Driver());
		Connection conn_1 = DriverManager.getConnection(
				"jdbc:db2://192.168.31.128:50000/etsgw?user=db2inst1&password=abcdefg");
		String sql = "select * from sys_meta_data_type";
		Statement stmt = conn_1.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int typeValue = rs.getInt(1);
			String name = rs.getString(2);
			String description = rs.getString(3);
			model.put("metadata", StringUtil.capFirst(
					StringUtil.replace_AndLowercaseWithCapital(name)));
			model.put("description", description);
			Connection conn_2 = DriverManager.getConnection(
					"jdbc:mysql://192.168.66.128:3306/oa?user=root&password=abcdefg1");
			sql = "select * from sys_type_meta_data where meta_data_type = " + typeValue 
				+ " union "
				+ "select * from sys_status_meta_data where meta_data_type = " + typeValue;
			Statement stmt_2 = conn_2.createStatement();
			ResultSet rs_2 = stmt_2.executeQuery(sql);
			List types = new ArrayList();
			model.put("types", types);
			while(rs_2.next()) {
				int value = rs_2.getInt(1);
				String typeName = rs_2.getString(3);
				String typeDesc = rs_2.getString(4);
				TypeMetaData type = new TypeMetaData();
				type.setType(value);
				type.setName(StringUtil.capFirst(
						StringUtil.replace_AndLowercaseWithCapital(typeName)));
				type.setDescription(typeDesc);
				type.setMetaDataType(typeValue);
				types.add(type);
			}
			rs_2.close();
			stmt_2.close();
			conn_2.close();
			if (types.size() > 0) {
				super.generate(model);
			}
		}
		rs.close();
		stmt.close();
		conn_1.close();
	}
}
