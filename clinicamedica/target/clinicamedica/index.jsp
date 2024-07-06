<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Clínica Médica Saúde Total</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <header>
        <div class="container">
            <h1>Clínica Médica Saúde Total</h1>
            <nav>
                <ul>
                    <li><a href="#home">Início</a></li>
                    <li><a href="#about">Sobre</a></li>
                    <li><a href="#services">Serviços</a></li>
                    <li><a href="#contact">Contato</a></li>
                </ul>
            </nav>
        </div>
    </header>
    <section id="home" class="hero">
        <div class="container">
            <h2>Bem-vindo à Clínica Médica Saúde Total</h2>
            <p>Sua saúde é a nossa prioridade</p>
            <a href="#about" class="btn">Saiba Mais</a>
        </div>
    </section>
    <section id="about" class="about">
        <div class="container">
            <h2>Sobre Nós</h2>
            <p>Nós somos uma clínica dedicada a proporcionar os melhores cuidados médicos para você e sua família. Nossa equipe de profissionais qualificados está pronta para atender suas necessidades de saúde com excelência e carinho.</p>
        </div>
    </section>
    <section id="services" class="services">
        <div class="container">
            <h2>Nossos Serviços</h2>
            <div class="service">
                <h3>Consulta Médica</h3>
                <p>Atendimento com médicos especialistas para diversas áreas da saúde.</p>
            </div>
            <div class="service">
                <h3>Exames Laboratoriais</h3>
                <p>Realizamos uma variedade de exames laboratoriais com precisão e rapidez.</p>
            </div>
            <div class="service">
                <h3>Emergências</h3>
                <p>Atendimento de emergências médicas 24 horas por dia, 7 dias por semana.</p>
            </div>
        </div>
    </section>
    <section id="contact" class="contact">
        <div class="container">
            <h2>Contato</h2>
            <p>Entre em contato conosco para agendar sua consulta ou para mais informações.</p>
            <form action="submit_form.php" method="post">
                <label for="name">Nome:</label>
                <input type="text" id="name" name="name" required>
                
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
                
                <label for="message">Mensagem:</label>
                <textarea id="message" name="message" required></textarea>
                
                <button type="submit" class="btn">Enviar</button>
            </form>
        </div>
    </section>
    <footer>
        <div class="container">
            <p>&copy; 2024 Clínica Médica Saúde Total. Todos os direitos reservados.</p>
        </div>
    </footer>
</body>
</html>