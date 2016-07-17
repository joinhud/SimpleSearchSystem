<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
    <spring:url value="/resources/core/css/bootstrap.min.css" var="btstrp" />
    <link href="${btstrp}" rel="stylesheet" />
    <spring:url value="/resources/core/css/material-kit.css" var="mtrlKit" />
    <link href="${mtrlKit}" rel="stylesheet" />

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <!--     Fonts and icons     -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" />
</head>
<body>
<div class="section section-full-screen section-signup" style="background-image: url('/resources/core/img/city.jpg'); background-size: cover; background-position: top center; min-height: 700px;">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="card card-signup">
                    <form:form action="/signup" method="post" commandName="userForm" cssClass="form">
                        <div class="header header-primary text-center">
                            <h4>Sign Up</h4>
                        </div>
                        <div class="content">

                            <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">face</i>
										</span>
                                <div class="form-group label-floating">
                                    <label class="control-label">Name</label>
                                    <form:input path="name" cssClass="form-control" />
                                </div>
                            </div>

                            <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                <div class="form-group label-floating">
                                    <label class="control-label">Password</label>
                                    <form:password path="password" cssClass="form-control" />
                                </div>
                            </div>

                        </div>
                        <div class="footer text-center">
                            <button class="btn btn-primary btn-wd btn-lg" type="submit">
                                Sign Up
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
</html>
