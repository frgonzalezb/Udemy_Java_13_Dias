<%@ include file="common/head.jsp"%>

<body>
<%@ include file="common/navbar.jsp"%>

<!-- Main page -->
<div class="container">
    <div class="text-center" style="margin: 30px">
        <h3>Sistema de Empleados</h3>
    </div>
    <!-- Table -->
    <div class="container">
        <table class="table table-striped table-hover table-bordered align-middle">
            <thead class="table-dark text-center">
            <tr>
                <th scope="col">#</th>
                <th scope="col">Nombre</th>
                <th scope="col">Apellido</th>
                <th scope="col">Cargo</th>
                <th scope="col">Departamento</th>
                <th scope="col">Email</th>
                <th scope="col">Fono</th>
                <th scope="col">Sueldo bruto</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <th scope="row">${employee.id}</th>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.jobTitle}</td>
                    <td>${employee.department}</td>
                    <td>${employee.email}</td>
                    <td>${employee.phone}</td>
                    <td>
                        <fmt:setLocale value="en_US"/>
                        <fmt:formatNumber value="${employee.grossPayUSD}" type="currency" currencySymbol="USD"/>
                    </td>
                    <td class="text-center">
                        <c:set var="editEmployee">
                            <c:url value="${application.contextPath}/edit-employee/">
                                <c:param name="id" value="${employee.id}"/>
                            </c:url>
                        </c:set>
                        <a href="${editEmployee}" class="btn btn-warning btn-sm me-3">Editar</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@ include file="common/footer.jsp"%>

<%@ include file="common/scripts.jsp"%>
</body>
</html>
