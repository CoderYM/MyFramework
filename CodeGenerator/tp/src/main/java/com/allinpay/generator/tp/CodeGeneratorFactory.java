/**
 * @(#) CodeGeneratorFactory.java
 * module  : CodeGenerator
 * version : 版本管理系统中的文件版本
 * date    : 2009-8-5
 * name    : 马仁配
 */
package com.allinpay.generator.tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.allinpay.generator.ibatis.ICodeGenerator;
import com.allinpay.generator.tp.generator.BuildFileGenerator;
import com.allinpay.generator.tp.generator.CommunicationServiceFileGenerator;
import com.allinpay.generator.tp.generator.ContextFileGenerator;
import com.allinpay.generator.tp.generator.OrderNoServiceFileGenerator;
import com.allinpay.generator.tp.generator.SecurityServiceFileGenerator;
import com.allinpay.generator.tp.generator.TPFileGenerator;
import com.allinpay.generator.tp.generator.TxParamsMessageServiceFileGenerator;
import com.allinpay.generator.tp.generator.TxXmlMessageServiceFileGenerator;
import com.allinpay.util.DateUtil;

/**
 * 如果有任何对代码的修改,请按下面的格式注明修改的内容.
 * 序号   时间             作者                   修改内容
 * 1.    2009-8-5       马仁配        created this class.
 */
public class CodeGeneratorFactory {
	/**
	 * Singleton instance.
	 */
	private static CodeGeneratorFactory factory = new CodeGeneratorFactory();
	
    /**
     * Singleton method.
     * @return
     */
    public static CodeGeneratorFactory getFactory() {
    	return factory;
    }
    
	/**
	 * All regiested generators.
	 */
    private List <ICodeGenerator> generators = new ArrayList <ICodeGenerator> ();
    
    /**
     * 缺省的自动生成的文件保存目录.
     */
    private String defaultDistDir;
    
    public void register(ICodeGenerator generator) {
        generators.add(generator);
        generator.setGeneratedRootFilePath(getDefaultDistDir());
    }
    
    /**
	 * @return the defaultDistDir
	 */
	public String getDefaultDistDir() {
		return defaultDistDir;
	}

	/**
	 * @param defaultDistDir the defaultDistDir to set
	 */
	public void setDefaultDistDir(String defaultDistDir) {
		this.defaultDistDir = defaultDistDir;
	}
    
    public void generate(
    		String packageName, 
    		String author, 
    		String time,
    		String ebank,
    		String bankType) {
		//set template model.
        Map data = new HashMap ();
        data.put("prefixPackage", packageName);
        data.put("author", author);
        data.put("time", time);
        data.put("ebank", ebank);
        data.put("ebankType", bankType);
        
        //register meta data info.
        Iterator it = generators.iterator();
        while (it.hasNext()) {
            ICodeGenerator generator = (ICodeGenerator) it.next();
            generator.register(data);
        }
        
        //generate configure files.
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
        factory.register(new ContextFileGenerator());
        factory.register(new OrderNoServiceFileGenerator());
        factory.register(new SecurityServiceFileGenerator());
        factory.register(new TPFileGenerator());
        factory.register(new TxParamsMessageServiceFileGenerator());
        factory.register(new TxXmlMessageServiceFileGenerator());
        factory.register(new CommunicationServiceFileGenerator());
        factory.register(new BuildFileGenerator());
        factory.generate(
        		"com.allinpay.api", 
        		"nilomiao",
        		DateUtil.formatCurrDateTime(DateUtil.DF_YYYY_MM_DD),
        		"abc", 
        		"st");
    }
}
