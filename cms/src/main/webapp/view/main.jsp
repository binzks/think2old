<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title><sitemesh:write property='title'/></title>

    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <script src="../js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="../js/My97DatePicker/WdatePicker.js"></script>
    <sitemesh:write property='head'/>
    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="../assets/css/bootstrap.css"/>
    <link rel="stylesheet" href="../assets/css/font-awesome.css"/>
    <link rel="stylesheet" href="../assets/css/chosen.css"/>
    <link rel="stylesheet" href="../assets/css/select2.css"/>
    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="../assets/css/ace-fonts.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="../assets/css/ace.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../assets/css/ace-part2.css" class="ace-main-stylesheet"/>
    <![endif]-->

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../assets/css/ace-ie.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="../assets/js/ace-extra.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="../assets/js/html5shiv.js"></script>
    <script src="../assets/js/respond.js"></script>
    <![endif]-->
</head>

<body class="no-skin">

<!-- #section:basics/navbar.layout -->
<div id="navbar" class="navbar navbar-default">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <!-- #section:basics/sidebar.mobile.toggle -->
        <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
            <span class="sr-only">Toggle sidebar</span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>
        </button>

        <!-- /section:basics/sidebar.mobile.toggle -->
        <div class="navbar-header pull-left">
            <!-- #section:basics/navbar.layout.brand -->
            <a href="#" class="navbar-brand">
                <small>
                    <i class="fa fa-leaf"></i>
                    智慧昆山
                </small>
            </a>

        </div>

        <!-- #section:basics/navbar.dropdown -->
        <div class="navbar-buttons navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <!-- #section:basics/navbar.user_menu -->
                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<span class="user-info">
									<small>${session_user.name} </small> ${session_user.roleName}
								</span>

                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="/user/chg_psw-${session_user.id}.idea">
                                <i class="ace-icon fa fa-key"></i>
                                修改密码
                            </a>
                        </li>

                        <li class="divider"></li>

                        <li>
                            <a href="/sys/logout.do">
                                <i class="ace-icon fa fa-power-off"></i>
                                注销
                            </a>
                        </li>
                    </ul>
                </li>

                <!-- /section:basics/navbar.user_menu -->
            </ul>
        </div>

        <!-- /section:basics/navbar.dropdown -->
    </div>
    <!-- /.navbar-container -->
</div>

