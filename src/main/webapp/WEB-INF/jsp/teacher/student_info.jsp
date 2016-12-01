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
    <title>Teacher | Student info</title>

    <fmt:setLocale value="${sessionScope.local}" />
    <fmt:setBundle basename="localization.local" var="loc" />
    <fmt:message bundle="${loc}" key="local.language" var="language" />
    <fmt:message bundle="${loc}" key="local.logout" var="logout" />
    <fmt:message bundle="${loc}" key="local.hello" var="hello" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
    <fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
    <fmt:message bundle="${loc}" key="local.profile" var="profile" />
    <fmt:message bundle="${loc}" key="local.courses" var="courses" />
    <fmt:message bundle="${loc}" key="local.course_info" var="course_info" />
    <fmt:message bundle="${loc}" key="local.course.name" var="name" />
    <fmt:message bundle="${loc}" key="local.course.description" var="description" />
    <fmt:message bundle="${loc}" key="local.course.start" var="start" />
    <fmt:message bundle="${loc}" key="local.course.ends" var="end" />
    <fmt:message bundle="${loc}" key="local.course.status" var="status" />
    <fmt:message bundle="${loc}" key="local.teacher.teacher" var="teacher" />
    <fmt:message bundle="${loc}" key="local.student.student" var="student" />
    <fmt:message bundle="${loc}" key="local.student.course" var="course" />
    <fmt:message bundle="${loc}" key="local.student.exp_date" var="exp_date" />
    <fmt:message bundle="${loc}" key="local.fullname" var="fullname" />
    <fmt:message bundle="${loc}" key="local.university" var="university" />
    <fmt:message bundle="${loc}" key="local.faculty" var="faculty" />
    <fmt:message bundle="${loc}" key="local.department" var="department" />
    <fmt:message bundle="${loc}" key="local.position" var="position" />
    <fmt:message bundle="${loc}" key="local.experience" var="experience" />
    <fmt:message bundle="${loc}" key="local.birth_date" var="birth_date" />
    <fmt:message bundle="${loc}" key="local.change" var="change" />
    <fmt:message bundle="${loc}" key="local.phone" var="phone" />
    <fmt:message bundle="${loc}" key="local.email" var="email" />
    <fmt:message bundle="${loc}" key="local.course.under_recruitment" var="unrec" />
    <fmt:message bundle="${loc}" key="local.course.started" var="started" />
    <fmt:message bundle="${loc}" key="local.course.completed" var="completed" />
    <fmt:message bundle="${loc}" key="local.course.applied" var="applied" />
    <fmt:message bundle="${loc}" key="local.course.apply" var="apply" />
    <fmt:message bundle="${loc}" key="local.teacher.more" var="more" />
    <fmt:message bundle="${loc}" key="local.admin.add" var="add" />
    <fmt:message bundle="${loc}" key="local.teacher.mark" var="mark" />
    <fmt:message bundle="${loc}" key="local.teacher.review" var="review" />

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
<div class="container" style="margin-top: 50px;">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <jsp:useBean id="user" scope="session" class="com.pechonick.registration.entity.common.User" />
                <span class="navbar-text">${hello} <a href="#" class="navbar-link">${user.login}</a></span>
            </div>
            <form action="/Controller" method="post" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="get-user-info"/>
                <input type="hidden" name="userId" value="${user.id}"/>
                <button type="submit" class="btn btn-link">${profile}</button>
            </form>
            <form action="/Controller" method="post" class="navbar-form navbar-left">
                <input type="hidden" name="command" value="get-teacher-index-info"/>
                <button type="submit" class="btn btn-link">${courses}</button>
            </form>
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
                <li>
                    <form action="Controller" method="post" class="navbar-form navbar-right">
                        <input type="hidden" name="command" value="logout"/>
                        <button type="submit" class="btn btn-link">
                            <span class="glyphicon glyphicon-log-out"></span> ${logout}
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">${student}</div>
                <table class="table">
                    <tr>
                        <td>${fullname}:</td>
                        <td>${sessionScope.student.secondname}
                            ${sessionScope.student.firstname}
                            ${sessionScope.userInfo.thirdname}
                        </td>
                    </tr>
                    <tr>
                        <td>${university}:</td>
                        <td>${sessionScope.student.university}</td>
                    </tr>
                    <tr>
                        <td>${faculty}:</td>
                        <td>${sessionScope.student.faculty}</td>
                    </tr>
                    <tr>
                        <td>${department}:</td>
                        <td>${sessionScope.student.department}</td>
                    </tr>
                    <tr>
                        <td>${course}:</td>
                        <td>${sessionScope.student.course}</td>
                    </tr>
                    <tr>
                        <td>${exp_date}:</td>
                        <td>${sessionScope.student.expDate}</td>
                    </tr>
                    <tr>
                        <td>${birth_date}:</td>
                        <td>${sessionScope.student.birthDate}</td>
                    </tr>
                    <tr>
                        <td>${phone}:</td>
                        <td>${sessionScope.student.phone}</td>
                    </tr>
                    <tr>
                        <td>Email:</td>
                        <td>${sessionScope.student.email}</td>
                    </tr>
                </table>
            </div>
        </div>
        <c:if test="${sessionScope.review != null}">
            <div class="col-md-6">
                <div class="panel panel-default">
                    <div class="panel-heading">${student}</div>
                    <table class="table">
                        <tr>
                            <td>${mark}:</td>
                            <td>${sessionScope.review.mark}</td>
                        </tr>
                        <tr>
                            <td>${review}:</td>
                            <td>${sessionScope.review.text}</td>
                        </tr>
                    </table>
                </div>
            </div>
        </c:if>
    </div>

</div>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>
