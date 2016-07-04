<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Accounting</title>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="btstrp_m" />
    <link href="${btstrp_m}" rel="stylesheet" />
    <spring:url value="/resources/core/css/material-kit.css" var="mtrlKit" />
    <link href="${mtrlKit}" rel="stylesheet" />
    <%--<spring:url value="/resources/core/css/bootstrap.css" var="btstrp" />
    <link href="${btstrp}" rel="stylesheet" />--%>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
</head>
<body>
<div class="section section-full-screen section-signup">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-success">
                    <!-- Default panel contents -->
                    <div class="panel-heading">Gains</div>
                    <!-- Table -->
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Date</th>
                                <th>Value</th>
                                <th>Category</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>21</td>
                                <td>Eugene</td>
                                <td>root</td>
                            </tr>
                            <tr>
                                <td>45</td>
                                <td>Test</td>
                                <td>test</td>
                            </tr>
                        </tbody>
                    </table>
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
</html>
