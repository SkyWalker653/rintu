package tools.automation.web;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tools.automation.bean.Notification;
import tools.automation.bean.RequestMaster;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.User;
import tools.automation.utility.DataSecurity;

@RequestMapping("/svn")
@Controller
public class SvnController {

	@RequestMapping(value="repository", produces = "text/html")
	public String listRepository(HttpServletRequest httpServletRequest) {
		return "svn/repository/list";
	}

	@RequestMapping(value="repository/create", produces = "text/html")
	public String createRepository(HttpServletRequest httpServletRequest) {
		return "svn/repository/create";
	}

	@RequestMapping(value="repository/edit", produces = "text/html")
	public ModelAndView editRepository(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		DataSecurity dataSecurity = new DataSecurity();
		SvnRepository repository = SvnRepository.findSvnRepository(Integer.parseInt( dataSecurity.decryptTextURL(id) ));
		ModelAndView view = new ModelAndView("svn/repository/edit","repository", repository);
		return view;
	}
	
	@RequestMapping(value="request/access", produces = "text/html")
	public String requestAccess(HttpServletRequest httpServletRequest) {
		return "svn/request/access";
	}
	
	@RequestMapping(value="request/accesslist", produces = "text/html")
	public String requestAccessList(HttpServletRequest httpServletRequest) {
		return "svn/request/requestaccesslist";
	}
	
	@RequestMapping(value="request/migration", produces = "text/html")
	public String requestMigration(HttpServletRequest httpServletRequest) {
		return "svn/request/migration";
	}
	
	@RequestMapping(value="restoration/file", produces = "text/html")
	public String restorationFile(HttpServletRequest httpServletRequest) {
		return "svn/restoration/file";
	}
	
	@RequestMapping(value="restoration/list", produces = "text/html")
	public String restorationRepositoryList(HttpServletRequest httpServletRequest) {
		return "svn/restoration/list";
	}
	
	@RequestMapping(value="restoration/repository", produces = "text/html")
	public String restorationRepository(HttpServletRequest httpServletRequest) {
		return "svn/restoration/repository";
	}

	@RequestMapping(method = RequestMethod.POST, value="restoration/submitforapproval" ,  produces = "text/html")
	public String submitForApproval(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		
		// TODO: Send mail "Waiting For Approval"
		
		SvnRepository svnRepository = SvnRepository.findSvnRepository( Integer.parseInt( id ));
		svnRepository.setStatus("Waiting for approval");
		svnRepository.merge();
		
		
		RequestMaster requestMaster = new RequestMaster();
		requestMaster.setApplication("New Repository Request");
		requestMaster.setRequest("Repository access request --> Repository : "+svnRepository.getRepositoryName() );
		requestMaster.setRequestorId(svnRepository.getSpoc().getId());

		requestMaster.setDateTime(Calendar.getInstance());
		requestMaster.setRequestId(svnRepository.getId());
		requestMaster.setIsApproved("Pending...");
		requestMaster.setApproverId(svnRepository.getProjectManager().getId());

		requestMaster.merge();

		requestMaster.flush();
		
		
		
		
		if(svnRepository.getServer() == null){
			Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Request for new repository", ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName() + " has requested for new repository and is waiting for approval.");
			Notification.addNotification( ((User)httpServletRequest.getSession().getAttribute("validUser")).getId() , "SVN" , "Request for new repository", "You have requested for new repository which is pending for approval");
			Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Request for new repository", ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName() + " has requested for new repository and is waiting for approval.");
		}
		else{
			Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Request for new repository", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
			Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Request for new repository", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
			Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Request for new repository", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		}
		
		svnRepository.flush();
		
		return "svn/repository/list";
	}

	@RequestMapping(value="repository/approved" ,  produces = "text/html")
	public String approved(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		
		
		
		SvnRepository svnRepository = SvnRepository.findSvnRepository( Integer.parseInt( id ));
		
		/*try {
			Mailer.createRepositoryApproveTemplateAndSend(repository);
		} catch (Exception e) {
			System.out.println("Sending Mail Failed ...");
		}*/
		
		svnRepository.setStatus("Approved");
		svnRepository.setStage1Approved( (User)httpServletRequest.getSession().getAttribute("validUser") );
		svnRepository.setStage2Approved( (User)httpServletRequest.getSession().getAttribute("validUser") );
		
		svnRepository.merge();
		
		Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Approved", "Repository("+svnRepository.getRepositoryName()+") creation request has been approved by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		
		svnRepository.flush();
		
		return "svn/repository/list";
	}
	
	@RequestMapping(value="repository/rejected" ,  produces = "text/html")
	public String rejected(@RequestParam("id") String id, HttpServletRequest httpServletRequest) {
		
		// TODO: Send mail "Rejected"
		
		
		SvnRepository svnRepository = SvnRepository.findSvnRepository( Integer.parseInt( id ));
		svnRepository.setStatus("Rejected");
		svnRepository.setStage1Approved( (User)httpServletRequest.getSession().getAttribute("validUser") );
		svnRepository.merge();
		
		Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Rejected", "Repository("+svnRepository.getRepositoryName()+") creation request has been rejected by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Rejected", "Repository("+svnRepository.getRepositoryName()+") creation request has been rejected by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Rejected", "Repository("+svnRepository.getRepositoryName()+") creation request has been rejected by "+ ((User)httpServletRequest.getSession().getAttribute("validUser")).getFullName());
		
		svnRepository.flush();
		
		return "svn/repository/list";
	}
	
	
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadMultipleFileHandler(@RequestParam("incidentNumber") String incidentNumber, @RequestParam("file") MultipartFile[] files) {

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			
			System.out.println();
			
			String name = file.getOriginalFilename();
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				
				
				if (!dir.exists())
					dir.mkdirs();

				System.out.println(dir.getAbsolutePath());
				
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				
				System.out.println(serverFile.getAbsolutePath());
				
				BufferedOutputStream stream = new BufferedOutputStream(	new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				System.out.println("Server File Location = " + serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name + "<br>";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}
		
}
