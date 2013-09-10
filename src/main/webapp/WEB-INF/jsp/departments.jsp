<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<h3>Departments</h3>

<!-- Main Index Values --> 
<table id="t1"> 
	<tr><!-- <sec:authorize access="hasRole('ROLE_ADMIN')"> -->
		<!-- <th>Task</th></sec:authorize> --> 
		<th>Dept Name</th> <th>Parent Dept</th> <th></th>
	</tr> 
	<c:forEach items="${depts}" var="dept">
		<tr> 
			<!-- <sec:authorize access="hasRole('ROLE_ADMIN')">
				<td>delete</td>
			</sec:authorize> -->
			<td>${dept.name}</td> 
			<td>${dept.parentDepartment.name}</td>
			<td>
			  <button id="editBtn" style="width:45px;" value="${dept.id}">Edit</button>
			</td>
		</tr>
	</c:forEach> 
</table>

<div id="addBtn-container">
		<button type="button" id="addBtn" style="width: 45px;">Add</button>	
</div>


<!-- Edit Form -->
<div id="editEntity" style="display:none">
	<fieldset>
		<legend>Edit Department</legend>
		<form:form method="POST" action="deptEdit" commandName="Department">
		  <form:hidden path="id" />
		  <form:hidden path="parent_department_id" />
		  <label>Dept Name:</label>
		  <input type="submit" value="Save"/>
		</form:form>
	</fieldset>
</div>

<!-- Add Form -->
<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Department</legend>
		<form id="newDepartmentForm" action="deptCreate" method="post">
		<div><labeL>Dept Name:</labeL><input type="text" name="name" />
			<labeL>Parent Dept:</label>
			<select name="parent_id" >
				<option>...</option>
				<c:forEach items="${depts}" var="dept">
					<option value="${dept.id}">${dept.name}</option>
				</c:forEach>
			</select>
			<input type="submit" value="Save"/>
		</div>
		<div></div>
		</form>
	</fieldset>
</div> 

