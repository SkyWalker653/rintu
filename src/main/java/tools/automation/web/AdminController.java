package tools.automation.web;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tools.automation.bean.Notification;
import tools.automation.bean.RequestMaster;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.SvnRequestRepositoryAccess;
import tools.automation.bean.User;


@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@RequestMapping( value="/user/list", produces = "text/html")
    public String userList(HttpServletRequest httpServletRequest) {
		return "admin/user/list";
    }
	
	@RequestMapping( value="/user/edit", produces = "text/html")
    public String getUserEditData(HttpServletRequest httpServletRequest) {
		return "admin/user/edit";
    }
	
	
	@RequestMapping( value="/role/list", produces = "text/html")
    public String roleList(HttpServletRequest httpServletRequest) {
		return "admin/role/list";
    }
	
	@RequestMapping( value="/role/new", produces = "text/html")
    public String addRole(HttpServletRequest httpServletRequest) {
		return "admin/role/new";
    }
	
	@RequestMapping( value="/role/edit", produces = "text/html")
    public String editRole(HttpServletRequest httpServletRequest) {
		return "admin/role/edit";
    }
	
	@RequestMapping( value="/dashboardmenu/list", produces = "text/html")
    public String dashboardmenuList(HttpServletRequest httpServletRequest) {
		return "admin/dashboardmenu/list";
    }
	
	@RequestMapping( value="/server/list", produces = "text/html")
    public String serverList(HttpServletRequest httpServletRequest) {
		return "admin/server/list";
    }
	
	@RequestMapping( value="/svn/cleanup", produces = "text/html")
    public String svnCleanUp(HttpServletRequest httpServletRequest) {
		return "admin/svn/cleanup";
    }
	
	@RequestMapping( value="/pei/template", produces = "text/html")
    public String peiTemplate(HttpServletRequest httpServletRequest) {
		return "admin/pei/template";
    }
	@RequestMapping( value="/pendingapproval/list", produces = "text/html")
    public String requestPendingForApproval(HttpServletRequest httpServletRequest) {
		return "admin/pendingapproval/list";
    }
	
	@RequestMapping( value="/myrequest/list", produces = "text/html")
    public String myRequest(HttpServletRequest httpServletRequest) {
		return "admin/myrequest/list";
    }
	
	@RequestMapping(value="/pendingapproval/approved" ,  produces = "text/html")
	public String pendingApprovalApproved(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		
		RequestMaster requestMaster = RequestMaster.findRequestMaster( Integer.parseInt(id) );
		requestMaster.setIsApproved("Approved By "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName() );
		requestMaster.merge();
		
		String requestFor = requestMaster.getApplication();
		
		if("SVN Access Request".equalsIgnoreCase(requestFor)){
			
			SvnRequestRepositoryAccess requestRepositoryAccess = SvnRequestRepositoryAccess.findSvnRequestRepositoryAccess(requestMaster.getRequestId());
			requestRepositoryAccess.setApprovedOn(new Date());
			requestRepositoryAccess.setApprovedBy((User)httpServletRequest.getSession().getAttribute("validUser"));
			requestRepositoryAccess.setStatus("Approved");
			requestRepositoryAccess.merge();
		
		}else if("New Repository Request".equalsIgnoreCase(requestFor)){
			
			SvnRepository svnRepository = SvnRepository.findSvnRepository( Integer.parseInt( id ));
			
			/*try {
				Mailer.createRepositoryApproveTemplateAndSend(repository);
			} catch (Exception e) {
				System.out.println("Sending Mail Failed ...");
			}*/
			
			svnRepository.setStatus("Approved");
			svnRepository.setStage1Approved( (User)httpServletRequest.getSession().getAttribute("validUser") );
			svnRepository.merge();
			
			Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
			Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
			Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
			
			svnRepository.flush();
			
		}
		
		
		
		
		
		// TODO: Send mail "Approved"
		
		/*try {
			Mailer.createRepositoryApproveTemplateAndSend(repository);
		} catch (Exception e) {
			System.out.println("Sending Mail Failed ...");
		}*/

	/*	
		Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		
		svnRepository.flush();
		*/
		return "admin/pendingapproval/list";
	}
	
	@RequestMapping(value="/pendingapproval/rejected" ,  produces = "text/html")
	public String pendingApprovalRejected(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		
		RequestMaster requestMaster = RequestMaster.findRequestMaster( Integer.parseInt(id) );
		requestMaster.setIsApproved("Rejected By "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName() );
		requestMaster.merge();
		
		String requestFor = requestMaster.getApplication();
		
		if("SVN Access Request".equalsIgnoreCase(requestFor)){
			
			SvnRequestRepositoryAccess requestRepositoryAccess = SvnRequestRepositoryAccess.findSvnRequestRepositoryAccess(requestMaster.getRequestId());
			requestRepositoryAccess.setApprovedOn(new Date());
			requestRepositoryAccess.setApprovedBy((User)httpServletRequest.getSession().getAttribute("validUser"));
			requestRepositoryAccess.setStatus("Rejected");
			requestRepositoryAccess.merge();
		
		}
		
		
		
		
		
		// TODO: Send mail "Approved"
		
		/*try {
			Mailer.createRepositoryApproveTemplateAndSend(repository);
		} catch (Exception e) {
			System.out.println("Sending Mail Failed ...");
		}*/

	/*	
		Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		
		svnRepository.flush();
		*/
		return "admin/pendingapproval/list";
	}
}
