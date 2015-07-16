<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>ProjectZZY - TaskDetail</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!-- bootstrap -->
<link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- libraries -->
<link href="css/lib/bootstrap-wysihtml5.css" type="text/css"
	rel="stylesheet" />
<link href="css/lib/uniform.default.css" type="text/css"
	rel="stylesheet" />
<link href="css/lib/select2.css" type="text/css" rel="stylesheet" />
<link href="css/lib/bootstrap.datepicker.css" type="text/css"
	rel="stylesheet" />
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/form-showcase.css"
	type="text/css" media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

	<%@ include file="boot.jsp"%>


	<!-- main container -->
	<div class="content">

		<!-- settings changer -->

		<%
			int taskID = Integer.parseInt(request.getParameter("taskID"));
			int projectID = Integer.parseInt((String)request.getParameter("projectID"));
			Project tempProject = ProjectManagement.searchProject(projectID);
			Task detailTask = TaskManager.searchTask(taskID);
		%>
		<div class="container-fluid">
			<div id="pad-wrapper" class="form-page">
				<div class="row-fluid header">
					<h3>任务基本信息</h3>

			<%
				if(!detailTask.getState().equals("已经完成"))
				{
					%>
						<a class="btn-flat icon large pull-right edit"
						href=<%="dealTaskInformation.jsp?taskID="+detailTask.getTaskID()+"&projectID="+projectID %>> 处理任务信息 </a>
					<%
				}
			%>
					
				</div>
				<div class="row-fluid form-wrapper">
					<!-- left column -->
					<div class="span8 column">


						<form />
						<div class="field-box">
							<label>所属项目:</label> <input class="span8 inline-input"
								type="text" readonly="readonly" value=<%=tempProject.getName()%> />
						</div>
						<div class="field-box">
							<label>任务名称:</label> <input class="span8 inline-input"
								type="text" readonly="readonly"
								value=<%=detailTask.getTaskName()%> />
						</div>


						<div class="span12 field-box">
							<label>任务状态:</label>
							<%
								if (detailTask.getState().equals("进行中的")) {
							%>
							<span class="label label-info">Progress</span>
							<%
								}
								if (detailTask.getState().equals("已经完成")) {
							%>
							<span class="label label-success">Completed</span>
							<%
								}
								if (detailTask.getState().equals("出现问题")) {
							%>
							<span class="label label-warning">Warning</span>
							<%
								}
							%>
						</div>
						<div class="span12 field-box">
							<label>是否是里程碑:</label>
							<div class="row ctrls">
								<div class="slider-frame primary">
									<%
										if (detailTask.getMilepost() == 1) {
									%>
									<span data-on-text="YES" data-off-text="NO"
										class="slider-button">YES</span>
									<%
										} else {
									%>
									<span data-on-text="YES" data-off-text="NO"
										class="slider-button">NO</span>
									<%
										}
									%>

								</div>
							</div>
						</div>
						<div class="field-box">
							<label>任务进度:</label>
							<div class="progress progress-striped active">
								<%
									int rateDis = (int) detailTask.getRate();
								%>
								<div class="bar" style="width: <%=rateDis%>%;"><%=rateDis%>%
								</div>
							</div>
						</div>
						<ul class="nav nav-list">
							<div class="row-fluid header"></div>
							<li class="divider"></li>
						</ul>
						<div class="row-fluid header">
							<h3>任务详细信息:</h3>
						</div>

						<!-- 详细信息 -->

						<div class="span12 field-box">

							<label>任务优先级:</label>
							<%
								if (detailTask.getLevel() == 1) {
							%>
							<input class="span2 inline-input" type="text" readonly="readonly"
								value="正常" />
							<%
								} else {
							%>
							<input class="span2 inline-input" type="text" readonly="readonly"
								value="最高" />
							<%
								}
							%>
						</div>
						<div class="field-box">
							<label>开始时间:</label> <input class="span3 " type="text"
								readonly="readonly" value=<%=detailTask.getStartTime()%> />
						</div>
						<div class="field-box">
							<label>预期结束时间:</label> <input class="span3 " type="text"
								readonly="readonly" value=<%=detailTask.getPlanEndtime()%> />
						</div>
						<div class="field-box">
							<label>父任务名称:</label> <input class="span8 " type="text"
								readonly="readonly" value="无" />
						</div>
						<div class="field-box">
							<label>预算指标:</label>
							<div class="input-append">
								<input class="input-large" type="text" readonly="readonly"
									value=<%=detailTask.getBudget()%> /> <span class="add-on">万元</span>
							</div>
						</div>
						<div class="field-box">
							<label>人力资源:</label>
							<%
								String userList = "";
								Vector list = detailTask.getUserList();
								for (int i = 0; i < list.size(); i++) {
									userList += ((User) list.get(i)).getName() + ",";
								}
							%>
							<input class="span8 " type="text" readonly="readonly"
								value=<%=userList%> />
						</div>
						<div class="field-box">
							<label>简介:</label>
							<textarea class="span9" rows="4"><%=detailTask.getSummary()%></textarea>
						</div>

					</div>

					<!-- right column -->
					<div class="span3 column form-sidebar pull-right">

						<div class="alert alert-info hidden-tablet">
							<i class="icon-lightbulb pull-left"></i> 点击上面的按钮，根据您的喜爱切换输入框的风格。
						</div>
						<h6>Sidebar text for instructions</h6>
						<p>Add multiple users at once</p>
						<p>Choose one of the following file types:</p>
						<ul>
							<li><a href="#">Upload a vCard file</a></li>
							<li><a href="#">Import from a CSV file</a></li>
							<li><a href="#">Import from an Excel file</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end main container -->

	<!-- scripts for this page -->
	<script src="js/wysihtml5-0.3.0.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap-wysihtml5-0.0.2.js"></script>
	<script src="js/bootstrap.datepicker.js"></script>
	<script src="js/jquery.uniform.min.js"></script>
	<script src="js/select2.min.js"></script>
	<script src="js/theme.js"></script>




	<!-- call this page plugins -->
	<script type="text/javascript">
		$(function() {

			// add uniform plugin styles to html elements
			$("input:checkbox, input:radio").uniform();

			// select2 plugin for select elements
			$(".select2").select2({
				placeholder : "Select a State"
			});

			// datepicker plugin
			$('.datepicker').datepicker().on('changeDate', function(ev) {
				$(this).datepicker('hide');
			});

			// wysihtml5 plugin on textarea
			$(".wysihtml5").wysihtml5({
				"font-styles" : false
			});
		});
	</script>
	<div style="display: none">
		<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
			language='JavaScript' charset='gb2312'></script>
	</div>
</body>
</html>