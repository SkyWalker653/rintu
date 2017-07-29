<script type='text/javascript' src='/toolsautomation/dwr/interface/RepositoryService.js'></script>
<script type='text/javascript' src='/toolsautomation/dwr/interface/UserService.js'></script>
<script type="text/javascript">

function createNewRepository(){
	
	var repository = {
			
			"incidentNumber":null,
			"repositoryName":null,
			"projectSapCode":null,
			"projectName":null,
			"cmtoolUserCount":null,
			
	};
	
	dwr.util.getValues(repository);

	
	RepositoryService.createRepository(repository, $('#pmSAPCode').val() , $('#spocSAPCode').val() , $('#alternateApproverSapCode').val() , $('#serviceStartDate').val()   ,  $('#serviceEndDate').val()  , function(p){
		$('#id').val(p);
	});
	
		
}

function fetchUserBySapCode(u){
	UserService.getUserDataBySAPCode( u , function(p){
		$('#userloginid').val(p.loginId);
		$('#usersapid').val(p.sapCode);
		$('#useremailid').val(p.emailId);
	});
}

function fetchUserDataByEmailID(u){
	UserService.getUserDataByEmailID( u , function(p){
		$('#userloginid').val(p.loginId);
		$('#usersapid').val(p.sapCode);
		$('#useremailid').val(p.emailId);
	});
}

function fetchUserByLoginId(u){
	UserService.getUserDataByLoginID( u , function(p){
		$('#userloginid').val(p.loginId);
		$('#usersapid').val(p.sapCode);
		$('#useremailid').val(p.emailId);
	});
}


function fetchManager(u){
	UserService.getUserDataBySAPCode( u , function(p){
		$('#projectManager').val(p.fullName);
	});
}

function fetchSpoc(u){
	UserService.getUserDataBySAPCode( u , function(p){
		$('#spocName').val(p.fullName);
		$('#spocEmail').val(p.emailId);
	});
}

function fetchAlternateApprover(u){
	UserService.getUserDataBySAPCode( u , function(p){
		$('#alternateApproverName').val(p.fullName);
		$('#alternateApproverEmail').val(p.emailId);
	});
}

function getDateValue(v){
	var tempDate = v.split('-');
	return new Date(tempDate[0] ,tempDate[1] ,tempDate[2] );
}

