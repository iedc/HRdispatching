<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人力规划</title>
<script type="text/javascript">
function searchReset(){
	$('.p_name').parent().removeClass("selected");
	$('#sch_place').val('');
	$('#_p_err_tip').val('').hide();
}
function singleDelProject(pid){
	if(confirm("确认删除？")){
		delForm = document.getElementById("_dp_hdn");
		delUrl = "deleteProject.do?pid="+pid;
		delForm.action = delUrl;
		delForm.submit();
	}
}

function singleEditProject(pid, pname, psdate, pduration, revenue, fcost, mcost){
	//alert("//TODO:修改单个项目："+pid);
	tipShow("#editProject");
	$('#uh_pid').val(pid);
	$('#sup_pid').val(pid);
	$('#sup_pname').val(pname);
	$('#sup_psdate').val(psdate);
	$('#sup_pduration').val(pduration);
	$('#sup_revenue').val(revenue);
	$('#sup_fcost').val(fcost);
	$('#sup_mcost').val(mcost);
}

</script>
</head>
<body>
<div class="box">
	<div class="content">
		<div id="addr1" class="loc icon">
			<samp class="t12"></samp>
			当前位置：派遣规划管理&nbsp;&raquo;&nbsp;
			<span class="gray" title="项目总览">管理项目</span>
		</div>
	</div>
	<form id="form1" name="form1" action="" method="post" class="form">
		<div id="sch" class="sch">
			<p class="l">
				<input type="button" id="btn_add_project" name="" class="hand btn60x20 sub1" value="添加项目" onclick="tipShow('#addProject');" />
			</p>		
		</div>
		<span id="_p_err_tip" class="tip errorTip" style="display:none">&nbsp;</span>
		<span id="_p_ok_tip" class="tip okTip" style="display:none">&nbsp;</span>
		<table id="myTable" class="tab" cellspacing="0">
			<thead>
				<tr>
					<th>项目ID</th>
					<th>项目名称</th>
					<th>项目开始时间</th>
					<th>项目持续时间</th>
					<th>项目状态</th>
					<th>项目收益</th>
					<th>项目固定成本</th>
					<th>项目人均管理费用</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="item">
				<tr>
					<td>${item.project_id }</td>
					<td class="p_name">${item.project_name }</td>
					<td>${item.start_date }</td>
					<td>${item.project_duration }</td>
					<td>${item.project_status }</td>
					<td>${item.project_revenue }</td>
					<td>${item.project_fixed_cost }</td>
					<td>${item.project_manage_cost }</td>
					<td><a href="javascript:void(0);" onclick="singleEditProject('${item.project_id}','${item.project_name }','${item.start_date }','${item.project_duration }','${item.project_revenue }','${item.project_fixed_cost }','${item.project_manage_cost }');">修改</a>&nbsp;<a href="javascript:void(0);" onclick="singleDelProject('${item.project_id}');">删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	<form id="_dp_hdn" action="" method="post">
	</form>
</div>
<!-- 添加项目浮动层 -->
<div id="addProject" class="alt" style="display:none">
    <div class="t"></div>
    <div class="c set">
        <h2 title="添加项目">添加项目</h2>
        <div id="addProjectClose" class="o" title="关闭"></div>
        <form id="form1" name="form1" action="addProject.do" method="post">
		<div class="edit set">
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]{8}" class="text medium" name="pid" placeholder="项目ID" maxlength="8" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" name="pname" placeholder="项目名称" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]{6}" class="text medium" name="psdate" placeholder="项目开始时间" value="" maxlength=6 required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]+" class="text medium" name="pduration" placeholder="项目持续时间" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" name="revenue" placeholder="项目收益" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" name="fcost" placeholder="项目固定成本" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" name="mcost" placeholder="人均管理费用" value="" required="required" />
            </p>
            <div class="alg_c orange">温馨提示：项目开始时间应当以“年月”的6位数字形式，例如2000年2月，应当输入“200002”。</div>
            <p><label>&nbsp;</label>
            	<input type="submit" class="hand btn83x23" value="确定" />
            	<input type="reset" class="hand btn83x23b" value="重置" />
            </p>
        </div>
		</form>
    </div>
    <div class="f"></div>
</div>

<!-- 编辑项目浮动层 -->
<div id="editProject" class="alt" style="display:none">
    <div class="t"></div>
    <div class="c set">
        <h2 title="修改项目">修改项目</h2>
        <div id="editProjectClose" class="o" title="关闭"></div>
        <form id="form2" name="form2" action="updateProject.do" method="post">
		<div class="edit set">
            <p><label>&nbsp;</label>
            	<input type="hidden" id="uh_pid" name="uh_pid" value="" />
            	<input type="text" class="text medium" id="sup_pid" name="u_pid" placeholder="项目ID" value="" required="required" disabled="true" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" id="sup_pname" name="u_pname" placeholder="项目名称" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]{6}" class="text medium" id="sup_psdate" name="u_psdate" placeholder="项目开始时间" value="" maxlength=6 required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]+" class="text medium" id="sup_pduration" name="u_pduration" placeholder="项目持续时间" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" id="sup_revenue" name="u_revenue" placeholder="项目收益" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" id="sup_fcost" name="u_fcost" placeholder="项目固定成本" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" id="sup_mcost" name="u_mcost" placeholder="人均管理费用" value="" required="required" />
            </p>
            <div class="alg_c orange">温馨提示：项目开始时间应当以“年月”的6位数字形式，例如2000年2月，应当输入“200002”。</div>
            <p><label>&nbsp;</label>
            	<input type="submit" class="hand btn83x23" value="确定" />
            	<input type="button" id="editProjectReset" class="hand btn83x23b" value="取消" />
            </p>
        </div>
		</form>
    </div>
    <div class="f"></div>
</div>
</body>
</html>