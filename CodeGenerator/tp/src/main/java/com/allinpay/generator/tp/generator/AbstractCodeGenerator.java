/**
 * Created at 2007-12-13 by pony
 */
package com.allinpay.generator.tp.generator;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import com.allinpay.generator.ibatis.ICodeGenerator;
import com.allinpay.util.FileUtil;
import com.allinpay.util.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 */
public abstract class AbstractCodeGenerator implements ICodeGenerator {
    /**
     * Template file name, not include the file path.
     */
	private String templateFile;
	
	/**
	 * Generated file, only include the file path.
	 */
	private String generatedRootFilePath;
	
	private String moduleFilePath;
	
	/**
	 * model.
	 */
	protected Map model;

	/**
	 * @return the templateFile
	 */
	public String getTemplateFile() {
		return templateFile;
	}

	/**
	 * @param templateFile the templateFile to set
	 */
	public void setTemplateFile(String templateFile) {
		this.templateFile = templateFile;
	}

	/**
	 * @return the generatedFilePath
	 */
	public String getGeneratedFilePath() {
		return generatedRootFilePath;
	}

	/**
	 * @param generatedFilePath the generatedFilePath to set
	 */
	public void setGeneratedRootFilePath(String generatedFilePath) {
		this.generatedRootFilePath = generatedFilePath;
	}

	/**
	 * @return the moduleFilePath
	 */
	public String getModuleFilePath() {
		return moduleFilePath;
	}

	/**
	 * @param moduleFilePath the moduleFilePath to set
	 */
	public void setModuleFilePath(String moduleFilePath) {
		this.moduleFilePath = moduleFilePath;
	}

	/**
	 * @return Returns the model.
	 */
	public Map getModel() {
		return model;
	}

	/**
	 * @param model The model to set.
	 */
	public void setModel(Map model) {
		this.model = model;
	}

	/**
	 * Read template.
	 * @param templateFilePath
	 * @return
	 * @throws IOException
	 */
    protected Template getTemplate(String templateFilePath) throws IOException  {
    	Configuration conf = Configuration.getDefaultConfiguration();
    	conf.setEncoding(Locale.CHINA, "utf-8");
    	conf.setOutputEncoding("utf-8");
    	conf.setDirectoryForTemplateLoading(new File("E:/dev-workspace/ets/CodeGenerator/tp/src/main/java/com/allinpay/generator/tp/ftl/"));
        return conf.getTemplate(templateFilePath);
    }
    
    /**
     * pkg diretory.
     * @return
     */
    protected String getPackageDir() {
    	String pkg = getRealPackageName();
    	String pkgDir = pkg.replaceAll("[.]", "/");
    	return pkgDir;
    }
    
    protected String getRealPackageName() {
    	String pkg = (String) getModel().get("prefixPackage");
    	if (!StringUtil.isEmpty(getPackageIdentifier())) {
			pkg += "." + getPackageIdentifier();
    	}
		pkg	+= "." + (String) getModel().get("lastPackageName");
    	return pkg;
    }
    
    public abstract File createEmptyGeneratedFile(String filePath, String fileName);
    
    public abstract String getPackageIdentifier();
    
    public void register(Map model) {
    	this.model = model;
    }
    
	/* (non-Javadoc)
	 * @see com.allinpay.ibatis.generaotor.sqlmap.ICodeGenerator#generate(java.util.Map)
	 */
	public void generate(final Map model) {
		this.model = model;
		Writer writer = null;
		FileWriter fw = null;
		try {
			writer = new CharArrayWriter();
			Template template = getTemplate(getTemplateFile());
			model.put("package", getRealPackageName());
			template.process(model, writer);
			writer.flush();
			File file = createEmptyGeneratedFile((String)model.get("ebankType"), (String)model.get("ebank"));
			file.delete();
			OutputStream os = new FileOutputStream(file);
			FileUtil.save(os, writer.toString().getBytes("utf-8"));
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != writer) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != fw) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
