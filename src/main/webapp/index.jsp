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

            .welcome-section{
                width: 100%;
                height: 640px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: url('https://images.unsplash.com/photo-1633084002199-bb289c1d2624?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                background-size: cover;
                background-position: 30% 80%;
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

            .cartao{
                width: 20%;
                height: 180px;
                background-color: black;
            }

            @media screen and (max-width: 768px) {

                .welcome-section{
                width: 100%;
                height: 400px;
                display: flex;
                align-items: center;
                justify-content: center;
                background-image: url('https://images.unsplash.com/photo-1633084002199-bb289c1d2624?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
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
                    <a class="navbar-brand" href="/ClinicaMedica">
                        <fmt:message key="brand.name" />
                    </a>

                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                  
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav">
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

        <section id="home" class="welcome-section jumbotron text-center text-white bg-image">
            <div class="container">
                <h2 class="welcome-title display-4 p-1">
                    <fmt:message key="banner.title" />
                </h2>
                <p class="lead fs-4 pb-3">
                    <fmt:message key="banner.text" />
                </p>
                <a href="#services" class="btn btn-outline-light fw-medium btn-lg px-4 py-2">
                    <fmt:message key="banner.button" />
                </a>
            </div>
        </section>

        <section id="about" class="py-5">
            <div class="container d-flex flex-column align-items-center justify-content-center">
                <div class="w-75 text-center mb-4">
                    <h2 class="py-2">
                        <fmt:message key="about.title" />
                    </h2>
                
                    <p>
                        <fmt:message key="about.text" />
                    </p>
                </div>

                <div class="row">
                    <div class="col-md-3">
                        <div class="card mb-3 border-0">
                            <div class="card-body text-center">
                                <h3 class="card-title">Consulta Médica</h3>
                                <p class="card-text">Atendimento com médicos especialistas para diversas áreas
                                    da saúde.</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3">
                        <div class="card mb-3 border-0">
                            <div class="card-body text-center">
                                <h3 class="card-title">Consulta Médica</h3>
                                <p class="card-text">Atendimento com médicos especialistas para diversas áreas
                                    da saúde.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3 border-0">
                            <div class="card-body text-center">
                                <h3 class="card-title">Consulta Médica</h3>
                                <p class="card-text">Atendimento com médicos especialistas para diversas áreas
                                    da saúde.</p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3 border-0">
                            <div class="card-body text-center">
                                <h3 class="card-title">Consulta Médica</h3>
                                <p class="card-text">Atendimento com médicos especialistas para diversas áreas
                                    da saúde.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="services" class="py-5 bg-light">
            <div class="container">
                <h2 class="py-2">Nossos Serviços</h2>
                <div class="row">
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="card-title">Consulta Médica</h3>
                                <p class="card-text">Atendimento com médicos especialistas para diversas áreas
                                    da saúde.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="card-title">Exames Laboratoriais</h3>
                                <p class="card-text">Realizamos uma variedade de exames laboratoriais com
                                    precisão e rapidez.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h3 class="card-title">Emergências</h3>
                                <p class="card-text">Atendimento de emergências médicas 24 horas por dia, 7 dias
                                    por semana.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section id="doctors" class="py-5 bg-light">
            <div class="container">
                <h2>Nossos Médicos</h2>
                <div class="container">
                    <div class="row justify-content-center">
                        <div class="col-md-12">
                            <table class="table table-light table-hover">
                                <thead>
                                    <tr>
                                        <th>Nome</th>
                                        <th>Email</th>
                                        <th>CRM</th>
                                        <th>Especialidade</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${empty listaMedicos}">
                                        <tr>
                                            <td colspan="4">Nenhum médico cadastrado.</td>
                                        </tr>
                                    </c:if>
                                    <c:forEach var="medico" items="${listaMedicos}">
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
            </div>
        </section>

        <footer class="bg-dark text-white text-center p-3 mt-4">
            <div class="container">
                <p class="mb-0">
                    <fmt:message key="footer.text" />
                </p>
            </div>
        </footer>
        
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>