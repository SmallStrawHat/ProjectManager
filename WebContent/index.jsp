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
<link href="css/bootstrap/bootstrap-overrides.css" type="text/css"
	rel="stylesheet" />

<!-- libraries -->
<link href="css/lib/jquery-ui-1.10.2.custom.css" rel="stylesheet"
	type="text/css" />
<link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />

<!-- global styles -->
<link rel="stylesheet" type="text/css" href="css/layout.css" />
<link rel="stylesheet" type="text/css" href="css/elements.css" />
<link rel="stylesheet" type="text/css" href="css/icons.css" />

<!-- this page specific styles -->
<link rel="stylesheet" href="css/compiled/index.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="css/compiled/personal-info.css"
	type="text/css" media="screen" />

<!-- open sans font -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css' />

<!-- lato font -->
<link
	href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900,300italic,400italic,700italic,900italic'
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
		<!-- <div class="skins-nav">
			<a href="#" class="skin first_nav selected"> <span class="icon"></span><span
				class="text">Default skin</span>
			</a> <a href="#" class="skin second_nav" data-file="css/skins/dark.css">
				<span class="icon"></span><span class="text">Dark skin</span>
			</a>
		</div> -->

		<div class="container-fluid">

			<img
				src="img/bgs/0.jpg" />

			<!-- ABC container .wide-content is used for this layout without sidebar :)  -->
			<div class="content wide-content">
				<div class="container-fluid">
					<div class="settings-wrapper" id="pad-wrapper">
						<!-- avatar column -->
						<div class="span3 avatar-box">
							<div class="personal-image">
								<img src="img/personal-info.png" class="avatar img-circle" />
							</div>
						</div>

						<!-- edit form column -->
						<div class="span7 personal-info">
							<div class="row-fluid">
								<h5 class="personal-title">联系我们</h5>
							</div>
							<div class="alert alert-info">
								<div class="span2">
									<ul>
										<li><strong>E-MAIL</strong><i class="icon-envelope"></i><br> <br>
											<a href=mailto:1@ncepu.edu.cn>1@ncepu.edu.cn</a> </li>
									</ul>
								</div>
								<i class="icon-lightbulb"></i> <strong>Software Project
									Management</strong> is the art and science of planning and leading
								software projects. It is a sub-discipline of project management
								in which software projects are planned, implemented, monitored
								and controlled.
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- end ABC container -->
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

		<!-- pre load bg imgs -->
		<script type="text/javascript">
			$(function() {
				// bg switcher
				var $btns = $(".bg-switch .bg");
				$btns.click(function(e) {
					e.preventDefault();
					$btns.removeClass("active");
					$(this).addClass("active");
					var bg = $(this).data("img");

					$("html").css("background-image",
							"url('img/bgs/" + bg + "')");
				});

			});
		</script>

		<script type="text/javascript">
			$(function() {

				// jQuery Knobs
				$(".knob").knob();

				// jQuery UI Sliders
				$(".slider-sample1").slider({
					value : 100,
					min : 1,
					max : 500
				});
				$(".slider-sample2").slider({
					range : "min",
					value : 130,
					min : 1,
					max : 500
				});
				$(".slider-sample3").slider({
					range : true,
					min : 0,
					max : 500,
					values : [ 40, 170 ],
				});

				// jQuery Flot Chart
				var visits = [ [ 1, 50 ], [ 2, 40 ], [ 3, 45 ], [ 4, 23 ],
						[ 5, 55 ], [ 6, 65 ], [ 7, 61 ], [ 8, 70 ], [ 9, 65 ],
						[ 10, 75 ], [ 11, 57 ], [ 12, 59 ] ];
				var visitors = [ [ 1, 25 ], [ 2, 50 ], [ 3, 23 ], [ 4, 48 ],
						[ 5, 38 ], [ 6, 40 ], [ 7, 47 ], [ 8, 55 ], [ 9, 43 ],
						[ 10, 50 ], [ 11, 47 ], [ 12, 39 ] ];

				var plot = $.plot($("#statsChart"), [ {
					data : visits,
					label : "Signups"
				}, {
					data : visitors,
					label : "Visits"
				} ], {
					series : {
						lines : {
							show : true,
							lineWidth : 1,
							fill : true,
							fillColor : {
								colors : [ {
									opacity : 0.1
								}, {
									opacity : 0.13
								} ]
							}
						},
						points : {
							show : true,
							lineWidth : 2,
							radius : 3
						},
						shadowSize : 0,
						stack : true
					},
					grid : {
						hoverable : true,
						clickable : true,
						tickColor : "#f9f9f9",
						borderWidth : 0
					},
					legend : {
						// show: false
						labelBoxBorderColor : "#fff"
					},
					colors : [ "#a7b5c5", "#30a0eb" ],
					xaxis : {
						ticks : [ [ 1, "JAN" ], [ 2, "FEB" ], [ 3, "MAR" ],
								[ 4, "APR" ], [ 5, "MAY" ], [ 6, "JUN" ],
								[ 7, "JUL" ], [ 8, "AUG" ], [ 9, "SEP" ],
								[ 10, "OCT" ], [ 11, "NOV" ], [ 12, "DEC" ] ],
						font : {
							size : 12,
							family : "Open Sans, Arial",
							variant : "small-caps",
							color : "#697695"
						}
					},
					yaxis : {
						ticks : 3,
						tickDecimals : 0,
						font : {
							size : 12,
							color : "#9da3a9"
						}
					}
				});

				function showTooltip(x, y, contents) {
					$('<div id="tooltip">' + contents + '</div>').css({
						position : 'absolute',
						display : 'none',
						top : y - 30,
						left : x - 50,
						color : "#fff",
						padding : '2px 5px',
						'border-radius' : '6px',
						'background-color' : '#000',
						opacity : 0.80
					}).appendTo("body").fadeIn(200);
				}

				var previousPoint = null;
				$("#statsChart")
						.bind(
								"plothover",
								function(event, pos, item) {
									if (item) {
										if (previousPoint != item.dataIndex) {
											previousPoint = item.dataIndex;

											$("#tooltip").remove();
											var x = item.datapoint[0]
													.toFixed(0), y = item.datapoint[1]
													.toFixed(0);

											var month = item.series.xaxis.ticks[item.dataIndex].label;

											showTooltip(item.pageX, item.pageY,
													item.series.label + " of "
															+ month + ": " + y);
										}
									} else {
										$("#tooltip").remove();
										previousPoint = null;
									}
								});
			});
		</script>
		<div style="display: none">
			<script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540'
				language='JavaScript' charset='gb2312'></script>
		</div>
</body>
</html>