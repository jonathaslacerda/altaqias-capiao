jQuery(function($){
	datePicker();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		datePicker();
	}
})

function datePicker(){
	$('.date-picker').datetimepicker({
		format: 'DD/MM/YYYY',
		locale: 'pt-br'
	}).on('click', function(){
		$(this).focus();
	}).next().on('click', function(){
		$(this).prev().focus();
	});
}