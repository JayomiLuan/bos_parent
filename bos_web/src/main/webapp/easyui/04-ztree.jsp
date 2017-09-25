<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ztree-树</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/js/easyui/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/easyui/jquery.easyui.min.js"></script>
<!-- 引入ztree资源文件 -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/js/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">
	<div title="XX管理系统" data-options="region:'north'" style="height:100px">北部区域</div>
	<div title="系统菜单" data-options="region:'west'" style="width:200px">
		<!-- 折叠面板 
		fit:填充满fit所在标签的父容器，默认值是false
		-->
		<div class="easyui-accordion" fit="true">
			<div title="面板一" data-options="iconCls:'icon-help'">
				<a onclick="doAdd();" class="easyui-linkbutton">动态添加选项卡</a>
				<script type="text/javascript">
				function doAdd(){
					//调用tabs的方法，动态添加一个选项卡
					$('#tt').tabs('add',{
						title:'动态选项卡',
						content:'动态选项卡内容！这个是个动态的哦！',
						closable:true
					});
				}
				</script>
			</div>
			<div title="面板二">
			<!-- 使用标准json构建ztree （了解）-->
			<!-- 1.提供一个容器 -->
			<ul id="ztree1" class="ztree"></ul>
			<script type="text/javascript">
				$(function(){
					//2.定义全局的setting变量
					var setting = {};//标准json的setting可以为空，可以使用默认值
					//3.定义树节点变量
					var nodes = [
					             {name:'系统管理'},//表一个一个节点数据，name节点的名称
					             {name:'用户管理',children:[
					                                    {name:'用户添加'},
					                                    {name:'用户修改'},
					                                    {name:'用户删除'}
					                                    ]},
					             {name:'权限管理'}
					             ];
					//4.初始化树
					$.fn.zTree.init($('#ztree1'), setting, nodes);
				});
			</script>
			</div>
			<div title="面板三">
			<!-- 使用简单json构建ztree -->
			<!-- 1.提供一个容器 -->
			<ul id="ztree2" class="ztree"></ul>
			<script type="text/javascript">
				$(function(){
					//2.定义全局的setting变量
					var setting = {
							data:{
								simpleData:{
									enable:true//开启简单json数据功能
								}
							}
					};//标准json的setting可以为空，可以使用默认值
					//3.定义树节点变量
					var nodes = [
					             {id:'1', pId:'0', name:'系统管理'},//表一个一个节点数据，name节点的名称
					             {id:'2', pId:'0', name:'用户管理'},
					             {id:'21', pId:'2', name:'用户添加'},
					             {id:'22', pId:'2', name:'用户修改'},
					             {id:'23', pId:'2', name:'用户删除'},
					             {id:'3', pId:'0', name:'权限管理'}
					             ];
					//4.初始化树
					$.fn.zTree.init($('#ztree2'), setting, nodes);
				});
			</script>
			</div>
			<div title="面板四">
			<!-- 基于ajax加载ztree节点数据 -->
			<!-- 1.提供一个容器 -->
			<ul id="ztree3" class="ztree"></ul>
			<script type="text/javascript">
				$(function(){
					//2.定义全局的setting变量
					var setting = {
							data:{
								simpleData:{
									enable:true//开启简单json数据功能
								}
							},
							callback:{
								onClick:function(event, treeId, treeNode){
									//添加动态添加选项卡的操作
									//判断根节点，是非根节点才能添加选项卡
									var page = treeNode.page;
									if(undefined != page){
										//有page属性，是子节点，
										//重复的选项卡只能添加一次
										//判断选项卡中是否已存在当前添加的选项卡
										var b = $('#tt').tabs('exists', treeNode.name);
										if(b){
											//已存在选项卡：选中
											$('#tt').tabs('select', treeNode.name)
										} else {
											//不存在：添加
											$('#tt').tabs('add',{
												title:treeNode.name,
												content:'<iframe src="${pageContext.request.contextPath}/'+page+'" frameborder="0" style="height:100%;width:100%"></iframe>',
												closable:true
											});
										}
									}
								}
							}
					};//标准json的setting可以为空，可以使用默认值
					//3.基于ajax加载节点数据
					var url="${pageContext.request.contextPath}/json/menu.json";
					$.post(url,{},function(data){
						//4.初始化树
						$.fn.zTree.init($('#ztree3'), setting, data);
					},'json');
				});
			</script>
			</div>
		</div>
	</div>
	<div data-options="region:'center'">
		<!-- 选项卡 -->
		<div id="tt" class="easyui-tabs" fit="true">
			<div title="面板一" data-options="iconCls:'icon-help',closable:true">1</div>
			<div title="面板二">1</div>
			<div title="面板三">1</div>
			<div title="面板四">1</div>
		</div>
	</div>
	<div data-options="region:'east'" style="width:100px">东部区域</div>
	<div data-options="region:'south'" style="height:100px">南部区域</div>
</body>
</html>