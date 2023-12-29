<%--
  Created by IntelliJ IDEA.
  User: hcmqu
  Date: 12/29/2023
  Time: 9:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
            crossorigin="anonymous"></script>
    <title>Todo App</title>
    <style>
        .odede {
            text-decoration: line-through;
        }
    </style>
</head>

<body>
<center>
    <h3>Todo App</h3>
</center>
<%--form-create--%>
<form action="/jobs?act=create" method="post">
    <div class="mb-3">
        <label class="form-label">Description</label>
        <input type="text" class="form-control" name="description" aria-describedby="emailHelp">
    </div>
    <div class="mb-3">
        <label class="form-label">Time</label>
        <input type="time" class="form-control" name="time">
    </div>
    <button class="btn btn-primary">Submit</button>
</form>

<%--form-list--%>
<div class="container-fluid">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Status</th>
            <th scope="col">Description</th>
            <th scope="col">Time</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="job" items="${jobs}">
            <tr class="<c:if test="${job.isStatus()}">odede</c:if>">
                <td>
                    <input class="form-check-input cb" type="checkbox" value="" <c:if test="${job.isStatus()}">disabled</c:if>
                           id="${job.id}" ${job.isStatus() ? "checked" : ""}>

                </td>
                <td>
                    ${job.description}
                </td>
                <td >
                    ${job.time}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>

<script>
    let cbs = document.querySelectorAll(".cb");

    cbs.forEach((v) => {
        v.addEventListener('change', function () {
            if (this.checked) {
                window.location.href = `/jobs?act=checked&id=` + v.id;
            }
        });
    })


</script>
</html>
