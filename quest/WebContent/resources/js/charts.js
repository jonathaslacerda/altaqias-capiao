jQuery(function($){
	easyPieChartLoad();
});

jsf.ajax.addOnEvent(function(data){
	if (data.status == "success"){
		easyPieChartLoad();
	}
})

function easyPieChartLoad(){
	var size = parseInt($(this).data('size')) || 30;
	$('.easy-pie-chart').each(function(){
		   $(this).easyPieChart({
		       barColor: function(percent) {
		    	   return percent>=50?'#68BC31':(percent<=20?'#DA5430':'#FEE074');
		       },
		       trackColor: '#BBBBBB',
		       scaleColor: false,
		       lineCap: 'butt',
		       lineWidth: parseInt(size/10),
		       animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,//don't animate for IE8 (too slow)
		   });
		});
}