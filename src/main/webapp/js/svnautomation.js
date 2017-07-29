
$.ajaxSetup({ cache: false });


function approveRepository(repoId){
	
	var obj =  { 
			"whichForm":"REPOSITORY_APPROVED",
			"repoID": repoId,
			"action": 'UPDATE'
		}

	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(obj)
	    },
	    success : function(responseText) {
	    	location.reload();
	    }
	});
	
}

function rejectRepository(repoId){
	
	var obj =  { 
			"whichForm":"REPOSITORY_REJECTED",
			"repoID": repoId,
			"action": 'UPDATE'
		}

	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(obj)
	    },
	    success : function(responseText) {
	    	location.reload();
	    }
	});
	
}


function getuserfromLDAP(){
	
	$.ajax({
        url : '/ajax/getuserfromldap.jsp',
        data : {
        	loginId : $('#userloginid').val()
        },
        success : function(responseText) {
			var user = JSON.parse(responseText);
			$('#usersapid').val(user.sapcode);
			$('#useremailid').val(user.mail);
        }
    });
}


function removeFromUserList(p){
	
	var obj =  { 
			"whichForm":"DEACTIVATE_USER",
			"repoID": $('#repositoryID').val(),
			"sapcode": p,
			"status": "DE-ACTIVATED",
			"action": 'REMOVE'
		}

	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(obj)
	    },
	    success : function(responseText) {
	    	$('#'+p).remove();
	    	
	    	var loginids = "";
			$("input[name='requestUserloginid']").each(function(){
				loginids = 	loginids + this.value+",";
			});
			$('#user').val(loginids);
	    	
	    }
	});
	
}


function removeFromFolderList(p){
	
	var obj =  { 
			"whichForm":"DEACTIVATE_FOLDER",
			"repoID": $('#repositoryID').val(),
			"folder": p,
			"status": "DE-ACTIVATED",
			"action": 'REMOVE'
		}

	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(obj)
	    },
	    success : function(responseText) {
	    	$('#'+p).remove();
	    }
	});
	
	
	
}


function removeFromGroupList(p){
	
	var obj =  { 
			"whichForm":"DEACTIVATE_GROUP",
			"repoID": $('#repositoryID').val(),
			"group": p,
			"status": "DE-ACTIVATED",
			"action": 'REMOVE'
		}

	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(obj)
	    },
	    success : function(responseText) {
	    	$('#'+p).remove();
	    }
	});
	
}



function saveForm1(){
	
	
	var sar =  { 
				"whichForm":"INFORMATION",
				"incidentNumber": $('#incidentNumber').val(),
				"projectCode": $('#projectCode').val(), 
				"projectName": $('#projectName').val(), 
				"pmName": $('#pmName').val(), 
				"pmSAPCode": $('#pmSAPCode').val(), 
				"repositoryName": $('#repositoryName').val(), 
				"repoUserCount": $('#repoUserCount').val(), 
				"serviceStartDate": $('#serviceStartDate').val(), 
				"serviceEndDate": $('#serviceEndDate').val(), 
				
				"spocName": $('#spocName').val(), 
				"spocSAPCode": $('#spocSAPCode').val(), 
				"spocEmail": $('#spocEmail').val(), 
				
				"repoPurpose": getValueRepoPurpose(), 
				"cmtassSource": getValueCmtassSource()
			}
	
	
 	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
        data : {
        	repodata : JSON.stringify(sar)
        },
        success : function(responseText) {
			
        	$('#repositoryID').val(responseText);
        	$('#switchDefination').click();
        	
        }
    });
	
}

function defaultGroups(){
	$('#accessgroupname').html('');
	saveAccessGroupName("PM");
	saveAccessGroupName("QA");
	saveAccessGroupName("CC");
	saveAccessGroupName("DEVELOPER");
	saveAccessGroupName("TESTER");
}

function defaultTopFolders(val){
	
	if(val != ""){
	
		var folders = val.split(",");
		
		for(var i = 0 ; i < folders.length ; i++){
			saveFolderName(folders[i]);
		}
	
	}
}


function chooseDefaultTopFolders(){
	$('#defaultTopFoldersModal').modal('show');
}


