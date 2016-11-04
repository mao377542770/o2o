<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  	<%@include file="common/head.jsp" %>
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main_pc.css" />
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/box.css" />
    <title>讯果平台</title>
  </head>
  <body>
    <nav class="navbar navbar-default navbar-static-top myNav" role="navigation">
    	 <div class="container-fluid">
    		<!-- Brand and toggle get grouped for better mobile display -->
    		<div class="navbar-header">
    			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
    				<span class="sr-only">讯果平台</span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    				<span class="icon-bar"></span>
    			</button>
    			<a class="navbar-brand" href="#">讯果平台</a>
    		</div>
    		<!-- 按钮组 -->
    		<div class="collapse navbar-collapse navbar-ex1-collapse">
    			<ul class="nav navbar-nav">
    				<li class="active"><a href="#">Link</a></li>
    				<li><a href="#">Link</a></li>
    			</ul>
    			<ul class="nav navbar-nav navbar-right">
    				<li><a href="#">Link</a></li>
    				<li class="dropdown">
    					<a href="#" class="dropdown-toggle dropdown_minsize" data-toggle="dropdown">
    						<img src="images/avatar5.png" class="user-image" alt="User Image">
    						<span class="hidden-xs">用户名</span>
    					</a>
    					<ul class="dropdown-menu">
    						<li><a href="#">个人信息</a></li>
    						<li><a href="#">功能1</a></li>
    						<li><a href="#">功能2</a></li>
    						<li><a href="#">注销登录</a></li>
    					</ul>
    				</li>
    			</ul>
    		</div>
    	</div>
    </nav>
	
	<!-- 轮播图-->
    <div id="myCarousel" class="carousel slide" data-ride="carousel" data-wrap="true" data-interval="5000">
       <!-- 轮播（Carousel）指标 -->
       <ol class="carousel-indicators">
          <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
          <li data-target="#myCarousel" data-slide-to="1"></li>
          <li data-target="#myCarousel" data-slide-to="2"></li>
       </ol>   

       <!-- 轮播（Carousel）项目 -->
       <div class="carousel-inner" ng-swipe-left="leftSwipe()" ng-swipe-right="rightSwipe()">
          <div class="banner_a item active">
             <img src="images/banner.jpg" alt="First slide">
          </div>
          <div class="banner_a item">
             <img src="images/banner.jpg" alt="Second slide">
          </div>
          <div class="banner_a item">
             <img src="images/banner.jpg" alt="Third slide">
          </div>
       </div>
       <!-- 轮播（Carousel）导航  -->
       <a class="carousel-control left"  
          data-slide="prev">&lsaquo;</a>
       <a id="nextCarousel" class="carousel-control right"  
          data-slide="next">&rsaquo;</a>
    </div>
	
	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="navbar-header">
						<span data-target="#main-menu" data-toggle="collapse" class="nav-toggle-button collapsed">
							<span class="sr-only">Toggle navigation</span> <i class="fa fa-bars"></i>
						</span>
						<i class="fa fa-bars"></i>
					</div>
					<div id="main-menu" class="collapse navbar-collapse">
						<ul class="menu">
							<li role="presentation" class="nav-current">
								<a href="#">首页</a>
							</li>
							<li role="presentation">
								<a target="_blank" title="Lumen中文文档" href="http://lumen.golaravel.com">Lumen</a>
							</li>
							<li role="presentation">
								<a target="_blank" title="Laravel问答社区" href="http://wenda.golaravel.com">问答社区</a>
							</li>
							<li role="presentation">
								<a target="_blank" title="Laravel 中文文档" href="/laravel/docs/">中文文档</a>
							</li>
							<li role="presentation">
								<a title="下载 Laravel 中文文档离线版" href="/post/laravel-documents-offline user-image-package/">下载离线文档</a>
							</li>
							<li role="presentation">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
									主菜单3
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="">功能1</a>
										<a href="">功能2</a>
										<a href="">功能3</a>
										<a href="">功能4</a>
									</li>
								</ul>
							</li>
							<li role="presentation" class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
									主菜单4
									<span class="caret"></span>
								</a>
								<ul class="dropdown-menu">
									<li>
										<a href="">功能1</a>
										<a href="">功能2</a>
										<a href="">功能3</a>
										<a href="">功能4</a>
										<a href="">选择文件<input type="file"></a>
									</li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
	
	<!-- 内容部分 -->
	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">公告</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"> <i class="fa fa-minus"></i>
							</button>
							<button type="button" class="btn btn-box-tool" data-widget="remove"> <i class="fa fa-times"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->	
					<div class="box-body">
						<ul class="products-list product-list-in-box">
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Samsung TV
										<span class="label label-warning pull-right">$1800</span>
									</a>
									<span class="product-description">Samsung 32" 1080p 60Hz LED Smart HDTV.</span>
								</div>
							</li>
							<!-- /.item -->	
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Bicycle
										<span class="label label-info pull-right">$700</span>
									</a>
									<span class="product-description">26" Mongoose Dolomite Men's 7-speed, Navy Blue.</span>
								</div>
							</li>
							<!-- /.item -->	
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Xbox One
										<span class="label label-danger pull-right">$350</span>
									</a>
									<span class="product-description">Xbox One Console Bundle with Halo Master Chief Collection.</span>
								</div>
							</li>
							<!-- /.item -->	
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										PlayStation 4
										<span class="label label-success pull-right">$399</span>
									</a>
									<span class="product-description">PlayStation 4 500GB Console (PS4)</span>
								</div>
							</li>
							<!-- /.item -->	
						</ul>
					</div>
					<!-- /.box-body -->	
					<div class="box-footer text-center">
						<a href="javascript:void(0)" class="uppercase">View All Products</a>
					</div>
					<!-- /.box-footer -->	
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-sm-8">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">待开发模块</h3>
						<div class="box-tools pull-right" data-toggle="tooltip" title="Status">
							<div class="btn-group" data-toggle="btn-toggle">
								<button type="button" class="btn btn-default btn-sm active"> <i class="fa fa-square text-green"></i>
								</button>
								<button type="button" class="btn btn-default btn-sm">
									<i class="fa fa-square text-red"></i>
								</button>
							</div>
						</div>
					</div>
					<div class="box-body chat" id="chat-box">
						<!-- chat item -->			
						<div class="item">
							<img src="images/img/user4-128x128.jpg" alt="user image" class="online user-image">			

							<p class="message">
								<a href="#" class="name">
									<small class="text-muted pull-right">
										<i class="glyphicon glyphicon-time" aria-hidden="true"></i>
										2:15
									</small>
									Mike Doe
								</a>
								I would like to meet you to discuss the latest news about
                  the arrival of the new theme. They say it is going to be one the
                  best themes on the market
							</p>
							<div class="attachment">
								<h4>Attachments:</h4>

								<p class="filename">Theme-thumbnail-image.jpg</p>

								<div class="pull-right">
									<button type="button" class="btn btn-primary btn-sm btn-flat">Open</button>
								</div>
							</div>
							<!-- /.attachment -->			
						</div>
						<!-- /.item -->			
						<!-- chat item -->			
						<div class="item">
							<img src="images/img/user3-128x128.jpg" alt="user image" class="offline user-image">			

							<p class="message">
								<a href="#" class="name">
									<small class="text-muted pull-right">
										<i class="glyphicon glyphicon-time" aria-hidden="true"></i>
										5:15
									</small>
									Alexander Pierce
								</a>
								I would like to meet you to discuss the latest news about
                  the arrival of the new theme. They say it is going to be one the
                  best themes on the market
							</p>
						</div>
						<!-- /.item -->			
						<!-- chat item -->			
						<div class="item">
							<img src="images/img/user2-160x160.jpg" alt="user image" class="offline user-image">			

							<p class="message">
								<a href="#" class="name">
									<small class="text-muted pull-right">
										<i class="glyphicon glyphicon-time" aria-hidden="true"></i>
										5:30
									</small>
									Susan Doe
								</a>
								I would like to meet you to discuss the latest news about
                  the arrival of the new theme. They say it is going to be one the
                  best themes on the market
							</p>
						</div>
						<!-- /.item -->			
					</div>
					<!-- /.chat -->			
					<div class="box-footer">
						<div class="input-group">
							<input class="form-control" placeholder="Type message...">			

							<div class="input-group-btn">
								<button type="button" class="btn btn-success">
									<i class="fa fa-plus"></i>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-4">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">Recently Added Products</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"> <i class="fa fa-minus"></i>
							</button>
							<button type="button" class="btn btn-box-tool" data-widget="remove"> <i class="fa fa-times"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->			
					<div class="box-body">
						<ul class="products-list product-list-in-box">
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Samsung TV
										<span class="label label-warning pull-right">$1800</span>
									</a>
									<span class="product-description">Samsung 32" 1080p 60Hz LED Smart HDTV.</span>
								</div>
							</li>
							<!-- /.item -->			
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Bicycle
										<span class="label label-info pull-right">$700</span>
									</a>
									<span class="product-description">26" Mongoose Dolomite Men's 7-speed, Navy Blue.</span>
								</div>
							</li>
							<!-- /.item -->			
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										Xbox One
										<span class="label label-danger pull-right">$350</span>
									</a>
									<span class="product-description">Xbox One Console Bundle with Halo Master Chief Collection.</span>
								</div>
							</li>
							<!-- /.item -->			
							<li class="item">
								<div class="product-img">
									<img src="images/img/default-50x50.gif" alt="Product Image"></div>
								<div class="product-info">
									<a href="javascript:void(0)" class="product-title">
										PlayStation 4
										<span class="label label-success pull-right">$399</span>
									</a>
									<span class="product-description">PlayStation 4 500GB Console (PS4)</span>
								</div>
							</li>
							<!-- /.item -->			
						</ul>
					</div>
					<!-- /.box-body -->			
					<div class="box-footer text-center">
						<a href="javascript:void(0)" class="uppercase">View All Products</a>
					</div>
					<!-- /.box-footer -->			
				</div>
			</div>
		</div>
	</div>

	<!-- 公司信息 -->
	<div class="copyright">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<span>
						Copyright &copy;
						<a href="http://www.ghostchina.com/">优超科技有限公司</a>
					</span>
					|
					<span>
						<a target="_blank" href="http://www.miibeian.gov.cn/">京ICP备11008151号</a>
					</span>
					|
					<span>京公网安备11010802014853</span>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>

<script type="text/javascript">
  //启用轮播
  setTimeout(function() {
    $('#myCarousel').carousel("next"); //下一帧
  }, 5000);
  $("#myCarousel a.left").click(function() {
    $("#myCarousel").carousel("prev");
  });
  $("#myCarousel a.right").click(function() {
    $("#myCarousel").carousel("next");
  });
</script>