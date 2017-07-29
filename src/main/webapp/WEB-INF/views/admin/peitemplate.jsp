<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>
<script type='text/javascript' src='/toolsautomation/bootstrap/js/bootstrap-multiselect.js'></script>

<script type="text/javascript">

$(window).load(function() {

	AdminService.getAllPEITemplate( function(p){
		
		alert( p[0].peiTemplates[0].parameter );
		
	});
	
});


</script>


<table>

</div>