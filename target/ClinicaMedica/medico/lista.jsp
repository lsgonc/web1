<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>
		<div>
			<h1>
                Lista de médicos
			</h1>
		</div>
		<div>
			<table>
				<tr>
					<th>Nome</th>
					<th>Email</th>
					<th>Especialidade</th>
					<th>Crm</th>
				</tr>
				<c:forEach var="medicos" items="${requestScope.listaMedicos}">
					<tr>
						<td><c:out value="${medicos.nome}" /></td>
						<td><c:out value="${medicos.email}" /></td>
						<td><c:out value="${medicos.especialidade}" /></td>
						<td><c:out value="${medicos.crm}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</body>
</fmt:bundle>

</html>