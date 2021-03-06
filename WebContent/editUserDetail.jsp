<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>
<!DOCTYPE html>
<html>
<head>
	<title>ProjectZZY - Home</title>
    
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
        <!-- <div class="skins-nav">
            <a href="#" class="skin first_nav selected">
                <span class="icon"></span><span class="text">Default</span>
            </a>
            <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
                <span class="icon"></span><span class="text">Dark skin</span>
            </a>
        </div> -->
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>编辑用户信息</h3>
                </div>
                
                <%
                	int editID = Integer.parseInt((String)request.getParameter("actionID"));
                	User editUser = MemberInformation.seachUser(editID);
                %>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form class="new_user_form inline-input" action="UserManager" method="post"/>
                            	<input name="functionMy" value="editUser" type="hidden" />
                                <div class="span12 field-box">
                                    <label>员工号:</label>
                                    <input name="userID" class="span9" type="text" readonly="readonly" value=<%=editUser.getUserID()%> />
                                </div>
                                <div class="span12 field-box">
                                    <label>用户名:</label>
                                    <input name="userName" class="span9" type="text" value=<%=editUser.getName()%> />
                                </div>
                                <div class="span12 field-box">
                                    <label>用户类型:</label>
                                    <div class="ui-select span5">
                                        <select name="selectRole">
                                        <% 
                                        	if(editUser.getUserRole().equals("项目经理"))
                                        	{
                                        %>
                                        	<option value="OP" selected="selected" />项目经理
                                            <option value="OO" />项目成员
                                        <% 
                                        	}
                                        	else
                                        	{
                                        %>
                                        	 <option value="OP"  />项目经理
                                            <option value="OO" selected="selected" />项目成员
                                        <%
                                        	}
                                        %>
                                            
                                        </select>
                                    </div>
                                </div>
                                <div class="span12 field-box">
                                    <label>密码:</label>
                                    <input name="userPassword" class="span9" type="password" value=<%=editUser.getPassword()%> />
                                </div>
                                <div class="span12 field-box">
                                    <label>确认密码:</label>
                                    <input class="span9" type="password" value=<%=editUser.getPassword()%> />
                                </div>
                                 <div class="span12 field-box">
                                    <label>用户状态:</label>
                                    <div class="ui-select span5">
                                        <select name="selectState">
                                        <% 
                                        	if(editUser.getUserState() == 1)
                                        	{
                                        %>
                                            <option value="AC" selected="selected" />开启
                                            <option value="PU" />暂停
                                        <% 
                                        	}
                                        	else
                                        	{
                                        %>
                                        	 <option value="AC" />开启
                                            <option value="PU" selected="selected" />暂停
                                        <%
                                        	}
                                        %>
                                        </select>
                                    </div>
                                </div>
                                <div class="span12 field-box">
                                    <label>联系方式:</label>
                                    <input name="userPhone" class="span9" type="text" value=<%=editUser.getPhone()%> />
                                </div>
                                <div class="span12 field-box">
                                    <label>Email:</label>
                                    <input name="userEmail" class="span9" type="text" value=<%=editUser.getEmail()%> />
                                </div>
                                <div class="span12 field-box textarea">
                                    <label>简介:</label>
                                    <textarea name="userSummary" class="span9"  ><%=editUser.getSummary()%></textarea>
                                    <span class="charactersleft">个人简介最多不超过250个字符！</span>
                                </div>
                                
                                <div class="span11 field-box actions">
                                    <input type="submit" class="btn-glow primary" value="保存修改" />
                                </div>
                            </form>
                            
                            
                            
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