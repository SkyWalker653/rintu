package tools.automation.web;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tools.automation.bean.PmsmartProject;

@RequestMapping("/pmsmart")
@Controller
public class PMSmartController {

	@RequestMapping(value="project/create", produces = "text/html")
	public String newProjectForm(HttpServletRequest httpServletRequest) {
		return "pmsmart/project/create";
	}

	@RequestMapping(value="project/list", produces = "text/html")
	public String listProject(HttpServletRequest httpServletRequest) {
		return "pmsmart/project/list";
	}
	
	@RequestMapping(value="request/access", produces = "text/html")
	public String createAccessRequest(HttpServletRequest httpServletRequest) {
		return "pmsmart/request/access";
	}

	@RequestMapping(value="request/list", produces = "text/html")
	public String listAccessRequest(HttpServletRequest httpServletRequest) {
		return "pmsmart/request/list";
	}

}
