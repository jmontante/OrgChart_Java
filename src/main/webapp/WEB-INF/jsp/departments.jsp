<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Departments</title>
</head>
<body>
	<h3>Systems In Motion: Departments</h3>
	<button type="button" id="addDeptBtn" style="width: 45px;">Add</button>
	<br><br>
	<table id="t1" border="1"> 
		<tr>
			<th>Dept Name</th> <th>Parent Dept</th>
		</tr> 
		<c:forEach items="${depts}" var="dept">
			<tr> 
				<td>${dept.name}</td> 
				<td>${dept.parentDepartment.name}</td>			
					<td><div><button type="button" class="editDeptBtn" value="${dept.id}">Edit</button></div></td>
					<td>
						<form:form name="removeDeptBtn" action="depts" method="delete">
							<input type="hidden" name="id" value="${dept.id}">
							<button type="submit" class="removeDeptBtn">Delete</button>
						</form:form>
					</td>					
			</tr>
		</c:forEach>
	</table>

	<div id="addEntity" style="display:none">
		<fieldset>
			<legend id="newDeptFormLegend">Add Department</legend>
			<form:form id="newDeptForm" class="addDept" name="newDept" action="depts" method="post">
				<br><br>
				<div id="newDeptFormInputs">
					<labeL>Dept Name:</labeL><input type="text" id="name" name="name" required/> *<br>
					<labeL>Parent Dept:</label>
					<select id="parentDepartment.id" name="parentDepartment.id">
						<option value="">- Select Department -</option>
						<c:forEach items="${depts}" var="dept">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select> *<br><br>
					<button type="submit" id="saveDeptBtn">Save</button>
					<button type="button" id="cancelDeptBtn">Cancel</button>
				</div>		
			</form:form>
			<br><br><footer>Required Fields indicated with a *</footer>
		</fieldset>
	</div>

</body>
</html>