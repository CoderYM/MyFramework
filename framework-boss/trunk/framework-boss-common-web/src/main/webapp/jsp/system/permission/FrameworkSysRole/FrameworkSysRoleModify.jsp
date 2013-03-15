<%----------------------------------------------------------------------
  Copyright(c) 2011 ALLINPAY. All Right Reserver.

  File Name:  FrameworkSysRoleModify
  Detail   :  该页用于 FrameworkSysRole管理-FrameworkSysRole修改
----------------------------------------------------------------------%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script type="text/javascript">
setTimeout(function(){
	var rolepermits = "${rolePermits}";//角色权限字符串逗号分割
	if(rolepermits!=null && rolepermits!=""){
	  var permits = rolepermits.split(",");
	  for(var i=0;i<permits.length;i++){
	    var v_permMenu = $("input[type=checkbox][name=pmcheck][value="+permits[i]+"]").parent();
	    console.info(v_permMenu);
	    v_permMenu.toggleClass("unchecked checked");
	  }
	}
	}
	,1000);
	
function clickMenuSetPerm(){
	
	//var json = arguments[0];
	//$(json.items).each(function(i){
	//	if(this.name == 'pmcheck'){
	//		result += this.value+",";
	//	}
	//});
	var result="";
	
	$("input[type=checkbox][name=pmcheck]").each(function(i,obj){
		if($(this).parent().attr("class") == 'ckbox checked'){
			
			result += obj.value+",";
		}
	});
	$("#v_pmcheck").val(result);
}
</script>

<div class="pageContent">
<form method="post" action="sysrole/modifyFrameworkSysRoleAction?navTabId=sysrole&callbackType=closeCurrent" class="pageForm required-validate" onsubmit="return validateCallback(this,navTabAjaxDone);">
<input id="v_pmcheck" type="hidden" name="pmcheckStrs" value="">
<input type="hidden" name="id" value="${infos.id }">
	<div class="pageFormContent" layoutH="56">
	<fieldset>
	<legend>角色信息</legend>
		<p>
			<label>角色名</label>
			<input  type="text" name="sysRoleName" class="required" value="${infos.sysRoleName}"/>
		</p>
		<p>
			<label>角色描述</label>
			<input  type="text" name="sysRoleDescription" class="" value="${infos.sysRoleDescription}"/>
		</p>
	</fieldset>
	<fieldset>
	<legend>功能权限</legend>
	<div class="accordion" style="width:300px;float:left;margin:5px;">
		
		<div class="accordionHeader">
			<h2><span>Folder</span>功能权限</h2>
		</div>
		<div class="accordionContent">
			<ul class="tree treeFolder treeCheck expand" oncheck="clickMenuSetPerm">
				<c:forEach items="${parentListMenu}" var="pmenu" varStatus="mc">
					<li><a href="#" tname="menuName1_2" tvalue="${pmenu.id }">${pmenu.sys_menu_name }</a>
						<ul>
						<!-- 二级菜单迭代 -->
							<c:forEach items="${hashMap[pmenu.id]}" var="menu_2" varStatus="sc">
			        			<c:if test="${menu_2.is_leaf=='0' && menu_2.pid !='0'}">
									<li><a href="#" id="menu_id" tname="menuName1_2" tvalue="${menu_2.id }">${menu_2.sys_menu_name }</a>
								</c:if>
								<!-- 三级菜单迭代 -->
							      <c:forEach items="${hashMap[menu_2.id]}" var="cmenu" varStatus="tc">
									<ul>
										<li>
											<a href="#" id="menu_id" tname="menuName3" tvalue="${cmenu.id }" >${cmenu.sys_menu_name }</a>
											<!-- 权限迭代 -->
											<c:forEach items="${cmenu.listPermission}" var="permission" varStatus="stauts">
											<ul>
												<li>
													<a href="#" tname="pmcheck" tvalue="${permission.id }" >${permission.permissionName }</a>
												</li>
												
											</ul>
											</c:forEach>
										</li>
									</ul>
							      </c:forEach>
							      </li>
							</c:forEach>
						</ul>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	</fieldset>
	
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>