function getuserfromLDAP(val, by){
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getuserfromldap.jsp',
        data : {
        	userid : val,
        	by : by
        },
        success : function(responseText) {
			
        	if(responseText != ""){
        	
	        	var user = JSON.parse(responseText);
	        	
	        	$('#userloginid').val(user.loginid);
	        	$('#usersapid').val(user.sapcode);
	        	$('#useremailid').val(user.mail);
	          	
	        }
        }
    });
	
}


function switchTabs(whichTab){
	$('#'+whichTab).click();
}

var selectedRowObj=null;

function selectedRow(f){
	selectedRowObj = $(f).closest('tr');
	
	
	$( "#userList" ).html( "" );
	
	var tempUser = $(selectedRowObj).children('td:eq(3)').html().split(",");
	var tempUserSAPcode = $(selectedRowObj).children('td:eq(8)').children('input[name=userSAPCode]').val().split(",");
	var tempUserEmail = $(selectedRowObj).children('td:eq(8)').children('input[name=userEMailId]').val().split(",");
	
	for(var i = 0 ; i < tempUser.length ; i++){

		var str = '';
		str = str+'<tr id="'+tempUserSAPcode[i]+'">';	
		str = str+'<td><input value="'+tempUser[i]+'" name="requestUserloginid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+tempUserSAPcode[i]+'" name="requestUsersapid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+tempUserEmail[i]+'" name="requestUseremailid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td id="'+tempUserSAPcode[i]+'_action"> <a href="#" style="color: red;"  onclick="removeFromUserList(\''+tempUserSAPcode[i]+'\')"> <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
		str = str+'</tr>';
		
		$( str ).appendTo( "#userList" );
	
	}
	
	
}

function saveUserForAccessRequest(){
	
	var userloginid = '';
	$("input[name='requestUserloginid']").each(function(){
			userloginid = userloginid+this.value+",";
	});
	$(selectedRowObj).children('td:eq(3)').html(userloginid.substring(0,userloginid.length - 1) );
	
	userloginid = '';
	$("input[name='requestUsersapid']").each(function(){
			userloginid = userloginid+this.value+",";
	});
	
	
	$(selectedRowObj).children('td:eq(8)').children('input[name=userSAPCode]').val(userloginid.substring(0,userloginid.length - 1) );
	
	
	userloginid = '';
	$("input[name='requestUseremailid']").each(function(){
			userloginid = userloginid+this.value+",";
	});
	$(selectedRowObj).children('td:eq(8)').children('input[name=userEMailId]').val(userloginid.substring(0,userloginid.length - 1) );
	
	selectedRowObj=null;
	$('#userModal').modal('hide');
	
}

function addUserForAccessRequest(){
	
	if( $('#userloginid').val() != "" && $('#usersapid').val() != "" && $('#useremailid').val() != "" ){
	
		var userSapCode = $('#usersapid').val();
		var str = '';
		str = str+'<tr id="'+userSapCode+'">';	
		str = str+'<td><input value="'+$('#userloginid').val()+'" name="requestUserloginid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+$('#usersapid').val()+'" name="requestUsersapid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td><input value="'+$('#useremailid').val()+'" name="requestUseremailid" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
		str = str+'<td id="'+userSapCode+'_action"> <a href="#" style="color: red;"  onclick="removeFromUserList(\''+userSapCode+'\')"> <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
		str = str+'</tr>';
		
		$( str ).appendTo( "#userList" );
		
			
		$('#userloginid').val('');
		$('#usersapid').val('');
		$('#useremailid').val('');
		
		var loginids = "";
		$("input[name='requestUserloginid']").each(function(){
			loginids = 	loginids + this.value+",";
		});
		$('#user').val(loginids);
	
	}
}


function saveUser(){
	
	
	var userSapCode = $('#usersapid').val();
	
	var str = '';
	str = str+'<tr id="'+userSapCode+'">';	
	str = str+'<td><input value="'+$('#userloginid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td><input value="'+$('#usersapid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td><input value="'+$('#useremailid').val()+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
	str = str+'<td id="'+userSapCode+'_action"> <img src="/SVNAutomation-T1/icons/loader.gif"> </td>';	
	str = str+'</tr>';
	
	$( str ).appendTo( "#userList" );
	
	var user =  { 
				"whichForm":"USER",
				"sapCode": $('#usersapid').val(),
				"userName": 'FIND OUT', 
				"loginID": $('#userloginid').val(), 
				"emailID": $('#useremailid').val(), 
				"repoID": $('#repositoryID').val(), 
				"action": 'ADD'
			}
	
	$('#userloginid').val('');
	$('#usersapid').val('');
	$('#useremailid').val('');
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(user)
	    },
	    success : function(responseText) {
	    	$('#'+userSapCode+'_action').html('<a href="#" style="color: red;"  onclick="removeFromUserList(\''+userSapCode+'\')"> <span class="glyphicon glyphicon-minus"></span> </a>');
	    	
	    }
	});

	
}

