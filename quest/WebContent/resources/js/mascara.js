jQuery(function($){
	mascaraLoad();
});

jsf.ajax.addOnEvent(function(data){
//	if (data.status == "success"){
	mascaraLoad();
//	}
})

function mascaraLoad(){
	$('.mask-telefone').focusout(function(){
	    var phone, element;
	    element = $(this);
	    element.unmask();
	    phone = element.val().replace(/\D/g, '');
	    if(phone.length > 10) {
	        element.mask("(99) 99999-999?9",{placeholder:" "});
	    } else {
	        element.mask("(99) 9999-9999?9",{placeholder:" "});
	    }
	}).trigger('focusout');

	$(".mask-passaporte").mask("9999999999",{placeholder:" "});
	$(".mask-rne").mask("9999999999",{placeholder:" "});
	$(".mask-rg").mask("9999999",{placeholder:" "});
	$(".mask-numero").mask("9?9999999999999999",{placeholder:""});
	$(".mask-cpf").mask("999.999.999-99",{placeholder:" "});
	$(".mask-cnpj").mask("99.999.999/9999-99",{placeholder:" "});
	$(".mask-cep").mask("99999-999",{placeholder:" "});

	$(".mask-decimal").priceFormat({
		centsSeparator:',', 
		thousandsSeparator:'.',
		prefix:'',
		limit:11
	});
	$(".mask-area").priceFormat({
		centsSeparator:',', 
		thousandsSeparator:'.',
		prefix:'',
		suffix:' m²',
		limit:11,
		centsLimit:3
	});
	$(".mask-percentual").priceFormat({
		centsSeparator:',', 
		thousandsSeparator:'.',
		prefix:'',
		suffix:' %',
		limit:5,
		centsLimit:2
	});
	$(".mask-dinheiro").priceFormat({
		centsSeparator:',', 
		thousandsSeparator:'.',
		prefix:'R$ '
	});
	$(".uppercase").keyup(function() {
        this.value = this.value.toLocaleUpperCase();
    });
}