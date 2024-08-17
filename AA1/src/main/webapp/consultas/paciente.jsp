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
                                <a href="/ClinicaMedica/#home" class="nav-link">
                                    <fmt:message key="header.home" />
                                </a>
                            </li>
                    
                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica/#about" class="nav-link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>

                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica/medico" class="nav-link">
                                    <fmt:message key="header.doctors" />
                                </a>
                            </li>

                            <c:if test="${sessionScope.usuarioLogado == null}">
                                <li class="nav-item mx-2">
                                    <a href="/ClinicaMedica/login" class="nav-link">
                                        <fmt:message key="header.login" />
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.tipoUsuario == 'paciente'}">
                                <li class="nav-item mx-2">
                                    <a href="/ClinicaMedica/consulta" class="nav-link">
                                        <fmt:message key="header.consulta" />
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.usuarioLogado != null}">
                                <li class="nav-item mx-2">
                                    <a href="/ClinicaMedica/signout" class="nav-link">
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
                    <h1 class="text-center">${sessionScope.usuarioLogado.nome} - <fmt:message key="appointment.title" /></h1>
                </div>
        
                <div class="col col-auto">
                    <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#cadastroConsultaModal">
                        <fmt:message key="scheduling.done" />
                    </button>
                </div>
            </div>

            <div class="col-md-12">
                <table class="table table-light table-hover">
                    <thead class="">
                        <tr>
                            <th>
                                <fmt:message key="patient.name" />
                            </th>
                            <th>
                                <fmt:message key="doctor.name" />
                            </th>
                            <th>
                                <fmt:message key="appointment.date" />
                            </th>
                            <th>
                                <fmt:message key="appointment.time" />
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:if test="${requestScope.listaConsultas==null}">
                            <td colspan="4">
                                <fmt:message key="appointment.none" />
                            </td>
                        </c:if>
                        <c:if test="${requestScope.listaConsultas!=null}">
                            <c:forEach var="consulta" items="${requestScope.listaConsultas}">
                                <tr>
                                    <td>
                                        <c:out value="${consulta.paciente.nome}" />
                                    </td>
                                    
                                    <td>
                                        <c:out value="${consulta.medico.nome}" />
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
        
        <div class="modal fade" id="cadastroConsultaModal" tabindex="-1" aria-labelledby="cadastroConsultaModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cadastroConsultaModal">
                            <c:out value="${appointment.scheduling}" />
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="/ClinicaMedica/consulta/insercao" method="post">
                            <input type="hidden" value="${requestScope.pacienteInf.cpf}" name="cpfPaciente" id="cpfPaciente">
                            <div class="mb-3">
                                <select class="form-select" id="crmMedico" name="crmMedico" required>
                                    <c:forEach var="medico" items="${requestScope.listaMedicos}">
                                        <option value="${medico.crm}">${medico.nome}</option>
                                    </c:forEach>
                                </select>
        
                            </div>
                            <div class="mb-3">
                                <label for="dataConsulta">
                                    <fmt:message key="appointment.date" />
                                </label>
                                <input name="dataConsulta" type="date" class="form-control" id="dataConsulta">
                            </div>
                            <div class="mb-3">
                                <label for="horaConsulta">
                                    <fmt:message key="appointment.time" />
                                </label>
                                <select name="horaConsulta" class="form-select" id="horaConsulta" required>
                                    <option value="08:00">08:00</option>
                                    <option value="08:30">08:30</option>
                                    <option value="09:00">09:00</option>
                                    <option value="09:30">09:30</option>
                                    <option value="10:00">10:00</option>
                                    <option value="10:30">10:30</option>
                                    <option value="11:00">11:00</option>
                                    <option value="11:30">11:30</option>
                                    <option value="12:00">12:00</option>
                                    <option value="12:30">12:30</option>
                                    <option value="13:00">13:00</option>
                                    <option value="13:30">13:30</option>
                                    <option value="14:00">14:00</option>
                                    <option value="14:30">14:30</option>
                                    <option value="15:00">15:00</option>
                                    <option value="15:30">15:30</option>
                                    <option value="16:00">16:00</option>
                                    <option value="16:30">16:30</option>
                                    <option value="17:00">17:00</option>
                                    <option value="17:30">17:30</option>
                                </select>
                            </div>
        
                            <button type="submit" class="btn btn-success">
                                <fmt:message key="scheduling.done" />
                            </button>
                        </form>
        
                        <c:if test="${not empty requestScope.mensagemErro}">
                            <div class="alert alert-danger" role="alert">
                                <c:out value="${requestScope.mensagemErro}" />
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script>
            document.addEventListener('DOMContentLoaded', function() {
                var mensagemErro = '${requestScope.mensagemErro}';
                if (mensagemErro) {
                    var cadastroConsultaModal = new bootstrap.Modal(document.getElementById('cadastroConsultaModal'));
                    cadastroConsultaModal.show();
                }
            });
        </script>

        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>
