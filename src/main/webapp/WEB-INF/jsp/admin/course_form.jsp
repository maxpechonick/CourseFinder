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
    <title>Admin | Course form</title>

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
    <fmt:message bundle="${loc}" key="local.teacher.student_list" var="student_list" />
    <fmt:message bundle="${loc}" key="local.admin.add" var="add" />

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script>
        function addId(node){
            document.forms.courseForm.elements.teacherId.value = node.parentNode.children[0].value;
            document.forms.courseForm.elements.selectedTeacher.value = node.children[0].innerText;
        }
    </script>
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
                <input type="hidden" name="command" value="get-admin-index-info"/>
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
                <div class="panel-heading">${course_info}</div>
                <div class="panel-body">
                    <form name="courseForm" action="/Controller" method="post">
                        <input type="hidden" name="teacherId" value="${selectedTeacher.id}">
                        <input type="hidden" name="courseId" value="${sessionScope.course.id}">
                        <input type="hidden" name="command" value="${sessionScope.commandName}">
                        <div class="form-group">
                            <label for="cn">${name}:</label>
                            <input type="text" class="form-control" name="name" value="${sessionScope.course.name}" id="cn">
                        </div>
                        <div class="form-group">
                            <label for="dsc">${description}:</label>
                            <textarea class="form-control" name="description" id="dsc" style="height: 100px">${sessionScope.course.description}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="tc">${teacher}:</label>
                            <c:choose>
                                <c:when test="${selectedTeacher != null}">
                                    <input name="selectedTeacher" type="text" class="form-control" value="${selectedTeacher.secondname} ${selectedTeacher.firstname} ${selectedTeacher.thirdname}" id="tc" disabled>
                                </c:when>
                                <c:otherwise>
                                    <input name="selectedTeacher" type="text" class="form-control" id="tc" disabled>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="form-group">
                            <label for="st">${start}:</label>
                            <input type="date" class="form-control" name="startDate" value="${sessionScope.course.startDate}" id="st">
                        </div>
                        <div class="form-group">
                            <label for="end">${end}:</label>
                            <input type="date" class="form-control" name="endDate" value="${sessionScope.course.endDate}" id="end">
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-success">${add}</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <div class="panel panel-default">
                <div class="panel-heading">${teacher}</div>
                <div class="panel-body">
                    <div class="list-group">
                        <c:forEach items="${sessionScope.teachersList}" var="teacher">
                            <div class="list-group">
                                <form action="/Controller" method="post">
                                    <input type="hidden" name="teacherId" value="${teacher.id}"/>
                                    <input type="hidden" name="command" value="appoint-teacher-for-the-course"/>
                                    <a href="#" onclick="addId(this);" class="list-group-item">
                                        <h4 class="list-group-item-heading">
                                                ${teacher.secondname}
                                                ${teacher.firstname}
                                                ${teacher.thirdname}
                                        </h4>
                                        <p class="list-group-item-text">${university}: ${teacher.university}</p>
                                        <p class="list-group-item-text">${faculty}: ${teacher.faculty}</p>
                                    </a>
                                </form>
                            </div>
                        </c:forEach>
                    </div>
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
