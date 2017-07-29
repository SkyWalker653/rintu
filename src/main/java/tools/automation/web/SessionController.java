package tools.automation.web;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tools.automation.bean.MenuDashboard;
import tools.automation.bean.Role;
import tools.automation.bean.RoleDashboardMenuMap;
import tools.automation.bean.RolePrimaryMenuMap;
import tools.automation.bean.RoleSubmenuMap;
import tools.automation.bean.User;
import tools.automation.bean.UserRoleMap;
import tools.automation.utility.LDAPUtils;

@RequestMapping("/sessioncontroller")
@Controller
public class SessionController{

	@RequestMapping(method = RequestMethod.POST, produces = "text/html")
	public String login(@Valid User user, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {

		Set<Integer> dashboardAccess = new HashSet<Integer>();
		Set<Integer> primaryMenuAccess = new HashSet<Integer>();
		Set<Integer> subMenuAccess = new HashSet<Integer>();


		LDAPUtils ldapUtils = new LDAPUtils();
		if(ldapUtils.authenticate(user.getLoginId(), user.getPassword())){

			long count = User.countUsersForLogin(user);
			if(count <= 0){
				ldapUtils.createUserByLoginId(user.getLoginId());
			}

			User validUser = User.findUserForLogin(user);

			List<Role> roles = UserRoleMap.findAllRolesByUserId(validUser.getId());
			for (Role role : roles) {

				Set<RoleDashboardMenuMap> dashboardMenuAccessList = role.getRoleDashboardMenuMaps();
				for (RoleDashboardMenuMap roleDashboardMenuMap : dashboardMenuAccessList) {
					dashboardAccess.add(roleDashboardMenuMap.getMenuDashboard().getId());
				}

				Set<RolePrimaryMenuMap> primaryMenuAccessList = role.getRolePrimaryMenuMaps();
				for (RolePrimaryMenuMap rolePrimaryMenuMap : primaryMenuAccessList) {
					primaryMenuAccess.add(rolePrimaryMenuMap.getPrimaryManu().getId());
				}

				Set<RoleSubmenuMap> subMenuAccessList = role.getRoleSubmenuMaps();
				for (RoleSubmenuMap roleSubMenuMap : subMenuAccessList) {
					subMenuAccess.add(roleSubMenuMap.getMenuSub().getId());
				}

			}

			List<MenuDashboard> dashboardMenu = MenuDashboard.findAllMenuDashboards();

			httpServletRequest.getSession().setAttribute("validUser", validUser);
			httpServletRequest.getSession().setAttribute("dashboardmenu", dashboardMenu);
			httpServletRequest.getSession().setAttribute("dashboardAccess", dashboardAccess);
			httpServletRequest.getSession().setAttribute("primaryMenuAccess", primaryMenuAccess);
			httpServletRequest.getSession().setAttribute("subMenuAccess", subMenuAccess);

			return "home";

		}

		return "login";

	}

	@RequestMapping(value="logout",  method = RequestMethod.GET, produces = "text/html")
	public String logout(HttpServletRequest httpServletRequest) {

		httpServletRequest.getSession().invalidate();

		return "logout";


	}

}
