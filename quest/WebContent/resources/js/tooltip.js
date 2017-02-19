jQuery(function($){
	tooltipLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		tooltipLoad();
	}
})

function tooltipLoad(){
	$('.tooltip-link').tooltip({
		'container': 'body',
		'template': '<div class="tooltip tooltip-info"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
	});
	
	var tipo = $('.tooltip-dynamic').data("tipo")!==undefined?$('.tooltip-dynamic').data("tipo"):'info';
	$('.tooltip-dynamic').tooltip({
		'container': 'body',
		'template': '<div class="tooltip tooltip-'+tipo+'"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
	});
}