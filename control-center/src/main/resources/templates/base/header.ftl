<#setting number_format="#">
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数字长大-云控中心 | Dashboard</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="description" content="长沙学院校园双创服务微平台运营中心">
    <meta name="keywords" content="云控中心">
    <!-- bootstrap 3.0.2 -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- font Awesome -->
    <link href="../static/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
    <!-- Ionicons -->
    <link href="../static/css/ionicons.min.css" rel="stylesheet" type="text/css"/>
    <!-- Morris chart -->
    <link href="../static/css/morris/morris.css" rel="stylesheet" type="text/css"/>
    <!-- jvectormap -->
    <link href="../static/css/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css"/>
    <!-- Date Picker -->
    <link href="../static/css/datepicker/datepicker3.css" rel="stylesheet" type="text/css"/>
    <!-- fullCalendar -->
    <!-- <link href="css/fullcalendar/fullcalendar.css" rel="stylesheet" type="text/css" /> -->
    <!-- Daterange picker -->
    <link href="../static/css/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" type="text/css"/>
    <!-- iCheck for checkboxes and radio inputs -->
    <link href="../static/css/iCheck/all.css" rel="stylesheet" type="text/css"/>
    <!-- bootstrap wysihtml5 - text editor -->
    <!-- <link href="css/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css" rel="stylesheet" type="text/css" /> -->
    <link href='http://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
    <!-- Theme style -->
    <link href="../static/css/style.css" rel="stylesheet" type="text/css"/>
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <!-- jQuery 2.0.2 -->
    <script src="../static/js/jquery.min.js" type="text/javascript"></script>
    <!-- jQuery UI 1.10.3 -->
    <script src="../static/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
    <!-- Bootstrap -->
    <script src="../static/js/bootstrap.min.js" type="text/javascript"></script>
    <!-- daterangepicker -->
    <script src="../static/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="../static/js/plugins/chart.js" type="text/javascript"></script>
    <!-- iCheck -->
    <script src="../static/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
    <!-- calendar -->
    <script src="../static/js/plugins/fullcalendar/fullcalendar.js" type="text/javascript"></script>
    <!-- Director App -->
    <script src="../static/js/Director/app.js" type="text/javascript"></script>
    <!-- Director dashboard demo (This is only for demo purposes) -->
    <script src="../static/js/Director/dashboard.js" type="text/javascript"></script>
</head>
<!-- Director for demo purposes -->
<script type="text/javascript">
    $('input').on('ifChecked', function (event) {
        // var element = $(this).parent().find('input:checkbox:first');
        // element.parent().parent().parent().addClass('highlight');
        $(this).parents('li').addClass("task-done");
        console.log('ok');
    });
    $('input').on('ifUnchecked', function (event) {
        // var element = $(this).parent().find('input:checkbox:first');
        // element.parent().parent().parent().removeClass('highlight');
        $(this).parents('li').removeClass("task-done");
        console.log('not');
    });
</script>
<script>
    $('#noti-box').slimScroll({
        height: '400px',
        size: '5px',
        BorderRadius: '5px'
    });
    $('input[type="checkbox"].flat-grey, input[type="radio"].flat-grey').iCheck({
        checkboxClass: 'icheckbox_flat-grey',
        radioClass: 'iradio_flat-grey'
    });
    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    function sleep(numberMillis) {
        var now = new Date();
        var exitTime = now.getTime() + numberMillis;
        while (true) {
            now = new Date();
            if (now.getTime() > exitTime)
                return;
        }
    }

</script>
<body class="skin-black">
<!-- header logo: style can be found in header.less -->
<header class="header">
    <a href="/" class="logo">
        云控中心
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top" role="navigation">
        <!-- Sidebar toggle button-->

        <div class="navbar-right">
            <ul class="nav navbar-nav">
                <!-- User Account: style can be found in dropdown.less -->
                <li class="dropdown user user-menu">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <i class="fa fa-user"></i>
                        <span>${(user.name)!"未登录"} <i class="caret"></i></span>
                    </a>
                    <ul class="dropdown-menu dropdown-custom dropdown-menu-right">
                        <li class="dropdown-header text-center">Account</li>

                        <li>
                            <a href="#">
                                <i class="fa fa-clock-o fa-fw pull-right"></i>
                                <span class="badge badge-success pull-right">10</span> Updates</a>
                            <a href="#">
                                <i class="fa fa-envelope-o fa-fw pull-right"></i>
                                <span class="badge badge-danger pull-right">5</span> Messages</a>
                            <a href="#"><i class="fa fa-magnet fa-fw pull-right"></i>
                                <span class="badge badge-info pull-right">3</span> Subscriptions</a>
                            <a href="#"><i class="fa fa-question fa-fw pull-right"></i> <span class="badge pull-right">11</span>FAQ</a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="#">
                                <i class="fa fa-user fa-fw pull-right"></i>
                                Profile
                            </a>
                            <a data-toggle="modal" href="#modal-user-settings">
                                <i class="fa fa-cog fa-fw pull-right"></i>
                                Settings
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="/user/logout"><i class="fa fa-ban fa-fw pull-right"></i> Logout</a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
</header>

<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="../static/img/26115.jpg" class="img-circle" alt="User Image"/>
                </div>
                <div class="pull-left info">
                    <p>${(user.name)!"未登录"}</p>
                    <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
                </div>
            </div>
            <!-- search form -->
            <form action="#" method="get" class="sidebar-form">
                <div class="input-group">
                    <input type="text" name="q" class="form-control" placeholder="搜索..."/>
                    <span class="input-group-btn">
                                        <button type='submit' name='seach' id='search-btn' class="btn btn-flat"><i
                                                class="fa fa-search"></i></button>
                                    </span>
                </div>
            </form>
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="active">
                    <a href="/">
                        <i class="fa fa-dashboard"></i> <span>仪表盘</span>
                    </a>
                </li>
                <li>
                    <a href="/service">
                        <i class="fa fa-glass"></i> <span>服务管理</span>
                    </a>
                </li>
                <li>
                    <a href="/resource">
                        <i class="fa fa-gavel"></i> <span>资产管理</span>
                    </a>
                </li>
                <li>
                    <a href="/user">
                        <i class="fa fa-globe"></i> <span>用户管理</span>
                    </a>
                </li>
                <#--<li>-->
                    <#--<a href="/system">-->
                        <#--<i class="fa fa-flash"></i> <span>系统管理</span>-->
                    <#--</a>-->
                <#--</li>-->

                <!--<li>-->
                <!--<a href="#">-->
                <!--<i class="fa fa-tachometer"></i> <span>圈子管理</span>-->
                <!--</a>-->
                <!--</li>-->

                <!--<li>-->
                <!--<a href="#">-->
                <!--<i class="fa fa-comment-o"></i> <span>双创管理</span>-->
                <!--</a>-->
                <!--</li>-->
            </ul>
        </section>
    </aside>