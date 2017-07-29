package tools.automation.service;

import java.util.List;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.dao.EmptyResultDataAccessException;

import tools.automation.bean.User;
import tools.automation.utility.DataSecurity;
import tools.automation.utility.LDAPUtils;

@RemoteProxy
public class UserService {

	@RemoteMethod
	public String getUserList(int echo, int start, int max, String search) {

		List<User> users = User.findAllUsers();

		JSONObject result = new JSONObject();
		JSONArray arr = new JSONArray();

		try {
			for (User user : users) {

				JSONArray usr = new JSONArray();

				usr.put(user.getFullName());
				usr.put(user.getLoginId());
				usr.put(user.getEmailId());
				usr.put(user.getSapCode());
				usr.put(user.getPhoneNumber());
				usr.put(user.getStatus());

				arr.put(usr); 

			}

			result.put("iTotalRecords", arr.length());
			result.put("iTotalDisplayRecords", arr.length());
			result.put("sEcho", echo);
			result.put("aaData", arr);

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return result.toString();
	}


	@RemoteMethod
	public User getUserDataBySAPCode(String sapCode) {

		try {

			return User.findUserDataBySAPCode(sapCode);

		}
		catch (EmptyResultDataAccessException nre) {

			LDAPUtils ldapUtils = new LDAPUtils();
			User user = ldapUtils.createUserBySAPCode(sapCode);
			if(user != null)
				return User.findUserDataBySAPCode(sapCode);
			
			
		}  catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;

	}

	@RemoteMethod
	public User getUserDataByEmailID(String emailId) {

		try {

			return User.findUserDataByEmailID(emailId);


		}
		catch (EmptyResultDataAccessException nre) {

			LDAPUtils ldapUtils = new LDAPUtils();
			ldapUtils.createUserByEmailId(emailId);
			return User.findUserDataByEmailID(emailId);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RemoteMethod
	public User getUserDataByLoginID(String loginId) {

		try {

			return User.findUserDataByLoginID(loginId);

		} 
		catch (EmptyResultDataAccessException nre) {

			LDAPUtils ldapUtils = new LDAPUtils();
			ldapUtils.createUserByLoginId(loginId);
			return User.findUserDataByLoginID(loginId);

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RemoteMethod
	public User getUserDataByID(String id) {
		DataSecurity dataSecurity = new DataSecurity();
		try {

			return User.getUserDataByID( Integer.parseInt( dataSecurity.decryptTextURL(id) ) );

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RemoteMethod
	public void saveUserAuthentication(String userId , String userAuthentication) {
		DataSecurity dataSecurity = new DataSecurity();
		User user = User.findUser( Integer.parseInt( dataSecurity.decryptTextURL(userId) ) );
		user.setUserAuthentication(userAuthentication);
		user.merge();
		user.flush();
	}
}
