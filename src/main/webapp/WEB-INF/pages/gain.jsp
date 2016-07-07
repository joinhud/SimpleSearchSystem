<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Gain</title>
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
<body>
<nav class="navbar navbar-primary navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navigation-index	">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="">${userName}</a>
        </div>

        <div class="collapse navbar-collapse" id="navigation-index">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="/main" target="_self" class="btn">
                        <i class="material-icons">poll</i> Main
                    </a>
                </li>
                <li>
                    <a href="/logout" target="_self" class="btn">
                        <i class="material-icons">exit_to_app</i> Log Out
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="section section-full-screen section-signup" style="background-image: url('/resources/core/img/city.jpg'); background-size: cover; background-position: top center; min-height: 700px;">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="card">
                    <form:form action="${loginUrl}" method="post" commandName="gainForm" cssClass="form">
                        <div class="header header-success text-center">
                            <h4>Gain</h4>
                        </div>
                        <div class="content">

                            <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">date_range</i>
										</span>
                                <div class="form-group label-static">
                                    <label class="control-label">Date</label>
                                    <form:input type="text" path="date" cssClass="datepicker form-control" value="${currDate}"/>
                                </div>
                            </div>

                            <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">attach_money</i>
										</span>
                                <div class="form-group label-floating">
                                    <label class="control-label">Value</label>
                                    <form:input type="number" path="value" cssClass="form-control"/>
                                </div>
                            </div>

                            <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">bookmark</i>
										</span>
                                <form:select path="category" cssClass="form-control">
                                    <form:option value="">Choose category</form:option>
                                    <form:option value="Gifts"/>
                                    <form:option value="Debts"/>
                                    <form:option value="Salary"/>
                                    <form:option value="Part"/>
                                    <form:option value="Percents"/>
                                </form:select>
                            </div>

                        </div>
                        <div class="footer text-center">
                            <button class="btn btn-success btn-wd btn-lg" type="submit">
                                Ok
                                <div class="ripple-container"></div>
                            </button>
                        </div>
                    </form:form>
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
<%-- Datepicker --%>
<spring:url value="/resources/core/js/bootstrap-datepicker.js" var="datepickerJs"/>
<script src="${datepickerJs}" type="text/javascript"></script>
<script>
    $('.datepicker').datepicker({
        weekStart:1
    });
</script>
</html>
