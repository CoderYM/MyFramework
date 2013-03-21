<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.${appName}.${moduleName}.dao;

import ${basepackage}.framework.repository.mybatis.MyBatisRepository;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import ${basepackage}.${appName}.${moduleName}.model.${className};

/**
 * 其中@MyBatisRepository是我们创建的新的注解。
 * Dao中的方法名和TestMapper.xml中的id值必须一致。
 * 从而实现省略不必要的代码
 * @return
 */
@MyBatisRepository 
public interface I${className}Dao  {

	/**根据自定义的对象查询,返回对象集合
	 */
	public List<${className}> findListByObj(${className} ${classNameLower});
	public  ${className} findByPKId(@Param("pk_Id") int pk_Id);

	/**
	 * 查询所有集合
	 */
	public List<${className}> selectAll${className}();
	
	/**
	 * 统计在某个条件下的数量
	 * @param bossCnlProperties
	 * @return
	 */
	public Long select_${className}sCount(${className} ${classNameLower});
}

