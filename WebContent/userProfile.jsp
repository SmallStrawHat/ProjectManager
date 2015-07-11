<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.business.*,java.util.Vector" %>

<!DOCTYPE html>
<html>
<head>
	<title>Detail Admin - User Profile</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- libraries -->
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/user-profile.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>
<% 
   User localUser = MemberInformation.seachUser(Integer.parseInt(request.getParameter("targetID")));
   if(localUser == null)
   {
	   response.sendRedirect("index.jsp");
   }
%>
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
            <div id="pad-wrapper" class="user-profile">
                <!-- header -->
                <div class="row-fluid header">
                    <div class="span8">
                        <img src="img/contact-profile.png" class="avatar img-circle" />
                        <h3 class="name"><%=localUser.getName()%></h3>
                        <span class="area"><%=localUser.getEmail()%></span>
                    </div>
                    <a class="btn-flat icon pull-right delete-user" data-toggle="modal" data-target="#myModal" title="Delete user" data-placement="top">
                        <i class="icon-trash"></i>
                    </a>
                     <a class="btn-flat icon large pull-right edit" href=<%="editUserDetail.jsp?actionID="+localUser.getUserID()%> >
                        	编辑用户信息
                    </a>
                </div>

                <div class="row-fluid profile">
                    <!-- bio, new note & orders column -->
                    <div class="span9 bio">
                        <div class="profile-box">
                            <!-- biography -->
                            <div class="span12 section">
                                <h6>简介</h6>
                                <p><%=localUser.getSummary()%></p>
                            </div>

                            <h6>正在参与的任务</h6>
                            <br />
                            <!-- recent orders table -->
                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="span2">
                                            Order ID
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            Date
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            Items
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            Total amount
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
                                    <tr class="first">
                                        <td>
                                            <a href="#">#459</a>
                                        </td>
                                        <td>
                                            Jan 03, 2014
                                        </td>
                                        <td>
                                            3
                                        </td>
                                        <td>
                                            $ 3,500.00
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="#">#510</a>
                                        </td>
                                        <td>
                                            Feb 22, 2014
                                        </td>
                                        <td>
                                            5
                                        </td>
                                        <td>
                                            $ 800.00
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <a href="#">#618</a>
                                        </td>
                                        <td>
                                            Jan 03, 2014
                                        </td>
                                        <td>
                                            8
                                        </td>
                                        <td>
                                            $ 3,499.99
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <!-- end orders table -->
                            
                        <h6>用户日志</h6>
                        <br />
                      
                        <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th class="span2">
                                            LoginID
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            LoginDate
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            LogoutDate
                                        </th>
                                        <th class="span3">
                                            <span class="line"></span>
                                            Detailed operation
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!-- row -->
                                    <tr class="first">
                                        <td>
                                            112001
                                        </td>
                                        <td>
                                            Jan 03, 2014
                                        </td>
                                        <td>
                                            Jan 03, 2014
                                        </td>
                                        <td>
                                            $ 3,500.00
                                        </td>
                                    </tr>
                                    
                                    
                                </tbody>
                            </table>
                            
                            <!-- end log table -->
                      
                            <!-- new comment form -->
                            <div class="span12 section comment">
                                <h6>Add a quick note</h6>
                                <p>Add a note about this user to keep a history of your interactions.</p>
                                <textarea></textarea>
                                <a href="#">Attach files</a>
                                <div class="span12 submit-box pull-right">
                                    <input type="submit" class="btn-glow primary" value="Add Note" />
                                    <span>OR</span>
                                    <input type="reset" value="Cancel" class="reset" />
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- side address column -->
                    <div class="span3 address pull-right">
                        <h6>Address</h6>
                        
                        <ul>
                            <li>2301 East Lamar Blvd. Suite 140. </li>
                            <li>City, Arlington. United States,</li>
                            <li>Zip Code, TX 76006.</li>
                            <li class="ico-li">
                                <i class="ico-phone"></i>
                                1817 274 2933
                            </li>
                             <li class="ico-li">
                                <i class="ico-mail"></i>
                                <a href="#">alejandra@detailcanvas.com</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">删除用户</h4>
      </div>
      <div class="modal-body">    
       		这个操作将会删除用户的所有信息，真的要删除这个用户吗？
      </div>
      <div class="modal-footer">
      
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <a href=<%="UserManager?functionMy=deleteUser&accountID="+localUser.getUserID() %> ><button type="button" class="btn btn-danger">确定</button></a>
      </div>
    </div>
  </div>
</div>
<!--  end Modal-->

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>