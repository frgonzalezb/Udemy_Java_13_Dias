<!-- URLs for the app -->
<c:set var="home">
    <c:url value="${application.contextPath}/"/>
</c:set>
<c:set var="addEmployee">
    <c:url value="${application.contextPath}/add-employee/"/>
</c:set>

<!-- Navbar -->
<div class="container">
    <nav class="navbar navbar-expand-lg bg-primary" data-bs-theme="dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${home}">Sistema de Empleados</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${home}">Inicio</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${addEmployee}">Agregar empleado</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</div>