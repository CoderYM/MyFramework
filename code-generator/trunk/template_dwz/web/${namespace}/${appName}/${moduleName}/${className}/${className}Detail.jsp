<#include "/macro.include"/>
<#include "/custom.include"/>  
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do"> 
<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  ${className}Detail
  Detail   :  该页用于 ${table.tableAlias}管理-${table.tableAlias}明细信息
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<div class="pageContent">
<form method="post" action="" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
	<div class="pageFormContent" layoutH="56">
		 <#list table.columns as column>
		 <#if !column.pk>
	 	<p>
			<label>${column.columnAlias}</label>
			<#if !column.nullable>
			<input type="text" name="${column.columnNameLower}" value="${'${'}infos.${column.columnNameLower}${'}'}" class="required" maxlength="20"/>
			<#else>
			  	<#if column.isDateTimeColumn>
				<input type="text" name="${column.columnNameLower}" value='<fmt:formatDate value="${'${'}infos.${column.columnNameLower}${'}'}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/>' class="date" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true" maxlength="20"/>
			 	 <#else>
				<input type="text" name="${column.columnNameLower}" value="${'${'}infos.${column.columnNameLower}${'}'}" class="" maxlength="20"/>
				</#if>
			</#if>
		</p>
		</#if>
	 </#list>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>
