<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>EasyUI-accordion-折叠面板</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div title="XX管理系统" data-options="region:'north'" style="height:100px">北部区域</div>
	<div title="系统菜单" data-options="region:'west'" style="width:200px">
		<!-- 折叠面板 
		fit:填充满fit所在标签的父容器，默认值是false
		-->
		<div class="easyui-accordion" fit="true">
			<div title="面板一" data-options="iconCls:'icon-help'">1</div>
			<div title="面板二">1</div>
			<div title="面板三">1</div>
			<div title="面板四">1</div>
		</div>
	</div>
	<div data-options="region:'center'">中部区域</div>
	<div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:100px">南部区域</div>
</body>
</html>