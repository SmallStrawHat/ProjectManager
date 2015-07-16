<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
<title>ProjectZZY - NewTask</title>

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

	<%
		int taskID = Integer.parseInt((String) request.getParameter("taskID"));
		int projectID = Integer.parseInt((String) request.getParameter("projectID"));
		Project tempProject = ProjectManagement.searchProject(projectID);
		Vector taskList = tempProject.getTaskList();
		Task detailTask = TaskManager.searchTask(taskID);
		
	%>
	<!-- main container -->
	<div class="content">

		<!-- settings changer -->
		<!-- <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div> -->

		<div class="container-fluid">
			<div id="pad-wrapper" class="form-page">
				<div class="row-fluid form-wrapper">
					<!-- left column -->
					<div class="span8 column">
						<form action="TaskManagerExpre" method="post"  />
						<input name="functionMy" value="editTask" type="hidden" />
						<input name="taskID" value=<%=detailTask.getTaskID()%> type="hidden" />
						<input name="projectID" value=<%=projectID%> type="hidden" />
						<input name="rate" value=<%=detailTask.getRate()%> type="hidden" />
						<div class="field-box">
							<label>任务名称:</label> <input class="inline-input span8"
								readonly="readonly" type="text"
								value=<%=detailTask.getTaskName()%> />
						</div>
						<div class="field-box">
							<label>任务优先级:</label>
							<div class="ui-select">
								<select id="level" name="level">
									<%
										int level = detailTask.getLevel();
										if (level == 1) {
									%>
									<option value="L001" selected="" />正常
									<option value="L002" />优先
									<%
										} else {
									%>
									<option value="L001" />正常
									<option value="L002" selected="" />优先
									<%
										}
									%>
								</select>
							</div>
						</div>
						<div class="field-box">
							<label>选择父任务:</label> <select style="width: 225px" 
								class="select2" id="fatherTask" name="fatherTask" >
								<option />
								<%
									for(int k=0;k<taskList.size();k++)
									{
										Task tk = ((Task)taskList.get(k));
										if(detailTask.getTaskID() == tk.getTaskID())
										{
											continue;
										}
										
										%>
										<option value=<%=tk.getTaskID() %> /><%=tk.getTaskName() %>
										<%
									}
								%>
								
							</select>
						</div>
						<div id="userSelectCss" class="field-box">
							<label>分配人力资源:</label> <select style="width: 400px" multiple=""
								class="select2" id="userSelect" name="userSelect">
								<option />
								<%
									Vector user = MemberInformation.getUserList();
									Vector list = detailTask.getUserList();
									for (int i = 0; i < user.size(); i++) {

										int j;
										for (j = 0; j < list.size(); j++) {
											User tempUser = (User) list.get(j);
											if (tempUser.getName().equals(((User) user.get(i)).getName()))
												break;
										}
										if (j == list.size()) {
								%>
								<option value=<%="'" + ((User) user.get(i)).getUserID() + "'"%> /><%=((User) user.get(i)).getName()%>
								<%
									} else {
								%>
								<option selected=""
									value=<%="'" + ((User) user.get(i)).getUserID() + "'"%> /><%=((User) user.get(i)).getName()%>
								<%
									}
								
									}
								%>
							</select>
						</div>
						<div class="field-box">
							<label>任务开始时间:</label> <input type="text" value=<%=detailTask.getStartTime() %>
								readonly="readonly" class="input-large datepicker" />
						</div>
						<div class="field-box">
							<label>任务预计结束时间:</label> <input id="planEndTime" name="planEndTime" type="text" value=<%=detailTask.getPlanEndtime() %>
								class="input-large datepicker" />
						</div>
						<div id="budgetCss" class="field-box">
							<label>修改预算:</label>
							<div class="input-append">
								<input id="budget" name="budget" class="input-large" type="text" value=<%=detailTask.getBudget() %>
									onblur="checkBudget()" /> <span class="add-on">万元</span>
							</div>
							<span id="budgetSpan" style="display: none;" class="alert-msg"><i
								class="icon-remove-sign"></i>只能为数字！</span>
						</div>
						<div class="field-box">
							<label>是否结束:</label>
							<div class="ui-select">
								<select id="stopSlect" name="stopSelect" >
									<option value="S1" selected="" />正常进行
									<option value="S2" />正常结束
									<option value="S3" />意外终止
								</select>
							</div>
						</div>
						<div class="field-box">
							<label>任务简介:</label>
							<textarea id="summary" name="summary" class="span8" rows="4"><%=detailTask.getSummary() %></textarea>
						</div>
						<button type="submit" class="btn btn-primary">确认修改</button>


						</form>
					</div>

					<!-- right column -->
					<div class="span4 column pull-right">
						<form   />
						<div class="field-box">
							<input class="search span9" type="text"
								placeholder="输入任务名搜索已有任务信息.." />
						</div>
						<div class="field-box">
							<label>开始时间:</label> <input type="text" value="03/29/2014"
								class="input-large datepicker" />
						</div>
						<div class="field-box">
							<label>结束时间:</label> <input type="text" value="03/29/2014"
								class="input-large datepicker" />
						</div>
						<div class="field-box">
							<label>任务简介:</label>
							<textarea class="span9 " rows="5"></textarea>


						</div>


						</form>
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