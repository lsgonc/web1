<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="pt-br">
<fmt:bundle basename="messages">

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>
		<fmt:message key="page.title" />
	</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="styles.css">
</head>

<body>
    <header class="bg-primary text-white text-center py-3">
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                    	<a href="#home" class="nav-link">
                    		<fmt:message key="header.home" />
                    	</a>
                    </li>
                    
                    <li class="nav-item">
                    	<a href="#about" class="nav-link">
                    		<fmt:message key="header.about" />
                    	</a>
                    </li>
                    
                    <li class="nav-item">
                    	<a href="#services" class="nav-link">
                    		<fmt:message key="header.services" />
                    	</a>
                    </li>
                    
                    <li class="nav-item">
                    	<a href="#contact" class="nav-link">
                    		<fmt:message key="header.contact" />
	                    </a>
                    </li>
                    
                    <li class="nav-item">
                    	<a href="#login" class="nav-link">
                    		<fmt:message key="header.login" />
                    	</a>
                    </li>
                </ul>
            </nav>
        </div>
    </header>

    <section id="home" class="jumbotron text-center text-white bg-info">
        <div class="container">
            <h2 class="display-4">Bem-vindo à Clínica Médica Saúde Total</h2>
            <p class="lead">Sua saúde é a nossa prioridade</p>
            <a href="#about" class="btn btn-light btn-lg">Saiba Mais</a>
        </div>
    </section>

    <section id="about" class="py-5">
        <div class="container">
            <h2>Sobre Nós</h2>
            <p>Nós somos uma clínica dedicada a proporcionar os melhores cuidados médicos para você e sua família. Nossa equipe de profissionais qualificados está pronta para atender suas necessidades de saúde com excelência e carinho.</p>
        </div>
    </section>

    <section id="services" class="py-5 bg-light">
        <div class="container">
            <h2>Nossos Serviços</h2>
            <div class="row">
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title">Consulta Médica</h3>
                            <p class="card-text">Atendimento com médicos especialistas para diversas áreas da saúde.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title">Exames Laboratoriais</h3>
                            <p class="card-text">Realizamos uma variedade de exames laboratoriais com precisão e rapidez.</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="card mb-4">
                        <div class="card-body">
                            <h3 class="card-title">Emergências</h3>
                            <p class="card-text">Atendimento de emergências médicas 24 horas por dia, 7 dias por semana.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <section id="contact" class="py-5">
        <div class="container">
            <h2>Contato</h2>
            <p>Entre em contato conosco para agendar sua consulta ou para mais informações.</p>
            <form action="submit_form.php" method="post">
                <div class="form-group">
                    <label for="name">Nome:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="message">Mensagem:</label>
                    <textarea class="form-control" id="message" name="message" required></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>
    </section>

    <section id="register" class="py-5 bg-light">
        <div class="container">
            <h2>Cadastro</h2>
            <form action="register.php" method="post">
                <div class="form-group">
                    <label for="register-name">Nome Completo:</label>
                    <input type="text" class="form-control" id="register-name" name="register-name" required>
                </div>
                <div class="form-group">
                    <label for="register-email">Email:</label>
                    <input type="email" class="form-control" id="register-email" name="register-email" required>
                </div>
                <div class="form-group">
                    <label for="register-password">Senha:</label>
                    <input type="password" class="form-control" id="register-password" name="register-password" required>
                </div>
                <div class="form-group">
                    <label for="register-phone">Telefone:</label>
                    <input type="tel" class="form-control" id="register-phone" name="register-phone" required>
                </div>
                <button type="submit" class="btn btn-primary">Cadastrar</button>
            </form>
        </div>
    </section>

    <footer class="bg-dark text-white text-center py-3">
        <div class="container">
            <p>2024 Clínica Médica Saúde Total. Todos os direitos reservados.</p>
        </div>
    </footer>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</fmt:bundle>
</html>