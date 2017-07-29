package tools.automation.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

import tools.automation.bean.SvnedgeServers;

public class RepositoryUtility {

	private static final String USER_AGENT = "Mozilla/5.0";


	// HTTP POST request
	public static int createRepository(String repositoryName, SvnedgeServers svnedgeServers)  {

		String url="http://"+svnedgeServers.getIpAddress()+":"+svnedgeServers.getCsvnPort()+"/csvn/api/1/repository?format=json";


		int responseCode = 0;
		try {
			String urlParameters = "{\"name\":\""+repositoryName+"\",\"applyStandardLayout\":\"false\",\"applyTemplateId\":\"1\"}";

			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestProperty("Authorization", "Basic " + (new String(Base64.encodeBase64( (svnedgeServers.getAppAdmin()+":"+svnedgeServers.getAppPassword()).getBytes())) ) );

			//add reuqest header
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", USER_AGENT);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			return responseCode;

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;

	}


	public static String getRepositoryListFromServer(SvnedgeServers server) throws Exception {

		String url="http://"+server.getIpAddress()+":"+server.getCsvnPort()+"/csvn/api/1/repository?format=json";

		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestProperty("Authorization", "Basic " + (new String((server.getAppAdmin()+":"+server.getAppPassword() ) ) ) );

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		return response.toString();

	}


}
