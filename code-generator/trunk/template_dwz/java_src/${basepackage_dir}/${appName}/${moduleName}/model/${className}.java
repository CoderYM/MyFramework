<#include "/custom.include">
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${appName}.${moduleName}.model;

import javax.persistence.Table;

import ${basepackage}.framework.annotation.Description;
import ${basepackage}.framework.repository.mybatis.model.MyBatisBaseModel;
import org.apache.ibatis.type.Alias;

@Table(name="${table.sqlName}")
@Alias(value="${className}")
public class ${className} extends  MyBatisBaseModel {
	private static final long serialVersionUID = ${serialVersionUID};
	
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	/** 变量 ${column.constantName} . */
	@Description(value="${column.columnAlias}开始")
	private String ${column.columnNameLower}Begin;
	@Description(value="${column.columnAlias}结束")
	private String ${column.columnNameLower}End;
	</#if>
	</#list>
	
	//columns START
	<#list table.columns as column>
	/** 变量 ${column.columnNameLower} . */
	@Description(value="${column.columnAlias}")
	private ${column.javaType} ${column.columnNameLower};
	</#list>
	//columns END

<@generateConstructor className/>
<@generateJavaColumns/>
<@generateJavaOneToMany/>
<@generateJavaManyToOne/>

}

<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
		public void set${column.columnName}Begin(String value) {
			this.${column.columnNameLower}Begin = value;
		}
		
		public String get${column.columnName}Begin() {
			return this.${column.columnNameLower}Begin;
		}
		public void set${column.columnName}End(String value) {
			this.${column.columnNameLower}End = value;
		}
		
		public String get${column.columnName}End() {
			return this.${column.columnNameLower}End;
		}
		</#if>	

	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
		<#if column.pk>
	public ${column.javaType} getPk() {
		return this.${column.columnNameLower};
	}
		</#if>
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
