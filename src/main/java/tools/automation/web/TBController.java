package tools.automation.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.automation.bean.SvnRepository;
import tools.automation.bean.User;
import tools.automation.utility.DataSecurity;

@RequestMapping("/tb")
@Controller
public class TBController {

	@RequestMapping(value="server/status", produces = "text/html")
	public String listRepository(HttpServletRequest httpServletRequest) {
		return "tb/server/status";
	}
	
	@RequestMapping(value="instance/create", produces = "text/html")
	public String createInstance(HttpServletRequest httpServletRequest) {
		return "tb/instance/create";
	}
	
	@RequestMapping(value="instance/list", produces = "text/html")
	public String listInstance(HttpServletRequest httpServletRequest) {
		return "tb/instance/list";
	}
	
}
