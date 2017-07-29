<div style="height:30px; width: 100%;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>


<button id="refreshbtn" class="btn btn-sm btn-primary" style="float: right;" onclick="refreshServerData()">Refresh Server Data</button>


<table id="example" class="table table-bordered table-striped" cellspacing="0" width="100%">
</table>

<script>


	

$(document).ready(function() {

	prepareTable();
	
});
	
	
function prepareTable(){
	

	$('#example_wrapper').html('');
	
	var sEcho = 0;
	var start = 0;
	var max = 0;
	var search = "";
	
	AdminService.getServerList( sEcho, start,max, search, function(p){
		
		    $('#example').DataTable( {
		    	"iDisplayLength": 15,
		        data: JSON.parse(p).aaData,
		        columns: [
		                  { "sTitle": "Current" },
		                  { "sTitle": "Name" },
		                  { "sTitle": "IP Address" },
		                  { "sTitle": "Port" },
		                  { "sTitle": "User ID" },
		                  { "sTitle": "Password" },
		                  { "sTitle": "Used Memory" },
		                  { "sTitle": "Status" },
		                  { "sTitle": "Action" }
		        ]
		    } );

	
	});
	
	
	
/* 	oTable = $('#example').dataTable( {
		 
	 	"bRetrieve": false,
    	"bDestroy": true,
    	"bAutoWidth": false,
    	"bDeferRender": true,
    	"bJQueryUI": false,
    	"bLengthChange": false,
    	"iDisplayLength": 10,
    	"bSort": false,
        "aoColumns": [
            { "sTitle": "Current" },
            { "sTitle": "Name" },
            { "sTitle": "IP Address" },
            { "sTitle": "Port" },
            { "sTitle": "User ID" },
            { "sTitle": "Password" },
            { "sTitle": "Used Memory" },
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
        	
        	AdminService.getServerList( sEcho, start,max, search, function(p){
				fnCallback(jQuery.parseJSON( p ));
			});
	      },
    } ); */

}
	
	
function refreshServerData(){
	
	AdminService.refreshServerData(function(){
		prepareTable();
	});
	
}


function markAsCurrent(serverId){
	
	
	AdminService.markAsCurrent( serverId , function(){
		
		prepareTable();
	});
}


</script>

</div>