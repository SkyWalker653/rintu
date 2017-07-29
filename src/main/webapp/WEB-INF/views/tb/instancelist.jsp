TB INSTANCE LIST


<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/RepositoryService.js'></script>

<table id="example" class="table table-bordered table-striped" cellspacing="0" width="100%">
      	<thead style="background-color: #BDBDBD">
      	</thead>
		<tbody>
		</tbody>
    </table>

<script>

/* function populateaccessmodal(datax){

	RepositoryService.getRepositoryAccessByRepositoryId(datax , function(p){
		$('#accessdatamodal').html(p);
	});
	
	
	
	
}
	

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
	            { "sTitle": "Repository ID" },
	            { "sTitle": "Repository Name" },
	            { "sTitle": "Project SAP Code" },
	            { "sTitle": "Project Name" },
	            { "sTitle": "Project Manager" },
	            { "sTitle": "Service Start Date" },
	            { "sTitle": "Service End Date" },
	            { "sTitle": "Server" },
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
	        	
	        	RepositoryService.getRepositoryList( sEcho, start,max, search, function(p){
					fnCallback(jQuery.parseJSON( p ));
				});
		      },
	    } );
	
});
	
 */
</script>






<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Repository Access</h4>
      </div>
      <div class="modal-body" id="accessdatamodal" align="left">
			No Data Found ...
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>

</div>