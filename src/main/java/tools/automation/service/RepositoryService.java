package tools.automation.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.automation.bean.ProjectMaster;
import tools.automation.bean.RepositoryFolder;
import tools.automation.bean.RepositoryGroup;
import tools.automation.bean.RepositoryGroupFolderAccess;
import tools.automation.bean.RepositoryGroupUserMap;
import tools.automation.bean.RepositoryUserMap;
import tools.automation.bean.RequestMaster;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.SvnRepositoryAccessText;
import tools.automation.bean.SvnRequestRepositoryAccess;
import tools.automation.bean.SvnRequestRepositoryRestoration;
import tools.automation.bean.User;
import tools.automation.utility.DataSecurity;
import tools.automation.utility.RoleUtils;


@RemoteProxy
public class RepositoryService {

	@RemoteMethod
	public String getRepositoryList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		User currentUser = (User) WebContextFactory.get().getSession().getAttribute("validUser");

		List<SvnRepository> repositories = null;

		if( RoleUtils.isSuperAdmin(currentUser) ){
			repositories = SvnRepository.findAllSvnRepositorys();
		}else{
			repositories = SvnRepository.findAllSvnRepositorysByUser(search);
		}


		try {
			for (SvnRepository repository : repositories) {

				JSONArray repo = new JSONArray();

				repo.put(repository.getRepoId());
				repo.put(repository.getRepositoryName());
				repo.put(repository.getProjectSapCode());
				repo.put(repository.getProjectName());
				repo.put(repository.getProjectManager().getFullName());
				repo.put(repository.getServiceStartDate());
				repo.put(repository.getServiceEndDate());
				repo.put(repository.getServer());
				repo.put(repository.getStatus());

				StringBuffer actionOptions = new StringBuffer();
				actionOptions.append("<a title='Edit Repository' href='/toolsautomation/svn/repository/edit?id="+dataSecurity.encryptTextURL(repository.getId()+"")+"'><span class='glyphicon glyphicon-edit'></span><a>") ;
				actionOptions.append("&nbsp;&nbsp;<a title='Show Repository Access' href='#' data-toggle='modal' data-target='#myModal' onclick='populateaccessmodal("+repository.getId()+")'><span class='glyphicon glyphicon-info-sign'></span><a>") ;

				if( "Waiting for approval".equalsIgnoreCase(repository.getStatus())){
					if( currentUser.getId() == repository.getProjectManager().getId() || currentUser.getId() == repository.getAlternateApprover().getId() ){
						actionOptions.append("&nbsp;&nbsp;<a title='Approve' href='/toolsautomation/svn/repository/approved?id="+dataSecurity.encryptTextURL(repository.getId()+"")+"'><span class='glyphicon glyphicon-ok'></span><a>") ;
						actionOptions.append("&nbsp;&nbsp;<a title='Reject' href='/toolsautomation/svn/repository/rejected?id="+dataSecurity.encryptTextURL(repository.getId()+"")+"'><span class='glyphicon glyphicon-remove'></span><a>") ;
					}
				}
				
				actionOptions.append("&nbsp;&nbsp;<a title='Start Lync Chat' href='sip:"+repository.getProjectManager().getEmailId()+"' ><span class='glyphicon glyphicon-retweet'></span><a>") ;
				
				repo.put(actionOptions.toString()); 

				arr.put(repo); 

			}

			result.put("iTotalRecords", arr.length());
			result.put("iTotalDisplayRecords", arr.length());
			result.put("sEcho", echo);
			result.put("aaData", arr);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return result.toString();
	}

