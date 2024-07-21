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
    
        <div class="d-flex flex-column vh-100 justify-content-between">
            <header class="">
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

            <div class="col justify-content-center">
                <h1 class="text-center">PAINEL DE ADMINISTRAÇÃO - ${sessionScope.usuarioLogado.nome}</h1>
                
                <div class="container">
                    <div class="row justify-content-center mt-5">
                        <div class="col-md-6">
                            <div class="card">
                                <div class="card-header text-center">
                                    <h3>Cadastro de Médico</h3>
                                </div>
                                <div class="card-body">
                                    <form action="/ClinicaMedica/medico/insercao" method="post">
                                        <div class="mb-3">
                                            <label for="nome" class="form-label">Nome:</label>
                                            <input type="text" class="form-control" id="nome" name="nome" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="email" class="form-label">Email:</label>
                                            <input type="email" class="form-control" id="email" name="email" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="senha" class="form-label">Senha:</label>
                                            <input type="password" class="form-control" id="senha" name="senha" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="crm" class="form-label">CRM:</label>
                                            <input type="text" class="form-control" id="crm" name="crm" required>
                                        </div>
                                        <div class="mb-3">
                                            <label for="especialidade" class="form-label">Especialidade:</label>
                                            <input type="text" class="form-control" id="especialidade" name="especialidade" required>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <footer class="bg-dark text-white text-center p-3 mt-4 w-100">
                <div class="container">
                    <p class="mb-0">
                        <fmt:message key="footer.text" />
                    </p>
                </div>
            </footer>
        </div> 
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>