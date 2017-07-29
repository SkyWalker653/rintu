<script type='text/javascript' src='/toolsautomation/dwr/interface/RepositoryService.js'></script>	
<script type='text/javascript' src='/toolsautomation/dwr/interface/UserService.js'></script>
<script type="text/javascript">

	function getRepositoryInformation(){
		
		if($('#searchRepositoryName').val() == null || $('#searchRepositoryName').val() == ""){
			return;
		}
		
		RepositoryService.findRepositoryByName( $('#searchRepositoryName').val() , function(p){
			dwr.util.setValues(JSON.parse(p));
		} );
		
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
	
	
	function addUserToRepository(){
		
		var userSapCode = $('#usersapid').val();
		
		var str = '';
		str = str+'<tr id="'+userSapCode+'">';	
		str = str+'<td width="25%"><input value="'+$('#userloginid').val()+'" type="text" readonly="readonly" style="width: 95%; background-color: #dff0d8"></td>';	
		str = str+'<td width="25%"><input value="'+$('#usersapid').val()+'" type="text" readonly="readonly" name="selectedSapCode" style="width: 95%; background-color: #dff0d8"></td>';	
		str = str+'<td width="25%"><input value="'+$('#useremailid').val()+'" type="text" readonly="readonly" style="width: 95%; background-color: #dff0d8"></td>';
		str = str+'<td width="20%"><input value="'+$('#repoGroups').val()+'" type="hidden" name="selectedGroup"><input value="'+$('#repoGroups > option:selected').text()+'" type="text" readonly="readonly" style="width: 95%; background-color: #dff0d8"></td>';
		str = str+'<td width="5%" id="'+userSapCode+'_action"> <a href="#" style="color: red;"  onclick="removeFromUserList('+ $('#usersapid').val() +')"> <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
		str = str+'</tr>';
		
		$( str ).appendTo( "#userList" );
		
		$('#userloginid').val('');
		$('#usersapid').val('');
		$('#useremailid').val('');
		
	}
	
	function saveAccessRequest(){
		
		var accessRequests = new Array();
		var trCount = $('#userList > tbody > tr').length;
		for( var i = 0 ; i < trCount ; i++ ){
			
			var accessRequest = {
				    "userSapCode": $("input[name='selectedSapCode']")[i].value,
				    "repository": $("#repositoryId").val(),
				    "repoSitoryGroup": $("input[name='selectedGroup']")[i].value 
				}
			
			accessRequests.push( JSON.stringify( accessRequest ) );
		}
		

		RepositoryService.saveAccessRequest(accessRequests , function(){
			alert("Done");
		});
		
	}
	
</script>



<table style="width: 100%; margin-left: 20px;">
<tr>
<td width="40%" align="center">
<table  style="width: 90%; height: 400px;" class="table">
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;">
					<input type="text" class="form-control" placeholder="Repository Name" onblur="getRepositoryInformation()" style="width: 500px;" id="searchRepositoryName" name="searchRepositoryName" value="">
					<input type="hidden" id="repositoryId" name="repositoryId" value="">
				</td>			
			</tr>
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;Repository Name</label></td>			
				<td><span id="repositoryName"></span></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Project Code</label></td>			
				<td><span id="projectSapCode"></span></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Project Name</label></td>			
				<td><span id="projectName"></span></td>
			</tr>
			<tr>
				<td><label for="pmName">&nbsp;&nbsp;&nbsp;&nbsp;Project Manager</label></td>			
				<td><span id="projectManager"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Service Start Date</label></td>			
				<td><span id="serviceStartDate"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Service End Date</label></td>			
				<td><span id="serviceEndDate"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Project Spoc</label></td>			
				<td><span id="projectSpoc"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Alternate Approver</label></td>			
				<td><span id="alternameApprover"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Request Status</label></td>			
				<td><span id="status"></span></td>
			</tr>
						
			</table>

	
			
			
		</td>
		<td valign="top">
			
			<table border="0" style="width: 90%; margin-left: 20px;" >
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">User</label></td>			
			</tr>
			<tr>
				<td valign="top" align="center">
					
					<table style="width: 100%">
						<tr>
							<td align="center" width="25%">Login ID</td>
							<td align="center" width="25%">SAP Code</td>
							<td align="center" width="25%">Email ID</td>
							<td align="center" width="20%">Group</td>
							<td align="center" width="5%">Action</td>	
						</tr>
						<tr>	
							<td align="center"><input type="text" id="userloginid" style="width: 95%;" onblur="fetchUserByLoginId(this.value)"></td>
							<td align="center"><input type="text" id="usersapid" style="width: 95%;" onblur="fetchUserBySapCode(this.value)"></td>
							<td align="center"><input type="text" id="useremailid" style="width: 95%;" onblur="fetchUserDataByEmailID(this.value)"></td>
							<td align="center">
								<select style="width: 95%;" id="repoGroups">
									<option value="HCLT">HCLTech User</option>
									<option value="GEO">GEO User</option>
									<option value="HCLC">ISD User</option>
								</select>
							
							</td>
							<td align="center"> <a href="#" onclick="addUserToRepository()"> <span class="glyphicon glyphicon-plus"></span> </a> </td>
						</tr>
					</table>
					
					<hr>
					
					<table id="userList" style="width: 100%">
						
						
					</table>
				</td>
			</tr>
			
			
		</table>
		
		</td>
	</tr>
	
</table>

<br><button class="btn btn-primary" type="button" onclick="saveAccessRequest()" >Request</button>


