<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../../images/favicon.png">
    <!-- Pignose Calender -->
    <link href="../../../plugins/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href="../../../plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="../../../plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="../../../css/style.css" rel="stylesheet">
    <link href="../../../plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">
</head>

<body>

<!--*******************
    Preloader start
********************-->
<div id="preloader">
    <div class="loader">
        <svg class="circular" viewBox="25 25 50 50">
            <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10"/>
        </svg>
    </div>
</div>
<!--*******************
    Preloader end
********************-->


<!--**********************************
    Main wrapper start
***********************************-->
<div id="main-wrapper">

    <!--**********************************
        Nav header start
    ***********************************-->
    <div class="nav-header">
        <div class="brand-logo">
            <a href="index.html">
                <b class="logo-abbr"><img src="../../../images/logo.png" alt=""> </b>
                <span class="logo-compact"><img src="../../../images/logo-compact.png" alt=""></span>
                <span class="brand-title">
                        <img src="../../../images/logo-text.png" alt="">
                    </span>
            </a>
        </div>
    </div>
    <!--**********************************
        Nav header end
    ***********************************-->

    <!--**********************************
        Header start
    ***********************************-->
    <div class="header">
        <div class="header-content clearfix">

            <div class="nav-control">
                <div class="hamburger">
                    <span class="toggle-icon"><i class="icon-menu"></i></span>
                </div>
            </div>
            <div class="header-right">
                <ul class="clearfix">
                    <li class="icons dropdown">
                        <div class="user-img c-pointer position-relative" data-toggle="dropdown">
                            <span class="activity active"></span>
                            <img src="../../../images/user/1.png" height="40" width="40" alt="">
                        </div>
                        <div class="drop-down dropdown-profile animated fadeIn dropdown-menu">
                            <div class="dropdown-content-body">
                                <ul>
                                    <li>
                                        <a href="app-profile.html"><i class="icon-user"></i> <span>Profile</span></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void()">
                                            <i class="icon-envelope-open"></i> <span>Inbox</span>
                                            <div class="badge gradient-3 badge-pill gradient-1">3</div>
                                        </a>
                                    </li>

                                    <hr class="my-2">
                                    <li>
                                        <a href="page-lock.html"><i class="icon-lock"></i> <span>Lock Screen</span></a>
                                    </li>
                                    <li><a href="page-login.html"><i class="icon-key"></i> <span>Logout</span></a></li>
                                </ul>
                            </div>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--**********************************
        Header end ti-comment-alt
    ***********************************-->

    <!--**********************************
        Sidebar start
    ***********************************-->
    <div class="nk-sidebar">
        <div class="nk-nav-scroll">
            <ul class="metismenu" id="menu">
                <%--                <li class="nav-label">Dashboard</li>--%>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-speedometer menu-icon"></i><span class="nav-text">Dashboard</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../index.html">Home 1</a></li>--%>
                <%--                        <!-- <li><a href="../../../index-2.html">Home 2</a></li> -->--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <%--                <li class="mega-menu mega-menu-sm">--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-globe-alt menu-icon"></i><span class="nav-text">Layouts</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../layout-blank.html">Blank</a></li>--%>
                <%--                        <li><a href="../../../layout-one-column.html">One Column</a></li>--%>
                <%--                        <li><a href="../../../layout-two-column.html">Two column</a></li>--%>
                <%--                        <li><a href="../../../layout-compact-nav.html">Compact Nav</a></li>--%>
                <%--                        <li><a href="../../../layout-vertical.html">Vertical</a></li>--%>
                <%--                        <li><a href="../../../layout-horizontal.html">Horizontal</a></li>--%>
                <%--                        <li><a href="../../../layout-boxed.html">Boxed</a></li>--%>
                <%--                        <li><a href="../../../layout-wide.html">Wide</a></li>--%>


                <%--                        <li><a href="../../../layout-fixed-header.html">Fixed Header</a></li>--%>
                <%--                        <li><a href="layout-fixed-sidebar.html">Fixed Sidebar</a></li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <%--                <li class="nav-label">Apps</li>--%>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-envelope menu-icon"></i> <span class="nav-text">Email</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../email-inbox.html">Inbox</a></li>--%>
                <%--                        <li><a href="../../../email-read.html">Read</a></li>--%>
                <%--                        <li><a href="../../../email-compose.html">Compose</a></li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <li>
                    <a href="/hoa-don/hien-thi" aria-expanded="false">
                        <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">Hoá đơn</span>
                    </a>
                </li>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-graph menu-icon"></i> <span class="nav-text">Charts</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../chart-flot.html">Flot</a></li>--%>
                <%--                        <li><a href="../../../chart-morris.html">Morris</a></li>--%>
                <%--                        <li><a href="../../../chart-chartjs.html">Chartjs</a></li>--%>
                <%--                        <li><a href="../../../chart-chartist.html">Chartist</a></li>--%>
                <%--                        <li><a href="../../../chart-sparkline.html">Sparkline</a></li>--%>
                <%--                        <li><a href="../../../chart-peity.html">Peity</a></li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>

                <li>
                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                        <i class="icon-grid menu-icon"></i><span class="nav-text">Quản lý sản phẩm</span>
                    </a>
                    <ul aria-expanded="false">
                        <li><a href="/san-pham/hien-thi">Sản Phẩm</a></li>
                        <li><a href="/chat-lieu/hien-thi">Chất Liệu</a></li>
                        <li><a href="/co-ao/hien-thi">Cổ Áo</a></li>
                        <li><a href="/kich-co/hien-thi">Kích Cỡ</a></li>
                        <li><a href="/mau-sac/hien-thi">Màu Sắc</a></li>
                        <li><a href="/thuong-hieu/hien-thi">Thương Hiệu</a></li>
                    </ul>
                </li>
                <li>
                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                        <i class="icon-grid menu-icon"></i><span class="nav-text">Quản lý tài khoản</span>
                    </a>
                    <ul aria-expanded="false">
                        <li><a href="/nhan-vien/hien-thi">Nhân Viên</a></li>
                        <li><a href="/khach-hang/hien-thi">Khách Hàng</a></li>
                        <li><a href="/dia-chi/hien-thi">Địa Chỉ</a></li>
                        <li><a href="/chuc-vu/hien-thi">Chức Vụ</a></li>
                        <li><a href="/phieu-giam-gia/hien-thi">Phiếu Giảm Giá</a></li>
                    </ul>
                </li>
                <%--                <li>--%>
                <%--                    <a href="widgets.html" aria-expanded="false">--%>
                <%--                        <i class="icon-badge menu-icon"></i><span class="nav-text">Widget</span>--%>
                <%--                    </a>--%>
                <%--                </li>--%>
                <%--                <li class="nav-label">Forms</li>--%>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-note menu-icon"></i><span class="nav-text">Forms</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../form-basic.html">Basic Form</a></li>--%>
                <%--                        <li><a href="../../../form-validation.html">Form Validation</a></li>--%>
                <%--                        <li><a href="../../../form-step.html">Step Form</a></li>--%>
                <%--                        <li><a href="../../../form-editor.html">Editor</a></li>--%>
                <%--                        <li><a href="../../../form-picker.html">Picker</a></li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <%--                <li class="nav-label">Table</li>--%>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-menu menu-icon"></i><span class="nav-text">Table</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../table-basic.html" aria-expanded="false">Basic Table</a></li>--%>
                <%--                        <li><a href="../../../table-datatable.html" aria-expanded="false">Data Table</a></li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
                <%--                <li class="nav-label">Pages</li>--%>
                <%--                <li>--%>
                <%--                    <a class="has-arrow" href="javascript:void()" aria-expanded="false">--%>
                <%--                        <i class="icon-notebook menu-icon"></i><span class="nav-text">Pages</span>--%>
                <%--                    </a>--%>
                <%--                    <ul aria-expanded="false">--%>
                <%--                        <li><a href="../../../page-login.html">Login</a></li>--%>
                <%--                        <li><a href="../../../page-register.html">Register</a></li>--%>
                <%--                        <li><a href="../../../page-lock.html">Lock Screen</a></li>--%>
                <%--                        <li><a class="has-arrow" href="javascript:void()" aria-expanded="false">Error</a>--%>
                <%--                            <ul aria-expanded="false">--%>
                <%--                                <li><a href="../../../page-error-404.html">Error 404</a></li>--%>
                <%--                                <li><a href="../../../page-error-403.html">Error 403</a></li>--%>
                <%--                                <li><a href="../../../page-error-400.html">Error 400</a></li>--%>
                <%--                                <li><a href="../../../page-error-500.html">Error 500</a></li>--%>
                <%--                                <li><a href="../../../page-error-503.html">Error 503</a></li>--%>
                <%--                            </ul>--%>
                <%--                        </li>--%>
                <%--                    </ul>--%>
                <%--                </li>--%>
            </ul>
        </div>
    </div>

    <!--**********************************
        Sidebar end
    ***********************************-->

    <!--**********************************
        Content body start
    ***********************************-->
    <div class="content-body">
        <div class="container-fluid mt-3">
            <jsp:include page="${contentPage}"/>
        </div>
    </div>
    <!--**********************************
        Content body end
    ***********************************-->


    <!--**********************************
        Footer start
    ***********************************-->

    <!--**********************************
        Footer end
    ***********************************-->
