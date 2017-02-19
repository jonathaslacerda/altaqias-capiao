jQuery(function($){
	textareaLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		textareaLoad();
	}
})

function textareaLoad(){
	$('textarea.limited').inputlimiter({
		remText: '%n caracteres%s restantes...',
		limitText: 'Máximo permitido: %n.',
	});	
}