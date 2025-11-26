<%@ page import="java.util.*,com.payroll.model.Employee"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee List</title>
<style>
body {
	font-family: Arial;
	background-color: #f0f0f0;
}

table {
	width: 80%;
	margin: 20px auto;
	border-collapse: collapse;
	background: white;
}

th, td {
	padding: 10px;
	border: 1px solid #ddd;
	text-align: center;
}

th {
	background-color: #4CAF50;
	color: white;
}

a {
	text-decoration: none;
	color: blue;
}

form {
	text-align: center;
	margin-top: 20px;
}

input[type=text] {
	width: 250px;
	padding: 8px;
	font-size: 14px;
}

input[type=submit] {
	padding: 8px 14px;
	background-color: #4CAF50;
	border: none;
	color: white;
	cursor: pointer;
	font-size: 14px;
}

input[type=submit]:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

	<h2 style="text-align: center;">Employee Payroll Records</h2>


	<form action="viewEmployees" method="get">
		<input type="text" name="search" placeholder="Search by ID"
			value="<%= request.getParameter("search") == null ? "" : request.getParameter("search") %>">
		<input type="submit" value="Search">
	</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Designation</th>
			<th>Basic</th>
			<th>HRA</th>
			<th>DA</th>
			<th>Deductions</th>
			<th>Net Salary</th>
		</tr>

		<%
            List<Employee> list = (List<Employee>) request.getAttribute("employeeList");
            if (list != null && !list.isEmpty()) {
                for (Employee e : list) {
        %>
		<tr>
			<td><%= e.getEmpId() %></td>
			<td><%= e.getEmpName() %></td>
			<td><%= e.getDesignation() %></td>
			<td><%= e.getBasicSalary() %></td>
			<td><%= e.getHra() %></td>
			<td><%= e.getDa() %></td>
			<td><%= e.getDeductions() %></td>
			<td><%= e.getNetSalary() %></td>
		</tr>
		<%      }
            } else { %>
		<tr>
			<td colspan="8">No employees found.</td>
		</tr>
		<% } %>
	</table>

	<div style="text-align: center;">
		<a href="index.html">Back to Home</a>
	</div>

</body>
</html>
