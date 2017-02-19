jQuery(function($){
	dragdropLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		dragdropLoad();
	}
})

function dragdropLoad(){
	$('.vcart-draggable').sortable({
		cursor: "pointer",
		connectWith: '.novos-donos',
		opacity: 0.8,
		revert: true,
		forceHelperSize: true,
		placeholder: 'widget-placeholder',
		forcePlaceholderSize: true,
		tolerance: 'pointer',
		start: function(event, ui){
			ui.item.parent().css({'min-height': ui.item.height()})
			$("#origem").val(ui.item.parent().attr("id"));
		},
		update: function(event, ui) {
			ui.item.parent({'min-height':''})
		},
		receive: function(event, ui) {
			$("#adquirente").val(ui.item.attr("id"));
		},
		stop: function(event, ui){
			ui.item.parent().css({'min-height': ui.item.height()})
			$("#destino").val(ui.item.parent().attr("id"));
			$("#transacionar").click();
		},
	});
}