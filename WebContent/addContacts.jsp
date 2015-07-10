<%@ page language="java" pageEncoding="utf-8"%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>ProjectZZY - Home</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/tables.css" type="text/css" media="screen" />

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
            <div id="pad-wrapper">
                
 
                <!-- users table -->
                <div class="table-wrapper users-table section">
                    <div class="row-fluid head">
                        <div class="span12">
                            <h4>添加联系人</h4>
                        </div>
                    </div>

                    <div class="row-fluid filter-block">
                        <div class="pull-right">
                            <a class="btn-flat pull-right success new-product add-user">+ 添加</a>
                            <input type="text" class="search user-search" placeholder="搜索联系人.." />
                            <div class="ui-select">
                                <select>
                                  <option />姓名
                                  <option />员工号
                                  <option />手机号
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                	<th class="span3">
                                	<input type="checkbox" />
                                  	Name
                               		 </th>
                                	<th class="span2">
                                    	<span class="line"></span>UserID
                                	</th>
                                	<th class="span2">
                                    	<span class="line"></span>Phone
                                	</th>
                                    <th class="span2 align-right">
                                        <span class="line"></span>Email
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <!-- row -->
                                <tr class="first">
                                    <td>
                                    <input type="checkbox" />
                                        <img src="img/contact-img.png" class="img-circle avatar hidden-phone" />
                                        <a href="user-profile.html" class="name">Alejandra Galvan Castillo</a>
                                        <span class="subtext">Graphic Design</span>
                                    </td>
                                    <td>
                                        Jan 11, 2012
                                    </td>
                                    <td>
                                        $ 500.00
                                    </td>
                                    <td class="align-right">
                                        <a href="#">alejandra@gmail.com</a>
                                    </td>
                                </tr>
                               
                            </tbody>
                        </table>
                    </div>
                </div>
                <!-- end users table -->
            </div>
        </div>
    </div>
    <!-- end main container -->

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>