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
                                <a href="login" class="nav-link">
                                    <fmt:message key="header.login" />
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <div class="d-flex flex-column vh-100 align-items-center mt-5">
            <div class="login-box d-flex justify-content-center my-auto">
                <div class="col-md-4">
                    <div class="card login-form">
                        <div class="card-header text-center mb-2">
                            <h3 class="fs-4 p-1">
                                <fmt:message key="signin.title" />
                            </h3>
                        </div>

                        <div class="card-body">
                            <div class="container">
                                <form action="/ClinicaMedica/signin/insercao" method="post">
                                    <div class="mb-3">
                                        <label for="nome" class="form-label">
                                            <fmt:message key="signin.name" />
                                        </label>
                                        
                                        <input type="nome" class="form-control" id="nome" name="nome" placeholder="<fmt:message key='placeholder.name'/>" required>  
                                        
                                        <c:if test="${mensagensNome.existeErros}">
                                            <div class="mt-2" id="erro">
                                                 <c:forEach var="erro" items="${mensagensNome.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="mb-3">
                                        <label for="email" class="form-label">
                                            <fmt:message key="signin.email" />
                                        </label>
                                        
                                        <input type="email" class="form-control" id="email" name="email" placeholder="<fmt:message key='placeholder.email'/>" required>
        
                                        <c:if test="${mensagensEmail.existeErros}">
                                            <div class="mt-2" id="erro">
                                                 <c:forEach var="erro" items="${mensagensEmail.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="mb-3">
                                        <label for="senha" class="form-label">
                                            <fmt:message key="signin.password" />
                                        </label>

                                        <input type="password" class="form-control" id="senha" name="senha" placeholder="<fmt:message key='placeholder.password'/>" required>
        
                                        <c:if test="${mensagensSenha.existeErros}">
                                            <div class="mt-2" id="erro">
                                                <c:forEach var="erro" items="${mensagensSenha.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="mb-3">
                                        <label for="tipoUsuario" class="form-label">
                                            <fmt:message key="signin.userType" />
                                        </label>

                                        <select name="tipoUsuario" id="tipoUsuario" class="p-2" onchange="toggleField()">
                                            <option value="paciente" selected>
                                                <fmt:message key="signin.select.p" />
                                            </option>

                                            <option value="medico">
                                                <fmt:message key="signin.select.m" />
                                            </option>
                                        </select>
                                    </div>

                                    <div class="mb-3" id="cpfField">
                                        <label for="cpf" class="form-label">
                                            <fmt:message key="signin.cpf" />
                                        </label>
                                    
                                        <input type="text" class="form-control" id="cpf" name="cpf" placeholder="<fmt:message key='placeholder.cpf'/>" pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" required>
                                    
                                        <c:if test="${mensagensCPF.existeErros}">
                                            <div class="mt-2" id="erro">
                                                <c:forEach var="erro" items="${mensagensCPF.erros}">
                                                    <p class="text-danger"> ${erro} </li>
                                                </c:forEach>
                                            </div>
                                        </c:if>
                                    </div>

                                    <div class="mb-3" id="telefoneField">
                                        <label for="telefone" class="form-label">
                                            <fmt:message key="signin.phone" />
                                        </label>
                                    
                                        <input type="tel" class="form-control" id="telefone" name="telefone" placeholder="<fmt:message key='placeholder.phone'/>">
                                    </div>

                                    <div class="mb-3" id="sexoField">
                                        <label for="sexo" class="form-label">
                                            <fmt:message key="signin.gender" />
                                        </label>

                                        <select name="sexo" id="sexo" class="p-2">
                                            <option value="Masculino" selected>
                                                <fmt:message key="signin.g.male" />
                                            </option>

                                            <option value="Feminino">
                                                <fmt:message key="signin.g.female" />
                                            </option>

                                            <option value="Outro">
                                                <fmt:message key="signin.g.other" />
                                            </option>
                                        </select>
                                    </div>

                                    <div class="mb-3" id="dataNascimentoField">
                                        <label for="dataNascimento" class="form-label">
                                            <fmt:message key="signin.date" />
                                        </label>
                                    
                                        <input type="date" class="form-control" id="dataNascimento" name="dataNascimeto" placeholder="<fmt:message key='placeholder.date'/>" required>
                                    </div>

                                    <div class="mb-3" id="crmField" style="display: none;">
                                        <label for="crm" class="form-label">
                                            CRM
                                        </label>
                                    
                                        <input type="text" class="form-control" id="crm" name="crm" placeholder="<fmt:message key='placeholder.crm'/>" require>
                                    </div>

                                    <div class="mb-3" id="specField" style="display: none;">
                                        <label for="espec" class="form-label">
                                            <fmt:message key="signin.specialty" />
                                        </label>
                                    
                                        <input type="text" class="form-control" id="especialidade" name="especialidade" placeholder="<fmt:message key='placeholder.specialty'/>" require>
                                    </div>

                                    <div class="d-flex justify-content-end mb-2">
                                        <button type="submit" class="btn btn-success px-4">
                                            <fmt:message key="login.button" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function toggleField() {
                var select = document.getElementById("tipoUsuario");

                var cpfField = document.getElementById("cpfField");
                var telefoneField = document.getElementById("telefoneField");
                var sexoField = document.getElementById("sexoField");
                var dataNascimentoField = document.getElementById("dataNascimentoField");

                var crmField = document.getElementById("crmField");
                var specField = document.getElementById("specField");

                if (select.value === "paciente") {
                    cpfField.style.display = "block";
                    telefoneField.style.display = "block";
                    sexoField.style.display = "block";
                    dataNascimentoField.style.display = "block";
                    crmField.style.display = "none";
                    specField.style.display = "none";
                } else {
                    cpfField.style.display = "none";
                    telefoneField.style.display = "none";
                    sexoField.style.display = "none";
                    dataNascimentoField.style.display = "none";
                    crmField.style.display = "block";
                    specField.style.display = "block";
                }
            }
        
            // Chama a função quando a página carrega para definir a visibilidade inicial
            window.onload = function() {
                toggleField();
            }
        </script>

        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

    </body>
</fmt:bundle>
</html>