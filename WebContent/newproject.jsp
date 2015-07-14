<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>ProjectZZY - NewProject</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/bootstrap-wysihtml5.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/uniform.default.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/select2.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/bootstrap.datepicker.css" type="text/css" rel="stylesheet" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/form-showcase.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

  <%@ include file="boot.jsp" %>
  <% Vector p=new Vector();
	p=MemberInformation.getUserList();			   
	%>
	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="form-page">
            <div class="row-fluid header">
                    <h3>创建项目</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span8 column">
 					<form action="ProjectManager" method="post" />
							<input name="function" value="createProject" type="hidden" />
							<div class="field-box">
								<label>项目名称:</label> <input name="pname" class="span3"type="text" />
							</div>
							<div class="field-box">
								<label>开始时间:</label> 
								<input name="starttime" type="text" value="03/29/2014" class="input-large datepicker" />
							</div>
							<div class="field-box">
								<label>预计结束时间:</label> 
								<input name="expectendtime" type="text" value="03/24/2014" class="input-large datepicker" />
							</div>
							<div class="field-box">
								<label>项目预算:</label> <input name="budget" class="span3"
									type="text" />万元
							</div>
							<div class="field-box">
                                <label>选择项目经理:</label>
                                <select style="width:250px" class="select2" name="manager">
                                	<option />
                               		<% for(int i=0;i<p.size();i++)
                               		{
                               			User user=(User)p.get(i);
                               			if(user.getUserRole().equals("项目经理"))
                               			{
                               		%>
                               		<option value=<%="'"+user.getUserID()+"'"%> /><%=user.getName() %>(ID:<%=user.getUserID()%>)
                               		<% } 
                               		} %>
                                </select>
                            </div>
							<div class="field-box">
								<label>项目优先级:</label> <input name="priority" class="span3"
									type="text" />
							</div>

							<div class="field-box">
								<label>计划耗时:</label> <input name="plantime" class="span3"
									type="text" />小时
							</div>

							<div class="field-box">
								<label>项目概述:</label>
								<textarea name="summary" class="span8" type="text"></textarea>
							</div>
							<div class="span11 field-box actions">
								<input type="submit" name="Submit"
									class="btn-glow primary active" value="提交" />
							</div>
							</form>                                       
                    </div>

                    <!-- right column -->
                    <div class="span3 column form-sidebar pull-right">
                      
                        <div class="alert alert-info hidden-tablet">
                            <i class="icon-lightbulb pull-left"></i>
                            	点击上面的按钮，根据您的喜爱切换输入框的风格。
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
    
    
        <script type="text/javascript">
        $(function () {

            // Switch slide buttons
            $('.slider-button').click(function() {
                if ($(this).hasClass("on")) {
                    $(this).removeClass('on').html($(this).data("off-text"));   
                    document.getElementById('milepost').value = '0';
                } else {
                    $(this).addClass('on').html($(this).data("on-text"));
                    document.getElementById('milepost').value = '1';
                }
            });

        });
    </script>

    <!-- call this page plugins -->
    <script type="text/javascript">
        $(function () {

            // add uniform plugin styles to html elements
            $("input:checkbox, input:radio").uniform();

            // select2 plugin for select elements
            $(".select2").select2({
                placeholder: "Select a State"
            });

            // datepicker plugin
            $('.datepicker').datepicker().on('changeDate', function (ev) {
                $(this).datepicker('hide');
            });

            // wysihtml5 plugin on textarea
            $(".wysihtml5").wysihtml5({
                "font-styles": false
            });
        });
    </script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
 language='JavaScript' charset='gb2312'></script></div>
</body>
</html>



	
	



   