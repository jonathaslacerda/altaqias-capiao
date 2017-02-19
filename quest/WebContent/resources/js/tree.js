jQuery(function($){
	treeLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		treeLoad();
	}
})

function treeLoad(){
	$('#tree').ace_tree();
}

