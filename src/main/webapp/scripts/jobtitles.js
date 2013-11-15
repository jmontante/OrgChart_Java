$(document).ready(function() {

	//
	$('#addJobBtn').button().click(function() {
		/* if the class is currently "editJob" then remove it and
		 * add the class "addJob" instead.
		 * also remove the hidden input type to change method back to post
	     */
		if ($('#newJobForm').hasClass("editJob")) {
			$('#newJobForm').removeClass("editJob");
			$('#newJobForm').addClass("addJob");
			$("#putJobsMethod").remove();
			
			// Change title from "Edit Job Title" to "Add Job Title" and clear all the fields
			$("#jobsFormLegend").text("Add Job Title");
			$("#id").remove();
			$('#name').val("");
		}
		$('#addEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
	});
	
	$('.editJobBtn').button().click(function() {
		
		//change the form class from add to edit
		if ($('#newJobForm').hasClass("addJob")) {
			$('#newJobForm').removeClass("addJob");
			$('#newJobForm').addClass("editJob");
			$('#newJobForm').prepend("<input type='hidden' name='_method' value='put' id='putJobsMethod'/>");
			
			// Change the form title from "Add Job TItle" to "Edit Job title" 
			$("#newJobFormLegend").text("Edit Job Title");
			
			//add hidden input field which has the id of the job
			$("#newJobFormInputs").prepend("<input type='hidden' name='id' id='id' />");
		}
			
		//ajax call that prefills the add jobs form data
		$.ajax({
			url : "jobs/" + $(this).val(),
			type : "GET"
		}).done(function(data){
			var form = $.parseJSON(data);
			$('#id').val(form.id);
			$('#name').val(form.name);			
			$('#addJobsBtn').fadeToggle("fast", "linear");			
			$('#addEntity').dialog({ minWidth : 400});
		});
	});
	
	$('.removeJobBtn').button();
	
	$('#saveJobBtn').button();	
	
	//Cancel button on the form clears out all the fields and closes the form
	$('#cancelJobBtn').button().click(function() {
		$('#addEntity').dialog("close");
		$('#id').val("");
		$('#name').val("");
	});
});