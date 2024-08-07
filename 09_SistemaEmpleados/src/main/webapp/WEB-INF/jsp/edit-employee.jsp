<%@ include file="common/head.jsp"%>

<body>
<%@ include file="common/urls.jsp"%>
<%@ include file="common/navbar.jsp"%>

<!-- Main page -->
<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Editar Empleado</h3>
    </div>
    <div class="container">
        <form action="${editEmployee}" modelAttribute="employeeForm" method="POST">
            <!-- Hidden div for id -->
            <input type="hidden" name="id" value="${employee.id}"/>
            <!-- Rest of the form -->
            <div class="mb-3">
                <label for="firstName" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value="${employee.firstName}" required>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value="${employee.lastName}" required>
            </div>
            <div class="mb-3">
                <label for="jobTitle" class="form-label">Cargo</label>
                <input type="text" class="form-control" id="jobTitle" name="jobTitle" value="${employee.jobTitle}" required>
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Departamento</label>
                <input type="text" class="form-control" id="department" name="department" value="${employee.department}" required>
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" name="email" value="${employee.email}" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3">
                <label for="phone" class="form-label">Fono</label>
                <input type="number" class="form-control" id="phone" name="phone" value="${employee.phone}" required>
            </div>
            <div class="mb-3">
                <label for="grossPayUSD" class="form-label">Sueldo bruto</label>
                <input type="number" step="any" class="form-control" id="grossPayUSD" name="grossPayUSD" value="${employee.grossPayUSD}" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-sm me-3">Guardar</button>
                <a href="${home}" class="btn btn-danger btn-sm">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="common/footer.jsp"%>

<%@ include file="common/scripts.jsp"%>
</body>
</html>