function saveNewAccessGroupName(){
	
	
	var gName = $('#repositoryName').val()+'-'+$('#groupNameTemp').val();
	
	var str = '';
	str = str+'<tr id="'+gName+'">';	
	str = str+'<td style="width: 90%;"><div name="groupNames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+gName+'</div></td>';	
	str = str+'<td id="'+gName+'_action"> <img src="/SVNAutomation-T1/icons/loader.gif"> </td>';	
	str = str+'</tr>';
	
		
	$( str ).appendTo( "#accessgroupname" );
	
	var group =  { 
				"whichForm":"GROUP",
				"groupName": gName,
				"repoID": $('#repositoryID').val(), 
				"action": 'ADD'
			}
	
	$('#groupNameTemp').val('');
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(group)
	    },
	    success : function(responseText) {
	    	$('#'+gName+'_action').html('<a href="#" style="color: red;" onclick="removeFromGroupList(\''+gName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	    }
	});

	
}


function saveAccessGroupName( gNameTemp ){
	
	
	var gName = $('#repositoryName').val()+'-'+gNameTemp;
	
	var str = '';
	str = str+'<tr id="'+gName+'">';	
	str = str+'<td style="width: 90%;"><div name="groupNames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+gName+'</div></td>';	
	str = str+'<td id="'+gName+'_action"> <img src="/SVNAutomation-T1/icons/loader.gif"> </td>';	
	str = str+'</tr>';
	
		
	$( str ).appendTo( "#accessgroupname" );
	
	var group =  { 
				"whichForm":"GROUP",
				"groupName": gName,
				"repoID": $('#repositoryID').val(), 
				"action": 'ADD'
			}
	
	$('#groupNameTemp').val('');
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(group)
	    },
	    success : function(responseText) {
	    	$('#'+gName+'_action').html('<a href="#" style="color: red;" onclick="removeFromGroupList(\''+gName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	    }
	});

	
}


function createGroupFolderMap( group , folder){
	
	var groupFolder =  { 
				"whichForm":"GROUP_FOLDER_MAP_ADD",
				"repoID": $('#repositoryID').val(),
				"groupName": group,
				"folder": folder,
				"access": "",
				"action": 'ADD'
			}
	
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(groupFolder)
	    },
	    success : function(responseText) {
	
	    	
	    }
	});

	
}

function updateGroupFolderMap(group, folder, access){
	
	var groupFolder =  { 
				"whichForm":"GROUP_FOLDER_MAP_UPDATE",
				"repoID": $('#repositoryID').val(),
				"groupName": group,
				"folder": folder,
				"access": access,
				"action": 'UPDATE'
			}
	
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(groupFolder)
	    },
	    success : function(responseText) {
	
	    	
	    }
	});

	
}


function saveNewFolderName(){
	
	
	var fName = $('#folderNameTemp').val();
	
	var str = '';
	str = str+'<tr id="'+fName+'">';	
	str = str+'<td style="width: 90%;"><div name="foldernames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+fName+'</div></td>';	
	str = str+'<td id="'+fName+'_action"> <img src="/SVNAutomation-T1/icons/loader.gif"> </td>';	
	str = str+'</tr>';
	
		
	$( str ).appendTo( "#foldername" );
	
	var folder =  { 
				"whichForm":"FOLDER",
				"folderName": $('#folderNameTemp').val(),
				"repoID": $('#repositoryID').val(), 
				"action": 'ADD'
			}
	
	$('#folderNameTemp').val('');
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(folder)
	    },
	    success : function(responseText) {
	    	$('#'+fName+'_action').html('<a href="#" style="color: red;" onclick="removeFromFolderList(\''+fName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	    }
	});

	
}

