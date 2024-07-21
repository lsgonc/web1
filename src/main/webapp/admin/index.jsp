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

        <h1 class="text-center mb-5">PAINEL DE ADMINISTRAÇÃO - ${sessionScope.usuarioLogado.nome}</h1>
        
        <div class="container">
            <div class="row justify-content-center">
                <div class="row justify-content-between">
                    <div class="col col-auto">
                        <h2>Gerenciamento de médicos</h2>    
                    </div>
                    <div class="col col-auto">
                        <button type="button" class="btn btn-dark rounded-0 align-self-end" data-bs-toggle="modal" data-bs-target="#cadastroMedicoModal">Cadastrar Médico</button>
                    </div>

                                
                    
                </div>
                <div class="col-md-12">
                    <table class="table table-light table-hover">
                        <thead class="">
                            <tr>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>CRM</th>
                                <th>Especialidade</th>
                                <th>Operações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="medico" items="${requestScope.listaMedicos}">
                                <tr>
                                    <td>
                                        <c:out value="${medico.nome}" />
                                    </td>
                                    <td>
                                        <c:out value="${medico.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${medico.crm}" />
                                    </td>
                                    <td>
                                        <c:out value="${medico.especialidade}" />
                                    </td>
                                    <td>
                                        <div class="row">
                                            <div class="col">
                                                <form action="/ClinicaMedica/medico/edicao" method="post">
                                                    <button name="crm" value="${medico.crm}" type="submit" class="btn btn-warning rounded-0 align-self-end">Editar Médico</button>
                                                </form>
                                            </div>
                                            <div class="col">
                                                <form action="/ClinicaMedica/medico/remocao" method="post">
                                                    <button  name="id" value="${medico.id}" type="submit" class="btn btn-danger rounded-0 align-self-end">Deletar Médico</button>
                                                </form>
                                            </div>
                                        </div>
                                    </td>
                                    
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row justify-content-center">
                <div class="row justify-content-between">
                    <div class="col col-auto">
                        <h2>Gerenciamento de pacientes</h2>    
                    </div>
                    <div class="col col-auto">
                        <button type="button" class="btn btn-dark rounded-0 align-self-end" data-bs-toggle="modal" data-bs-target="#cadastroPacienteModal">Cadastrar Paciente</button>
                    </div>

                                
                    
                </div>
                <div class="col-md-12">
                    <table class="table table-light table-hover">
                        <thead class="">
                            <tr>
                                <th>Nome</th>
                                <th>Email</th>
                                <th>CPF</th>
                                <th>Telefone</th>
                                <th>Sexo</th>
                                <th>Data de nascimento</th>
                                <th>Operações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="paciente" items="${requestScope.listaPacientes}">
                                <tr>
                                    <td>
                                        <c:out value="${paciente.nome}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.email}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.cpf}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.telefone}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.sexo}" />
                                    </td>
                                    <td>
                                        <c:out value="${paciente.dataNascimento}" />
                                    </td>
                                    <td>
                                        <div class="row">
                                            <div class="col">
                                                <form action="/ClinicaMedica/paciente/edicao" method="post">
                                                    <button name="cpf" value="${paciente.cpf}" type="submit" class="btn btn-warning rounded-0 align-self-end">Editar Paciente</button>
                                                </form>
                                            </div>
                                            <div class="col">
                                                <form action="/ClinicaMedica/paciente/remocao" method="post">
                                                    <button  name="id" value="${paciente.id}" type="submit" class="btn btn-danger rounded-0 align-self-end">Deletar Paciente</button>
                                                </form>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <c:if test="${medicoEdit != null}">
            <div class="modal d-block" role="dialog" id="edicaoMedicoModal" tabindex="-1" aria-labelledby="edicaoMedicoModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="edicaoMedicoModal">Editar médico</h5>
                            <button onclick="fechaModal()" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/ClinicaMedica/medico/atualizacao" method="post">
                                <input type="hidden" value="${medicoEdit.id}" name="id">
                                <div class="mb-3">
                                    <label for="nome" class="form-label">Nome:</label>
                                    <input value="${medicoEdit.nome}" type="text" class="form-control" id="nome" name="nome" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <input value="${medicoEdit.email}" type="email" class="form-control" id="email" name="email" required>
                                </div>
                                <div class="mb-3">
                                    <label for="senha" class="form-label">Senha:</label>
                                    <input value="${medicoEdit.senha}"  type="password" class="form-control" id="senha" name="senha" required>
                                </div>
                                <div class="mb-3">
                                    <label for="crm" class="form-label">CRM:</label>
                                    <input value="${medicoEdit.crm}" type="text" class="form-control" id="crm" name="crm" required>
                                </div>
                                <div class="mb-3">
                                    <label for="especialidade" class="form-label">Especialidade:</label>
                                    <input value="${medicoEdit.especialidade}" type="text" class="form-control" id="especialidade" name="especialidade" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>

        <!-- Modal Cadastro Médico -->
        <div class="modal fade" id="cadastroMedicoModal" tabindex="-1" aria-labelledby="cadastroMedicoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cadastroMedicoModalLabel">Cadastro de Médico</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
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

        <c:if test="${pacienteEdit != null}">
            <div class="modal d-block" role="dialog" id="edicaoPacienteModal" tabindex="-1" aria-labelledby="edicaoPacienteModal" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="edicaoPacienteModal">Editar Paciente</h5>
                            <button onclick="fechaModalPac()" type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="/ClinicaMedica/paciente/atualizacao" method="post">
                                <input type="hidden" value="${pacienteEdit.id}" name="id">
        
                                <div class="mb-3">
                                    <label for="nome" class="form-label">Nome:</label>
                                    <input value="${pacienteEdit.nome}" type="text" class="form-control" id="nome" name="nome" required>
                                </div>
        
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email:</label>
                                    <input value="${pacienteEdit.email}" type="email" class="form-control" id="email" name="email" required>
                                </div>
        
                                <div class="mb-3">
                                    <label for="senha" class="form-label">Senha:</label>
                                    <input value="${pacienteEdit.senha}" type="password" class="form-control" id="senha" name="senha" required>
                                </div>
        
                                <div class="mb-3">
                                    <label for="cpf" class="form-label">CPF:</label>
                                    <input value="${pacienteEdit.cpf}" type="text" class="form-control" id="cpf" name="cpf" required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" title="Digite um CPF no formato: xxx.xxx.xxx-xx">
                                </div>
        
                                <div class="mb-3">
                                    <label for="telefone" class="form-label">Telefone:</label>
                                    <input value="${pacienteEdit.telefone}" type="text" class="form-control" id="telefone" name="telefone" required>
                                </div>
        
                                <div class="mb-3">
                                    <label for="sexo" class="form-label">Sexo:</label>
                                    <select class="form-select" id="sexo" name="sexo" required>
                                        <option value="Masculino" ${pacienteEdit.sexo == 'Masculino' ? 'selected' : ''}>Masculino</option>
                                        <option value="Feminino" ${pacienteEdit.sexo == 'Feminino' ? 'selected' : ''}>Feminino</option>
                                        <option value="Outro" ${pacienteEdit.sexo == 'Outro' ? 'selected' : ''}>Outro</option>
                                    </select>
                                </div>
        
                                <div class="mb-3">
                                    <label for="dataNascimento" class="form-label">Data de Nascimento:</label>
                                    <input value="${pacienteEdit.dataNascimento}" type="date" class="form-control" id="dataNascimento" name="dataNascimento" required>
                                </div>
        
                                <button type="submit" class="btn btn-primary">Editar</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        

        <!-- Modal Cadastro Paciente -->
        <div class="modal fade" id="cadastroPacienteModal" tabindex="-1" aria-labelledby="cadastroPacienteModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="cadastroPacienteModalLabel">Cadastro de Paciente</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="/ClinicaMedica/paciente/insercao" method="post">
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
                                <label for="cpf" class="form-label">CPF:</label>
                                <input type="text" class="form-control" id="cpf" name="cpf" required pattern="\d{3}\.\d{3}\.\d{3}-\d{2}" \
                                title="Digite um CPF no formato: xxx.xxx.xxx-xx">
                            </div>
                            <div class="mb-3">
                                <label for="telefone" class="form-label">Telefone:</label>
                                <input type="text" class="form-control" id="telefone" name="telefone" required>
                            </div>
                            <div class="mb-3">
                                <label for="sexo" class="form-label">Sexo:</label>
                                <select class="form-select" id="sexo" name="sexo" required>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Feminino">Feminino</option>
                                    <option value="Outro">Outro</option>
                                </select>
                            </div>
                            <div class="mb-3">
                                <label for="dataNascimento" class="form-label">Data de Nascimento:</label>
                                <input type="date" class="form-control" id="dataNascimento" name="dataNascimento" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Cadastrar</button>
                        </form>
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

        <script>
            $(document).ready(function(){
                $("#edicaoMedicoModal").modal('show');
                $("#edicaoPacienteModal").modal('show');
            });

            function fechaModal() {
               var element = document.getElementById("edicaoMedicoModal");
                element.classList.remove("d-block");
            }
            function fechaModalPac() {
               var element = document.getElementById("edicaoPacienteModal");
                element.classList.remove("d-block");
            }
        </script>


        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>
