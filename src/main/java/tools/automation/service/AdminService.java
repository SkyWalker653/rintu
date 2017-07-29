package tools.automation.service;

import java.sql.SQLException;
import java.util.List;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tools.automation.bean.MenuDashboard;
import tools.automation.bean.MenuPrimary;
import tools.automation.bean.MenuSub;
import tools.automation.bean.PeiTemplateCategory;
import tools.automation.bean.RequestMaster;
import tools.automation.bean.Role;
import tools.automation.bean.RoleDashboardMenuMap;
import tools.automation.bean.RolePrimaryMenuMap;
import tools.automation.bean.RoleSubmenuMap;
import tools.automation.bean.SvnedgeServers;
import tools.automation.bean.User;
import tools.automation.bean.UserRoleMap;
import tools.automation.utility.DataSecurity;
import tools.automation.utility.ServerUtility;

@RemoteProxy
public class AdminService {

	@RemoteMethod
	public String getUserList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();
		List<User> users = User.findAllUsers();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (User user : users) {

				JSONArray usr = new JSONArray();

				usr.put(user.getFullName());
				usr.put(user.getLoginId());
				usr.put(user.getEmailId());
				usr.put(user.getSapCode());
				usr.put(user.getPhoneNumber());
				usr.put(user.getUserAuthentication());
				usr.put(user.getCreatedOn());
				usr.put(user.getCreatedBy());
				usr.put(user.getStatus());

				usr.put("<a href='/toolsautomation/admin/user/edit?id="+dataSecurity.encryptTextURL(user.getId()+"")+"'><img src='/toolsautomation/images/update.png'><a>");
				arr.put(usr); 

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
	public String getRoleList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();
		List<Role> roles = Role.findAllRoles();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (Role role : roles) {

				JSONArray usr = new JSONArray();
				usr.put(role.getRoleName());
				usr.put(role.getDescription());
				usr.put(role.getStatus());
				usr.put("<a href='/toolsautomation/admin/role/edit?id="+dataSecurity.encryptTextURL(role.getId()+"")+"'><img src='/toolsautomation/images/update.png'><a>");
				arr.put(usr); 

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
	public String getDashboardList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();
		List<MenuDashboard> dashboards = MenuDashboard.findAllMenuDashboards();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (MenuDashboard dashboard : dashboards) {

				JSONArray usr = new JSONArray();
				usr.put(dashboard.getImage());
				usr.put(dashboard.getName());
				usr.put(dashboard.getUrl());
				usr.put(dashboard.getStatus());
				usr.put("<a href='/toolsautomation/users/edit?id="+dataSecurity.encryptTextURL(dashboard.getId()+"")+"'><img src='/toolsautomation/images/update.png'><a>");
				arr.put(usr); 

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
	public String getServerList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();
		List<SvnedgeServers> servers = SvnedgeServers.findAllSvnedgeServerses();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (SvnedgeServers server : servers) {

				JSONArray usr = new JSONArray();
				
				if("Active Current".equalsIgnoreCase(server.getStatus())){
					usr.put("<input type='radio' name='currectserver' onclick='markAsCurrent("+server.getId()+",this)' checked='checked'>");
				}
				else{
					usr.put("<input type='radio' name='currectserver' onclick='markAsCurrent("+server.getId()+",this)'>");
				}
				
				usr.put(server.getName());
				usr.put(server.getIpAddress());
				usr.put(server.getCsvnPort());
				usr.put(server.getUserId());
				usr.put("******");
				usr.put(server.getUsedMemory() + " %");
				usr.put(server.getStatus());
				usr.put("<a href='/toolsautomation/users/edit?id="+dataSecurity.encryptTextURL(server.getId()+"")+"'><img src='/toolsautomation/images/update.png'><a>");
				arr.put(usr); 

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
	public void markAsCurrent(int id) {
		
		SvnedgeServers currentServer = SvnedgeServers.findCurrentSvnedgeServer();
		currentServer.setStatus("Active");
		currentServer.merge();
		currentServer.flush();
		
		SvnedgeServers server = SvnedgeServers.findSvnedgeServers(id);
		server.setStatus("Active Current");
		server.merge();
		server.flush();
		
	}

	
	

	@RemoteMethod
	public List<Role> getRoleListByUserId(String id) {
		DataSecurity dataSecurity = new DataSecurity();
		return UserRoleMap.findAllRolesByUserId(Integer.parseInt( dataSecurity.decryptTextURL(id) ) );
	}

	@RemoteMethod
	public List<Role> findOtherRolesByUserId(String id) {
		DataSecurity dataSecurity = new DataSecurity();
		return Role.findOtherRolesByUserId(Integer.parseInt( dataSecurity.decryptTextURL(id) ) );
	}

	@RemoteMethod
	public void addUserRoleMap(String userId , String[] roleId) {
		DataSecurity dataSecurity = new DataSecurity();

		for (int i = 0; i < roleId.length; i++) {

			UserRoleMap map = new UserRoleMap();
			map.setUser( User.findUser( Integer.parseInt( dataSecurity.decryptTextURL( userId ) ) ) );
			map.setRole(Role.findRole( Integer.parseInt( roleId[i] )));
			map.merge();
			map.flush();

		}

	}

	@RemoteMethod
	public void removeUserRoleMap(String userId , String[] roleId) {
		DataSecurity dataSecurity = new DataSecurity();

		for (int i = 0; i < roleId.length; i++) {
			UserRoleMap.findUserRoleMapByUserAndRole(  Integer.parseInt( dataSecurity.decryptTextURL( userId ) ) , Integer.parseInt( roleId[i] )  ).remove();
		}

	}


	@RemoteMethod
	public void updateRole(String roleId, String name , String description , String status , Integer[] dashboardList , Integer[] primaryMenuList , Integer[] subMenuList) {
		DataSecurity dataSecurity = new DataSecurity();

		Role role = Role.findRole( Integer.parseInt( dataSecurity.decryptTextURL( roleId ) ) );
		role.setRoleName(name);
		role.setDescription(description);
		role.setStatus(status);
		role.merge();
		role.flush();

		List<RoleDashboardMenuMap> existingRoleDashboardMap = RoleDashboardMenuMap.findMapByRole(   Integer.parseInt( dataSecurity.decryptTextURL( roleId ) ) );
		for (RoleDashboardMenuMap roleDashboardMenuMap : existingRoleDashboardMap) {
			roleDashboardMenuMap.remove();
		}

		List<RolePrimaryMenuMap> existingRolePrimaryMap = RolePrimaryMenuMap.findMapByRole( Integer.parseInt( dataSecurity.decryptTextURL( roleId ) ) );
		for (RolePrimaryMenuMap rolePrimaryMenuMap : existingRolePrimaryMap) {
			rolePrimaryMenuMap.remove();
		}

		List<RoleSubmenuMap> existingRoleSubMenuMap = RoleSubmenuMap.findMapByRole( Integer.parseInt( dataSecurity.decryptTextURL( roleId ) ) );
		for (RoleSubmenuMap rolePrimaryMenuMap : existingRoleSubMenuMap) {
			rolePrimaryMenuMap.remove(); 
		}

		for (int i = 0; i < dashboardList.length; i++) {
			RoleDashboardMenuMap map = new RoleDashboardMenuMap();
			map.setRole(role);
			map.setMenuDashboard(MenuDashboard.findMenuDashboard(dashboardList[i]));
			map.merge();
			map.flush();
		}

		for (int i = 0; i < primaryMenuList.length; i++) {
			RolePrimaryMenuMap map = new RolePrimaryMenuMap();
			map.setRole(role);
			map.setPrimaryManu(MenuPrimary.findMenuPrimary(primaryMenuList[i]));
			map.merge();
			map.flush();
		}

		for (int i = 0; i < subMenuList.length; i++) {
			RoleSubmenuMap map = new RoleSubmenuMap();
			map.setRole(role);
			map.setMenuSub(MenuSub.findMenuSub(subMenuList[i]));
			map.merge();
			map.flush();
		}

	}

	@RemoteMethod
	public void addNewRole(String name , String description , String status , Integer[] dashboardList , Integer[] primaryMenuList , Integer[] subMenuList) {

		Role role = new Role();
		role.setRoleName(name);
		role.setDescription(description);
		role.setStatus(status);
		role = role.merge();

		for (int i = 0; i < dashboardList.length; i++) {
			RoleDashboardMenuMap map = new RoleDashboardMenuMap();
			map.setRole(role);
			map.setMenuDashboard(MenuDashboard.findMenuDashboard(dashboardList[i]));
			map.merge();
			map.flush();
		}

		for (int i = 0; i < subMenuList.length; i++) {
			RoleSubmenuMap map = new RoleSubmenuMap();
			map.setRole(role);
			map.setMenuSub(MenuSub.findMenuSub(subMenuList[i]));
			map.merge();
			map.flush();
		}

		RoleSubmenuMap map = new RoleSubmenuMap();
		map.setRole(role);
		map.setMenuSub(MenuSub.findMenuSub(9));
		map.merge();
		map.flush();

		role.flush();

	}
	
	@RemoteMethod
	public List<PeiTemplateCategory> getAllPEITemplate() {

		List<PeiTemplateCategory> templateCategory = PeiTemplateCategory.findAllPeiTemplateCategorys();
		return templateCategory;
		
	}

	@RemoteMethod
	public String pendingApprovalList(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();
		User currentUser = (User) WebContextFactory.get().getSession().getAttribute("validUser");
		List<RequestMaster> requestMasters = RequestMaster.pendingApprovalList(currentUser.getId());

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (RequestMaster requests : requestMasters) {

				JSONArray req = new JSONArray();

				req.put(requests.getApplication()); 
				req.put(requests.getDateTime().getTime());
				req.put(requests.getRequest());
				req.put(User.getUserDataByID(requests.getRequestorId()).getFullName());
				req.put(requests.getIsApproved());
			

				StringBuffer actionOptions = new StringBuffer();
				if( requests.getIsApproved().contains("Pending") ){
					
						actionOptions.append("&nbsp;&nbsp;<a title='Approve' href='/toolsautomation/admin/pendingapproval/approved?id="+dataSecurity.encryptTextURL(requests.getId()+"")+"'><span class='glyphicon glyphicon-ok'></span><a>") ;
						actionOptions.append("&nbsp;&nbsp;<a title='Reject' href='/toolsautomation/admin/pendingapproval/rejected?id="+dataSecurity.encryptTextURL(requests.getId()+"")+"'><span class='glyphicon glyphicon-remove'></span><a>") ;
					
				}


				req.put(actionOptions.toString()); 

				arr.put(req); 

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
	public String getAllMyRequestMasters(int echo, int start, int max, String search) {

		DataSecurity dataSecurity = new DataSecurity();

		User currentUser = (User) WebContextFactory.get().getSession().getAttribute("validUser");
		List<RequestMaster> requestMasters = RequestMaster.findAllMyRequestMasters(currentUser.getId());

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (RequestMaster requests : requestMasters) {

				JSONArray req = new JSONArray();

				req.put(requests.getApplication());
				req.put(requests.getDateTime().getTime());
				req.put(requests.getRequest());
				req.put(User.getUserDataByID(requests.getRequestorId()).getFullName());
				req.put(requests.getIsApproved());
				arr.put(req); 

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
	public void refreshServerData()  {
		try {
			
			ServerUtility.updateServerUtilization();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	
}
