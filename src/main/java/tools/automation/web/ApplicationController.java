package tools.automation.web;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tools.automation.bean.SvnRepository;
import tools.automation.bean.User;
import tools.automation.utility.DataSecurity;

@RequestMapping("/application")
@Controller
public class ApplicationController {

	@RequestMapping(value="feedback", produces = "text/html")
	public String feedBack(HttpServletRequest httpServletRequest) {
		return "application/feedback";
	}

	@RequestMapping(value="contact", produces = "text/html")
	public String contactUs(HttpServletRequest httpServletRequest) {
		return "application/contact";
	}

	@RequestMapping(value="aboutus", produces = "text/html")
	public String aboutUs(HttpServletRequest httpServletRequest) {
		return "application/aboutus";
	}

	
}
