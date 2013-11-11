$(document).ready(function() {
	//$('#Btn-container').css('width', $('#t1').width());

	$('#addJobBtn').button().click(function() {
		//$('#Btn-container').fadeToggle("fast", "linear", function() {
			$('#addEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
		//});
	});
	
	$('.editJobBtn').button().click(function() {
		//$('#Btn-container').fadeToggle("fast", "linear", function() {
			$('#editEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
		//});	
	});
	
	$('.removeJobBtn').button();	
});