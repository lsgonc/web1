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
        <script src="https://kit.fontawesome.com/906fdf974e.js" crossorigin="anonymous"></script>

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

            i{
                font-size: 6rem;
                padding: 12px;
                color: #30cc00;
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
                    <a class="navbar-brand" href="#">
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
                                <a href="medico/" class="nav-link">
                                    <fmt:message key="header.doctors" />
                                </a>
                            </li>
                    
                            <li class="nav-item mx-2">
                                <a href="login/" class="nav-link">
                                    <fmt:message key="header.login" />
                                </a>
                            </li>
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
                <a href="medico/" class="btn btn-outline-light fw-medium btn-lg px-4 py-2">
                    <fmt:message key="banner.button" />
                </a>
            </div>
        </section>

        <section id="about" class="py-5 mt-4">
            <div class="container d-flex flex-column align-items-center justify-content-center">
                <div class="w-75 text-center mb-4">
                    <h2 class="py-2">
                        <fmt:message key="about.title" />
                    </h2>
                
                    <p>
                        <fmt:message key="about.text" />
                    </p>
                </div>

                <div class="row w-100">
                    <div class="col-md-3">
                        <div class="card mb-3">  
                            <div class="card-body text-center">
                                <i class="fas fa-stethoscope"></i>
                                <h3 class="card-title fs-4 mt-3">
                                    <fmt:message key="card.title1" />
                                </h3>
                                <p class="mt-2">
                                    <fmt:message key="card.text1" />
                                </p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body text-center">
                                <i class="fas fa-flask"></i>
                                <h3 class="card-title fs-4 mt-3">
                                    <fmt:message key="card.title2" />
                                </h3>
                                <p class="mt-2">
                                    <fmt:message key="card.text2" />
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body text-center">
                                <i class="fas fa-procedures"></i>
                                <h3 class="card-title fs-4 mt-3">
                                    <fmt:message key="card.title3" />
                                </h3>
                                <p class="mt-2">
                                    <fmt:message key="card.text3" />
                                </p>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-3">
                        <div class="card mb-3">
                            <div class="card-body text-center">
                                <i class="fas fa-search"></i>
                                <h3 class="card-title fs-4 mt-3">
                                    <fmt:message key="card.title4" />
                                </h3>
                                <p class="mt-2">
                                    <fmt:message key="card.text4" />
                                </p>
                            </div>
                        </div>
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
        
        <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    </body>
</fmt:bundle>
</html>