<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>添加项目</title>
<script type="text/javascript">
function addJobRow(obj){
	var html = '<tr><td><select class="job-plan" id="" name="job_to_plan"><c:forEach items="${joblist}" var="jnames"><option value="${jnames.job_id}">${jnames.job_name}</option></c:forEach></select></td><td><input class="add-hour" type="text" id="" name="" placeholder="工时（小时）" pattern="[0-9]+" required="true" /><input class="add-duration" type="text" id="" name="" placeholder="工期（月）" pattern="[0-9]+" required="true" /></td><td><a href="javascript:void(0);" onclick="addJobRow($(this))">添加工种</a>&nbsp;<a href="javascript:void(0);" onclick="$(this).parent().parent().remove()">删除</a></td></tr>'
	$(obj).parent().parent().parent().append(html);
}
function ajaxProject(pid){
	ajaxUrl="getSingleProjectDetail.do";
	$.ajax({
		url:ajaxUrl,
		type:"POST",
		data:"pid="+pid,
		dataType:"text",
		success:function(data){
			var json = eval("("+data+")")[0];
			
			var html2 = '<tr><td class="project-ID">'+json.projectID+'</td><td>'+json.pname+'</td>';
			html2 += '<td>'+json.revenue+'</td><td>'+json.fcost+'</td>';
			html2 += '<td>'+json.mcost+'</td><td class="inner_table_container">';
			html2 += '<table id="_inner_table" cellspacing="0"><tr><td><select class="job-plan" id="" name="job_to_plan"><c:forEach items="${joblist}" var="jnames">';
			html2 += '<option value="${jnames.job_id}">${jnames.job_name}</option></c:forEach>';
			html2 += '</select></td><td><input class="add-hour" type="text" id="" name="" placeholder="工时（小时）" pattern="[0-9]+" required="true" />';
			html2 += '<input class="add-duration" type="text" id="" name="" placeholder="工期（月）" pattern="[0-9]+" required="true" /></td>';
			html2 += '<td><a href="javascript:void(0);" onclick="addJobRow($(this))">添加工种</a>&nbsp;<a href="javascript:void(0);" onclick="alert(\'不可删除最后一行！\');">删除</a></td>';
			html2 += '</tr></table></td><td>'+json.pw+'</td>';
			html2 += '<td><a href="javascript:void(0);" onclick="$(this).parent().parent().remove()">删除</a></td></tr>';
			$('#_tbody').append(html2);
			//alert(json.projectID);
		}
	});
	
	tipHide('#addProjectToPlan');
}

function submitAction(){
	$('#pids').val("");
	$('#jids').val("");
	$('#hours').val("");
	$('#durations').val("");
	
	var pids="";
	var jps="";
	var addhours="";
	var jds="";
	var obj1 = $('.project-ID');
	var obj2 = $('.job-plan');
	var obj3 = $('.add-hour');
	var obj4 = $('.add-duration')
	
	for(i=0;i<obj1.length;i++){
		if(i==0){
			pids+=obj1[i].innerHTML;
		}else{
			pids=pids+','+obj1[i].innerHTML;
		}
	}
	$('#pids').val(pids);
	
	for(i=0;i<obj2.length;i++){
		if(i==0){
			jps+=obj2[i].value;
		}else{
			jps=jps+','+obj2[i].value;
		}
	}
	$('#jids').val(jps);
	
	for(i=0;i<obj3.length;i++){
		if(i==0){
			addhours+=obj3[i].value;
		}else{
			addhours=addhours+','+obj3[i].value;
		}
	}
	$('#hours').val(addhours);
	
	for(i=0;i<obj4.length;i++){
		if(i==0){
			jds+=obj4[i].value;
		}else{
			jds=jds+','+obj4[i].value;
		}
	}
	$('#durations').val(jds);
	
	if(confirm('规划一旦添加即不可修改，确定吗？')){
		document.getElementById('form1').submit();
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
			<a href="listPlanning.do">规划管理</a>&nbsp;&raquo;&nbsp;
			<span class="gray" title="新建规划">新建规划</span>
		</div>
	</div>
	<h2 class="h2">新建规划</h2>
	<form id="form1" name="form1" action="doAddPlan.do" method="post">
		<div class="edit set">
			<p>
				<label><samp>*</samp> 规划ID：</label>
				<input type="text" class="text state" id="add_pid" name="add_pid" pattern="[0-9]{8}" maxlength="8" required="true"/>
			</p>
			<p>
				<label><samp>*</samp> 规划名称：</label>
				<input type="text" class="text state" id="add_pname" name="add_pname" />
			</p>
		</div>
		<h2 class="h2">绑定对应的项目</h2>
		<table class="tab" cellspacing="0">
			<thead>
				<tr>
					<th>项目ID</th>
					<th>名称</th>
					<th>收益</th>
					<th>固定成本</th>
					<th>人均管理费用</th>
					<th>工种、工时和工期</th>
					<th>权重（1-5）</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody id="_tbody">
			<!-- for test -->
			</tbody>
		</table>
		<div class="page_c">
			<span class="r">
				<input id="" name="" type="button" class="hand btn80x20" onclick="tipShow('#addProjectToPlan')" value="添加项目" />
			</span>
		</div>
		<div class="edit set">
			<label>&nbsp;</label>
			<input type="button" onclick="submitAction()" class="hand btn83x23" value="提交" />
			<input type="button" class="hand btn83x23b" value="返回" />
		</div>
		<input type="hidden" id="pids" name="pids" />
		<input type="hidden" id="jids" name="jids" />
		<input type="hidden" id="hours" name="hours" />
		<input type="hidden" id="durations" name="durations" />
	</form>
	
	
</div>

<!-- 绑定浮动层 -->
<div id="addProjectToPlan" class="alt" style="display:none">
    <div class="t"></div>
    <div class="c set">
        <h2 title="绑定项目">绑定项目</h2>
        <div id="addProjectToPlanClose" class="o" title="关闭"></div>
        <form id="form3" name="form3" action="addProjectToPlan.do" method="post">
		<div class="edit set">
            <p><label>选择项目名称：</label>
            	<select id="_ptop" name="project_to_plan">
           		<c:forEach items="${plist}" var="pitems">
           			<option value="${pitems.project_id}">${pitems.project_name}</option>
           		</c:forEach>
            	</select>
            </p>
            <div class="alg_c orange">温馨提示：请从下拉列表中选择想要绑定的项目。</div>
            <p><label>&nbsp;</label>
            	<input type="button" class="hand btn83x23" onclick="ajaxProject($('#_ptop').val())" value="确定" />
            	<input type="button" id="addProjectToPlanReset" class="hand btn83x23b" value="取消" />
            </p>
        </div>
		</form>
    </div>
    <div class="f"></div>
</div>
</body>
</html>