function addUserToRepository(){
	
	var userSapCode = $('#usersapid').val();
	
	var str = '';
	str = str+'<tr id="'+userSapCode+'">';	
	str = str+'<td><input value="'+$('#userloginid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td><input value="'+$('#usersapid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td><input value="'+$('#useremailid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td id="'+userSapCode+'_action"> <img src="/toolsautomation/images/loader.gif"> </td>';	
	str = str+'</tr>';
	
	$( str ).appendTo( "#userList" );
	
	RepositoryService.addUserToRepository( $('#id').val(), userSapCode ,   function(p){
		$('#'+userSapCode+'_action').html('<a href="#" style="color: red;"  onclick="removeFromUserList('+p+')"> <span class="glyphicon glyphicon-minus"></span> </a>');
	
		var str = '';
		str = str+'<tr id="'+userSapCode+'">';
		str = str+'<td><input value="'+$('#userloginid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+$('#usersapid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+$('#useremailid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td id="'+userSapCode+'_action"> <input type="checkbox" id="user_'+userSapCode+'" name="allusercheckbox" value="'+userSapCode+'" > </td>';	
		str = str+'</tr>';
		
		$( str ).appendTo( "#userListAssignationModal" );
	
	});
	
	
	$('#userloginid').val('');
	$('#usersapid').val('');
	$('#useremailid').val('');
	
}

function populateDefinationTable(){
	
	populateRepoUserData( $('#id').val() );
	populateRepoGroupData( $('#id').val() );
	populateRepoFolderData( $('#id').val() );
	
}

function populateRepoUserData(rid){
	
	$("#userList").html(''); 
	
	RepositoryService.getUserListByRepository( rid , function(p){
		
		 $.each(p, function(k, v) {
		       
			 var str = '';
				str = str+'<tr id="'+v.sapCode+'">';	
				str = str+'<td><input value="'+v.loginId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str = str+'<td><input value="'+v.sapCode+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str = str+'<td><input value="'+v.emailId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str = str+'<td id="'+v.sapCode+'_action"> <a href="#" style="color: red;"  onclick="removeFromUserList('+v.id+')"> <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
				str = str+'</tr>';
				
				$( str ).appendTo( "#userList" );
			
				
		 });
		 
	});
	
}

function addGroupToRepository(){
	
	var gName = $('#repositoryName').val()+'-'+$('#groupNameTemp').val();
	
	var str = '';
	str = str+'<tr id="'+gName+'">';	
	str = str+'<td style="width: 90%;"><div name="groupNames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+gName+'</div></td>';	
	str = str+'<td id="'+gName+'_action"> <img src="/toolsautomation/images/loader.gif"> </td>';	
	str = str+'</tr>';
		
	$( str ).appendTo( "#accessgroupname" );
	$('#groupNameTemp').val('');
	
	RepositoryService.addGroupToRepository( $('#id').val(), gName ,   function(p){
		$('#'+gName+'_action').html('<a href="#" style="color: red;" onclick="removeFromGroupList(\''+p+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	});
	
	
}

function populateRepoGroupData(rid){
	
	$("#accessgroupname").html(''); 
	
	RepositoryService.getGroupListByRepository( rid , function(p){
	
		$.each(p, function(k, v) {
		      
		 var str = '';
			str = str+'<tr id="'+v.groupName+'">';	
			str = str+'<td style="width: 90%;"><div name="groupNames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+v.groupName+'</div></td>';	
			str = str+'<td id="'+v+'_action"> <a href="#" style="color: red;" onclick="removeFromGroupList(\''+v.id+'\')" > <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
			str = str+'</tr>';
			$( str ).appendTo( "#accessgroupname" );
			
		});
	});
}


function saveNewFolderName(){
	
	var fName = $('#folderNameTemp').val();
	
	var fNameAsId = fName.replace(/\//g, "");
	
	var str = '';
	str = str+'<tr id="'+fNameAsId+'">';	
	str = str+'<td style="width: 90%;"><div name="foldernames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+fName+'</div></td>';	
	str = str+'<td id="'+fNameAsId+'_action"> <img src="/toolsautomation/images/loader.gif""> </td>';	
	str = str+'</tr>';
		
	$( str ).appendTo( "#foldername" );

	$('#folderNameTemp').val('');
	
	RepositoryService.addFolderToRepository( $('#id').val(), fName ,   function(p){
		$('#'+fNameAsId+'_action').html('<a href="#" style="color: red;" onclick="removeFromFolderList(\''+p+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	});
	
}

function populateRepoFolderData(rid){
	
	$("#foldername").html(''); 
	
	RepositoryService.getFolderListByRepository( rid , function(p){

		 $.each(p, function(k, v) {
			 
			 var fNameAsId = v.folderPath.replace(/\//g, "");
		       
			 var str = '';
				str = str+'<tr id="'+fNameAsId+'">';	
				str = str+'<td style="width: 90%;"><div name="foldernames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+v.folderPath+'</div></td>';	
				str = str+'<td id="'+fNameAsId+'_action"> <a href="#" style="color: red;" onclick="removeFromFolderList(\''+v.id+'\')" > <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
				str = str+'</tr>';
				$( str ).appendTo( "#foldername" );
				
		 });
	});
}

function getDataForAssignationTab(){
	
	RepositoryService.getDataForAssignationTab( $('#id').val() , function(p){
		
		$('#assignationTabAccordion').html(p);
		
	});
	
	$("#userListAssignationModal").html(''); 
	RepositoryService.getUserListByRepository( $('#id').val() , function(p){
		
		 $.each(p, function(k, v) {
				
			var str2 = '';
				str2 = str2+'<tr id="'+v.sapCode+'">';	
				str2 = str2+'<td><input value="'+v.loginId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str2 = str2+'<td><input value="'+v.sapCode+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str2 = str2+'<td><input value="'+v.emailId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
				str2 = str2+'<td id="'+v.sapCode+'_action"> <input type="checkbox" id="user_'+v.sapCode+'" name="allusercheckbox" value="'+v.sapCode+'" > </td>';	
				str2 = str2+'</tr>';
				
				$( str2 ).appendTo( "#userListAssignationModal" );
				
				
		 });
		 
	});
	
	
	
}


function getReadyToAddUser(groupId , groupName){
	$('#selectedGroupId').val(groupId);
	$('#completeUserListLabel').html(groupName);
	
	$('input[name=allusercheckbox]').each(function(){
		this.checked=false;
	});	
	
	$('td[name=userMapTableSAPCode'+groupId+']').each(function(){
		$('#user_'+$(this).html()).prop('checked', true);
	});
	
	
	
}  


function addUsersToGroup(){
	
	var userListCheck = new Array();
	$('input[name=allusercheckbox]:checked').each(function(){
		userListCheck.push(this.value);
	});	
	
	
	
	RepositoryService.addUsersToGroup( $('#id').val() , $('#selectedGroupId').val() , userListCheck  , function(p){
		
		$('#usermapwithgrouptable'+$('#selectedGroupId').val()).html(p);
		$('#completeUserList').modal('hide');
		
	});
	
}


function updateGroupFolderAccess(grpid , fldid , access){
	
	RepositoryService.updateGroupFolderAccess( grpid , fldid , access , function(p){
		
		
		
	});

}

function populatConfirmationTab(){
	
	RepositoryService.getDataToPopulatConfirmationTab( $('#id').val() ,  function(p){

			var result = JSON.parse(p);
			
			dwr.util.setValues(result.GENERALINFO);
			$('#confirmUserTable').html(result.USERLIST);
			$('#confirmAccessGroup').html(result.GROUPLIST);
			$('#confirmGroupUser').html(result.GROUPUSERMAP);
			$('#confirmFolderAccess').html(result.ACCESS);
			
			
		
	});
}


function removeFromUserList(userId){

	RepositoryService.removeFromUserRepositoryMapping( $('#id').val() , userId ,  function(p){
		populateRepoUserData( $('#id').val() );
	});
	
}

function removeFromGroupList(groupId){

	RepositoryService.removeGroupRepositoryMapping( $('#id').val() , groupId ,  function(p){
		populateRepoGroupData( $('#id').val() );
	});
	
}

function removeFromFolderList(folderId){

	RepositoryService.removeFolderRepositoryMapping( $('#id').val() , folderId ,  function(p){
		populateRepoFolderData( $('#id').val() );
	});
	
}



</script>

<div>

  <!-- Nav tabs -->
  <ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="active"><a onclick="populateRepoInfoData()" href="#SARTab" aria-controls="SARTab" role="tab" data-toggle="tab" id="switchSar">
    	<span class="badge" style="position: relative; float: left;">Step-1</span>&nbsp;&nbsp;INFORMATION</a>
    </li>
    <li role="presentation"><a onclick="populateDefinationTable()" href="#defineTab" aria-controls="defineTab" role="tab" data-toggle="tab" id="switchDefination">
    	<span class="badge" style="position: relative; float: left;">Step-2</span>&nbsp;&nbsp;DEFINATION</a>
    </li>
    <li role="presentation"><a onclick="getDataForAssignationTab()" href="#assignationTab" aria-controls="assignationTab" role="tab" data-toggle="tab" id="switchAssignation">
    	<span class="badge" style="position: relative; float: left;">Step-3</span>&nbsp;&nbsp;ASSIGNATION</a>
    </li>
    <li role="presentation"><a onclick="populatConfirmationTab()" href="#confirmationTab" aria-controls="confirmationTab" role="tab" data-toggle="tab" id="switchConfirmation">
    	<span class="badge" style="position: relative; float: left;">Step-4</span>&nbsp;&nbsp;CONFIRMATION</a>
    </li>
  </ul>

  <!-- Tab panes -->
  <div class="tab-content">
    <div role="tabpanel" class="tab-pane active" id="SARTab" align="center">


	<table border="1" style="width: 80%;">
	
		<tr class="active">
			<td colspan="2" style="height: 52px;" valign="middle" >
				
				<input type="text" class="form-control" placeholder="REMEDY ID" id="incidentNumber" name="incidentNumber" style="display:inline; float:left; width: 250px; margin-left: 20px;">
					
				<span style="margin-left: 10%;">Please provide the following project details to create new repository</span>
				
			</td>
		</tr>
	
	<tr>
		<td width="50%" valign="top">


		<table border="0" style="width: 90%; height: 400px; margin-left: 20px;" >
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Information</label></td>			
			</tr>
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;Project Code</label></td>			
				<td><input type="text" class="form-control" id="projectSapCode" ></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Project Name</label></td>			
				<td><input type="text" class="form-control" id="projectName" ></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Manager SAP Code</label></td>			
				<td><input type="text" class="form-control" id="pmSAPCode" onblur="fetchManager(this.value)"></td>
			</tr>
			<tr>
				<td><label for="pmName">&nbsp;&nbsp;&nbsp;&nbsp;Manager Name</label></td>			
				<td><input type="text" class="form-control" id="projectManager" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Repository Name</label></td>			
				<td><input type="text" class="form-control" id="repositoryName" ></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;No of CM tool users</label></td>			
				<td><input type="text" class="form-control" id="cmtoolUserCount" value="0" ></td>
			</tr>
			<tr>
				<td><label for="serviceStartDate">&nbsp;&nbsp;&nbsp;&nbsp;Service Start Date <br> &nbsp;&nbsp;&nbsp;&nbsp;[ YYYY-MM-DD ]</label></td>			
				<td>
				
					<div class='input-group date' id='datetimepicker1'>
                    	<input type="text" class="form-control" id="serviceStartDate" >
                    	<span class="input-group-addon">
                       		<span class="glyphicon glyphicon-calendar"></span>
                    	</span>
                	
				
				</div>
				</td>
				
			</tr>
			<tr>
				<td><label for="serviceEndDate">&nbsp;&nbsp;&nbsp;&nbsp;Service End Date <br> &nbsp;&nbsp;&nbsp;&nbsp;[ YYYY-MM-DD ] </label></td>			
				<td>
					<div class='input-group date' id='datetimepicker2'>
                    	<input type="text" class="form-control" id="serviceEndDate" >
                    	<span class="input-group-addon">
                       		<span class="glyphicon glyphicon-calendar"></span>
                    	</span>
                	</div>
				</td>
			</tr>
			
			</table>
			
			<script type="text/javascript">
	            $(function () {
	                $('#datetimepicker1').datetimepicker({pickTime: false, format:'YYYY-MM-DD'});
	                $('#datetimepicker2').datetimepicker({pickTime: false, format:'YYYY-MM-DD'});
	            });
        	</script>
			
		</td>
		<td width="50%" valign="top">
			
			<table border="0" style="width: 90%;height: 400px; margin-left: 20px;" >
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Spoc for SVN</label></td>			
			</tr>
			<tr>
				<td><label for="spocSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;SAP Code</label></td>			
				<td><input type="text" class="form-control" id="spocSAPCode" onblur="fetchSpoc(this.value)"></td>
			</tr>
			<tr>
				<td width="47%"><label for="spocName">&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;Name</label></td>			
				<td><input type="text" class="form-control" id="spocName" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Mail Id</label></td>			
				<td><input type="text" class="form-control" id="spocEmail" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Alternate Approver</label></td>			
			</tr>
			<tr>
				<td><label for="spocSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;SAP Code</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverSapCode" onblur="fetchAlternateApprover(this.value)"></td>
			</tr>
			<tr>
				<td width="47%"><label for="spocName">&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;Name</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverName" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Mail Id</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverEmail" readonly="readonly"></td>
			</tr>
			
			
			
			
			
		</table>
		
		<script type="text/javascript">
			function changeCMTassSourceValue(f){
				$('#cmtassSource7').val('Other - '+f.value);
			}
		</script>

</td>
</tr>
</table>

<br><button class="btn btn-primary" type="button" onclick="createNewRepository()">NEXT</button>

</div>



 <div role="tabpanel" class="tab-pane" id="defineTab" align="center">
		<table border="1" style="width: 90%; height: 500px;" >
	
			<tr>
				<td colspan="3" style="height: 52px;">
					<div class="alert alert-info" role="alert" style="margin-bottom:0px;" align="center">User Access</div>
				</td>
			</tr>
			<tr>
				
				<td valign="top" align="center" style="width: 40%;"> 
					<div class="alert alert-info" role="alert" style="margin-bottom:0px; border-bottom: 1px; border-bottom-color: #bce8f1" align="center">
						User List 
					</div> 
					<table>
						<tr><td align="center">Login ID</td><td align="center">SAP Code</td><td align="center">Email ID</td></tr>
						<tr>	
							<td><input type="text" id="userloginid" onblur="fetchUserByLoginId(this.value)"></td>
							<td><input type="text" id="usersapid" onblur="fetchUserBySapCode(this.value)"></td>
							<td><input type="text" id="useremailid" onblur="fetchUserDataByEmailID(this.value)"></td>
							<td> <a href="#" onclick="addUserToRepository()"> <span class="glyphicon glyphicon-plus"></span> </a> </td>
						</tr>
					</table>
					
					<hr>
					
					<table id="userList">
						
						
					</table>
				
				</td>
				<td align="center"  valign="top" style="width: 30%;"> 
					<div class="alert alert-info" role="alert" style="margin-bottom:0px; border-bottom: 1px; border-bottom-color: #bce8f1" align="center">
						Access Group Name <button type="button" class="btn btn-sm btn-success" style="float: right" onclick="defaultGroups()">Default</button>
					</div>  
					<table style="width: 100%;">
						<tr>	
							<td style="width: 90%;" align="center">
								<div class="input-group">
									 <span class="input-group-addon" id="basic-addon2">Repo name - </span>
								  	 <input type="text" style="width: 100%;" id="groupNameTemp" placeholder="Group Name" >
								 
								</div>	
								
							</td>
							<td align="center"> <a href="#" onclick="addGroupToRepository()"> <span class="glyphicon glyphicon-plus"></span> </a> </td>
						</tr>
					</table>
					<hr>
					
					<table style="width: 100%;" id="accessgroupname">
						
					</table>
					
					</td >
					<td  align="center"  valign="top" style="width: 30%;">
				
					<div class="alert alert-info" role="alert" style="margin-bottom:0px; border-bottom: 1px; border-bottom-color: #bce8f1" align="center">
					 	Top Level Folders <button type="button" class="btn btn-sm btn-success" style="float: right" onclick="chooseDefaultTopFolders()">Default</button>
					 </div>  
					
					<table style="width: 100%;">
						<tr>	
							<td style="width: 90%;" align="center"><input type="text" style="width: 100%;" id="folderNameTemp"></td>
							<td align="center"> <a href="#" onclick="saveNewFolderName()"> <span class="glyphicon glyphicon-plus"></span> </a> </td>
						</tr>
					</table>
					<hr>
					
					<table style="width: 100%;" id="foldername">
						
					</table>
					
					
				</td>
				
				
			</tr>
			
		</table>

		<!-- <button class="btn btn-danger" type="button" >Cancel</button>
		
		<button class="btn btn-info" type="button" onclick="switchTabs('SARTab')"> << Prev </button>
		<button class="btn btn-info" type="button" onclick="switchTabs('assignationTab')">Next >> </button> -->

	</div>
    <div role="tabpanel" class="tab-pane" id="assignationTab"  align="center">
    	
    	<div class="alert alert-info" role="alert" style="margin-bottom:0px;" align="center">User Access</div>
    	
    	<div class="alert" role="alert" style="margin-bottom:0px; border-bottom: 1px; border-bottom-color: #bce8f1" align="center"> 
			
			
<div class="panel-group" id="assignationTabAccordion" role="tablist" aria-multiselectable="true" align="left">

  
  
</div>
 
    
    	<!-- <button class="btn btn-danger" type="button">Cancel</button>
		
		<button class="btn btn-info" type="button" onclick="switchTabs('assignationTab')"> << Prev </button>
		<button class="btn btn-info" type="button">Confirm </button> -->
    
    
    </div>
    
  </div>

 	<div role="tabpanel" class="tab-pane" id="confirmationTab" align="center">
     
     <br>
     
    <table border="1" style="width: 90%; height:200px; border: 3px;border-color: gray;border-style: groove;">
    	<tr>
    		<td valign="top" align="left">
    			<table border="0" style="width: 100%; height:200px; margin-left: 10px;">
				    <tr><td style="width: 30%;">Project SAP Code :</td><td>  <span id="confirmProjectSAPCode"></span>  </td></tr>
				    <tr><td>Project Name :</td><td> <span id="confirmProjectName"></span> </td></tr>
    				<tr><td>PM's SAP Code :</td><td> <span id="confirmPmSAPCode"></span>  </td></tr>
    				<tr><td>PM's Name :</td><td> <span id="confirmPmName"></span>  </td></tr>
    				
    				<tr><td>Alternate Approver SAP Code :</td><td> <span id="confirmAltApproverSAPCode"></span> </td></tr>
    				<tr><td>Alternate Approver Name :</td><td> <span id="confirmAltApproverName"></span>  </td></tr>
    				<tr><td>Alternate Approver Email :</td><td> <span id="confirmAltApproverEmail"></span>  </td></tr>
    			</table>
    		</td>
    		<td valign="top" align="left">
    
   				<table border="0" style="width: 100%; height:200px;  margin-left: 10px;">
				    <tr><td style="width: 30%;">Repository Name :</td><td> <span id="confirmRepositoryName"></span> </td></tr>
				    <tr><td>No of CM tool users :</td><td> <span id="confirmCMUserCount"></span>   </td></tr>
    				<tr><td>Service Start Date :</td><td> <span id="confirmServiceStartDate"></span>   </td></tr>
    				<tr><td>Service End Date :</td><td> <span id="confirmServiceEndDate"></span>   </td></tr>
    				
    				<tr><td>SPOC's SAP Code :</td><td> <span id="confirmSpocSAPCode"></span> </td></tr>
    				<tr><td>SPOC's Name :</td><td> <span id="confirmSpocName"></span>  </td></tr>
    				<tr><td>SPOC's Email :</td><td> <span id="confirmSpocEmail"></span>  </td></tr>
    			</table> 
    		</td>
    		
    	</tr>
    </table> 
    
    <hr>
    
     <table border="1" style="width: 90%; border: 3px;border-color: gray;border-style: groove;">
    	<tr>
    		<td valign="top" align="left" width="25%">
    			<div style="border-bottom: 2px; border-bottom-style: solid; border-bottom-color: gray; text-align: center;" >User Access List</div>
    			<table id="confirmUserTable" border="1" style="width: 100%;">
    			</table>
    		
    		</td>
    		<td valign="top" align="left" width="25%">
    			<div style="border-bottom: 2px; border-bottom-style: solid; border-bottom-color: gray; text-align: center;" >Access Group Name</div>
    			<table id="confirmAccessGroup" border="1" style="width: 100%;">
    			</table>
    		
    		
    		</td>
    		<td valign="top" align="left" width="25%">
    			<div style="border-bottom: 2px; border-bottom-style: solid; border-bottom-color: gray; text-align: center;" >Group - User Assignment</div>
    			<table id="confirmGroupUser" border="1" style="width: 100%;">
    			</table>
    		
    		</td>
    		<td valign="top" align="left" width="25%">
    			<div style="border-bottom: 2px; border-bottom-style: solid; border-bottom-color: gray; text-align: center;" >Folder Access Assignment</div>
    			<table id="confirmFolderAccess" border="1" style="width: 100%;">
    			</table>
    		
    		</td>
     	</tr>
    	</table>
    </div>


</div>


<div class="modal fade" id="completeUserList" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="completeUserListLabel">Modal title</h4>
      </div>
      <div class="modal-body">
        <table id="userListAssignationModal">
		</table>
		
		<input type="hidden" id="selectedGroupId" value=0>
		
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <button type="button" class="btn btn-primary" onclick="addUsersToGroup()" >Add Users</button>
      </div>
    </div>
  </div>
</div>



</div>