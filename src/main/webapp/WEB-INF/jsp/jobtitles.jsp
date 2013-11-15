<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Job Titles</title>
</head>
<body>
	<h3>Systems In Motion: Job Titles</h3> 

		<button type="button" id="addJobBtn" style="width: 45px;">Add</button><br><br>

	<table id="jobsTable" border="1"> 
		<tr>
			<th>Name</th>
		</tr> 
		<c:forEach items="${jobs}" var="job">
			<tr> 
				<td>${job.name}</td> 
				<td><div><button type="button" class="editJobBtn" value="${job.id}">Edit</button></div></td>
				<td>
					<form:form name="removeJob" action="jobs" method="delete">
						<input type="hidden" name="id" value="${job.id}">
						<button type="submit" class="removeJobBtn">Delete</button>				
					</form:form>
				</td>
			</tr>
		</c:forEach> 
	</table>
		
	<div id="addEntity" style="display:none">
	<fieldset>
		<legend id="newJobFormLegend">Add Job Title</legend>
		<form:form id="newJobForm" class="addJob" name="newJob" action="jobs" method="post">
			<div id="newJobFormInputs">
				<br><br><labeL>Job Name:</labeL>
				<input type="text" id="name" name="name" required> *
				<br><br>
				<button type="submit" id="saveJobBtn">Save</button>
				<button type="button" id="cancelJobBtn">Cancel</button>
			</div>
			<br><footer>Required Fields indicated with a *</footer>
		</form:form>
	</fieldset>
	</div>	
<!-- 	<script> -->
<!-- 	$("#addEntity").validate(); -->
<!-- 	</script> -->

</body>
</html>