	@RemoteMethod
	public String getRepositoryRestorationList(int echo, int start, int max, String search) {
		DataSecurity dataSecurity = new DataSecurity();

		User currentUser = (User) WebContextFactory.get().getSession().getAttribute("validUser");

		List<SvnRequestRepositoryRestoration> restorationList = null;

		if(RoleUtils.isSuperAdmin(currentUser)){
			restorationList = SvnRequestRepositoryRestoration.findAllSvnRequestRepositoryRestorations();
		}else{
			restorationList = SvnRequestRepositoryRestoration.findAllSvnRequestRepositoryRestorationsByUser(currentUser.getLoginId());
		}


		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();


		try {
			for (SvnRequestRepositoryRestoration requestList : restorationList) {

				JSONArray repo = new JSONArray();

				/*repo.put(requestList.getIncidentNo());
				repo.put(requestList.getUser().getFullName());
				repo.put(requestList.getRepository().getRepositoryName());
				repo.put(requestList.getRepoGroup().getGroupName());
				repo.put(requestList.getRequestedBy().getFullName());
				repo.put(requestList.getRequestedOn());
				repo.put(requestList.getStatus());*/

				if(requestList.getApprovedBy() != null){
					repo.put(requestList.getApprovedBy().getFullName());
				}else{
					repo.put("NA");
				}

				if(requestList.getApprovedBy() != null){
					repo.put(requestList.getApprovedOn());
				}else{
					repo.put("NA");
				}

				arr.put(repo); 

			}

			result.put("iTotalRecords", arr.length());
			result.put("iTotalDisplayRecords", arr.length());
			result.put("sEcho", echo);
			result.put("aaData", arr);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return result.toString();
	}


	@RemoteMethod
	public String getRepositoryAccessRequestList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();

		User currentUser = (User) WebContextFactory.get().getSession().getAttribute("validUser");

		List<SvnRequestRepositoryAccess> accessRequestList = null;
		if(RoleUtils.isSuperAdmin(currentUser)){
			accessRequestList = SvnRequestRepositoryAccess.findAllSvnRequestRepositoryAccesses();
		}else{
			accessRequestList = SvnRequestRepositoryAccess.findAllSvnRequestRepositoryAccessesByUser(currentUser.getLoginId());
		}


		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();


		try {
			for (SvnRequestRepositoryAccess requestList : accessRequestList) {

				JSONArray repo = new JSONArray();

				repo.put(requestList.getIncidentNo());
				repo.put(requestList.getUser().getFullName());
				repo.put(requestList.getRepository().getRepositoryName());
				repo.put(requestList.getRepoGroup().getGroupName());
				repo.put(requestList.getRequestedBy().getFullName());
				repo.put(requestList.getRequestedOn());
				repo.put(requestList.getStatus());

				if(requestList.getApprovedBy() != null){
					repo.put(requestList.getApprovedBy().getFullName());
				}else{
					repo.put("NA");
				}

				if(requestList.getApprovedBy() != null){
					repo.put(requestList.getApprovedOn());
				}else{
					repo.put("NA");
				}

				arr.put(repo); 

			}

			result.put("iTotalRecords", arr.length());
			result.put("iTotalDisplayRecords", arr.length());
			result.put("sEcho", echo);
			result.put("aaData", arr);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return result.toString();
	}
	@RemoteMethod
	public String getRepositoryAccessByRepositoryId(int repoId) {
		return SvnRepositoryAccessText.findRepositoryAccessByRepositoryId(repoId).replaceAll("\n", "<br>");
	}


	@RemoteMethod
	public SvnRepository createRepository(SvnRepository repository, String pm, String spocCode, String alternateApprover, String startDate, String endDate) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			repository.setProjectManager( User.findUserDataBySAPCode(pm) );
			repository.setPrimaryApprover( User.findUserDataBySAPCode(pm) );
			repository.setAlternateApprover( User.findUserDataBySAPCode(alternateApprover) );
			repository.setSpoc( User.findUserDataBySAPCode(spocCode) );

			repository.setServiceStartDate( format.parse(startDate) );
			repository.setServiceEndDate( format.parse(endDate) );
			repository.setRepoCreatedOn( Calendar.getInstance() );

			repository.setRepoId( "REPO".concat( new Date().getTime()+"" ) );
			repository.setStatus("NEW");

			repository.setRepoAccess("".getBytes());

			SvnRepository repo = repository.merge();

			return repo;
			//repository.merge();


		} catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@RemoteMethod
	public SvnRepository saveRepository(SvnRepository editedRepository, String pm, String spocCode, String alternateApprover, String startDate, String endDate) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		try {

			SvnRepository repository = SvnRepository.findSvnRepository(editedRepository.getId());

			repository.setProjectManager( User.findUserDataBySAPCode(pm) );
			repository.setPrimaryApprover( User.findUserDataBySAPCode(pm) );
			repository.setAlternateApprover( User.findUserDataBySAPCode(alternateApprover) );
			repository.setSpoc( User.findUserDataBySAPCode(spocCode) );

			repository.setServiceStartDate( format.parse(startDate) );
			repository.setServiceEndDate( format.parse(endDate) );

			repository.setProjectSapCode(editedRepository.getProjectSapCode());
			repository.setProjectName(editedRepository.getProjectName());

			SvnRepository repo = repository.merge();

			return repo;
			//repository.merge();


		} catch (Exception e){
			e.printStackTrace();
		}

