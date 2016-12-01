<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Login page</title>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.page" var="page" />
    <fmt:message bundle="${loc}" key="local.language" var="language" />
    <fmt:message bundle="${loc}" key="local.username" var="username" />
    <fmt:message bundle="${loc}" key="local.password" var="password" />
    <fmt:message bundle="${loc}" key="local.login" var="login"/>
    <fmt:message bundle="${loc}" key="local.registration" var="registration" />
    <fmt:message bundle="${loc}" key="local.login.message" var="loginmessage" />
    <fmt:message bundle="${loc}" key="local.logout.message" var="logout" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.enter" var="enter" />

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
    <div class="container" style="margin-top:50px">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">${language}<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <form class="form-inline" action="Controller" method="post">
                                        <input type="hidden" name="local" value="ru" />
                                        <input type="hidden" name="command" value="change-locale" />
                                        <div class="btn-group btn-group-justified" role="group">
                                            <div class="btn-group" role="group">
                                                <input type="submit" class="btn btn-default" value="${ru_button}" />
                                            </div>
                                        </div>
                                    </form>
                                </li>
                                <li>
                                    <form class="form-inline" action="Controller" method="post">
                                        <input type="hidden" name="local" value="en" />
                                        <input type="hidden" name="command" value="change-locale" />
                                        <div class="btn-group btn-group-justified" role="group">
                                            <div class="btn-group" role="group">
                                                <input type="submit" class="btn btn-default" value="${en_button}" />
                                            </div>
                                        </div>
                                    </form>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-heading">${enter}</div>
                    <div class="panel-body">
                        <c:if test="${not empty error}">
                            <div class=" alert alert-danger fade in">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    ${loginmessage}
                            </div>
                        </c:if>
                        <c:if test="${not empty success}">
                            <div class=" alert alert-success fade in">
                                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                                    ${logout}
                            </div>
                        </c:if>
                        <form action="Controller" method="POST" role="form">
                            <div class="form-group">
                                <input type="hidden" name="command" value="login">
                            </div>
                            <div class="form-group">
                                <label for="username">${username}:</label>
                                <input class="form-control" name="login" id="username" required>
                            </div>
                            <div class="form-group">
                                <label for="pwd">${password}:</label>
                                <input type="password" class="form-control" name="password" id="pwd" required>
                            </div>
                            <span class="input-group-btn">
                                <span>
                                    <button type='submit' id='register-button' class='btn btn-success btn-large pull-right' >${login}</button>
                                </span>
                            </span>
                        </form>
                        <span>
                            <form action="Controller" role="form" method="post">
                                <input type="hidden" name="command" value="get-registration-page"/>
                                <button type="submit" class="btn btn-link pull-left">${registration}</button>
                            </form>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>