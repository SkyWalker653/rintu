<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>

<table id="example" class="table table-bordered table-striped" cellspacing="0" width="100%">
      	<thead style="background-color: #BDBDBD">
      	</thead>
		<tbody>
		</tbody>
    </table>

<script>


	

$(document).ready(function() {

	oTable = $('#example').dataTable( {
		 
		 	"bRetrieve": false,
	    	"bDestroy": true,
	    	"bAutoWidth": false,
	    	"bDeferRender": true,
	    	"bJQueryUI": false,
	    	"bLengthChange": false,
	    	"iDisplayLength": 10,
	    	"bSort": false,
	        "aoColumns": [
	            { "sTitle": "Image" },
	            { "sTitle": "Name" },
	            { "sTitle": "Url" },
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
	        	
	        	AdminService.getDashboardList( sEcho, start,max, search, function(p){
					fnCallback(jQuery.parseJSON( p ));
				});
		      },
	    } );
	
});
	

</script>

</div>