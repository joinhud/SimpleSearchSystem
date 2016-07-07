<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Accounting</title>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="btstrp_m" />
    <link href="${btstrp_m}" rel="stylesheet" />
    <spring:url value="/resources/core/css/material-kit.css" var="mtrlKit" />
    <link href="${mtrlKit}" rel="stylesheet" />
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
</head>
<body class="index-page">
<nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-index	">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#pablo">${userName}</a>
        </div>

        <div class="collapse navbar-collapse" id="navigation-index">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="" target="_blank" class="btn">
                        <i class="material-icons">poll</i> Main
                    </a>
                </li>
                <li>
                    <a href="" target="_blank" class="btn">
                        <i class="material-icons">exit_to_app</i> Log Out
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="wrapper">
    <div class="header header-filter" style="background-image: url('/resources/core/img/accounting-degree-tools.jpg');">
        <div class="container">
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h1>Personal e-accounting</h1>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="main main-raised">
        <div class="section">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="card">
                            <div class="card-body text-center">
                                <h5>
                                    Current balance : ${balance} $
                                </h5>
                                <div class="btn btn-primary">
                                    <i class="material-icons">add</i>
                                    Add
                                    <div class="ripple-container"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <c:choose>
                            <c:when test="${empty userGains}">
                                <button class="btn btn-raised btn-success btn-lg text-center">
                                    Add first gain
                                    <div class="ripple-container"></div>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <div class="panel panel-success">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">Gains</div>
                                    <!-- Table gains -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">Date</th>
                                                    <th class="text-center">Value</th>
                                                    <th class="text-center">Category</th>
                                                    <th class="text-center">Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${userGains}" var="gain">
                                                    <tr>
                                                        <td class="text-center">${gain.date}</td>
                                                        <td class="text-center">${gain.value}</td>
                                                        <td class="text-center">${gain.category}</td>
                                                        <td class="td-actions text-center">
                                                            <button type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                                                                <i class="fa fa-edit"></i>
                                                            </button>
                                                            <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                                                                <i class="fa fa-times"></i>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <c:choose>
                            <c:when test="${empty userExpenses}">
                                <button class="btn btn-raised btn-danger btn-lg text-center">
                                    Add first expense
                                    <div class="ripple-container"></div>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <div class="panel panel-danger">
                                    <!-- Default panel contents -->
                                    <div class="panel-heading">Expenses</div>
                                    <!-- Table -->
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="text-center">Date</th>
                                                <th class="text-center">Value</th>
                                                <th class="text-center">Category</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${userExpenses}" var="expense">
                                                    <tr>
                                                        <td class="text-center">${expense.date}</td>
                                                        <td class="text-center">${expense.value}</td>
                                                        <td class="text-center">${expense.category}</td>
                                                        <td class="td-actions text-center">
                                                            <button type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                                                                <i class="fa fa-edit"></i>
                                                            </button>
                                                            <button type="button" rel="tooltip" title="Remove" class="btn btn-danger btn-simple btn-xs">
                                                                <i class="fa fa-times"></i>
                                                            </button>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
<!--   Core JS Files   -->
<spring:url value="/resources/core/js/bootstrap.min.js" var="btstrpJs"/>
<script src="${btstrpJs}"></script>
<spring:url value="/resources/core/js/jquery.min.js" var="jqrJs"/>
<script src="${jqrJs}"></script>
<spring:url value="/resources/core/js/material.min.js" var="mtrlJs"/>
<script src="${mtrlJs}"></script>
<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
<spring:url value="/resources/core/js/material-kit.js" var="mtrlKitJs"/>
<script src="${mtrlKitJs}"></script>
<!-- Script for flex  -->
<script type="text/javascript">
    $().ready(function(){
        // the body of this function is in assets/material-kit.js
        //materialKit.initSliders();
        $(window).on('scroll', materialKit.checkScrollForTransparentNavbar);

        window_width = $(window).width();

        if (window_width >= 500){//768
            big_image = $('.wrapper > .header');

            $(window).on('scroll', materialKitDemo.checkScrollForParallax);
        }

    });
</script>
</html>
