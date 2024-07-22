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
                                <a href="/ClinicaMedica/#about" class="nav-link">
                                    <fmt:message key="header.about" />
                                </a>
                            </li>

                            <li class="nav-item mx-2">
                                <a href="medico" class="nav-link">
                                    <fmt:message key="header.doctors" />
                                </a>
                            </li>
                    
                            <c:if test="${sessionScope.usuarioLogado == null}">
                                <li class="nav-item mx-2">
                                    <a href="login" class="nav-link">
                                        <fmt:message key="header.login" />
                                    </a>
                                </li>
                            </c:if>

                            <c:if test="${sessionScope.usuarioLogado != null && sessionScope.usuarioLogado.tipoUsuario == 'admin'}">
                                <li class="nav-item mx-2">
                                    <a href="admin" class="nav-link">
                                        <fmt:message key="header.admin" />
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
                    <h2>
                        <fmt:message key="crud.doctor.title" />
                    </h2>
                </div>
        
                <div class="col col-auto">
                    <button type="button" class="btn btn-outline-dark rounded-0 align-self-end" data-bs-toggle="modal" data-bs-target="#cadastroMedicoModal">Cadastrar Médico</button>
                </div>
            </div>

            <div class="col-md-12">
                <table class="table table-light table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>
                                <fmt:message key="signin.name" />
                            </th>
                            <th>
                                <fmt:message key="signin.email" />
                            </th>
                            <th>
                                CRM
                            </th>
                            <th>
                                <fmt:message key="signin.specialty" />
                            </th>
                            <th>
                                <fmt:message key="crud.operations" />
                            </th>
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
                                                <button name="crm" value="${medico.crm}" type="submit"
                                                    class="btn btn-warning rounded-0 align-self-end">Editar Médico</button>
                                            </form>
                                        </div>
                                        <div class="col">
                                            <form action="/ClinicaMedica/medico/remocao" method="post">
                                                <button name="id" value="${medico.id}" type="submit"
                                                    class="btn btn-danger rounded-0 align-self-end">Deletar Médico</button>
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

        <div class="container mt-5">
            <div class="row justify-content-between mb-2">
                <div class="col col-auto">
                    <h2>
                        <fmt:message key="crud.patient.title" />
                    </h2>
                    <c:if test="${errosPaciente.existeErros}">
                        <c:forEach var="erro" items="${errosPaciente.erros}">
                            <li class="text-danger"> ${erro} </li>
                        </c:forEach>
                    </c:if>
                </div>

                <div class="col col-auto">
                    <button type="button" class="btn btn-outline-dark rounded-0 align-self-end" data-bs-toggle="modal" data-bs-target="#cadastroPacienteModal">Cadastrar Paciente</button>
                </div>
            </div>

            <div class="col-md-12">
                <table class="table table-light table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>
                                <fmt:message key="signin.name" />
                            </th>
                            <th>
                                <fmt:message key="signin.email" />
                            </th>
                            <th>
                                <fmt:message key="signin.cpf" />
                            </th>
                            <th>
                                <fmt:message key="signin.phone" />
                            </th>
                            <th>
                                <fmt:message key="signin.gender" />
                            </th>
                            <th>
                                <fmt:message key="signin.date" />
                            </th>
                            <th>
                                <fmt:message key="crud.operations" />
                            </th>
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
                                                <button name="cpf" value="${paciente.cpf}" type="submit"
                                                    class="btn btn-warning rounded-0 align-self-end">Editar Paciente</button>
                                            </form>
                                        </div>
                                        <div class="col">
                                            <form action="/ClinicaMedica/paciente/remocao" method="post">
                                                <button name="id" value="${paciente.id}" type="submit"
                                                    class="btn btn-danger rounded-0 align-self-end">Deletar Paciente</button>
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
                                    <input value="${medicoEdit.email}" type="email" class="form-controlGerenciamento de pacientes" id="email" name="email" required>
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
