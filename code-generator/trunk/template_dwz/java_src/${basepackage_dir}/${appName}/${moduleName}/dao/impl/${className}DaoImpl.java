<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${appName}.${moduleName}.dao.impl;

import ${basepackage}.framework.repository.mybatis.dao.AbstractBaseMyBatisDao;
import ${basepackage}.framework.repository.mybatis.dao.IMyBatisBaseDao;
import ${basepackage}.${appName}.${moduleName}.model.${className};

import org.springframework.stereotype.Component;


@Component
public class ${className}DaoImpl extends AbstractBaseMyBatisDao<${className}> implements IMyBatisBaseDao<${className}>  {
	
	
}
