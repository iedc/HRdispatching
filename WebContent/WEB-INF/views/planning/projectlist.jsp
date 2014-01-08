<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/resources/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人力规划</title>
<script type="text/javascript">
function searchForProject(str){
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
			$('#_p_err_tip').html('找不到名字为 “'+str+'” 的项目').show();
		}
	}
}
function searchReset(){
	$('.p_name').parent().removeClass("selected");
	$('#sch_place').val('');
	$('#_p_err_tip').val('').hide();
}
</script>
</head>
<body>
<div class="box">
	<div class="content">
		<div id="addr1" class="loc icon">
			<samp class="t12"></samp>
			当前位置：派遣规划管理&nbsp;&raquo;&nbsp;
			<span class="gray" title="项目总览">项目总览</span>
		</div>
	</div>
	<form id="form1" name="form1" action="" method="post" class="form">
		<div id="sch" class="sch">
			<p class="l">
				<input type="text" id="sch_place" name="" class="text20 medium grey" placeholder="查询项目名称" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="查询" onclick="searchForProject($('#sch_place').val())" />
				<input type="button" id="" name="" class="hand btn60x20 sub1" value="重置" onclick="searchReset()" />  
			</p>
			<span id="_p_err_tip" class="l tip errorTip" style="display:none">&nbsp;</span>
		</div>
	
		<table id="myTable" class="tab" cellspacing="0">
			<thead>
				<tr>
					<th>项目ID</th>
					<th>项目名称</th>
					<th>项目开始时间</th>
					<th>项目持续时间</th>
					<th>项目状态</th>
					<th>项目收益</th>
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
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
	
	
</div>
</body>
</html>