function saveFolderName(fName){
	

	var str = '';
	str = str+'<tr id="'+fName+'">';	
	str = str+'<td style="width: 90%;"><div name="foldernames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+fName+'</div></td>';	
	str = str+'<td id="'+fName+'_action"> <img src="/SVNAutomation-T1/icons/loader.gif"> </td>';	
	str = str+'</tr>';
	
		
	$( str ).appendTo( "#foldername" );
	
	var folder =  { 
				"whichForm":"FOLDER",
				"folderName": fName,
				"repoID": $('#repositoryID').val(), 
				"action": 'ADD'
			}
	
	$('#folderNameTemp').val('');
	
	
	$.ajax({
		cache: false,
	    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
	    data : {
	    	repodata : JSON.stringify(folder)
	    },
	    success : function(responseText) {
	    	$('#'+fName+'_action').html('<a href="#" style="color: red;" onclick="removeFromFolderList(\''+fName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');
	    }
	});

	
}



function getValueCmtassSource(){
	
	var repoCmtassSource = '';
	$("input[name='cmtassSource']").each(function(){
		
		if(this.checked){
			repoCmtassSource = repoCmtassSource+this.value;
		}
		
	});
	
	return repoCmtassSource;
	
}

function getValueRepoPurpose(){
	
	var repoPurposeValues = '';
	$("input[name='repoPurpose']").each(function(){
		
		if(this.checked){
			repoPurposeValues = repoPurposeValues+this.value;
		}
		
	});
	
	return repoPurposeValues;
	
}



function getDataForAssignationTab(){
	
	
	
	var folderObj = null;
	var groupObj = null;
	var folderaccess = null;
	var groupUsers = null;

	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : $('#repositoryID').val(),
        	whichTab : 'ASSIGNATION'
        },
        success : function(responseText) {
        	
        	folderObj = JSON.parse(responseText).FOLDER;
        	groupObj = JSON.parse(responseText).GROUP;
        	folderaccess = JSON.parse(responseText).MAPPING;
        	groupUsers = JSON.parse(responseText).USERS;
        	
        	prepareUserAccessTable(folderObj , groupObj , folderaccess, groupUsers);
        	prepareUsersForPermission();
        	createGroupFolderMap( groupObj , folderObj );
        	
        }
    });
	
	
	
	
	
}


function prepareUserAccessTable(folderObj , groupObj , folderaccess , groupUsers){
	
	
	var accessTable = '';
	accessTable=accessTable+'<tr>'; 
	
	
	$.each(groupObj, function(counter,obj){
		
		accessTable=accessTable+'<td align="center" valign="top">';
		
		accessTable=accessTable+obj.groupName+'<span data-toggle="modal" onclick="updateModal(\''+obj.groupName+'\')" data-target="#myModal" class="label label-primary" style="cursor: pointer; position: relative; float: right;">Manage Users</span>';
		accessTable=accessTable+'<hr> ';
				
		accessTable=accessTable+'<table style="width : 100%;"> <tr> <td width="60%" align="center" valign="top">';
		accessTable=accessTable+ prepareGroupUserList( obj.groupName, groupUsers );
		accessTable=accessTable+'</td> <td width="40%" align="center" valign="top">';
		accessTable=accessTable+ preparePermissionTable(obj.groupName,folderObj,folderaccess);
		accessTable=accessTable+'</td> </tr> </table>';
		
		accessTable=accessTable+'</td>';
		
		if(counter!=0 && (counter%2)==1){
			accessTable=accessTable+'</tr><tr>';
		}
		
		
		
	});
	
	
	
	
	accessTable=accessTable+'</tr></table>';
	$('#userAccess').html(accessTable);
	
}

function prepareGroupUserList(group , users){

	var user = '';
	
	user=user+'<table id="'+group+'_user_tbl" border="1" style="width: 95%;">';
	user=user+'<tr><td colspan="3" align="center">Users</td></tr>';
	user=user+'<tr>';
	user=user+'<td align="center" width="40%">SAPCode</td>';
	user=user+'<td align="center" width="60%">Login ID</td>';
	user=user+'</tr>';
	
	$.each(users, function(counter,obj){
		if( obj.group ==  group ){
			user=user+'<tr><td align="center" name="'+group+'_user_td">'+obj.sapCode+'</td><td align="left"> '+obj.loginid+' </td></tr>';
		}
	});
	
	user=user+'</tr></table><br>';
	
	return user;
}


