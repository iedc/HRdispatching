<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人力规划</title>
<script type="text/javascript">
function searchForJob(str){
	$('.j_name').parent().removeClass("selected");
	$('#_j_err_tip').val('').hide();
	list = $('.j_name');
	count=0;
	if(str==null || str==""){
		$('#_j_err_tip').html('搜索内容不能为空！').show();
	}else{
		for(i=0;i<list.length;i++){
			if(list[i].innerHTML.indexOf(str)!=-1){
				$(list[i]).parent().addClass("selected");
				count++;
			}
		}
		if(count==0){
			$('#_j_err_tip').html('找不到名字为 “'+str+'” 的工种').show();
		}
	}
}
function searchReset(){
	$('.j_name').parent().removeClass("selected");
	$('#sch_place').val('');
	$('#_j_err_tip').val('').hide();
}
function updateSingleJob(jid, jname, jsalary, jrcost){
	tipShow('#editJob');
	$('#uh_jid').val(jid);
	$('#u_jid').val(jid);
	$('#u_jname').val(jname);
	$('#u_jsalary').val(jsalary);
	$('#u_jrcost').val(jrcost);
}

function confirmDeleteJob(jid){
	if(confirm('确认删除？')){
		delForm = document.getElementById("_dj_hdn");
		delUrl = "deleteSingleJob.do?jid="+jid;
		delForm.action = delUrl;
		delForm.submit();
	}
}
</script>
</head>
<body>
<div class="box">
	<div class="content">
		<div id="addr1" class="loc icon">
			<samp class="t12"></samp>
			当前位置：派遣规划管理&nbsp;&raquo;&nbsp;
			<span class="gray" title="工种管理">工种管理</span>
		</div>
	</div>
	<form id="form1" name="form1" action="" method="post" class="form">
		<div id="sch" class="sch">
			<p class="l">
				<input type="text" id="sch_place" name="" class="text20 medium grey" placeholder="查询项目名称" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="查询" onclick="searchForJob($('#sch_place').val())" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="重置" onclick="searchReset()" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="添加工种" onclick="tipShow('#addJob')" />  
			</p>
			<span id="_j_err_tip" class="l tip errorTip" style="display:none">&nbsp;</span>
		</div>
	
		<table id="myTable" class="tab" cellspacing="0">
			<thead>
				<tr>
					<th>工种ID</th>
					<th>工种名称</th>
					<th>工种平均工资</th>
					<th>工种平均招募费用</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${joblist}" var="item">
				<tr>
					<td>${item.job_id }</td>
					<td class="j_name">${item.job_name }</td>
					<td>${item.job_salary }</td>
					<td>${item.job_recruit_cost }</td>
					<td><a href="javascript:void(0);" onclick="updateSingleJob('${item.job_id}','${item.job_name}','${item.job_salary}','${item.job_recruit_cost}')">修改</a>&nbsp;<a href="javascript:void(0);" onclick="confirmDeleteJob('${item.job_id}')">删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	<form id="_dj_hdn" action="" method="post">
	</form>
</div>

<!-- 添加工种浮动层 -->
<div id="addJob" class="alt" style="display:none">
    <div class="t"></div>
    <div class="c set">
        <h2 title="添加工种">添加工种</h2>
        <div id="addJobClose" class="o" title="关闭"></div>
        <form id="form3" name="form3" action="addSingleJob.do" method="post">
		<div class="edit set">
            <p><label>&nbsp;</label>
            	<input type="text" pattern="[0-9]{8}" maxlength="8" class="text medium" id="jid" name="jid" placeholder="工种ID" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" id="jname" name="jname" placeholder="工种名称" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" class="text medium" id="jsalary" name="jsalary" placeholder="工种平均月薪" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" id="jrcost" name="jrcost" placeholder="工种人均招聘费用" value="" required="required" />
            </p>
            <div class="alg_c orange">温馨提示：工种ID必须是8位数字，添加完成后不可以修改。</div>
            <p><label>&nbsp;</label>
            	<input type="submit" class="hand btn83x23" value="确定" />
            	<input type="button" id="addJobReset" class="hand btn83x23b" value="取消" />
            </p>
        </div>
		</form>
    </div>
    <div class="f"></div>
</div>

<!-- 修改工种浮动层 -->
<div id="editJob" class="alt" style="display:none">
    <div class="t"></div>
    <div class="c set">
        <h2 title="修改工种">修改工种</h2>
        <div id="editJobClose" class="o" title="关闭"></div>
        <form id="form3" name="form3" action="editSingleJob.do" method="post">
		<div class="edit set">
            <p><label>&nbsp;</label>
            	<input type="hidden" id="uh_jid" name="uh_jid" />
            	<input type="text" class="text medium" id="u_jid" name="u_jid" placeholder="工种ID" value="" required="required" disabled="true" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" id="u_jname" name="u_jname" placeholder="工种名称" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" class="text medium" id="u_jsalary" name="u_jsalary" placeholder="工种平均月薪" value="" required="required" />
            </p>
            <p><label>&nbsp;</label>
            	<input type="text" class="text medium" pattern="^[0-9]+\.{0,1}[0-9]{0,2}$" id="u_jrcost" name="u_jrcost" placeholder="工种人均招聘费用" value="" required="required" />
            </p>
            <div class="alg_c orange">温馨提示：完成修改后请点击确认按钮，否则请点击取消按钮。</div>
            <p><label>&nbsp;</label>
            	<input type="submit" class="hand btn83x23" value="确定" />
            	<input type="button" id="addJobReset" class="hand btn83x23b" value="取消" />
            </p>
        </div>
		</form>
    </div>
    <div class="f"></div>
</div>
</body>
</html>