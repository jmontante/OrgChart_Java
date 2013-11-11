$(document).ready(function() {
	//$('#Btn-container').css('width', $('#t1').width());
	
	$('#addDeptBtn').button().click(function() {
		$('#addEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
	});
		
	$('.editDeptBtn').button().click(function() {
		$('#editEntity').fadeToggle("fast", "linear").dialog({ minWidth : 400});
	});
		
	$('.removeDeptBtn').button();
	
});
