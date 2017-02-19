jQuery(function($){
	dateTimePicker();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		dateTimePicker();
	}
})

function dateTimePicker(){
	$('.datetime-picker').datetimepicker({
		format: 'DD/MM/YYYY HH:mm:ss',
		locale: 'pt-br',
		sideBySide: true
	}).on('dp.change', function(){
		$('.bootstrap-datetimepicker-widget').fadeOut();
	}).on('click', function(){
		$(this).focus();
	}).next().on('click', function(){
		$(this).prev().focus();
	});
}