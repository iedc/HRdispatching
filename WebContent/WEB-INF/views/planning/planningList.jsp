<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>规划列表</title>
<script type="text/javascript">
function searchForPlanning(str){
	$('.p_name').parent().removeClass("selected");
	$('#_p_err_tip').val('').hide();
	list = $('.p_name');
	count=0;
	if(str==null || str==""){
		$('#_p_err_tip').html('搜索内容不能为空！').show();
	}else{
		for(i=0;i<list.length;i++){
			if(list[i].innerHTML.indexOf(str)!=-1){
				$(list[i]).parent().addClass("selected");
				count++;
			}
		}
		if(count==0){
			$('#_p_err_tip').html('找不到名字为 “'+str+'” 的规划').show();
		}
	}
}
function searchReset(){
	$('.p_name').parent().removeClass("selected");
	$('#sch_place').val('');
	$('#_p_err_tip').val('').hide();
}
function confirmDelPlan(pid){
	if(confirm('确认删除？')){
		delForm = document.getElementById("_dp_hdn");
		delUrl = "deleteSinglePlan.do?planID="+pid;
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
			<span class="gray" title="规划管理">规划管理</span>
		</div>
	</div>
	<form id="form1" name="form1" action="" method="post" class="form">
		<div id="sch" class="sch">
			<p class="l">
				<input type="text" id="sch_place" name="" class="text20 medium grey" placeholder="查询规划名称" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="查询" onclick="searchForPlanning($('#sch_place').val())" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="重置" onclick="searchReset()" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="添加规划" onclick="window.location.href='addPlanning.do';" />  
			</p>
			<span id="_p_err_tip" class="l tip errorTip" style="display:none">&nbsp;</span>
		</div>
	
		<table id="myTable" class="tab" cellspacing="0">
			<thead>
				<tr>
					<th>规划ID</th>
					<th>规划名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${planlist}" var="item">
				<tr>
					<td>${item.plan_id }</td>
					<td class="p_name">${item.plan_name }</td>
					<td><a href="javascript:void(0)" onclick="">查看规划详情</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="">修改规划</a>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="confirmDelPlan('${item.plan_id}')">删除</a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	<form id="_dp_hdn" action="" method="post">
	</form>
</div>
</body>
</html>