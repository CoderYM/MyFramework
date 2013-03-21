<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${appName}.${moduleName}.service;


import javax.annotation.Resource;
import java.util.List;
import ${basepackage}.framework.utils.Page;
import ${basepackage}.${appName}.${moduleName}.dao.I${className}Dao;
import ${basepackage}.${appName}.${moduleName}.dao.impl.${className}DaoImpl;
import ${basepackage}.${appName}.${moduleName}.model.${className};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class ${className}ServiceImpl {
	@Autowired
	private I${className}Dao ${classNameLower}Dao;
	@Resource
	private ${className}DaoImpl myBatisDao;
	
	
	/**
	 * 新增
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String add(${className} ${classNameLower}) {
		// 保存申请信息
		myBatisDao.save(${classNameLower});
		return null;
	}
	
	
	/**
	 * 删除
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String delete(int pk_id) {
		myBatisDao.deleteById(pk_id);
		return null;
	}
	
	/**
	 * 新增修改
	 * 有唯一主键，且主键自动生成不可编辑时
	 * @param  POJO对象
	 * @return String
	 */
//	public String saveOrUpdate(${className} ${classNameLower}) {
//		
//    	// 保存申请信息
//		if(${classNameLower}.getPk() != null)
//			${classNameLower}Dao.update(${classNameLower});
//		else 		
//			${classNameLower}Dao.save(${classNameLower});
//		
//		return null;
//	}
	
	/**
	 * 新增修改
	 * 
	 * @param  POJO对象
	 * @return String
	 */
	public String update(${className} ${classNameLower}) {
    	// 保存申请信息
		myBatisDao.update(${classNameLower});
		
		return null;
	}
	
	/**
	 * 分页查询
	 * @param  POJO对象
	 * @param  pageIndex          当前页页数
	 * @param  pageSize           每页记录数
	 * @return Page
	 */
	public Page find${className}s(${className} ${classNameLower},
			int pageIndex,
			int pageSize) {
		return myBatisDao.pageBy(null, null, ${classNameLower}, pageIndex, pageSize);
	}
	
	/**
	 * 根据主键对象获取信息
	 * 
	 * @param  POJO对象
	 * @return ${className}
	 */
	public List<${className}> get${className}ListByObj(${className} ${classNameLower}) {
		return ${classNameLower}Dao.findListByObj(${classNameLower});
	}
	
	/**
	 * 根据主键获取信息
	 * 
	 * @param  POJO对象
	 * @return ${className}
	 */
	public ${className} get${className}ByPk(int pk_Id) {
		return ${classNameLower}Dao.findByPKId(pk_Id);
	}
	
	/**
	 * 根据条件获取信息
	 * 
	 * @param  POJO对象
	 * @return ${className}返回第一个符合条件的对象，适合条件能唯一定位记录的应用场景
	 *	public List<${className}> get${className}ListBySql(${className} ${classNameLower}) {
	 *		return ${classNameLower}Dao.findListBySqlId("selectPage_${className}s",${classNameLower});
	 *	}
	 */
	
	/**
	 * 查询所有
	 */
	public List<${className}> findAll(){
		return ${classNameLower}Dao.selectAll${className}();
	}
	
	/**
	 * 统计在某个条件下的数量
	 * @param bossCnlProperties
	 * @return
	 */
	public int getCountByObj(${className} ${classNameLower}){
		return ${classNameLower}Dao.select_${className}sCount(${classNameLower}).intValue();
	}
	
}
