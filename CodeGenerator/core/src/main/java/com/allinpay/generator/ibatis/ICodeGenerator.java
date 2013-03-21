/**
 * 
 */
package com.allinpay.generator.ibatis;

import java.util.Map;

/**
 * @author pony
 *
 */
public interface ICodeGenerator {
	void register(Map model);
	
	void generate(Map model);
	
	String getName();
	
	void setGeneratedRootFilePath(String path);
}
