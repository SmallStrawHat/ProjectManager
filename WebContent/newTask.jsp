<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>ProjectZZY - NewTask</title>
    
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

  <%@ include file="boot.jsp" %>


	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="form-page">
            <div class="row-fluid header">
                    <h3>创建任务</h3>
                </div>
                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span8 column">
 					<%Vector project = ProjectManagement.getAllProjectList(); 
 						
 					%>
                    
                        <form action="TaskManagerExpre" method="post" />
                        	<input name="functionMy" value="createTask" type="hidden" />
                        	<input name="milepost" id="milepost" value="0" type="hidden" />
                            <div class="field-box">
                                <label>任务名称:</label>
                                <input name="taskName" class="span8" type="text" />
                            </div>
                            <div class="field-box">
                                <label>选择所属项目:</label>
                                <select style="width:250px" class="select2" name="projectSelect">
                                	<option />
                               		<% for(int i=0;i<project.size();i++)
                               		{	
                               		%>
                               		<option value=<%="'"+((Project)project.get(i)).getId()+"'"%> /><%=((Project)project.get(i)).getName()%>
                               		<%} %>
                                </select>
                            </div>
                            <div class="span12 field-box">
                            	<label>任务状态:</label>
                                <div class="ui-select span3" >
                                	<select name="taskStateSelect">
                                    	<option value="S001" />进行中的
                                        <option value="S002" />已经完成
                                        <option value="S003" />出现问题
                                    </select>
                                </div>
                                
                                <label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任务优先级:</label>
                                <div class="ui-select span3">
                                	<select name="taskLevelSelect">
                                    	<option value="L001" />正常
                                        <option value="L002" />最高
                                    </select>
                                </div>
                            </div>
                            <div class="span12 field-box">
                                    <label>是否是里程碑:</label>
                                    <div class="row ctrls">
	                            		<div class="slider-frame primary">
                                			<span  data-on-text="YES" data-off-text="NO" class="slider-button">NO</span>
                            			</div>
                        			</div>    
                            </div>
                            <div class="field-box">
                                <label>任务进度:</label>
                                
                                <div class="input-append">
                                    <input name="rate" class="input-large span5" type="text" placeholder="0" />
                                    <span class="add-on">%</span>
                                </div>
                            </div>
                            <ul class="nav nav-list">
                            	<div class="row-fluid header">
                				</div>
                                <li class="divider"></li>
                            </ul>
                            <div class="row-fluid header">
                    				<h3>任务详细信息:</h3>
                			</div>

                			
                			<!-- 详细信息 -->
                            <div class="field-box">
                                <label>开始时间:</label>
                                <input name="startTime" type="text" value="03/29/2014" class="input-large datepicker" />
                            </div>
                            <div class="field-box">
                                <label>结束时间:</label>
                                <input name="endTime" type="text" value="" class="input-large datepicker" />
                            </div>
                            <div class="field-box">
                                <label>预期的期限:</label>
                                <input name="planEndtime" type="text" value="03/29/2014" class="input-large datepicker" />
                            </div>
                             <%-- <div class="field-box">
                                <label>选择父任务:</label>
                                <select style="width:250px" class="select2" name="fatherTaskSelect">
                                    <option />
                                    <option value="NONE" />无
                                    <% Vector task = project.getTaskList();
                                    
                                    %>
                                    <option value="AK" />Alaska
                                    <option value="HI" />Hawaii
                                    <option value="CA" />California
                                    <option value="NV" />Nevada
                                    <option value="OR" />Oregon
                                    <option value="WA" />Washington
                                    <option value="AZ" />Arizona
                                    <option value="CO" />Colorado
                                    <option value="ID" />Idaho
                                </select>
                            </div> --%>
                            <div class="field-box">
                                <label>预算指标:</label>
                                <div class="input-append">
                                    <input name="budget" class="input-large" type="text" placeholder="0" />
                                    <span class="add-on">万元</span>
                                </div>
                            </div>
                            <div class="field-box">
                                <label>分配人力资源:</label>
                                <select style="width:500px" multiple="" class="select2" name="userSelect">
                                    <option />
                                    <% Vector user = MemberInformation.getUserList();
                                    	for(int i=0;i<user.size();i++)
                                    	{
                                    %>
                                    <option value=<%="'"+((User)user.get(i)).getUserID()+"'" %> /><%=((User)user.get(i)).getName()%>
                                    <%} %>
                                </select>
                            </div>
                            <div class="field-box">
                                <label>概述:</label>
                                <textarea name="summary" class="span9" rows="4"></textarea>
                            </div>
                            <div class="span11 field-box actions">
                                    <input type="submit" class="btn-glow primary" value="创建任务" />
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
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>



	
	



   