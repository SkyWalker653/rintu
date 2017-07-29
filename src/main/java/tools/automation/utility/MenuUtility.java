package tools.automation.utility;

import java.util.List;

import tools.automation.bean.MenuDashboard;
import tools.automation.bean.User;

public class MenuUtility {
	
	public List<MenuDashboard> getMenu(User validUser){
		
		List<MenuDashboard> userMenu = User.findAllDashboardMenuByUserRole(validUser);
		
		return userMenu;
		
	}
	
	
	public static String checkUrl(String url){
		
		if(url != null && !"".equalsIgnoreCase(url) ){
			return "/toolsautomation/"+url;
		}
		return "#";
	}

}