function preparePermissionTable(group , folderObj , folderaccess){
	
	var permissiontab = '';
	
	permissiontab=permissiontab+'<table border="1" style="width: 95%;">';
	permissiontab=permissiontab+'<tr><td colspan="4" align="center">Access</td></tr>';
	permissiontab=permissiontab+'<tr>';
	permissiontab=permissiontab+'<td align="center" width="55%">FOLDER</td>';
	permissiontab=permissiontab+'<td align="center" width="15%">N</td>';
	permissiontab=permissiontab+'<td align="center" width="15%">R</td>';
	permissiontab=permissiontab+'<td align="center" width="15%">RW</td>';
	permissiontab=permissiontab+'</tr>';
	
	$.each(folderObj, function(counter,obj){
		
		if(folderaccess[ group+'_'+obj.folderPath ] == "r"){
			permissiontab=permissiontab+'<tr><td align="center">'+obj.folderPath+'</td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'\')" type="radio" value=""></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'r\')" type="radio" checked="checked" value="r"></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'rw\')" type="radio" value="rw"></td></tr>';
		}
		else if(folderaccess[ group+'_'+obj.folderPath ] == "rw"){
			permissiontab=permissiontab+'<tr><td align="center">'+obj.folderPath+'</td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'\')" type="radio" value=""></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'r\')" type="radio" value="r"></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'rw\')" checked="checked" type="radio" value="rw"></td></tr>';
		}
		else{
			permissiontab=permissiontab+'<tr><td align="center">'+obj.folderPath+'</td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'\')" type="radio"  checked="checked"  value=""></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'r\')" type="radio" value="r"></td><td align="center"><input name="'+group+counter+'" onclick="updateGroupFolderMap(\''+group+'\',\''+obj.folderPath+'\',\'rw\')" type="radio" value="rw"></td></tr>';
		}
	
	});
		
	permissiontab=permissiontab+'</table><br>';
	
	return permissiontab;
}


function prepareUsersForPermission(){
	
	$("#userListForGrpMap").html(''); 
		
		$.ajax({
	        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
	        data : {
	        	repoID : $('#repositoryID').val(),
	        	whichTab : 'DEFINATION_USER'
	        },
	        success : function(responseText) {
	        	
	        	var user = JSON.parse(responseText);
	
				 $.each(user, function(k, v) {
				       
					 var str = '';
						str = str+'<tr id="'+v.sapCode+'">';	
						str = str+'<td><input value="'+v.loginId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
						str = str+'<td><input value="'+v.sapCode+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
						str = str+'<td><input value="'+v.emailId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
						str = str+'<td id="'+v.sapCode+'_action"> <input name="allusercheckbox" id="'+v.sapCode+'_checkbox" value="'+v.loginId+'" type="checkbox" onclick="saveUserGrpMap(this)"> </td>';	
						str = str+'</tr>';
						
						$( str ).appendTo( "#userListForGrpMap" );
				 });
				
				
				
	        }
	    });
	
	
}

function saveUserGrpMap(v){
	
	if(v.checked){
		
		var userGroupMap =  { 
				"whichForm":"USER_GROUP_MAP_ADD",
				"groupName": $('#groupnamemodal').val(),
				"repoID": $('#repositoryID').val(), 
				"userID": v.value, 
				"action": 'ADD'
			}
	
		
		$.ajax({
			cache: false,
		    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
		    data : {
		    	repodata : JSON.stringify(userGroupMap)
		    },
		    success : function(responseText) {
		    	
		    	/*$('#'+fName+'_action').html('<a href="#" style="color: red;" onclick="removeFromFolderList(\''+fName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');*/
		    }
		});
		
	}else{
		
		var userGroupMap =  { 
				"whichForm":"USER_GROUP_MAP_REMOVE",
				"groupName": $('#groupnamemodal').val(),
				"repoID": $('#repositoryID').val(), 
				"userID": v.value, 
				"action": 'ADD'
			}
	
		
		$.ajax({
			cache: false,
		    url : '/SVNAutomation-T1/jsp/svn/ajax/saveSARForm.jsp',
		    data : {
		    	repodata : JSON.stringify(userGroupMap)
		    },
		    success : function(responseText) {
		    	
		    	/*$('#'+fName+'_action').html('<a href="#" style="color: red;" onclick="removeFromFolderList(\''+fName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a>');*/
		    }
		});
		
	}
}

