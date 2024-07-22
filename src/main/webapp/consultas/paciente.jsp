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
            #about, #services {
                padding: 60px 0;
            }

            .welcome-section {
                width: 100%;
                height: 640px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: url('https://images.unsplash.com/photo-1633084002199-bb289c1d2624?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                background-size: cover;
                background-position: center;
                background-repeat: no-repeat;
            }

            .welcome-title {
                font-family: 'Inter';
                font-weight: bold;
                color: white;
            }

            .blur-bg {
                background-color: rgba(0, 0, 0, 0.5);
                -webkit-backdrop-filter: blur(5px);
                backdrop-filter: blur(5px);
            }

            .card-custom {
                width: 20%;
                height: 180px;
                background-color: black;
            }

            @media screen and (max-width: 768px) {
                .welcome-section {
                    height: 400px;
                }
            }
        </style>
    </head>

    <body>
        <header>
            <nav class="navbar navbar-expand-sm navbar-dark blur-bg">
                <div class="container">
                    <a class="navbar-brand" href="/ClinicaMedica">
                        <fmt:message key="brand.name" />
                    </a>

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                  
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto">
                            <li class="nav-item mx-2">
                                <a href="#home" class="nav-link">
                                    <fmt:message key="header.home" />
                                </a>
                            </li>
                            <li class="nav-item mx-2">
                                <a href="#about" class="nav-link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>
                            <li class="nav-item mx-2">
                                <a href="#services" class="nav-link">
                                    <fmt:message key="header.services" />
                                </a>
                            </li>
                            <li class="nav-item mx-2">
                                <a href="#doctors" class="nav-link">
                                    <fmt:message key="header.doctors" />
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

        <h1 class="text-center">Minhas consultas - ${sessionScope.usuarioLogado.nome}</h1>

        <div class="container">
            <div class="row justify-content-center">
                
                <div>
                    <!-- Botão para abrir a modal -->
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#cadastroConsultaModal">
                        Agendar Consulta
                    </button>
                </div>

                <div class="col-md-12">
                    <table class="table table-light table-hover">
                        <thead class="">
                            <tr>
                                <th>Nome do paciente</th>
                                <th>Nome do medico</th>
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
        </div>

        <div class="modal fade" id="cadastroConsultaModal" tabindex="-1" aria-labelledby="cadastroConsultaModal" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cadastroConsultaModal">Agendamento de Consulta</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>>
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
                                <label for="dataConsulta">Data da Consulta</label>
                                <input name="dataConsulta"  type="date" class="form-control" id="dataConsulta">
                            </div>
                            <div class="mb-3">
                                <label for="horaConsulta">Hora da Consulta</label>
                                <label for="horaConsulta">Hora da Consulta</label>
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

                            <button type="submit" class="btn btn-primary">Agendar</button>
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
