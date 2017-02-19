jQuery(function($){
	$("#qualificacao").change(function(){
		if($(this).val() == null){
			limparTransacaoQualificacao();
		}
	});
	selectLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		selectLoad();
	}
});

function selectLoad(){
	$('.chosen-select').chosen({no_results_text: "Nenhum resultado encontrado",allow_single_deselect:true});

	$('.chosen-container').each(function(){
		$(this).css('width' , '100%');
		$(this).find('a:first-child').css('width' , '100%');
		$(this).find('.chosen-drop').css('width' , '100%');
		$(this).find('.chosen-search input').css('width' , '100%');
	});
}