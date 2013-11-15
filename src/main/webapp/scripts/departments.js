$(document).ready(function() {
	//$('#Btn-container').css('width', $('#t1').width());
	
	//
	$('#addDeptBtn').button().click(function() {
		/* if the form class is currently "editDept" then remove it and
		 * add the class "addDept' instead.
		 * also remove the hidden input type to change method back to post
	     */
		if($('#newDeptForm').hasClass("editDept")){
		   $('#newDeptForm').removeClass("editDept");
		   $('#newDeptForm').addClass("addDept");
		   $('#putDeptsMethod').remove();
		   
			// Change the form title from "Edit Department" to "Add Department" and clear all the fields
			$("#newDeptFormLegend").text("Add Department");
			$('#id').remove();
			$('#name').val("");
			$('#parentDepartment\\.id').val("");	
			$('#addEntity').dialog({ minWidth : 400});
		}
		$('#addEntity').dialog({ minWidth : 400});
	});

	//
	$('.editDeptBtn').button().click(function() {		
		/* if the form class is currently "addDept" then remove it and
		 * add the class "editDept" instead. Also, add a hidden input to 
		 * change the method to put
	     */
		if ($('#newDeptForm').hasClass("addDept")) {
			$('#newDeptForm').removeClass("addDept");
			$('#newDeptForm').addClass("editDept");
			$('#newDeptForm').prepend("<input type='hidden' name='_method' value='put' id='putDeptsMethod'/>");
			
			// Change the form title from "Add Department" to "Edit Department" 
			$("#newDeptFormLegend").text("Edit Department");
			
			//add hidden input field which has the id of the job
			$("#newDeptFormInputs").prepend("<input type='hidden' name='id' id='id' />");
		}
		
		//ajax call that prefills the add depts form data
		$.ajax({
			url : "depts/" + $(this).val(),
			type : "GET"
		}).done(function(data){
			var form = $.parseJSON(data);
			$('#id').val(form.id);
			$('#name').val(form.name);
			$('#parentDepartment\\.id').val(form.parentDepartment.id);			
			$('#addDeptsBtn').fadeToggle("fast", "linear");			
			$('#addEntity').dialog({ minWidth : 400});
		});
	});

	$('.removeDeptBtn').button();
	$('#saveDeptBtn').button();

	//Cancel button on the form clears out all the fields and closes the form
	$('#cancelDeptBtn').button().click(function() {
		$('#addEntity').dialog("close");
		$('#parentDepartment\\.id').val("");
	});
	
});