<!-- /section:basics/navbar.layout -->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <!-- #section:basics/sidebar -->
    <div id="sidebar" class="sidebar responsive">
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'fixed')
            } catch (e) {
            }
        </script>
        <script type="text/javascript">
            function initMenu() {
                $.ajax({
                    url: "/sys/getMenu.do",
                    type: "post",
                    dataType: "json",
                    success: function (data) {
                        if (data && data.length != 0) {
                            var content = getMenu(eval(data), 0);
                            $("#mainMenus").append(content);
                            doSel();
                        }
                    },
                    error: function () {

                    }
                });
            }

            function getMenu(data, pid) {
                var content = "";
                for (var i = 0; i < data.length; i++) {
                    if (data[i].parent_id == pid) {
                        if (data[i].type == 0) { // 模块组
                            content += '<li class=""> <a href="#" class="dropdown-toggle"> <i class="menu-icon fa '
                                    + data[i].icon + '"></i><span class="menu-text">' + data[i].name + '</span>';
                            var child = getMenu(data, data[i].id);
                            if (child != "") {
                                content += ' <b class="arrow fa fa-angle-down" > </b></a> <b class="arrow"></b>'
                                        + '  <ul class="submenu">' + child + '</ul></li> ';
                            } else {
                                content += '</a> <b class="arrow"></b></li>';
                            }
                        } else { // 模块
                            content += '<li id="menu_' + data[i].url + data[i].id + '" class="" ><a href="' + data[i].url + data[i].id + '.idea'
                                    + '"> <i class="menu-icon fa fa-caret-right"></i>'
                                    + data[i].name + '</a><b class="arrow"></b></li>';
                        }
                    }
                }
                return content;
            }

            function doSel() {
                var url = window.location.href;
                url = url.substring(url.indexOf(window.location.host)
                        + window.location.host.length, url.length);
                var i = url.indexOf(".");
                if (i > 0) {
                    url = url.substring(0, i);
                }
                i = url.indexOf("-");
                if (i > 0) {
                    url = url.substring(0, i);
                }
                var liObj = document.getElementById("menu_" + url);
                if (null == liObj) {
                    url = document.referrer;
                    url = url.substring(url.indexOf(window.location.host)
                            + window.location.host.length, url.length);
                    var i = url.indexOf(".");
                    if (i > 0) {
                        url = url.substring(0, i);
                    }
                    i = url.indexOf("-");
                    if (i > 0) {
                        url = url.substring(0, i);
                    }
                    liObj = document.getElementById("menu_" + url);
                }
                liObj.setAttribute("class", "active open");
                liObj.parentNode.parentNode.setAttribute("class", "open");
            }
        </script>

        <ul id="mainMenus" class="nav nav-list"></ul>

        <!-- #section:basics/sidebar.layout.minimize -->
        <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
            <i class="ace-icon fa fa-angle-double-left" data-icon1="ace-icon fa fa-angle-double-left"
               data-icon2="ace-icon fa fa-angle-double-right"></i>
        </div>

        <!-- /section:basics/sidebar.layout.minimize -->
        <script type="text/javascript">
            try {
                ace.settings.check('sidebar', 'collapsed')
            } catch (e) {
            }
            initMenu();
        </script>
    </div>

    <!-- /section:basics/sidebar -->
    <div class="main-content">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <sitemesh:write property='body'/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.main-content -->

    <div class="footer">
        <div class="footer-inner">
            <!-- #section:basics/footer -->
            <div class="footer-content">
						<span class="bigger-120">
							<span class="blue bolder">IDEA</span>
							智慧昆山 &copy; 2015-2016
						</span>
            </div>

            <!-- /section:basics/footer -->
        </div>
    </div>

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='../assets/js/jquery.js'>" + "<" + "/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='../assets/js/jquery1x.js'>" + "<" + "/script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ('ontouchstart' in document.documentElement) document.write("<script src='../assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="../assets/js/bootstrap.js"></script>

<script src="../assets/js/chosen.jquery.js"></script>

<script src="../assets/js/jquery.bootstrap-duallistbox.js"></script>
<script src="../assets/js/jquery.raty.js"></script>
<script src="../assets/js/bootstrap-multiselect.js"></script>
<script src="../assets/js/select2.js"></script>
<script src="../assets/js/typeahead.jquery.js"></script>
<!-- page specific plugin scripts -->

<!-- ace scripts -->
<script src="../assets/js/ace/elements.scroller.js"></script>
<script src="../assets/js/ace/elements.colorpicker.js"></script>
<script src="../assets/js/ace/elements.fileinput.js"></script>
<script src="../assets/js/ace/elements.typeahead.js"></script>
<script src="../assets/js/ace/elements.wysiwyg.js"></script>
<script src="../assets/js/ace/elements.spinner.js"></script>
<script src="../assets/js/ace/elements.treeview.js"></script>
<script src="../assets/js/ace/elements.wizard.js"></script>
<script src="../assets/js/ace/elements.aside.js"></script>
<script src="../assets/js/ace/ace.js"></script>
<script src="../assets/js/ace/ace.ajax-content.js"></script>
<script src="../assets/js/ace/ace.touch-drag.js"></script>
<script src="../assets/js/ace/ace.sidebar.js"></script>
<script src="../assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="../assets/js/ace/ace.submenu-hover.js"></script>
<script src="../assets/js/ace/ace.widget-box.js"></script>
<script src="../assets/js/ace/ace.settings.js"></script>
<script src="../assets/js/ace/ace.settings-rtl.js"></script>
<script src="../assets/js/ace/ace.settings-skin.js"></script>
<script src="../assets/js/ace/ace.widget-on-reload.js"></script>
<script src="../assets/js/ace/ace.searchbox-autocomplete.js"></script>

<!-- inline scripts related to this page -->

<!-- the following scripts are used in demo only for onpage help and you don't need them -->
<link rel="stylesheet" href="../assets/css/ace.onpage-help.css"/>

<script type="text/javascript"> ace.vars['base'] = '..'; </script>
<script src="../assets/js/ace/elements.onpage-help.js"></script>
<script src="../assets/js/ace/ace.onpage-help.js"></script>

</body>
</html>
