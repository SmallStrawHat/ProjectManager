<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector"%>
<!DOCTYPE html>
<html>
<head>
	<title>处理问题跟踪单</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet" type="text/css" />
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/new-user.css" type="text/css" media="screen" />  

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!-- lato font -->
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

  <%@ include file="boot.jsp" %>


	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div>
        
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>处理问题跟踪单</h3>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
        <%
            String str=request.getParameter("targetID");
            int problemID=Integer.parseInt(str);
            Vector problemList = TaskManager.searchAllProblem();
            
            for(int i=0;i<problemList.size();i++)
            {
               ProblemLog problem = (ProblemLog)problemList.get(i);
               int problemid = problem.getProblemID();
               if(problemID == problemid)
               {                           
          %>
                            <form action="DealwithProblemlog" method="post" class="new_user_form inline-input" />
                            <div class="span12 field-box">
                                    <label>处理问题单ID:</label>
                                    <input name="dealID"class="span9" type="text" />
                                </div>
                                <div class="span12 field-box">
                                    <label>问题单ID:</label>
                                    <input name="problemID"class="span9" type="text" readonly="readonly" value=<%="'"+problem.getProblemID()+"'" %>/><span><%=problem.getProblemID() %></span>
                                </div>
                                <div class="span12 field-box">
                                    <label>问题单路径:</label>
                                     <input name="path"class="span9" type="text" readonly="readonly" value=<%=problem.getLogPath() %>/><span><%=problem.getLogPath() %></span>                                 
                                </div>
                                <div class="span12 field-box">
                                    <label>处理人ID:</label>
                                    <input name="dealuserID"class="span9" type="text" readonle="readonly"value=<%="'"+problem.getDealUserID()+"'"%>/>
                                </div>
                                 <div class="span12 field-box">
                                    <label>创建人ID:</label>
                                    <input name="createrID"class="span9" type="text" readonle="readonly"value=<%="'"+problem.getDealUserID()+"'" %>/> 
                                </div>
                                <div class="span12 field-box">
                                    <label>任务ID:</label>
                                     <input name="taskID"class="span8 inline-input" type="text" readonly="readonly" value=<%="'"+problem.getTaskID()+"'" %> />
                                </div>      
                                <div class="field-box">
                                <label>问题概述:</label>
                                <textarea name="summary"class="span8" type="text"  readonly="readonly" value=<%=problem.getProblemDescreption() %>></textarea>    
                                 </div> 
                            <div class="field-box">
                                <label>处理问题方法描述:</label>
                                <textarea name="dealmethod"class="span8" type="text"></textarea>    
                                 </div>  
                                <div class="span11 field-box actions">
                                    <input type="button" class="btn-glow primary" value="提交" />
                                </div>
                            
                            </form>
                                                <%} %>
                    
<%} %>
                        </div>
                    </div>

                    <!-- side right column -->
                    <div class="span3 form-sidebar pull-right">
                        <div class="btn-group toggle-inputs hidden-tablet">
                            <button class="glow left active" data-input="inline">线条格式</button>
                            <button class="glow right" data-input="normal">方框格式</button>
                        </div>
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



	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-ui-1.10.2.custom.min.js"></script>
    <!-- knob -->
    <script src="js/jquery.knob.js"></script>
    <!-- flot charts -->
    <script src="js/jquery.flot.js"></script>
    <script src="js/jquery.flot.stack.js"></script>
    <script src="js/jquery.flot.resize.js"></script>
    <script src="js/theme.js"></script>

    

    <script type="text/javascript">
        $(function () {

            // toggle form between inline and normal inputs
            var $buttons = $(".toggle-inputs button");
            var $form = $("form.new_user_form");

            $buttons.click(function () {
                var mode = $(this).data("input");
                $buttons.removeClass("active");
                $(this).addClass("active");

                if (mode === "inline") {
                    $form.addClass("inline-input");
                } else {
                    $form.removeClass("inline-input");
                }
            });
        });
    </script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
