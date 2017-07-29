package tools.automation.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/dashboard")
@Controller
public class DashboardController {
	
	
	@RequestMapping( value="/admin", produces = "text/html")
    public String adminDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "admin/dashboard";
    }
	

	
	@RequestMapping( value="/svn", produces = "text/html")
    public String svnDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "svn/dashboard";
    }
	
	@RequestMapping( value="/pmsmart", produces = "text/html")
    public String pmsmartDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "pmsmart/dashboard";
    }
	
	
	@RequestMapping( value="/testlink", produces = "text/html")
    public String testlinkDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "commingsoon";
    }
	
	
	@RequestMapping( value="/bugzilla", produces = "text/html")
    public String bugzillaDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "commingsoon";
    }
	
	@RequestMapping( value="/report", produces = "text/html")
    public String reportDashoard(@RequestParam("id") String id , HttpServletRequest httpServletRequest) {
		return "commingsoon";
    }
	
	
	
	
	
	@RequestMapping(produces = "text/html")
    public String home(HttpServletRequest httpServletRequest) {
		return "home";
    }
	
	
	
}
