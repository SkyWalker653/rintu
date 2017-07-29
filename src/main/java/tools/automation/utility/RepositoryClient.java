package tools.automation.utility;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.fs.FSRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.ISVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import tools.automation.bean.RepositoryFolder;
import tools.automation.bean.SvnRepository;
import tools.automation.bean.SvnedgeServers;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;




public class RepositoryClient {

	private static SVNClientManager ourClientManager;
	private static Hashtable<String, String> config = new Hashtable<String, String>();


	public static String getUUID(String repoName, SvnedgeServers server){

		String url = "http://"+server.getIpAddress()+":"+server.getAppPort()+"/svn/"+repoName;

		setupLibrary();

		SVNRepository repository = null;
		try {
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));

			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(server.getAppAdmin(), server.getAppPassword());
			repository.setAuthenticationManager(authManager);

			System.out.println("Repository UUID: " + repository.getRepositoryUUID(true));

			return repository.getRepositoryUUID(true);

		} catch (SVNException svne) {
			System.err.println("error while creating an SVNRepository for location '" + url + "': " + svne.getMessage());
		}

		return null;
	}



	public static long createFolder(SvnRepository repository, SvnedgeServers server) {

		String url = "http://"+server.getIpAddress()+":"+server.getAppPort()+"/svn/"+repository.getRepositoryName();

		List<RepositoryFolder> folders = RepositoryFolder.findFolderByRepository(repository.getId());

		if(folders == null || folders.isEmpty() || folders.size() == 0){
			System.out.println("No Folders to Create ....");
			return 0;
		}

		setupLibrary();

		SVNURL repositoryURL = null;

		try {
			repositoryURL = SVNURL.parseURIEncoded( url );

			long committedRevision = -1;

			ISVNOptions options = SVNWCUtil.createDefaultOptions( true );
			ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(server.getAppAdmin(), server.getAppPassword());

			ourClientManager = SVNClientManager.newInstance( options , authManager );

			SVNURL urls[] = new SVNURL[folders.size()];

			int i = 0;
			for ( RepositoryFolder folder : folders ) {
				urls[i] = repositoryURL.appendPath( folder.getFolderPath() , false );
				System.out.println("Creating Folder : "+urls[i]);
				i++;
			}

			committedRevision = makeDirectory(urls, "Initial Setup ...").getNewRevision();

			System.out.println( "Committed to revision " + committedRevision );

			return committedRevision;

		} catch (SVNException e) {
			e.printStackTrace();
		}
		return 0;

	}


	private static SVNCommitInfo makeDirectory( SVNURL[] url , String commitMessage ) throws SVNException {
		return ourClientManager.getCommitClient().doMkDir( url , commitMessage );
	}

	private static void setupLibrary() {

		DAVRepositoryFactory.setup();

		SVNRepositoryFactoryImpl.setup();

		FSRepositoryFactory.setup();
	}



	public static Integer updateAccessFile(SvnedgeServers server, String accessText, String modificationType) {

		if("NEW".equalsIgnoreCase(modificationType)){

			config.put("StrictHostKeyChecking", "no");

			Session session = null;
			Channel channel = null;


			try {
				JSch ssh = new JSch();
				//System.out.println("JSch Object Created ....");

				JSch.setConfig(config);
				//System.out.println("Configuration Setup Done ....");

				session = ssh.getSession(server.getUserId(), server.getIpAddress(), server.getSshPort());
				//System.out.println("Session Found ....");

				session.setPassword(server.getPassWord());
				//System.out.println("Setting Password Done ....");

				session.connect();
				//System.out.println("Session Connected ....");

				channel = session.openChannel("sftp");
				//System.out.println("Opening Channel ....");

				channel.connect();
				//System.out.println("Channel Connected ....");



				ChannelSftp sftp = (ChannelSftp) channel;
				//System.out.println("Casting to sftp Done ....");










				File folder = new File("access_file/"+server.getName());
				if(!folder.exists()){
					folder.mkdirs();
				}

				File localFile = new File(folder, "/svn_access_file"+new Date().getTime());
				sftp.get(server.getAccessFilePath(), localFile.getAbsolutePath());
				System.out.println(folder.getAbsolutePath());

				String fileString = FileUtils.readFileToString(localFile);
				fileString = accessText.concat("\n\n\n").concat(fileString);

				sftp.put( IOUtils.toInputStream(fileString) , server.getAccessFilePath());

				if(sftp.isConnected()){
					sftp.disconnect();
				}


				return 1;

			} catch (JSchException e) {
				e.printStackTrace();
			} catch (SftpException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				if (channel != null) {
					channel.disconnect();
				}
				if (session != null) {
					session.disconnect();
				}
			}

			return 0;
		}
		return 0;
	}

	public static Integer modifyRepositoryAccess(SvnedgeServers server, String repositoryName) {

		config.put("StrictHostKeyChecking", "no");

		Session session = null;
		Channel channel = null;

		try {
			JSch ssh = new JSch();
			JSch.setConfig(config);
			session = ssh.getSession(server.getUserId(), server.getIpAddress(), server.getSshPort());
			session.setPassword(server.getPassWord());
			session.connect();

			channel = session.openChannel("exec");

			System.out.println("sudo -S -p '' chmod -R 777 "+server.getRepositoryPath()+"/"+repositoryName);
			ChannelExec exec = (ChannelExec) channel ;
			
			
			exec.setPty(true);
			exec.setCommand("sudo -S -p '' chmod -R 777 "+server.getRepositoryPath()+"/"+repositoryName);

			BufferedReader in = new BufferedReader(new InputStreamReader(exec.getInputStream()));
			BufferedReader err = new BufferedReader(new InputStreamReader(exec.getErrStream()));

			
			OutputStream out = exec.getOutputStream();
			channel.connect();
			out.write((server.getPassWord()+"\n").getBytes());
			out.flush();

			
			System.out.println("Error :- ");
			StringBuilder errStr = new StringBuilder();
			String errTemp = null;
			while((errTemp = err.readLine()) != null){
				errStr.append(errTemp + "\n");
			}
			System.out.println(errStr);


			System.out.println("Output :- ");
			StringBuilder output = new StringBuilder();
			String s = null;
			while((s = in.readLine()) != null){
				output.append(s + "\n");
			}
			System.out.println(output);





			return 1;

		} catch (JSchException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (channel != null) {
				channel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		return 0;

	}
}
