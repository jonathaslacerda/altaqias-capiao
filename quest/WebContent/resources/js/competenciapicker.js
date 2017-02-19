jQuery(function($){
	competenciaPicker();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		competenciaPicker();
	}
})

function competenciaPicker(){
	$('.competencia-picker').datetimepicker({
		format: 'MM/YYYY',
		locale: 'pt-br'
	}).on('click', function(){
		$(this).focus();
	}).next().on('click', function(){
		$(this).prev().focus();
	});
}