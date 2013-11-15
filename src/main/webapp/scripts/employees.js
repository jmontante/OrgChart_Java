$(document).ready(function() {
	
	//
	$('#addEmpBtn').button().click(function() {
		/* if the form class is currently "editEmp" then remove it and
		 * add the class "addEmp' instead.
		 * also remove the hidden input type to change method back to post
	     */
		if ($('#newEmpForm').hasClass("editEmp")) {
			$('#newEmpForm').removeClass("editEmp");
			$('#newEmpForm').addClass("addEmp");
			$("#putEmpsMethod").remove();
			
			// Change title from "Edit Employee" to "Add Employee" and clear all the fields
			$("#empsFormLegend").text("Add Employee");
			$("#id").remove();
			$('#firstName').val("");
			$('#lastName').val("");
			$('#email').val("");
			$('#skypeName').val("");
			$('#department\\.id').val("");
			$('#jobTitle\\.id').val("");
		}
		$('#addEntity').dialog({ minWidth : 400});
	});
	
	
	$('.editEmpBtn').button().click(function() {
		
		/* if the form class is currently "addDept" then remove it and
		 * add the class "editDept" instead. Also, add a hidden input to 
		 * change the method to put
	     */
		if ($('#newEmpForm').hasClass("addEmp")) {
			$('#newEmpForm').removeClass("addEmp");
			$('#newEmpForm').addClass("editEmp");
			$('#newEmpForm').prepend("<input type='hidden' name='_method' value='put' id='putEmpsMethod'/>");
			
			// Change the form title from "Add Employee" to "Edit Employee" 
			$("#newEmpFormLegend").text("Edit Employee");
			
			//add hidden input field which has the id of the job
			$("#newEmpFormInputs").prepend("<input type='hidden' name='id' id='id' />");
		}
			
		//ajax call that prefills the add emps form data
		$.ajax({
			url : "emps/" + $(this).val(),
			type : "GET"
		}).done(function(data){
			var form = $.parseJSON(data);
			$('#id').val(form.id);
			$('#firstName').val(form.firstName);	
			$('#lastName').val(form.lastName);
			$('#email').val(form.email);
			$('#skypeName').val(form.skypeName);
			$('#department\\.id').val(form.department.id);
			$('#jobTitle\\.id').val(form.jobTitle.id);
			$('#addEmpsBtn').fadeToggle("fast", "linear");			
			$('#addEntity').dialog({ minWidth : 400});
		});
	});
		
	$('.removeEmpBtn').button();	
	
	$('#saveEmpBtn').button();
	
	//Cancel button on the form clears out all the fields and closes the form
	$('#cancelEmpBtn').button().click(function() {
		$('#addEntity').dialog("close");
		$('#firstName').val("");
		$('#lastName').val("");
		$('#email').val("");
		$('#skypeName').val("");
		$('#department\\.id').val("");
		$('#jobTitle\\.id').val("");
	});
});