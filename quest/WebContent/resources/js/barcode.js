jQuery(function($){
	barcodeLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		barcodeLoad();
	}
})

function barcodeLoad(){
	$(".vcart-barcode").barcode(
		$(".vcart-barcode").attr("data-valor"),
		$(".vcart-barcode").attr("data-tipo"),
		{
			barWidth: $(".vcart-barcode").attr("data-largura"),
			barHeight: $(".vcart-barcode").attr("data-altura"),
			showHRI: $(".vcart-barcode").attr("data-ri")=="false"?false:true
		}
	);
}