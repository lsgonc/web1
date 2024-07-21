<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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