function updateModal(v){
	$('#groupnamemodal').val( v );
		
	$('input[name=allusercheckbox]').each(function(){
		this.checked=false;
	});
	

	$('td[name='+v+'_user_td]').each(function(){
		document.getElementById(this.innerHTML+'_checkbox').checked=true;
	});
	
}

function populateRepoInfoData(){
	
	if($('#repositoryID').val() != "" && $('#repositoryID').val().indexOf("REPO") >= 0){
	
	
		$.ajax({
	        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
	        data : {
	        	repoID : $('#repositoryID').val(),
	        	whichTab : 'INFORMATION'
	        },
	        success : function(responseText) {
				var repository = JSON.parse(responseText);
	
				$('#testDiv').html(JSON.stringify(repository));
				
				$('#projectCode').val(repository.projectSAPCode);
				$('#projectName').val(repository.projectName);
				$('#pmName').val(repository.pmName);
				$('#pmSAPCode').val(repository.pmSAPCode);
				
				$('#repositoryName').val(repository.repositoryName);
				$('#repoUserCount').val(repository.userCount);
				$('#serviceStartDate').val(repository.serviceStartDate);
				$('#serviceEndDate').val(repository.serviceEndDate);
				
				$('#spocName').val(repository.spocName);
				$('#spocSAPCode').val(repository.spocSAPCode);
				$('#spocEmail').val(repository.spocEmail);
				$('#incidentNumber').val(repository.incidentNo);
				
	        }
	    });
	
	}
	
}

function populateDefinationTable(){
	populateRepoUserData($('#repositoryID').val());
	populateRepoGroupData($('#repositoryID').val());
	populateRepoFolderData($('#repositoryID').val());
}

function populateRepoUserData(rid){
	
	$("#userList").html(''); 
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : rid,
        	whichTab : 'DEFINATION_USER'
        },
        success : function(responseText) {
        	
        	var user = JSON.parse(responseText);

			 $.each(user, function(k, v) {
			       
				 var str = '';
					str = str+'<tr id="'+v.sapCode+'">';	
					str = str+'<td><input value="'+v.loginId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
					str = str+'<td><input value="'+v.sapCode+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
					str = str+'<td><input value="'+v.emailId+'" type="text" readonly="readonly" style="background-color: #dff0d8"></td>';	
					str = str+'<td id="'+v.sapCode+'_action"> <a href="#" style="color: red;"  onclick="removeFromUserList(\''+v.sapCode+'\')"> <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
					str = str+'</tr>';
					
					$( str ).appendTo( "#userList" );
			 });
			
			
			
        }
    });
	
}

function populateRepoGroupData(rid){
	
	$("#accessgroupname").html(''); 
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : rid,
        	whichTab : 'DEFINATION_GROUP'
        },
        success : function(responseText) {
        	
        	var group = JSON.parse(responseText);

			 $.each(group, function(k, v) {
			       
				 var str = '';
					str = str+'<tr id="'+v.groupName+'">';	
					str = str+'<td style="width: 90%;"><div name="groupNames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+v.groupName+'</div></td>';	
					str = str+'<td id="'+v+'_action"> <a href="#" style="color: red;" onclick="removeFromGroupList(\''+v.groupName+'\')" > <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
					str = str+'</tr>';
					$( str ).appendTo( "#accessgroupname" );
					
			 });
        }
    });
	
}


function populateRepoFolderData(rid){
	
	$("#foldername").html(''); 
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : rid,
        	whichTab : 'DEFINATION_FOLDER'
        },
        success : function(responseText) {
        	
        	var folder = JSON.parse(responseText);

			 $.each(folder, function(k, v) {
			       
				 var str = '';
					str = str+'<tr id="'+v.folderPath+'">';	
					str = str+'<td style="width: 90%;"><div name="foldernames" class="alert alert-success" style="width: 100%; padding: 0px; margin-bottom: 0px;">'+v.folderPath+'</div></td>';	
					str = str+'<td id="'+v.folderPath+'_action"> <a href="#" style="color: red;" onclick="removeFromFolderList(\''+v.folderPath+'\')" > <span class="glyphicon glyphicon-minus"></span> </a> </td>';	
					str = str+'</tr>';
					$( str ).appendTo( "#foldername" );
					
			 });
        }
    });
	
}



