<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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

    <spring:url value="/resources/core/js/jquery.min.js" var="jqrJs"/>
    <script src="${jqrJs}" type="text/javascript"></script>

    <script>

        $(document).ready(function() {
            var deleteLink = $("a.removeItem");


            $(deleteLink).click(function(event) {
                var conBox = confirm("Are you sure ?");

                if(conBox){
                    $.ajax({
                        url: $(event.target).attr("href"),
                        type: "DELETE",

                        beforeSend: function(xhr) {
                            xhr.setRequestHeader("Accept", "application/json");
                            xhr.setRequestHeader("Content-Type", "application/json");
                        },

                        success: function() {
                            var tr = $(event.target).closest("tr");
                            tr.css("background-color","#000000");
                            tr.fadeIn(1000).fadeOut(200, function(){
                                tr.remove();
                            })
                        }
                    });
                } else {
                    event.preventDefault();
                }

                event.preventDefault();
            });

        });
    </script>
</head>
<body class="index-page">
<nav class="navbar navbar-transparent navbar-fixed-top navbar-color-on-scroll">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-index ">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#pablo">
                <i class="material-icons">account_circle</i>
                ${userName}
            </a>
        </div>

        <div class="collapse navbar-collapse" id="navigation-index">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${!empty userGains or !empty userExpenses}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <i class="material-icons">add</i>Add <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu">
                            <li><a href="/gain">Gain</a></li>
                            <li><a href="/expense">Expense</a></li>
                        </ul>
                    </li>
                </c:if>
                <li>
                    <a href="/logout" target="_self" class="btn">
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
                <div class="col-md-12 col-md-offset-2">
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
                            <div class="text-center">
                                <h4>
                                    Current balance : ${balance} $
                                </h4>
                            </div>
                            <hr/>
                            <div class="text-center">
                                <a href="/" class="btn btn-primary btn-round">
                                    Get All
                                </a>
                            </div>
                            <hr/>
                            <div class="text-center">
                                <button class="btn btn-info btn-round" data-toggle="collapse"
                                        data-target="#collapseSearch" aria-expanded="false"
                                        aria-controls="collapseSearch">
                                    <i class="material-icons">search</i> Search
                                </button>
                            </div>
                            <div class="collapse" id="collapseSearch">
                                <div>
                                    <form action="getByYear" method="get" class="form">
                                        <div class="content">
                                            <div class="text-center">
                                                <h5>
                                                    Search by year
                                                </h5>
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="material-icons">date_range</i>
                                                </span>
                                                <div class="form-group label-static">
                                                    <label class="control-label">Year</label>
                                                    <input type="number" min="1" name="year" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="footer text-center">
                                            <button class="btn btn-info btn-wd btn-lg" type="submit">
                                                Ok
                                                <div class="ripple-container"></div>
                                            </button>
                                        </div>
                                    </form>

                                    <hr/>

                                    <form action="getByMonth" method="get" class="form">
                                        <div class="content">
                                            <div class="text-center">
                                                <h5>
                                                    Search by month
                                                </h5>
                                            </div>
                                            <div class="input-group">
                                                <span class="input-group-addon">
                                                    <i class="material-icons">date_range</i>
                                                </span>
                                                <div class="form-group label-static">
                                                    <label class="control-label">Number of Month</label>
                                                    <input type="number" min="1" max="12" name="month" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="footer text-center">
                                            <button class="btn btn-info btn-wd btn-lg" type="submit">
                                                Ok
                                                <div class="ripple-container"></div>
                                            </button>
                                        </div>
                                    </form>

                                    <hr/>

                                    <form action="getByDay" method="get" class="form">
                                        <div class="content">
                                            <div class="text-center">
                                                <h5>
                                                    Search by day
                                                </h5>
                                            </div>
                                            <div class="input-group">
										        <span class="input-group-addon">
										        	<i class="material-icons">date_range</i>
										        </span>
                                                <div class="form-group label-static">
                                                    <label class="control-label">Date</label>
                                                    <input type="text" name="day" class="datepicker form-control" value="${currDay}">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="footer text-center">
                                            <button class="btn btn-info btn-wd btn-lg" type="submit">
                                                Ok
                                                <div class="ripple-container"></div>
                                            </button>
                                        </div>
                                    </form>

                                    <hr/>

                                    <form action="getByValueRange" method="get" class="form">
                                        <div class="content">
                                            <div class="text-center">
                                                <h5>
                                                    Search by value range
                                                </h5>
                                            </div>
                                            <div class="input-group">
                                                <div class="form-group label-static">
                                                    <label class="control-label">Min value</label>
                                                    <input type="number" min="0" max="999999" name="min_val" class="form-control">
                                                </div>
                                            </div>
                                            <div class="input-group">
                                                <div class="form-group label-static">
                                                    <label class="control-label">Max value</label>
                                                    <input type="number" min="0" max="999999" name="max_val" class="form-control">
                                                </div>
                                            </div>
                                        </div>
                                        <div class="footer text-center">
                                            <button class="btn btn-info btn-wd btn-lg" type="submit">
                                                Ok
                                                <div class="ripple-container"></div>
                                            </button>
                                        </div>
                                    </form>

                                    <hr/>

                                    <form action="getByCategory" method="get" class="form">
                                        <div class="content">
                                            <div class="text-center">
                                                <h5>
                                                    Search by category
                                                </h5>
                                            </div>
                                            <div class="input-group">
                                                <div class="form-group label-static">
                                                    <label class="control-label">Gains category</label>
                                                    <select name="gainCategory" class="form-control">
                                                        <option selected value="">Choose category</option>
                                                        <option value="Gifts">Gifts</option>
                                                        <option value="Debts">Debts</option>
                                                        <option value="Salary">Salary</option>
                                                        <option value="Part">Part</option>
                                                        <option value="Percents">Percents</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="input-group">
                                                <div class="form-group label-static">
                                                    <label class="control-label">Expenses category</label>
                                                    <select name="expenseCategory" class="form-control">
                                                        <option selected value="">Choose category</option>
                                                        <option value="Gifts">Gifts</option>
                                                        <option value="Debts">Debts</option>
                                                        <option value="Food">Food</option>
                                                        <option value="Car">Car</option>
                                                        <option value="Transport">Transport</option>
                                                        <option value="Food for pet">Food for pet</option>
                                                        <option value="Goods">Goods</option>
                                                        <option value="Services">Services</option>
                                                        <option value="Rest">Rest</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="footer text-center">
                                            <button class="btn btn-info btn-wd btn-lg" type="submit">
                                                Ok
                                                <div class="ripple-container"></div>
                                            </button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-md-4">
                        <c:choose>
                            <c:when test="${empty userGains}">
                                <a href="/gain" class="btn btn-raised btn-success btn-lg text-center">
                                    Add first gain
                                    <div class="ripple-container"></div>
                                </a>
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
                                                            <a href="/gain/${gain.id}"
                                                               type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                                                                <i class="fa fa-edit"></i>
                                                            </a>
                                                            <a href="${pageContext.request.contextPath}/deleteGain/${gain.id}.json"
                                                               type="button" rel="tooltip" title="Remove"
                                                               class="btn btn-danger btn-simple btn-xs removeItem">
                                                                <i class="fa fa-times"></i>
                                                            </a>
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
                                <a href="/expense" class="btn btn-raised btn-danger btn-lg text-center">
                                    Add first expense
                                    <div class="ripple-container"></div>
                                </a>
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
                                                            <a href="/expense/${expense.id}"
                                                               type="button" rel="tooltip" title="Edit" class="btn btn-success btn-simple btn-xs">
                                                                <i class="fa fa-edit"></i>
                                                            </a>
                                                            <a href="${pageContext.request.contextPath}/deleteExpense/${expense.id}.json"
                                                               type="button" rel="tooltip" title="Remove"
                                                               class="btn btn-danger btn-simple btn-xs removeItem">
                                                                <i class="fa fa-times"></i>
                                                            </a>
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
<script src="${btstrpJs}" type="text/javascript"></script>
<spring:url value="/resources/core/js/material.min.js" var="mtrlJs"/>
<script src="${mtrlJs}" type="text/javascript"></script>

<!-- Control Center for Material Kit: activating the ripples, parallax effects, scripts from the example pages etc -->
<spring:url value="/resources/core/js/material-kit.js" var="mtrlKitJs"/>
<script src="${mtrlKitJs}" type="text/javascript"></script>
<spring:url value="/resources/core/js/nouislider.min.js" var="sliderJs"/>
<script src="${sliderJs}" type="text/javascript"></script>
<spring:url value="/resources/core/js/bootstrap-datepicker.js" var="datepickerJs"/>
<script src="${datepickerJs}" type="text/javascript"></script>

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
