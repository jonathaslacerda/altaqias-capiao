jQuery(function($){
	editorLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		editorLoad();
	}
})

function editorLoad(){
	$('.editor').ace_wysiwyg();
}