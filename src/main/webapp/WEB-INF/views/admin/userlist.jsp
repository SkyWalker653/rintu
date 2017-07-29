<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>
<script type='text/javascript' src='/toolsautomation/bootstrap/js/bootstrap-multiselect.js'></script>

<style type="text/css">
    .alignLeft { text-align: left; }
</style>

<table id="example" class="table table-bordered table-striped" cellspacing="0" width="100%">
      	<thead style="background-color: #BDBDBD">
      	</thead>
		<tbody>
		</tbody>
    </table>

<script>


	

$(document).ready(function() {
	
	
	var sEcho = 0
	var start = 0;
	var max = 0;
	var search = "";
	
	AdminService.getUserList( sEcho, start,max, search, function(p){
	
		    $('#example').DataTable( {
		    	"iDisplayLength": 15,
		        data: JSON.parse(p).aaData,
		        columns: [
					{ "sTitle": "Full Name" ,"sClass": "alignLeft" },
					{ "sTitle": "Login ID" ,"sClass": "alignLeft" },
					{ "sTitle": "Email ID" ,"sClass": "alignLeft" },
					{ "sTitle": "SAP Code" },
					{ "sTitle": "Phone Number" },
					{ "sTitle": "User Authentication" },
					{ "sTitle": "Created BY" },
					{ "sTitle": "Created ON" },
					{ "sTitle": "Status" },
					{ "sTitle": "Action" }
		        ]
		    } );

	
	});

	/* oTable = $('#example').dataTable( {
		 
		 	"bRetrieve": false,
	    	"bDestroy": true,
	    	"bAutoWidth": false,
	    	"bDeferRender": true,
	    	"bJQueryUI": false,
	    	"bLengthChange": false,
	    	"iDisplayLength": 10,
	    	"bSort": true,
	        "aoColumns": [
	            { "sTitle": "Full Name" ,"sClass": "alignLeft" },
	            { "sTitle": "Login ID" ,"sClass": "alignLeft" },
	            { "sTitle": "Email ID" ,"sClass": "alignLeft" },
	            { "sTitle": "SAP Code" },
	            { "sTitle": "Phone Number" },
	            { "sTitle": "User Authentication" },
	            { "sTitle": "Created BY" },
	            { "sTitle": "Created ON" },
	            { "sTitle": "Status" },
	            { "sTitle": "Action" }
	        ],
	        "bProcessing": true,
	        "bServerSide": true,
	        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
	        	var dataProps = getDataProps(aoData);
	        	var start = dataProps['iDisplayStart'];
	        	var max = dataProps['iDisplayLength'];
	        	var search = dataProps['sSearch'];
	        	var sEcho = dataProps['sEcho'];
	        	
	        	AdminService.getUserList( sEcho, start,max, search, function(p){
					fnCallback(jQuery.parseJSON( p ));
					
					$('select[name=roleselect]').multiselect({
				    	includeSelectAllOption: true,
				    	buttonWidth: '150px',
				    	maxHeight: 200,
				    	buttonClass: ''
				    });
					
					
				});
		      },
		      
	    } );
	 */
});
	

</script>


</div>