function populatConfirmationTab(){
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : $('#repositoryID').val(),
        	whichTab : 'INFORMATION'
        },
        success : function(responseText) {
			var repository = JSON.parse(responseText);

			$('#confirmProjectSAPCode').html(repository.projectSAPCode);
			$('#confirmProjectName').html(repository.projectName);
			$('#confirmPmName').html(repository.pmName);
			$('#confirmPmSAPCode').html(repository.pmSAPCode);
			
			$('#confirmRepositoryName').html(repository.repositoryName);
			$('#confirmCMUserCount').html(repository.userCount);
			$('#confirmServiceStartDate').html(repository.serviceStartDate);
			$('#confirmServiceEndDate').html(repository.serviceEndDate);
			
			$('#confirmSpocName').html(repository.spocName);
			$('#confirmSpocSAPCode').html(repository.spocSAPCode);
			$('#confirmSpocEmail').html(repository.spocEmail);
			
        }
    });
	
	
	$("#confirmUserTable").html(''); 
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : $('#repositoryID').val(),
        	whichTab : 'DEFINATION_USER'
        },
        success : function(responseText) {
        	
        	var user = JSON.parse(responseText);

			 $.each(user, function(k, v) {
			       
				 var str = '';
					str = str+'<tr>';	
					str = str+'<td align="center">'+(k+1)+'</td>';
					str = str+'<td>'+v.loginId+'</td>';	
					str = str+'<td align="center">'+v.sapCode+'</td>';	
					str = str+'</tr>';
				$( str ).appendTo( "#confirmUserTable" );
			 });
        }
    });
	
	$("#confirmAccessGroup").html(''); 
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : $('#repositoryID').val(),
        	whichTab : 'DEFINATION_GROUP'
        },
        success : function(responseText) {
        	
        	var group = JSON.parse(responseText);

			 $.each(group, function(k, v) {
			       
				 var str = '';
					str = str+'<tr>';	
					str = str+'<td align="center">'+(k+1)+'</td>';	
					str = str+'<td>'+v.groupName+'</td>';	
					str = str+'</tr>';
					$( str ).appendTo( "#confirmAccessGroup" );
			 });
        }
    });
	
	
	var folderObj = null;
	var groupObj = null;
	var folderaccess = null;
	var groupUsers = null;

	$( "#confirmGroupUser" ).html('');
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : $('#repositoryID').val(),
        	whichTab : 'ASSIGNATION'
        },
        success : function(responseText) {
        	
        	folderObj = JSON.parse(responseText).FOLDER;
        	groupObj = JSON.parse(responseText).GROUP;
        	folderaccess = JSON.parse(responseText).MAPPING;
        	groupUsers = JSON.parse(responseText).USERS;
        	
        	
        	$.each(groupObj, function(k,v){
        		
        		var str = '';
				str = str+'<tr>';	
				str = str+'<td align="center">'+(k+1)+'</td>';	
				str = str+'<td>'+v.groupName+'</td>';	
				str = str+'<td><table id="confirm_'+v.groupName+'"></table></td>';
				str = str+'</tr>';
				$( str ).appendTo( "#confirmGroupUser" );
        		
        	});
        	
        	
        	$.each(groupUsers, function(k,v){

				$( '<tr><td>'+v.loginid+'</td></tr>' ).appendTo( "#confirm_"+v.group );
        		
        	});
        	

        	$( "#confirmFolderAccess" ).html('');
        	
        	var str = '';
			str = str+'<tr>';
			str = str+'<td align="center">Group Name</td>';
			
			$.each(folderObj, function(k2,v2){
				str = str+'<td align="center">'+v2.folderPath+'</td>';	
			});
			
			str = str+'</tr>';
			
        	$.each(groupObj, function(k,v){
        		
        		str = str+'<tr><td>'+v.groupName+'</td>';
        		
        		$.each(folderObj, function(k2,v2){
    				str = str+'<td align="center">'+folderaccess[v.groupName+"_"+v2.folderPath]+'</td>';	
    			});
        		
        		str = str+'</tr>';
				
        	});
        	
        	
			$( str ).appendTo( "#confirmFolderAccess" );
        	
        	
        }
    });
	
}

