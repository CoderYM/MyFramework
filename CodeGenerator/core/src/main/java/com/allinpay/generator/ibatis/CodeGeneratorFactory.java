/**
 * @(#) CodeGeneratorFactory.java
 * module  : codegenerator
 * version : 版本管理系统中的文件版本
 * date    : 2008-12-7
 * name    : 马仁配
 */
package com.allinpay.generator.ibatis;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.allinpay.framework.dao.ibatis.MetaDataRetriever;
import com.allinpay.frameworkdao.ibatis.metadata.TableMetaData;
import com.allinpay.generator.ibatis.generator.BuildFileGenerator;
import com.allinpay.generator.ibatis.generator.DaoCodeGenerator;
import com.allinpay.generator.ibatis.generator.DaoContextXmlGenerator;
import com.allinpay.generator.ibatis.generator.DaoInterfaceCodeGenerator;
import com.allinpay.generator.ibatis.generator.ModelCodeGenerator;
import com.allinpay.generator.ibatis.generator.ModelPkCodeGenerator;
import com.allinpay.generator.ibatis.generator.SqlMapCodeGenerator;
import com.allinpay.generator.ibatis.generator.SqlMapConfigXmlGenerator;
import com.allinpay.util.DateUtil;

/**
 * 
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容. 序号 时间 作者 修改内容 1. 2008-12-7 马仁配 created this
 * class.
 */
public class CodeGeneratorFactory {
	/**
	 * Singleton instance.
	 */
	private static CodeGeneratorFactory factory = new CodeGeneratorFactory();

	/**
	 * All regiested generators.
	 */
	private List<ICodeGenerator> generators = new ArrayList<ICodeGenerator>();

	/**
	 * 当filterTables不为空时,只有表在此列表中的才创建代码.
	 */
	private List filterTables = new ArrayList();

	/**
	 * 当filterModules不为空时,只有在此列表中的模块的表才创建代码.
	 */
	private List filterModules = new ArrayList();

	/**
	 * 缺省的自动生成的文件保存目录.
	 */
	private String defaultDistDir;

	/**
	 * 表模块别名,别名被用于类的包名的最后一部分. key为表名前缀,value为模块名.
	 */
	private static Map tableModuleAlias = new HashMap();
	static {
		tableModuleAlias.put("gw", "gateway");
		tableModuleAlias.put("mb", "member");
		tableModuleAlias.put("sys", "system");
		tableModuleAlias.put("biz", "biz");
		tableModuleAlias.put("batch", "batch");
		tableModuleAlias.put("dkf", "dkf");
		tableModuleAlias.put("boss", "boss");
		tableModuleAlias.put("gp", "guarantee");
		tableModuleAlias.put("stl", "settle");
		tableModuleAlias.put("clr", "clearing");
	}

	/**
	 * Singleton method.
	 * 
	 * @return
	 */
	public static CodeGeneratorFactory getFactory() {
		return factory;
	}

