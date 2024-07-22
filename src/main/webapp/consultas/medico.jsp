<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="pt-br">
<fmt:bundle basename="messages">

    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>
            <fmt:message key="page.title" />
        </title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap" rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    
        <style>
            .blur-bg{
                background-color: rgba(233, 233, 233, 0.4);
                -webkit-backdrop-filter: blur(5px);
                backdrop-filter: blur(5px);
            }

            footer{
                position: fixed;
                bottom: 0;
                width: 100%;
            }

            .manager-container{
                margin-top: 140px;
                margin-bottom: 50px;
            }

            tr {
                text-align: start; 
                vertical-align: middle;
            }
        </style>
    </head>

    <body>
        <header class="fixed-top">
            <nav class="navbar navbar-expand-sm navbar-light blur-bg">
                <div class="container">
                    <a class="navbar-brand" href="/ClinicaMedica">
                        <fmt:message key="brand.name" />
                    </a>

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                  
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica" class="nav-link">
                                    <fmt:message key="header.home" />
                                </a>
                            </li>
                            <li class="nav-item mx-2">
                                <a href="consulta" class="nav-link">
                                    <fmt:message key="header.schedule" />
                                </a>
                            </li>
                            <c:if test="${sessionScope.usuarioLogado == null}">
                            <li class="nav-item mx-2">
                                <a href="login/" class="nav-link">
                                    <fmt:message key="header.login" />
                                </a>
                            </li>
                            </c:if>
                            <c:if test="${sessionScope.usuarioLogado != null}">
                                <li class="nav-item mx-2">
                                    <a href="signout" class="nav-link">
                                        <fmt:message key="header.signout" />
                                    </a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <div class="manager-container container mb-5">
            <div class="row justify-content-between mb-2">
                <div class="col col-auto">
                    <h2 class="text-center">Minhas consultas - ${sessionScope.usuarioLogado.nome}</h2>
                </div>
            </div>

        <div class="container">
            <div class="row justify-content-center">

                <div class="col-md-12">
                    <table class="table table-light table-hover">
                        <thead class="">
                            <tr>
                                <th>Nome do medico</th>
                                <th>Nome do paciente</th>
                                <th>Data da consulta</th>
                                <th>Horario da consulta</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${requestScope.listaConsultas==null}">
                                <td colspan="4">Nenhuma consulta no momento.</td>
                            </c:if>
                            <c:if test="${requestScope.listaConsultas!=null}">
                                <c:forEach var="consulta" items="${requestScope.listaConsultas}">
                                    <tr>
                                        <td>
                                            <c:out value="${consulta.medico.nome}" />
                                        </td>   
                                        <td>
                                            <c:out value="${consulta.paciente.nome}" />
                                        </td>   
                                        <td>
                                            <c:out value="${consulta.data}" />
                                        </td>
                                        <td>
                                            <c:out value="${consulta.hora}" />
                                        </td>        
                                    </tr>
                                </c:forEach>
                            </c:if>
                            
                        </tbody>
                    </table>
                </div>
            </div>
        </div>


        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>
