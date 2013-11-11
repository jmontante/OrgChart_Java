$(document).ready(function() {
	//$('#Btn-container').css('width', $('#t1').width());
	
	$('#addEmpBtn').button().click(function() {
		$('#addEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
	});
	
	$('.editEmpBtn').button().click(function() {
//		$('#editBtn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').fadeToggle("fast", "linear");
//		});
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
//		$('#department\\.departmentId').val("");
//		$('#jobTitle\\.id').val("");
	});
});