	/**
	 * Default constructor.
	 */
	public CodeGeneratorFactory() {
		super();
		try {
			String path = CodeGeneratorFactory.class.getResource(".").getFile();
			path = URLDecoder.decode(path);
			new FreeMarkerConfig().configureFreeMarker(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the defaultDistDir
	 */
	public String getDefaultDistDir() {
		return defaultDistDir;
	}

	/**
	 * @param defaultDistDir
	 *            the defaultDistDir to set
	 */
	public void setDefaultDistDir(String defaultDistDir) {
		this.defaultDistDir = defaultDistDir;
	}

	public void addFilterTables(String tableName) {
		filterTables.add(tableName);
	}

	public void addFilterModules(String moduleName) {
		filterModules.add(moduleName);
	}

	public void register(ICodeGenerator generator) {
		generators.add(generator);
		generator.setGeneratedRootFilePath(getDefaultDistDir());
	}

	public void generate(String packageName, String author, String time) {
		// get table meta data.
		String context = "com/allinpay/generator/ibatis/context-dao.xml";
		MetaDataRetriever retriever = new MetaDataRetriever(context);
		List tables = retriever.getAllTables();
		for (int i = 0; i < tables.size(); i++) {
			String tableName = ((String) tables.get(i)).toLowerCase();
			String moduleName = getModuleNameFromTableName(tableName);
			if (filterModules.size() == 0
					|| (filterModules.size() > 0 && filterModules
							.contains(moduleName))) {
				if (filterTables.size() == 0
						|| (filterTables.size() > 0 && filterTables
								.contains(tableName))) {
					System.out.println("Generate table:" + tableName);
					generate(packageName, tableName, author, time, retriever);
				}
			}
		}
	}

	protected String getModuleNameFromTableName(String tableName) {
		int index = tableName.indexOf('_');
		if (index <= 0) {
			return "";
		}
		return tableName.substring(0, index);
	}

	protected String getModuleAliasNameFromTableName(String tableName) {
		String moduleName = getModuleNameFromTableName(tableName);
		return (String) tableModuleAlias.get(moduleName);
	}

	public void generate(String packageName, String tableName, String author,
			String time) throws Exception {
		// get table meta data.
		String context = "com/allinpay/generator/ibatis/context-dao.xml";
		MetaDataRetriever retriever = new MetaDataRetriever(context);
		generate(packageName, tableName, author, time, retriever);
	}

	private void generate(String packageName, String tableName, String author,
			String time, MetaDataRetriever retriever) {
		TableMetaData metaData = retriever.getTableMetaData(tableName);
		String moduleName = getModuleNameFromTableName(metaData.getTableName());
		String moduleAliasName = getModuleAliasNameFromTableName(metaData
				.getTableName());
		// set template model.
		Map data = new HashMap();
		data.put("tmd", metaData);
		data.put("prefixPackage", packageName);
		data.put("lastPackageName", tableModuleAlias.get(metaData
				.getTablePrefixName()));
		data.put("author", author);
		data.put("time", time);
		data.put("moduleName", moduleName);
		data.put("moduleAliasName", moduleAliasName);

		// register meta data info.
		Iterator it = generators.iterator();
		while (it.hasNext()) {
			ICodeGenerator generator = (ICodeGenerator) it.next();
			generator.register(data);
		}

		// generate configure files.
		it = generators.iterator();
		while (it.hasNext()) {
			ICodeGenerator generator = (ICodeGenerator) it.next();
			generator.generate(data);
		}
	}

	/**
	 * @param args
	 */
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CodeGeneratorFactory factory = CodeGeneratorFactory.getFactory();
		factory.setDefaultDistDir("E:\\dev-workspace\\generated");
		factory.register(new SqlMapCodeGenerator());
		factory.register(new ModelCodeGenerator());
		factory.register(new DaoCodeGenerator());
		factory.register(new DaoContextXmlGenerator());
		factory.register(new DaoInterfaceCodeGenerator());
		factory.register(new ModelPkCodeGenerator());
		factory.register(new SqlMapConfigXmlGenerator());
		factory.register(new BuildFileGenerator());
		// 小写
//		factory.addFilterTables("SYS_ORGANIZATION".toLowerCase());
//		factory.addFilterTables("GW_PAYMENT_REQUEST".toLowerCase());
//		factory.addFilterTables("GW_PAYMENT_RESPONSE".toLowerCase());
		factory.addFilterTables("GW_PAYMENT_ORDER".toLowerCase());
//		factory.addFilterTables("GW_GATEWAY_ORDER".toLowerCase());
//		factory.addFilterTables("GW_ACCTINFO_ORDER".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_REQUEST".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_RESPONSE".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_CHANNEL".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_CONNECTION".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_CONN_PARA".toLowerCase());
//		factory.addFilterTables("GW_TRANSACTION_ROUTER".toLowerCase());
//		factory.addFilterTables("GW_PAY_TYPE_CONFIG".toLowerCase());
//		factory.addFilterTables("GW_PAY_TRANS_CONFIG".toLowerCase());
//		factory.addFilterTables("GW_PAY_ISSUER_CONFIG".toLowerCase());
		
		// 小写
		factory.addFilterModules("gw");
		factory.generate("com.allinpay.api", "nilomiao", DateUtil
				.formatCurrDateTime(DateUtil.DF_YYYY_MM_DD));
	}
}