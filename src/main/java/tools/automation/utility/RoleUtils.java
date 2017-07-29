package tools.automation.utility;

import java.util.List;

import tools.automation.bean.Role;
import tools.automation.bean.User;
import tools.automation.bean.UserRoleMap;

public class RoleUtils {
	
	public static boolean isSuperAdmin(User currentUser){
		boolean isSuperAdmin = false;
		
		try {
			
			List<Role> roles = UserRoleMap.findAllRolesByUserId(currentUser.getId());
			for (Role role : roles) {
				if ("SUPER ADMIN".equalsIgnoreCase(role.getRoleName())){
					isSuperAdmin = true;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isSuperAdmin;
	}
	
	
}
