package tools.automation.crons;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import tools.automation.bean.Notification;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.SvnRepositoryAccessText;
import tools.automation.bean.SvnedgeServers;
import tools.automation.utility.Mailer;
import tools.automation.utility.RepositoryClient;
import tools.automation.utility.RepositoryUtility;

public class Schedular {


	

	//Runs in every 60 mins
	@Scheduled(fixedDelay=1*60*1000)
	public void createRepository(){ 
		
		String repositoryUuid = null;
		
		System.out.println("Schedular : Repository Creation : Last executed at : "+new Date());
		SvnedgeServers server = SvnedgeServers.findCurrentSvnedgeServer();
		System.out.println("Server : "+ server.getName());
		List<SvnRepository> repository = SvnRepository.findAllNewRepositories();
		System.out.println("No of Repositories to be created : "+repository.size());
		
		
		
		for (SvnRepository svnRepository : repository) {
			
			
			//Create Repository
			System.out.println("Creating repository : "+svnRepository.getRepositoryName());
			try {
				
				int responseCode = RepositoryUtility.createRepository( svnRepository.getRepositoryName() , server );
				
				if(responseCode == 0){
					svnRepository.setStatus("Auto Creation Failed");
					svnRepository = svnRepository.merge();
					continue;
				}
				svnRepository.setIsRepositoryCreated(1);
				
				
				//Provide Access
				String accessText = SvnRepositoryAccessText.findRepositoryAccessByRepositoryId(svnRepository.getId());
				Integer isAccessUpdated = RepositoryClient.updateAccessFile(server,accessText,"NEW");
				svnRepository.setIsAccessUpdated(isAccessUpdated);
				svnRepository.setAccessfileLastUpdateServer(Calendar.getInstance());
				svnRepository.setRepoAccess(accessText.getBytes());
				
				
				//Fetch UUID
				System.out.println("Fetching UUID for the repository ... ");			
				repositoryUuid = RepositoryClient.getUUID(svnRepository.getRepositoryName(), server);
				svnRepository.setRepositoryUuid(repositoryUuid);
				
				//Change Folder Permission
				System.out.println("Changing Repository Permision ...");
				long isPermissionModified = RepositoryClient.modifyRepositoryAccess(server, svnRepository.getRepositoryName());
				System.out.println("Is Permission Modified..." + isPermissionModified);
				
				
				//Create Folders
				System.out.println("Creating folders for the repository ... ");
				long isFoldersCreated = RepositoryClient.createFolder(svnRepository, server);
				svnRepository.setIsFoldersCreated( (int)isFoldersCreated );
				
				
				if( svnRepository.getIsRepositoryCreated() == 1 &&
					svnRepository.getIsAccessUpdated() == 1 &&
					svnRepository.getRepositoryUuid() != null  &&
					svnRepository.getIsFoldersCreated() == 1 
				){

					svnRepository.setStatus("Active");
				}else {
					
					svnRepository.setStatus("Incomplete");
					
				}
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				
				//Update Record in Database
				svnRepository.setServer(server.getId());
				svnRepository = svnRepository.merge();
				
			}
			
			//Update Notification
			Notification.addNotification( svnRepository.getProjectManager().getId() , "SVN" , "Repository Created", svnRepository.getRepositoryName() + " repository has been created for "+ svnRepository.getProjectName() + " project.");
			Notification.addNotification( svnRepository.getSpoc().getId() , "SVN" , "Repository Created", svnRepository.getRepositoryName() + " repository has been created for "+ svnRepository.getProjectName() + " project.");
			Notification.addNotification( svnRepository.getAlternateApprover().getId() , "SVN" , "Repository Created", svnRepository.getRepositoryName() + " repository has been created for "+ svnRepository.getProjectName() + " project.");
			
			//Mailer.createRepositoryTemplateAndSend(svnRepository);
						
		}
		
	}
	
}