</div>
<!--**********************************
    Main wrapper end
***********************************-->

<!--**********************************
    Scripts
***********************************-->

<script src="../../../plugins/common/common.min.js"></script>
<script src="../../../js/custom.min.js"></script>
<script src="../../../js/settings.js"></script>
<script src="../../../js/gleek.js"></script>
<script src="../../../js/styleSwitcher.js"></script>

<!-- Chartjs -->
<script src="../../../plugins/chart.js/Chart.bundle.min.js"></script>
<!-- Circle progress -->
<script src="../../../plugins/circle-progress/circle-progress.min.js"></script>
<!-- Datamap -->
<script src="../../../plugins/d3v3/index.js"></script>
<script src="../../../plugins/topojson/topojson.min.js"></script>
<script src="../../../plugins/datamaps/datamaps.world.min.js"></script>
<!-- Morrisjs -->
<script src="../../../plugins/raphael/raphael.min.js"></script>
<script src="../../../plugins/morris/morris.min.js"></script>
<!-- Pignose Calender -->
<script src="../../../plugins/moment/moment.min.js"></script>
<script src="../../../plugins/pg-calendar/js/pignose.calendar.min.js"></script>
<!-- ChartistJS -->
<script src="../../../plugins/chartist/js/chartist.min.js"></script>
<script src="../../../plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>


<script src="../../../js/dashboard/dashboard-1.js"></script>
<script src="../../../plugins/tables/js/jquery.dataTables.min.js"></script>
<script src="../../../plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
<script src="../../../plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
</body>

</html>