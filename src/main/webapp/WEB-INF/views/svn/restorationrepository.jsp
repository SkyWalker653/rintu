<script type='text/javascript' src='/toolsautomation/dwr/interface/RepositoryService.js'></script>	
<script type='text/javascript' src='/toolsautomation/dwr/interface/UserService.js'></script>
<script type='text/javascript' src='/toolsautomation/dwr/interface/FileUploadService.js'></script>

<script type="text/javascript">

	function getRepositoryInformation(){
		
		if($('#searchRepositoryName').val() == null || $('#searchRepositoryName').val() == ""){
			return;
		}
		
		RepositoryService.findRepositoryByName( $('#searchRepositoryName').val() , function(p){
			dwr.util.setValues(JSON.parse(p));
		} );
		
	}

	function fileUpload(){
		
		var file = dwr.util.getValue("folderToUpload");
		
		var x = document.getElementById("folderToUpload");
		
		alert(x.files[0]);
		
		
		/* alert( $('#folderToUpload').files );
		
		for (var i = 0 ; i < $('#folderToUpload').files.length;  i++) {
		     alert($('#folderToUpload').files[i].name);
		}
		 */
		 
		try{ 
		 
		FileUploadService.uploadFile( x.files[0], function(){
			alert("Done");
		});
		
		}catch (e){
			alert(e);
		}
		
	}
	
	
</script>

NEW RESTORATION REQUEST

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
				<td width="47%"><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Repository Name</label></td>			
				<td><span id="repositoryName"></span></td>
			</tr>
			<tr>
				<td><label for="projectSapCode">&nbsp;&nbsp;&nbsp;&nbsp;Project Code</label></td>			
				<td><span id="projectSapCode"></span></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Project Name</label></td>			
				<td><span id="projectName"></span></td>
			</tr>
			<tr>
				<td><label for="projectManager">&nbsp;&nbsp;&nbsp;&nbsp;Project Manager</label></td>			
				<td><span id="projectManager"></span></td>
			</tr>
			<tr>
				<td><label for="serviceStartDate">&nbsp;&nbsp;&nbsp;&nbsp;Service Start Date</label></td>			
				<td><span id="serviceStartDate"></span></td>
			</tr>
			<tr>
				<td><label for="serviceEndDate">&nbsp;&nbsp;&nbsp;&nbsp;Service End Date</label></td>			
				<td><span id="serviceEndDate"></span></td>
			</tr>
			<tr>
				<td><label for="projectSpoc">&nbsp;&nbsp;&nbsp;&nbsp;Project Spoc</label></td>			
				<td><span id="projectSpoc"></span></td>
			</tr>
			<tr>
				<td><label for="alternameApprover">&nbsp;&nbsp;&nbsp;&nbsp;Alternate Approver</label></td>			
				<td><span id="alternameApprover"></span></td>
			</tr>
			<tr>
				<td><label for="status">&nbsp;&nbsp;&nbsp;&nbsp;Request Status</label></td>			
				<td><span id="status"></span></td>
			</tr>
						
			</table>

	
			
			
		</td>
		<td valign="top">
			
			Restoration Request
			
			
			
			<form method="POST" action="<%= request.getContextPath() %>/svn/uploadMultipleFile" enctype="multipart/form-data">
				
				File1 to upload: <input type="file" name="file">
			
				Name1: <input type="text" name="name">
			
			
				File2 to upload: <input type="file" name="file">
			
				Name2: <input type="text" name="name">
			
			
				<input type="submit" value="Upload"> Press here to upload the file!
			</form>
			
			
			<hr>
			
			<form method="POST" action="<%= request.getContextPath() %>/svn/uploadMultipleFile" enctype="multipart/form-data">
			
			   <input type="text" name="incidentNumber">
			
			   <input id="folderToUpload" name="file" type="file" multiple="true" directory="" webkitdirectory="" mozdirectory=""/>
			   
			   
			   <button type="submit">Upload</button>
			   
			</form>
		
		</td>
	</tr>

</table>

<br><button class="btn btn-primary" type="button" onclick="saveAccessRequest()">Request</button>


