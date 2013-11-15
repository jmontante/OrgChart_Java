<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees</title>
</head>
<body>
	<h3>Systems In Motion: Employees</h3> 	
	<button type="button" id="addEmpBtn" style="width: 45px;">Add</button>	
	<br><br>
	<table id="empTable" border="1"> 
		<tr>
			<th>First Name</th> <th>Last Name</th> <th>Department</th> <th>Job Title</th>
		</tr> 
		<c:forEach items="${emps}" var="emp">
			<tr> 
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td>${emp.department.name}</td>
				<td>${emp.jobTitle.name}</td>
				<td><button type="button" class="editEmpBtn" value="${emp.id}">Edit</button></td>
				<td>
					<form:form name="removeEmp" action="emps" method="delete">
						<input type="hidden" name="id" value="${emp.id}">
						<button type="submit" class="removeEmpBtn">Delete</button>
					</form:form>
				</td>
			</tr>
		</c:forEach> 
	</table>
			
	<div id="addEntity" style="display:none">
		<fieldset>
			<legend id="newEmpFormLegend">Add Employee</legend>
			<form:form id="newEmpForm" class="addEmp" name="newEmp" action="emps" method="post">
				<div id="newEmpFormInputs">
					<br><br>
					<label>First Name:</label><input type="text" name="firstName" id="firstName" required/> *<br>
					<label>Last Name:</label><input type="text" name="lastName" id = "lastName" required/> *<Br>
					<label>Email:</label><input type="text" name="email" id="email" required/> *<Br>
					<label>Skype Name:</label><input type="text" name="skypeName" id ="skypeName" required/> *<Br><br>
					<label>Department:</label>
					<select name="department.id" id="department.id" required>
					 	<option value="">- Select Department -</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select> *<br> 
					<label>Job Title:</label>
					<select name="jobTitle.id" id="jobTitle.id" required>
						<option value="">- Select Job Title - </option>
						<c:forEach items="${jobs}" var="job">
							<option value="${job.id}">${job.name}</option>
						</c:forEach>
					</select> *<br><br>
					<label>Is Manager:</label><input type="checkbox" name="isManager"/>
					<br><br>
					<button type="submit" id="saveEmpBtn">Save</button>
					<button type="button" id="cancelEmpBtn">Cancel</button>
				</div>
			</form:form>
			<br><br><footer>Required Fields indicated with a *</footer>
		</fieldset>
	</div>
</body>
</html>