function populatViewModal(repoid){
	
	$('#accessView').modal('show');
	
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : repoid,
        	whichTab : 'INFORMATION'
        },
        success : function(responseText) {
        	
			var repository = JSON.parse(responseText);

			$('#confirmProjectSAPCode').html(repository.projectSAPCode);
			$('#confirmProjectName').html(repository.projectName);
			$('#confirmPmName').html(repository.pmName);
			$('#confirmPmSAPCode').html(repository.pmSAPCode);
			
			$('#confirmRepositoryName').html(repository.repositoryName);
			$('#confirmCMUserCount').html(repository.userCount);
			$('#confirmServiceStartDate').html(repository.serviceStartDate);
			$('#confirmServiceEndDate').html(repository.serviceEndDate);
			
			$('#confirmSpocName').html(repository.spocName);
			$('#confirmSpocSAPCode').html(repository.spocSAPCode);
			$('#confirmSpocEmail').html(repository.spocEmail);
			
			$('#textView_text').html(repository.repoAccess);
			
			
        }
    });
	
	
	$("#confirmUserTable").html(''); 
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : repoid,
        	whichTab : 'DEFINATION_USER'
        },
        success : function(responseText) {
        	
        	var user = JSON.parse(responseText);

			 $.each(user, function(k, v) {
			       
				 var str = '';
					str = str+'<tr>';	
					str = str+'<td align="center">'+(k+1)+'</td>';
					str = str+'<td>'+v.loginId+'</td>';	
					str = str+'<td align="center">'+v.sapCode+'</td>';	
					str = str+'</tr>';
				$( str ).appendTo( "#confirmUserTable" );
			 });
        }
    });
	
	$("#confirmAccessGroup").html(''); 
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : repoid,
        	whichTab : 'DEFINATION_GROUP'
        },
        success : function(responseText) {
        	
        	var group = JSON.parse(responseText);

			 $.each(group, function(k, v) {
			       
				 var str = '';
					str = str+'<tr>';	
					str = str+'<td align="center">'+(k+1)+'</td>';	
					str = str+'<td>'+v.groupName+'</td>';	
					str = str+'</tr>';
					$( str ).appendTo( "#confirmAccessGroup" );
			 });
        }
    });
	
	
	var folderObj = null;
	var groupObj = null;
	var folderaccess = null;
	var groupUsers = null;

	$( "#confirmGroupUser" ).html('');
	
	$.ajax({
        url : '/SVNAutomation-T1/jsp/svn/ajax/getRepoDetails.jsp',
        data : {
        	repoID : repoid,
        	whichTab : 'ASSIGNATION'
        },
        success : function(responseText) {
        	
        	folderObj = JSON.parse(responseText).FOLDER;
        	groupObj = JSON.parse(responseText).GROUP;
        	folderaccess = JSON.parse(responseText).MAPPING;
        	groupUsers = JSON.parse(responseText).USERS;
        	
        	
        	$.each(groupObj, function(k,v){
        		
        		var str = '';
				str = str+'<tr>';	
				str = str+'<td align="center">'+(k+1)+'</td>';	
				str = str+'<td>'+v.groupName+'</td>';	
				str = str+'<td><table id="confirm_'+v.groupName+'"></table></td>';
				str = str+'</tr>';
				$( str ).appendTo( "#confirmGroupUser" );
        		
        	});
        	
        	
        	$.each(groupUsers, function(k,v){

				$( '<tr><td>'+v.loginid+'</td></tr>' ).appendTo( "#confirm_"+v.group );
        		
        	});
        	

        	$( "#confirmFolderAccess" ).html('');
        	
        	var str = '';
			str = str+'<tr>';
			str = str+'<td align="center">Group Name</td>';
			
			$.each(folderObj, function(k2,v2){
				str = str+'<td align="center">'+v2.folderPath+'</td>';	
			});
			
			str = str+'</tr>';
			
        	$.each(groupObj, function(k,v){
        		
        		str = str+'<tr><td>'+v.groupName+'</td>';
        		
        		$.each(folderObj, function(k2,v2){
    				str = str+'<td align="center">'+folderaccess[v.groupName+"_"+v2.folderPath]+'</td>';	
    			});
        		
        		str = str+'</tr>';
				
        	});
        	
        	
			$( str ).appendTo( "#confirmFolderAccess" );
        	
        	
        }
    });
	
}