		return null;
	}

	@RemoteMethod
	public Integer addUserToRepository(int repoId, String sapCode) {

		try {

			long count = RepositoryUserMap.findMapCountByRepositoryUser(SvnRepository.findSvnRepository(repoId).getId() , User.findUserDataBySAPCode(sapCode).getId());

			if( count > 0){
				return null;
			}

			RepositoryUserMap repositoryUserMap = new RepositoryUserMap();
			repositoryUserMap.setRepository( SvnRepository.findSvnRepository(repoId) );
			repositoryUserMap.setUser(User.findUserDataBySAPCode(sapCode));
			repositoryUserMap = repositoryUserMap.merge();

			return repositoryUserMap.getUser().getId();

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RemoteMethod
	public List<User> getUserListByRepository(int repoId) {

		try {
			return RepositoryUserMap.findUsersByRepository(repoId);

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RemoteMethod
	public Integer addGroupToRepository(int repoId, String group) {

		try {

			long count = RepositoryGroup.findMapCountByRepositoryGroup(repoId, group);

			if( count > 0){
				return null;
			}


			RepositoryGroup repositoryGroup = new RepositoryGroup();
			repositoryGroup.setRepository(SvnRepository.findSvnRepository(repoId));
			repositoryGroup.setGroupName(group);
			repositoryGroup.setStatus("ACTIVE");
			repositoryGroup.setCreatedOn(new Date());

			repositoryGroup = repositoryGroup.merge();

			return repositoryGroup.getId() ;

		} catch (Exception e){
			e.printStackTrace();
		}
		return 0;
	}

	@RemoteMethod
	public List<RepositoryGroup> getGroupListByRepository(int repoId) {

		try {
			return RepositoryGroup.findGroupsByRepository(repoId);

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RemoteMethod
	public Integer addFolderToRepository(int repoId, String folderPath) {

		try {

			RepositoryFolder repositoryFolder = new RepositoryFolder();
			repositoryFolder.setRepository(SvnRepository.findSvnRepository(repoId));
			repositoryFolder.setFolderPath(folderPath);
			repositoryFolder.setStatus("ACTIVE");
			repositoryFolder.setCreatedOn(new Date());

			repositoryFolder = repositoryFolder.merge();

			return repositoryFolder.getId() ;

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RemoteMethod
	public List<RepositoryFolder> getFolderListByRepository(int repoId) {

		try {
			return RepositoryFolder.findFolderByRepository(repoId);

		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RemoteMethod
	public String getDataForAssignationTab(int repoId) {

		try {

			StringBuffer stringBuffer = new StringBuffer();

			SvnRepository svnRepository = SvnRepository.findSvnRepository(repoId);

			Set<RepositoryGroup> groups = svnRepository.getRepositoryGroups();
			Set<RepositoryFolder> folders = svnRepository.getRepositoryFolders();

			for (RepositoryGroup group : groups) {

				stringBuffer.append("<div class='panel panel-default'>");
				stringBuffer.append("<div class='panel-heading' role='tab' id='heading"+group.getGroupName()+"' title='Click to expand'>");
				stringBuffer.append("<h4 class='panel-title' style='width:50%'>");
				stringBuffer.append("<a role='button' data-toggle='collapse' data-parent='#accordion' href='#collapse"+group.getGroupName()+"' aria-expanded='true' aria-controls='collapse"+group.getGroupName()+"'>");
				stringBuffer.append(group.getGroupName());
				stringBuffer.append("</a>");
				stringBuffer.append("</h4>");
				stringBuffer.append("<button style='float:right; ' type='button' onclick='getReadyToAddUser("+group.getId()+",\""+group.getGroupName()+"\")' class='btn btn-primary btn-sm' data-toggle='modal' data-target='#completeUserList'>Add User</button>");
				stringBuffer.append("</div>");
				stringBuffer.append("<div id='collapse"+group.getGroupName()+"' class='panel-collapse collapse' role='tabpanel' aria-labelledby='heading"+group.getGroupName()+"'>");
				stringBuffer.append("<div class='panel-body'>");

				stringBuffer.append("<table style='width:100%;'><tr><td width='50%'  align='center' valign='top'>");

				stringBuffer.append("<table class='table-bordered table-striped' style='width:95%;' border='1'>");
				stringBuffer.append("<tr> <th>Folder</th> <th align='center' width='50px'>None</th> <th align='center' width='50px'>Read</th> <th align='center' width='50px'>Read/Write</th> </tr>");
				for(RepositoryFolder folder : folders){


					stringBuffer.append("<tr><td>"+folder.getFolderPath()+"</td> " );
					Set<RepositoryGroupFolderAccess> access = folder.getRepositoryGroupFolderAccesses();

					boolean hasAccess = false;


					for(RepositoryGroupFolderAccess folderAccess : access){

						if(folderAccess.getRepoGroup().getId() == group.getId()){
							hasAccess = true;
							if("".equalsIgnoreCase(folderAccess.getAccess())){
								stringBuffer.append("<td align='center'><input type='radio' checked='checked' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value=''></td> " );
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='r'></td> " );
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='rw'></td> </tr>");
							}else if("r".equalsIgnoreCase(folderAccess.getAccess())){
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value=''></td> " );
								stringBuffer.append("<td align='center'><input type='radio' checked='checked' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='r'></td> " );
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='rw'></td> </tr>");
							}else if("rw".equalsIgnoreCase(folderAccess.getAccess())){
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value=''></td> " );
								stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='r'></td> " );
								stringBuffer.append("<td align='center'><input type='radio' checked='checked' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='rw'></td> </tr>");
							}
							break;
						}
					}


					if(!hasAccess){

						stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value=''></td> " );
						stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='r'></td> " );
						stringBuffer.append("<td align='center'><input type='radio' onclick='updateGroupFolderAccess("+group.getId()+","+folder.getId()+",this.value)' name='fld"+folder.getId()+"grp"+group.getId()+"' value='rw'></td> </tr>");

					}





					System.out.println("--------------------------------------------------------------------------------------------------------------------");

				}
				stringBuffer.append("</table></td><td align='center' valign='top'>");

				stringBuffer.append("<table id='usermapwithgrouptable"+group.getId()+"' class='table-bordered table-striped' style='width: 95%;'>");

				stringBuffer.append("<tr><td colspan='4' align='center'>Users</td></tr>");
				stringBuffer.append("<tr>");
				stringBuffer.append("<td align='center' width='40%'>Login Id</td>");
				stringBuffer.append("<td align='center' width='20%'>SAPCode</td>");
				stringBuffer.append("<td align='center' width='40%'>Email</td>");

				stringBuffer.append("</tr>");

				Set<RepositoryGroupUserMap> users = group.getRepositoryGroupUserMaps();
				for( RepositoryGroupUserMap user: users ){
					stringBuffer.append("<tr><td align='left'> "+user.getUser().getLoginId()+" </td><td align='center' name='userMapTableSAPCode"+group.getId()+"'>"+user.getUser().getSapCode()+"</td><td align='left'> "+user.getUser().getEmailId()+" </td></tr>");
				}


				stringBuffer.append("</table></td></tr></table>");

				stringBuffer.append("</div>");
				stringBuffer.append("</div>");
				stringBuffer.append("</div>");

				users = null;

			}

			svnRepository = null;
			groups = null;
			folders = null;

			return stringBuffer.toString();

		} catch (Exception e){
			e.printStackTrace();
		}
		return "Some Error Occured";
	}

	@RemoteMethod
	public String addUsersToGroup(int repoId , int groupId , String[] userList) {

		List<RepositoryGroupUserMap> groupUserMap = RepositoryGroupUserMap.findAllRepositoryGroupUserMapByGroup(groupId);
		for (RepositoryGroupUserMap repositoryGroupUserMap : groupUserMap) {
			repositoryGroupUserMap.remove();
		}
		groupUserMap = null;

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<tr><td colspan='4' align='center'>Users</td></tr>");
		stringBuffer.append("<tr>");
		stringBuffer.append("<td align='center' width='40%'>Login Id</td>");
		stringBuffer.append("<td align='center' width='20%'>SAPCode</td>");
		stringBuffer.append("<td align='center' width='40%'>Email</td>");
		stringBuffer.append("</tr>");

		for (int i = 0; i < userList.length; i++) {
			RepositoryGroupUserMap map = new RepositoryGroupUserMap();
			map.setGroupName(RepositoryGroup.findRepositoryGroup(groupId));
			map.setUser(User.findUserDataBySAPCode(userList[i]));

			stringBuffer.append("<tr><td align='left'> "+map.getUser().getLoginId()+" </td><td align='center' name='userMapTableSAPCode"+groupId+"'>"+map.getUser().getSapCode()+"</td><td align='left'> "+map.getUser().getEmailId()+" </td></tr>");

			map.merge();
			map.flush();
			map=null;
		}

		return stringBuffer.toString();
	}

	@RemoteMethod
	public String updateGroupFolderAccess(int groupId , int folderId , String access) {

		try {
			List<RepositoryGroupFolderAccess> groupAccess = RepositoryGroupFolderAccess.findAllRepositoryGroupAndFolder(groupId, folderId);

			if(groupAccess.size() == 0){
				RepositoryGroupFolderAccess tempAccess = new RepositoryGroupFolderAccess();
				tempAccess.setRepoGroup(RepositoryGroup.findRepositoryGroup(groupId));
				tempAccess.setFolder(RepositoryFolder.findRepositoryFolder(folderId));
				tempAccess.setAccess(access);
				tempAccess.setCreatedOn(new Date());
				tempAccess.setStatus("ACTIVE");
				tempAccess.merge();

				tempAccess.flush();
				tempAccess = null;
			}else{
				groupAccess.get(0).setAccess(access);
				groupAccess.get(0).merge();
			}
			groupAccess = null;

			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Some Error Occured";

	}

	@RemoteMethod
	public String getDataToPopulatConfirmationTab(int repoId ) {

		JSONObject result = new JSONObject();
		SvnRepository repository = SvnRepository.findSvnRepository(repoId);

		//GENERALINFO
		try {

			JSONObject generalInfo = new JSONObject();
			generalInfo.put("confirmProjectSAPCode", repository.getProjectSapCode());
			generalInfo.put("confirmProjectName", repository.getProjectName());
			generalInfo.put("confirmPmSAPCode", repository.getProjectManager().getSapCode());
			generalInfo.put("confirmPmName", repository.getProjectManager().getFullName());
			generalInfo.put("confirmAltApproverSAPCode", repository.getAlternateApprover().getSapCode());
			generalInfo.put("confirmAltApproverName", repository.getAlternateApprover().getFullName());
			generalInfo.put("confirmAltApproverEmail", repository.getAlternateApprover().getEmailId());
			generalInfo.put("confirmRepositoryName", repository.getRepositoryName());
			generalInfo.put("confirmCMUserCount", repository.getCmtoolUserCount());
			generalInfo.put("confirmServiceStartDate", repository.getServiceStartDate());
			generalInfo.put("confirmServiceEndDate", repository.getServiceEndDate());
			generalInfo.put("confirmSpocSAPCode", repository.getSpoc().getSapCode());
			generalInfo.put("confirmSpocName", repository.getSpoc().getFullName());
			generalInfo.put("confirmSpocEmail", repository.getSpoc().getEmailId());

			result.put("GENERALINFO", generalInfo);

		} catch (JSONException e) {
			e.printStackTrace();
		}


		//USERLIST
		try {
			Set<RepositoryUserMap> userMap = repository.getRepositoryUserMaps();
			StringBuffer userList = new StringBuffer();
			for (RepositoryUserMap repositoryUserMap : userMap) {
				userList.append("<tr><td>").append(repositoryUserMap.getUser().getLoginId()).append("</td><td>").append(repositoryUserMap.getUser().getSapCode()).append("</td></tr>");
			}
			result.put("USERLIST", userList.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


		//GROUPLIST
		try {
			Set<RepositoryGroup> groupMap = repository.getRepositoryGroups();
			StringBuffer userList = new StringBuffer();
			for (RepositoryGroup repositoryGroupMap : groupMap) {
				userList.append("<tr><td>").append(repositoryGroupMap.getGroupName()).append("</td></tr>");
			}
			result.put("GROUPLIST", userList.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}



		//GROUPUSERMAP
		try {
			Set<RepositoryGroup> groupUserMap = repository.getRepositoryGroups();
			StringBuffer groupUser = new StringBuffer();
			for (RepositoryGroup repositoryGroup : groupUserMap) {

				Set<RepositoryGroupUserMap> userMap = repositoryGroup.getRepositoryGroupUserMaps();
				groupUser.append("<tr><td rowspan='").append(userMap.size()).append("'>").append(repositoryGroup.getGroupName()).append("</td>");
				for (RepositoryGroupUserMap repositoryGroupUserMap : userMap) {
					groupUser.append("<td>").append(repositoryGroupUserMap.getUser().getLoginId()).append("</td></tr><tr>");

				}
				groupUser.append("</tr>");
			}

			System.out.println(groupUser);

			result.put("GROUPUSERMAP", groupUser.toString());

		} catch (JSONException e) {
			e.printStackTrace();
		}


		//ACCESS
		try {
			Set<RepositoryGroup> groupMap = repository.getRepositoryGroups();
			Set<RepositoryFolder> folders = repository.getRepositoryFolders();

			StringBuffer userList = new StringBuffer();

			userList.append("<tr><td></td>");
			for (RepositoryFolder repositoryFolder : folders) {
				userList.append("<td>").append(repositoryFolder.getFolderPath()).append("</td>");
			}
			userList.append("</tr>");


			for (RepositoryGroup repositoryGroupMap : groupMap) {
				userList.append("<tr><td>").append(repositoryGroupMap.getGroupName()).append("</td>");

				Set<RepositoryGroupFolderAccess> access = repositoryGroupMap.getRepositoryGroupFolderAccesses();

				for (RepositoryFolder repositoryFolder : folders) {

					for (RepositoryGroupFolderAccess repositoryGroupFolderAccess : access) {

						if( repositoryGroupFolderAccess.getFolder().getId() ==  repositoryFolder.getId()){


							userList.append("<td>").append(repositoryGroupFolderAccess.getAccess() ).append("</td>");


						}

					}

				}

				userList.append("</tr>");

			}
			result.put("ACCESS", userList.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}








		return result.toString();
	}

	@RemoteMethod
	public void removeFromUserRepositoryMapping(int repoId , int userId){
		RepositoryUserMap.removeRepositoryUserMapByRepositoryAndUser(repoId , userId);
		RepositoryGroupUserMap.findRepositoryGroupUserMapByepositoryAndUser(repoId , userId);
	}


	@RemoteMethod
	public void removeGroupRepositoryMapping(int repoId , int groupId){
		RepositoryGroup.removeRepositoryGroupByRepositoryAndGroup(repoId , groupId);
	}

	@RemoteMethod
	public void removeFolderRepositoryMapping(int repoId , int folderId){

		RepositoryFolder.removeRepositoryFolderByRepositoryAndGroup(repoId , folderId);

	}

	@RemoteMethod
	public String findRepositoryByName(String repoName){

		JSONObject repository = new JSONObject();
		JSONArray groups = new JSONArray();
		try {

			SvnRepository repo = SvnRepository.findRepositoryByName(repoName);
			repository.put("repositoryId", repo.getId());
			repository.put("repositoryName", repo.getRepositoryName());
			repository.put("projectSapCode", repo.getProjectSapCode());
			repository.put("projectName", repo.getProjectName());
			repository.put("projectManager", repo.getProjectManager().getFullName() + " ( "+ repo.getProjectManager().getSapCode() + " ) ");
			repository.put("serviceStartDate", repo.getServiceStartDate());
			repository.put("serviceEndDate", repo.getServiceEndDate());
			repository.put("projectSpoc", repo.getSpoc().getFullName() + " ( "+ repo.getSpoc().getSapCode() + " ) ");
			repository.put("alternameApprover", repo.getAlternateApprover().getFullName() + " ( "+ repo.getAlternateApprover().getSapCode() + " ) ");
			repository.put("status", repo.getStatus());


			List<RepositoryGroup> repoGroup = RepositoryGroup.findGroupsByRepository(repo.getId());

			for(RepositoryGroup repositoryGroup : repoGroup ){
				groups.put( new JSONObject().put("value", repositoryGroup.getId()).put("text", repositoryGroup.getGroupName()));
			}

			repository.put("groups", groups);

		} catch (JSONException e) {
			e.printStackTrace();
		}


		return repository.toString();
	}

	@RemoteMethod
	public void saveAccessRequest(String[] accessRequests ){

		User requestedUser = (User)WebContextFactory.get().getHttpServletRequest().getSession().getAttribute("validUser");

		try {
			for (int i = 0; i < accessRequests.length; i++) {

				JSONObject obj = new JSONObject(accessRequests[i]);

				SvnRequestRepositoryAccess accessRequest = new SvnRequestRepositoryAccess();
				accessRequest.setRepository(SvnRepository.findSvnRepository( Integer.parseInt( obj.getString("repository") ) ) );
				accessRequest.setUser( User.findUserDataBySAPCode(  obj.getString("userSapCode") ) );
				accessRequest.setRepoGroup( RepositoryGroup.findRepositoryGroup(  Integer.parseInt( obj.getString("repoSitoryGroup") ) ) );

				accessRequest.setRequestedBy(requestedUser);
				accessRequest.setRequestedOn(new Date());
				accessRequest.setStatus("Waiting For Approval");

				accessRequest = accessRequest.merge();


				RequestMaster requestMaster = new RequestMaster();
				requestMaster.setApplication("SVN Access Request");
				requestMaster.setRequest("Repository access request --> Repository : "+accessRequest.getRepository().getRepositoryName()+ " ||  Group : " +accessRequest.getRepoGroup().getGroupName() + " || User : "+accessRequest.getUser().getFullName()+"("+accessRequest.getUser().getSapCode()+")");
				requestMaster.setRequestorId(requestedUser.getId());

				requestMaster.setDateTime(Calendar.getInstance());
				requestMaster.setRequestId(accessRequest.getId());
				requestMaster.setIsApproved("Pending...");
				requestMaster.setApproverId(accessRequest.getRepository().getProjectManager().getId());

				requestMaster.merge();

				requestMaster.flush();
				accessRequest.flush();

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	@RemoteMethod
	public String fetchProjectData( String projectCode){		
		JSONObject projectObject = new JSONObject();
		try {
			
			ProjectMaster pms = ProjectMaster.findProjectCodeBySap(projectCode);
			projectObject.put("projectName", pms.getName());
			projectObject.put("pmSapCode", pms.getPmSapCode());
			projectObject.put("pmName", pms.getPmName());
			projectObject.put("pmEmail", pms.getPmEmail());
			projectObject.put("lobName", pms.getLobName());
			projectObject.put("duName", pms.getDuName());
			projectObject.put("sduName", pms.getSduName());
			projectObject.put("noOfHClCMToolUser", pms.getTeamSize());
			projectObject.put("datetimepicker1", pms.getStartDate());
			projectObject.put("datetimepicker2", pms.getEndDate());
			projectObject.put("alternateApproverSapCode", pms.getAltApprSap());
			projectObject.put("alternateApproverName", pms.getAltApprName());
			projectObject.put("alternateApproverEmail", pms.getAltApprEmail());
			return projectObject.toString();
			
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RemoteMethod
	public String fetchLinkedProjectDetails( String linkedProjectSapCode, String sapCode){		
		System.out.println("*********Service"+linkedProjectSapCode+"********"+sapCode);
		JSONObject linkedprojectObject = new JSONObject();
		try {
			ProjectMaster result = ProjectMaster.findTotalTeamSize(linkedProjectSapCode, sapCode);
			linkedprojectObject.put("noOfHClCMToolUser", result.getTeamSize());
			return linkedprojectObject.toString();			
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
}
