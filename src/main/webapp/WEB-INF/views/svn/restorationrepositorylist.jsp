<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/RepositoryService.js'></script>

Restoration List


<a href="/toolsautomation/svn/restoration/repository" class="btn btn-primary btn-sm" style="float: right;">New Request</a>


<table id="example" class="table table-bordered table-striped" cellspacing="0" width="100%">
      	<thead>
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
	
	RepositoryService.getRepositoryRestorationList( sEcho, start,max, search, function(p){
	
		    $('#example').DataTable( {
		    	"iDisplayLength": 15,
		        data: JSON.parse(p).aaData,
		        columns: [
			        { "sTitle": "Incident Number" },
		            { "sTitle": "User ID" },
		            { "sTitle": "Source" },
		            { "sTitle": "URL" },
		            { "sTitle": "Created On" },
		            { "sTitle": "Removed On" },
		            { "sTitle": "Project Name" },
		            { "sTitle": "PM Name" },
		            { "sTitle": "Project SPOC" },
		            { "sTitle": "Status" },
		            { "sTitle": "Approved By" },
		            { "sTitle": "Approved On" }
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
	    	"bSort": false,
	        "aoColumns": [
	            { "sTitle": "Incident Number" },
	            { "sTitle": "User ID" },
	            { "sTitle": "Source" },
	            { "sTitle": "URL" },
	            { "sTitle": "Created On" },
	            { "sTitle": "Removed On" },
	            { "sTitle": "Project Name" },
	            { "sTitle": "PM Name" },
	            { "sTitle": "Project SPOC" },
	            { "sTitle": "Status" },
	            { "sTitle": "Approved By" },
	            { "sTitle": "Approved On" }
	        ],
	        "bProcessing": true,
	        "bServerSide": true,
	        "fnServerData": function ( sSource, aoData, fnCallback, oSettings ) {
	        	var dataProps = getDataProps(aoData);
	        	var start = dataProps['iDisplayStart'];
	        	var max = dataProps['iDisplayLength'];
	        	var search = dataProps['sSearch'];
	        	var sEcho = dataProps['sEcho'];
	        	
	        	RepositoryService.getRepositoryRestorationList( sEcho, start,max, search, function(p){
					fnCallback(jQuery.parseJSON( p ));
				});
		      },
	    } ); */
	
});
	

</script>



</div>