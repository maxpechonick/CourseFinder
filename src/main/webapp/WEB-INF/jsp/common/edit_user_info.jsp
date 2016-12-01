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
    <title>User | Edit info</title>

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
    <fmt:message bundle="${loc}" key="local.firstname" var="firstname" />
    <fmt:message bundle="${loc}" key="local.secondname" var="secondname" />
    <fmt:message bundle="${loc}" key="local.thirdname" var="thirdname" />
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
                <c:if test="${user.role == 'STUDENT'}">
                    <input type="hidden" name="command" value="get-student-index-info"/>
                </c:if>
                <c:if test="${user.role == 'TEACHER'}">
                    <input type="hidden" name="command" value="get-teacher-index-info"/>
                </c:if>
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
        <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-default">
                <c:if test="${user.role == 'STUDENT'}">
                    <div class="panel-heading">${student}</div>
                </c:if>
                <c:if test="${user.role == 'TEACHER'}">
                    <div class="panel-heading">${teacher}</div>
                </c:if>
                <div class="panel-body">
                    <form action="/Controller" method="post">
                        <c:if test="${userInfo.role == 'STUDENT'}">
                            <input type="hidden" name="command" value="edit-student-info"/>
                        </c:if>
                        <c:if test="${userInfo.role == 'TEACHER'}">
                            <input type="hidden" name="command" value="edit-teacher-info"/>
                        </c:if>
                        <input type="hidden" name="userId" value="${userInfo.id}"/>
                        <div class="form-group">
                            <label for="sn">${secondname}:</label>
                            <input type="text" class="form-control" name="secondname" value="${userInfo.secondname}" id="sn">
                        </div>
                        <div class="form-group">
                            <label for="fn">${firstname}:</label>
                            <input type="text" class="form-control" name="firstname" value="${userInfo.firstname}" id="fn">
                        </div>
                        <div class="form-group">
                            <label for="thn">${thirdname}:</label>
                            <input type="text" class="form-control" name="thirdname" value="${userInfo.thirdname}" id="thn">
                        </div>
                        <div class="form-group">
                            <label for="un">${university}:</label>
                            <input type="text" class="form-control" name="university" value="${userInfo.university}" id="un">
                        </div>
                        <div class="form-group">
                            <label for="fc">${faculty}:</label>
                            <input type="text" class="form-control" name="faculty" value="${userInfo.faculty}" id="fc">
                        </div>
                        <div class="form-group">
                            <label for="dp">${department}:</label>
                            <input type="text" class="form-control" name="department" value="${userInfo.department}" id="dp">
                        </div>
                        <c:choose>
                            <c:when test="${user.role == 'STUDENT'}">
                                <div class="form-group">
                                    <label for="crs">${course}:</label>
                                    <input type="text" class="form-control" name="course" value="${userInfo.course}" id="crs">
                                </div>
                                <div class="form-group">
                                    <label for="expd">${exp_date}:</label>
                                    <input type="text" class="form-control" name="expDate" value="${userInfo.expDate}" id="expd">
                                </div>
                            </c:when>
                            <c:when test="${user.role == 'TEACHER'}">
                                <div class="form-group">
                                    <label for="ps">${position}:</label>
                                    <input type="text" class="form-control" name="position" value="${userInfo.position}" id="ps">
                                </div>
                                <div class="form-group">
                                    <label for="exp">${experience}:</label>
                                    <input type="text" class="form-control" name="experience" value="${userInfo.experience}" id="exp">
                                </div>
                            </c:when>
                        </c:choose>
                        <div class="form-group">
                            <label for="bd">${birth_date}:</label>
                            <input type="date" class="form-control" name="birthDate" value="${userInfo.birthDate}" id="bd">
                        </div>
                        <div class="form-group">
                            <label for="ph">${phone}:</label>
                            <input type="text" class="form-control" name="phone" value="${userInfo.phone}" id="ph">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" value="${userInfo.email}" id="email">
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-success" class="form-control" value="${change}">
                        </div>
                    </form>
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
