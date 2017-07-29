<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>
<script type='text/javascript' src='/toolsautomation/dwr/interface/UserService.js'></script>
<script type='text/javascript' src='/toolsautomation/bootstrap/js/bootstrap-multiselect.js'></script>

<script type="text/javascript">


$( window ).load(function() {
	
	UserService.getUserDataByID( escape('<%=request.getParameter("id")%>') , function(p){
		dwr.util.setValues(p);
	});
	
	populateRoles();
	
});


function populateRoles(){
	
	AdminService.getRoleListByUserId(  escape('<%=request.getParameter("id")%>') , function(p){
		dwr.util.removeAllOptions("userRoles" );
		dwr.util.addOptions("userRoles" , p , "id" , "roleName" );
	});
	
	AdminService.findOtherRolesByUserId(  escape('<%=request.getParameter("id")%>') , function(p){
		dwr.util.removeAllOptions("allRoles" );
		dwr.util.addOptions("allRoles" , p , "id" , "roleName" );
	});
	
}

function addRoles(){
	
	var selectedVales = dwr.util.getValue("allRoles") ;
	
	AdminService.addUserRoleMap(  escape('<%=request.getParameter("id")%>') ,  selectedVales , function(){
		populateRoles();
	} );
	
}

function removeRoles(){
	
	var selectedVales = dwr.util.getValue("userRoles") ;
	
	AdminService.removeUserRoleMap(  escape('<%=request.getParameter("id")%>') ,  selectedVales , function(){
		populateRoles();
	} );
	
}

function saveUserAuthentication(userAuthenticationValue){
	UserService.saveUserAuthentication( escape('<%=request.getParameter("id")%>') , userAuthenticationValue );
}


</script>

<table style="width: 100%;">
<tr>
<td width="40%" align="center">
<table  style="width: 90%; height: 400px;" class="table">
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">User Information</label>  <button class='btn btn-sm btn-primary' style="float: right;">Refresh User Data</button>   </td>			
			</tr>
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;Full Name</label></td>			
				<td><span id="fullName"></span></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Login ID</label></td>			
				<td><span id="loginId"></span></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Sap Code</label></td>			
				<td><span id="sapCode"></span></td>
			</tr>
			<tr>
				<td><label for="pmName">&nbsp;&nbsp;&nbsp;&nbsp;Email ID</label></td>			
				<td><span id="emailId"></span></td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Phone Number</label></td>			
				<td><span id="phoneNumber"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;User Authentication</label></td>			
				<td>
					<select id="userAuthentication" style="width: 90%" onchange="saveUserAuthentication(this.value)">
						<option value="LDAP">LDAP</option>
						<option value="DB">DB</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Project Code</label></td>			
				<td><span id="projectCode"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Project Name</label></td>			
				<td><span id="projectName"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Designation</label></td>			
				<td><span id="designation"></span></td>
			</tr>
			<tr>
				<td><label for="repoUserCount">&nbsp;&nbsp;&nbsp;&nbsp;Status</label></td>			
				<td><span id="status"></span></td>
			</tr>
						
			</table>

	
			
			
		</td>
		<td valign="top">
			
			<table border="0" style="width: 90%; margin-left: 20px;" >
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Roles</label></td>			
			</tr>
			<tr>
				<td valign="top">
					<table style="width: 100%;" >
						<tr>
							<td width="45%"> 
								<label for="allRoles">Available Roles</label><br>
								<select multiple="multiple" id="allRoles" style="width: 90%; height: 400px;">
								</select>
							</td>
							<td width="10%">
								<button class="btn btn-sm btn-default" type="button" onclick="addRoles()"> <b> >> </b> </button>
								<br><br><br><br>
								<button class="btn btn-sm btn-default" type="button" onclick="removeRoles()"> <b> << </b> </button> 
							</td>
							<td width="45%"> 
								<label for="userRoles">User Roles</label><br>
								<select multiple="multiple" id="userRoles" style="width: 90%; height: 400px;"> 
									
								</select>
							</td>
						</tr>
					</table>
				</td>
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



<script type="text/javascript">
    $(document).ready(function() {
        $('#roleselect').multiselect({
        	includeSelectAllOption: true,
        	buttonWidth: '250px',
        	enableCaseInsensitiveFiltering: true
        });
    });
</script>


</div>