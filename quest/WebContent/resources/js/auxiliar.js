jQuery(function(){
	$("#home-bg").css('top', $(this).height() / 2 - $("#home-img").height() / 2);
	$("#home-bg").css('left', $(this).width() / 2 - $("#home-img").width() / 2);
});

function alerta(mensagem){
	bootbox.alert(mensagem);
//	bootbox.alert({
//	message: mensagem,
//	title: "Aviso"
//	})
}

function confirmaGravar(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaGravacaoRemoteCommand();
//				setTimeout(refresh,500);
			}else{
				cancelaGravacaoRemoteCommand();
//				setTimeout(refresh,500);
			}
		}
	});
}

function confirmaExcluir(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaExclusaoRemoteCommand();
//				setTimeout(refresh,500);
			}else{
				cancelaExclusaoRemoteCommand();
//				setTimeout(refresh,500);
			}
		}
	});
}

function excluir(){
	bootbox.confirm({
		message: "Deseja realmente excluir esse registro?",
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				excluirRemoteCommand();
				setTimeout(refresh,500);
			}
		}
	});
}

function refresh(){
	var url = $(location).attr('href').replace("#","");
	$(location).attr('href', url);
}

function grow(msg, style){
	var icon = style=="gritter-success"?"fa-check-circle":"fa-warning";
	jQuery.gritter.add({
		title: '',
		text: "<span style='font-size: 14px;'><i class='fa "+icon+"' style='margin-right: 5px;'></i>"+msg+"</span>",
		sticky: false,
		time: '2000',
		class_name: style + ' gritter-light'
	});
}


function confirmaExcluirParte(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaExclusaoParteRemoteCommand();
			}
		}
	});
}

function confirmaRetiradaOnus(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaRetiradaOnusRemoteCommand();
			}
		}
	});
}

function confirmaBaixaOnus(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaBaixaOnusRemoteCommand();
			}
		}
	});
}

function confirmaReativacaoOnus(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaReativacaoOnusRemoteCommand();
			}
		}
	});
}

function limparQualificacoes(){
	if($("#tipo-transacao-qualificacao").val() == null){
		limparTransacaoQualificacao();
	}
}

function confirmaExcluirDocumento(pergunta){
	bootbox.confirm({
		message: pergunta,
		buttons: {
			confirm: {
				label: "Sim",
			},
			cancel: {
				label: "Não",
			}
		},
		callback: function(result) {
			if(result){
				confirmaExclusaoDocumentoRemoteCommand();
			}
		}
	});
}

function checkboxUnico(id){
	var arr = id.split(':');
	var prefixo = arr[0];
	var checks = $("input[name*='"+prefixo+"']");
	checks.each(function(){
		$(this).prop("checked", $(this).attr('id') == id ? true:false);
	})
}

/*window.onkeydown = function(e){
	if(e.keyCode == 116){
		alert("Função não permitida");
		e.keyCode = 0;
		e.returnValue = false;
		return e.returnValue;
	}
}*/