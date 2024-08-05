<%@ include file="common/head.jsp"%>

<body>
<%@ include file="common/navbar.jsp"%>

<!-- Main page -->
<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Agregar Empleado</h3>
    </div>
    <div class="container">
        <form>
            <div class="mb-3">
                <label for="firstName" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="firstName" required>
            </div>
            <div class="mb-3">
                <label for="lastName" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastName" required>
            </div>
            <div class="mb-3">
                <label for="position" class="form-label">Cargo</label>
                <input type="text" class="form-control" id="position" required>
            </div>
            <div class="mb-3">
                <label for="department" class="form-label">Departamento</label>
                <input type="text" class="form-control" id="department" required>
            </div>
            <div class="mb-3">
                <label for="emailAddress" class="form-label">Email</label>
                <input type="email" class="form-control" id="emailAddress" aria-describedby="emailHelp" required>
            </div>
            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Fono</label>
                <input type="number" class="form-control" id="phoneNumber" required>
            </div>
            <div class="mb-3">
                <label for="grossPay" class="form-label">Sueldo bruto</label>
                <input type="number" step="any" class="form-control" id="grossPay" required>
            </div>
            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-sm me-3">Agregar</button>
                <a href="/simple-hrms/" class="btn btn-danger btn-sm">Cancelar</a>
            </div>
        </form>
    </div>
</div>

<%@ include file="common/footer.jsp"%>

<%@ include file="common/scripts.jsp"%>
</body>
</html>