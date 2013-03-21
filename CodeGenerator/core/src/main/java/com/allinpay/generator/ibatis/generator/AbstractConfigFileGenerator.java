/**
 * Created at 2008-01-23.
 */
package com.allinpay.generator.ibatis.generator;

import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import com.allinpay.frameworkdao.ibatis.metadata.TableMetaData;
import com.allinpay.util.FileUtil;

import freemarker.template.Template;

/**
 * @author pony * 如果有任何对代码的修改,请按下面的格式注明修改的内容. * 序号   时间       作者        修改内容 * 1.  2008-1-23	pony created this class.
 */
public abstract class AbstractConfigFileGenerator extends AbstractCodeGenerator {
	private static final String tokenBegin = "<!--Auto Generated Begin-->";
	private static final String tokenEnd = "<!--Auto Generated End-->";
	/**
	 * 把已经生成的配置文件内容读取出.
	 * @param filename
	 * @return
	 */
	public String readGeneratedContent(String filename) {
		try {
			String temp = FileUtil.read(filename);
			return parseGeneratedContent(temp);
		} catch (IOException e) {
		}
		return "";
	}
	
	public String parseGeneratedContent(String content) {
		int index1 = content.indexOf(tokenBegin);
		if (index1 == -1) {
			return "";
		}
		int index2 = content.indexOf(tokenEnd, index1);
		String result = content.substring(index1+tokenBegin.length(), index2);
		return result;
	}
	
	public String mergeContent(String oldContent, String newContent) {
		String temp = parseGeneratedContent(newContent);
		String content = oldContent + temp;
		int index1 = newContent.indexOf(tokenBegin);
		if (index1 == -1) {
			return "";
		}
		String prefix = newContent.substring(0, index1+tokenBegin.length());
		int index2 = newContent.indexOf(tokenEnd, index1);
		String postfix = newContent.substring(index2);
		content = prefix + content + postfix;
		return content;
	}
	
	abstract String getGeneratedFilename(String fileName);
	
	/* (non-Javadoc)
	 * @see com.allinpay.generator.ibatis.generator.AbstractCodeGenerator#generate(java.util.Map)
	 */
	@Override
	public void generate(Map model) {
		this.model = model;
		Writer writer = null;
		FileWriter fw = null;
		try {
			writer = new CharArrayWriter();
			Template modelDao = getTemplate(getTemplateFile());
			model.put("package", getRealPackageName());
			modelDao.process(model, writer);
			writer.flush();
			String filename = ((TableMetaData)model.get("tmd")).getJavaName();
			String generatedContent = readGeneratedContent(getGeneratedFilename(filename));
			String newContent = writer.toString();
			String content = mergeContent(generatedContent, newContent);
			File file = createEmptyGeneratedFile(filename);
			fw = new FileWriter(file);
			fw.write(content);
			fw.flush();
			fw.close();
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
