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

	<table id="jobsTable"> 
		<tr>
			<th>Name</th>
		</tr> 
		<c:forEach items="${jobs}" var="job">
			<tr> 
				<td>${job.name}</td> 
				<td><div><button type="button" class="editJobBtn" value="${job.id}">Edit</button></div></td>
<%-- 				<td><div><button type="button" class="removeJobBtn" value="${job.id}">Delete</button></div></td> --%>
			</tr>
		</c:forEach> 
	</table>
		
	<div id="addEntity" style="display:none">
	<fieldset>
		<legend>Add Job Title</legend>
		<form name="newJob" action="jobs" method="post">
			<div><labeL>Job Name:</labeL>
				 <input type="text" name="name"/>
				 <button type="submit">Save</button>
			</div>
		</form>
	</fieldset>
	</div>	
	
<!-- 	<div id="editEntity" style="display:none"> -->
<!-- 		<fieldset> -->
<!-- 			<legend>Edit Job Title</legend> -->
<%-- 			<form name="editJob" action="jobs" method="put"> --%>
<!-- 				<div><button type="submit">Save</button></div> -->
<%-- 			</form> --%>
<!-- 		</fieldset> -->
<!-- 	</div> -->
	
<!-- 	<div id="removeEntity" style="display:none"> -->
<!-- 		<fieldset> -->
<!-- 			<legend>Remove Job Title</legend> -->
<%-- 			<form name="removeJob" action="jobs" method="delete"> --%>
<!-- 				<div><button type="submit">Save</button></div>				 -->
<%-- 			</form> --%>
<!-- 		</fieldset> -->
<!-- 	</div> -->
</body>
</html>