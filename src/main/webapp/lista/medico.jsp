
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

</head>

	<body>

        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-12">
                    <table class="table table-light table-hover">
                        <thead class="">
                            <tr>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>CRM</th>
                                <th>Especialidade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="medico" items="${requestScope.listaMedicos}">
                                <tr>
                                    <td><c:out value="${medico.nome}" /></td>
                                    <td><c:out value="${medico.email}" /></td>
                                    <td><c:out value="${medico.crm}" /></td>
                                    <td><c:out value="${medico.especialidade}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

	</body>
</fmt:bundle>

</html>
