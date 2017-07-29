package tools.automation.service;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.web.multipart.MultipartFile;

@RemoteProxy
public class FileUploadService {

	@RemoteMethod
	public void uploadFile(MultipartFile files){
		
		String name = files.getClass().getCanonicalName();
		
		
		System.out.println(name);
		
	//	System.out.println(files.getFilename());
		
		/*System.out.println(files.length);
		
		for (int i = 0; i < files.length; i++) {
			System.out.println(files[i] +"\n");
		}*/
		
		
	}
	
	
}
