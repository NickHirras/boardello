<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" ng-app="boardello">
<head>
    <base href="<%=request.getContextPath()%>/app/">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Boardello</title>

    <!-- Bootstrap -->
    <link href="<%=request.getContextPath()%>/lib/bootstrap-3.2.0-dist/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <!--[if lt IE 9]>
          <script src="<%=request.getContextPath()%>/lib/html5shiv-3.7.0/html5shiv.js"></script>
          <script src="<%=request.getContextPath()%>/lib/respond.js-1.4.2/respond.min.js"></script>
        <![endif]-->

    <link href="<%=request.getContextPath()%>/app.css" rel="stylesheet">
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/favicon.ico" type="image/x-icon" >
</head>
<body>
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container-fluid">
          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
              <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-tasks"></span> <em>Boardello!</em></a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <form class="navbar-form navbar-left" role="search">
                <div class="input-group">
                    <input type="text" class="form-control">
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button"><span class="glyphicon glyphicon-search"></span></button>
                    </span>
                  </div><!-- /input-group -->
            </form>
            <ul class="nav navbar-nav navbar-right">
              <li><a href="#" title="Add a new board"><span class="glyphicon glyphicon-plus"></span></a></li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Nicholas Smith <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="#">Profile</a></li>
                  <li><a href="#">Cards</a></li>
                  <li><a href="#">Settings</a></li>
                  <li class="divider"></li>
                  <li><a href="#">Help</a></li>
                  <li><a href="#">Apps</a></li>
                  <li><a href="#">Shortcuts</a></li>
                  <li><a href="#">Share Trello</a></li>
                  <li class="divider"></li>
                  <li><a href="<%=request.getContextPath()%>/logout">Log Out</a></li>
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </div><!-- /navigation -->

    <div class="container-fluid">        
        <div class="row">
            <div class="col-xs-12" ng-view="">
                Loading...
            </div>
        </div>
    </div>


    <script src="<%=request.getContextPath()%>/lib/jquery-1.11.1/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/bootstrap-3.2.0-dist/js/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/angular-1.2.20/angular.js"></script>
    <script src="<%=request.getContextPath()%>/lib/angular-1.2.20/angular-route.js"></script>
    <script src="<%=request.getContextPath()%>/lib/angular-1.2.20/angular-resource.js"></script>
    <script src="<%=request.getContextPath()%>/lib/angular-1.2.20/angular-sanitize.js"></script>
    <script src="<%=request.getContextPath()%>/lib/angular-1.2.20/angular-animate.js"></script>
    <script src="<%=request.getContextPath()%>/lib/ui-bootstrap-0.11.0/ui-bootstrap-tpls-0.11.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/lib/ui-utils-0.1.1/ui-utils.min.js"></script>
    <script src="<%=request.getContextPath()%>/app.js"></script>
    <script src="<%=request.getContextPath()%>/app-controller.js"></script>
    <script src="<%=request.getContextPath()%>/boards/board-list-controller.js"></script>
    <script src="<%=request.getContextPath()%>/boards/board-show-controller.js"></script>
    <script src="<%=request.getContextPath()%>/cards/card-show-controller.js"></script>
</body>
</html>