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
            .page-bg{
                width: 100%;
                height: 100%;
                background-image: url('https://images.unsplash.com/photo-1655841439659-0afc60676b70?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');
                background-size: cover;
                background-position: 30% 80%;
                background-repeat: no-repeat;
            }

            .blur-bg{
                background-color: rgba(0, 0, 0, 0.5);
                -webkit-backdrop-filter: blur(5px);
                backdrop-filter: blur(5px);
            }

            .login-box{
                width: 75%;
            }

            .login-form{
                border-radius: 16px;
            }

            a{
                color: #333333;
            }
        </style>
    
    </head>

    <body class="page-bg bg-image">
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
                                <a href="/ClinicaMedica" class="nav-link">
                                    <fmt:message key="header.home" />
                                </a>
                            </li>
                    
                            <li class="nav-item mx-2">
                                <a href="/ClinicaMedica/#about" class="nav-link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>

                            <li class="nav-item mx-2">
                                <a href="medico" class="nav-link">
                                    <fmt:message key="header.doctors" />
                                </a>
                            </li>
                    
                            <li class="nav-item mx-2">
                                <a href="" class="nav-link">
                                    <fmt:message key="header.login" />
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <div class="d-flex flex-column vh-100 align-items-center">
            <div class="login-box d-flex justify-content-center my-auto">
                <div class="col-md-4">
                    <div class="card login-form">
                        <div class="card-header text-center mb-2">
                            <h3 class="fs-4 p-1">
                                <fmt:message key="login.title" />
                            </h3>
                        </div>

                        <div class="card-body">
                            <div class="container">
                                <form action="/ClinicaMedica/login" method="post">
                                    <div class="mb-3">
                                        <label for="email" class="form-label">
                                            <fmt:message key="login.email" />
                                        </label>
                                        
                                        <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key='placeholder.email'/>">
        
                                        <c:if test="${mensagensEmail.existeErros}">
                                            <div class="row mt-2" id="erro">
                                                 <c:forEach var="erro" items="${mensagensEmail.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="mb-3">
                                        <label for="senha" class="form-label">
                                            <fmt:message key="login.password" />
                                        </label>

                                        <input type="password" class="form-control" id="senha" name="senha" placeholder="<fmt:message key='placeholder.password'/>">
        
                                        <c:if test="${mensagensSenha.existeErros}">
                                            <div class="row mt-2" id="erro">
                                                <c:forEach var="erro" items="${mensagensSenha.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="d-flex justify-content-end mb-2">
                                        <button type="submit" class="btn btn-success px-4">
                                            <fmt:message key="login.button" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                            <div class="p-2 mx-1">
                                <a href="#">
                                    <fmt:message key="login.acc" />
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</fmt:bundle>
</html>