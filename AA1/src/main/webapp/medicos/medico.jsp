<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">

    <head>
        <title>
            <fmt:message key="page.title" />
        </title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100..900&display=swap"
            rel="stylesheet">

        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous">

        <style>
            .welcome-section{
                width: 100%;
                height: 600px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: url('https://images.unsplash.com/photo-1624727828489-a1e03b79bba8?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                background-size: cover;
                background-position: 30% 30%;
                background-repeat: no-repeat;
            }

            .welcome-title{
                font-family: 'Inter';
                font-weight: bold;
            }

            .blur-bg{
                background-color: rgba(0, 0, 0, 0.5);
                -webkit-backdrop-filter: blur(5px);
                backdrop-filter: blur(5px);
            }

            tr {
                line-height: 25px;
                min-height: 25px;
                height: 25px;
            }

            .title{
                font-family: 'Inter';
            }

            .opcoes{
                display: flex;
                align-items: center;
                justify-content: center;
                margin-bottom: 24px;
            }

            @media screen and (max-width: 768px) {

                .welcome-section{
                width: 100%;
                height: 400px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: url('https://images.unsplash.com/photo-1624727828489-a1e03b79bba8?q=80&w=2071&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                background-size: cover;
                background-position: 30% 80%;
                background-repeat: no-repeat;
                }
            }
        </style>

    </head>

    <body>
        <header class="fixed-top">
            <nav class="navbar navbar-expand-sm navbar-dark blur-bg">
                <div class="container">
                    <a class="navbar-brand" href="/ClinicaMedica/">
                        <fmt:message key="brand.name" />
                    </a>

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                  
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica/" class="nav-link">
                                    <fmt:message key="header.home" />
                                </a>
                            </li>
                    
                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica/#about" class="nav-link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>

                            <li class="nav-item mx-2">
                                <a href="" class="nav-link">
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

                            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.tipoUsuario == 'admin'}">
                                <li class="nav-item mx-2">
                                    <a href="/ClinicaMedica/admin" class="nav-link">
                                        <fmt:message key="header.admin" />
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.tipoUsuario == 'medico'}">
                                <li class="nav-item mx-2">
                                    <a href="/ClinicaMedica/consulta" class="nav-link">
                                        <fmt:message key="header.consulta" />
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

        <section id="home" class="welcome-section jumbotron text-center text-white bg-image">
            <div class="container">
                <h2 class="welcome-title display-4 p-1">
                    <fmt:message key="doctors.title" />
                </h2>
                <p class="lead fs-4 pb-3">
                    <fmt:message key="doctors.text" />
                </p>
            </div>
        </section>

        <section id="about" class="py-5 mt-4 mb-4">
            <div class="w-75 mx-auto mb-1 d-flex">
                <h2 class="title py-2 fs-4">
                    <fmt:message key="doctors.subtitle" />
                </h2>

                <div class="opcoes h-100 mx-4 mt-1">
                    <select name="selectMedico" id="selectMedico" class="p-2" onchange="filterTable()">
                        <option value="vazio" selected style="background-color: gray;">
                            <fmt:message key="doctors.specialty" />
                        </option>
    
                        <option value="Cardiologia"><fmt:message key="esp.cardiologia" /></option>
                        <option value="Dermatologia"><fmt:message key="esp.dermatologia" /></option>
                        <option value="Ginecologia"><fmt:message key="esp.ginecologia" /></option>
                        <option value="Neurologia"><fmt:message key="esp.neurologia" /></option>
                        <option value="Ortopedia"><fmt:message key="esp.ortopedia" /></option>
                        <option value="Oftalmologia"><fmt:message key="esp.oftalmologia" /></option>
                        <option value="Pediatria"><fmt:message key="esp.pediatria" /></option>
                        <option value="Psiquiatria"><fmt:message key="esp.psiquiatria" /></option>
                    </select>
                </div>
            </div>
        
            <div class="d-flex flex-column align-items-center justify-content-center">
                <div class="row w-75 p-1 border justify-content-center">
                    <div class="col-md-12 mt-3">
                        <table id="medicosTable" class="table table-light table-hover table-striped">
                            <thead class="">
                                <tr>
                                    <th>Nome</th>
                                    <th>Email</th>
                                    <th>Especialidade</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="medico" items="${requestScope.listaMedicos}">
                                    <tr data-specialty="${medico.especialidade}">
                                        <td>
                                            <c:out value="${medico.nome}" />
                                        </td>
                                        <td>
                                            <c:out value="${medico.email}" />
                                        </td>
                                        <td>
                                            <c:out value="${medico.especialidade}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <footer class="bg-dark text-white text-center p-3">
            <div class="container">
                <p class="mb-0">
                    <fmt:message key="footer.text" />
                </p>
            </div>
        </footer>

        <script>
            document.getElementById('selectMedico').addEventListener('change', function() {
                var selectedValue = this.value;
                var tableRows = document.querySelectorAll('#medicosTable tbody tr');
                
                tableRows.forEach(function(row) {
                    var specialty = row.getAttribute('data-specialty');
                    
                    if (selectedValue === 'vazio' || selectedValue === specialty) {
                        row.style.display = ''; // Mostra o médico
                    } else {
                        row.style.display = 'none'; // Esconde o médico
                    }
                });
            });
        </script>

        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</fmt:bundle>

</html>
