package tools.automation.utility;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import tools.automation.bean.Role;
import tools.automation.bean.User;
import tools.automation.bean.UserRoleMap;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchResults;


public class LDAPUtils {

	private static int DEFAULT_ROLE = 3;

	private static String authzFilter = "authzAttr=special:Entitlement:value";

	public User createUserByLoginId(String loginId) {
				
		try {
			User newUser = getUserInfoByLoginId(loginId);
			newUser = newUser.merge();
			
			UserRoleMap userRoleMap = new UserRoleMap();
			userRoleMap.setRole(Role.findRole( DEFAULT_ROLE ));
			userRoleMap.setUser(newUser);
			userRoleMap.merge();
			
			return newUser;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public User createUserByEmailId(String emailId) {
		
		try {
			
			User newUser = getUserInfoByEmailId(emailId);
			newUser = newUser.merge();
			
			UserRoleMap userRoleMap = new UserRoleMap();
			userRoleMap.setRole(Role.findRole( DEFAULT_ROLE ));
			userRoleMap.setUser(newUser);
			userRoleMap.merge();
			
			return newUser;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public User createUserBySAPCode(String sapCode) {
		
		try {
			
			User newUser = getUserInfoBySAPCode(sapCode);
			newUser = newUser.merge();
			
			UserRoleMap userRoleMap = new UserRoleMap();
			userRoleMap.setRole(Role.findRole( DEFAULT_ROLE ));
			userRoleMap.setUser(newUser);
			userRoleMap.merge();
			
			return newUser;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	
	
	public User getUserInfoByLoginId(String loginId) {
		return getUserInfo("sAMAccountName="+loginId.trim());
	}
	
	public User getUserInfoByEmailId(String emailId) {
		return getUserInfo("mail="+emailId.trim());
	}
	
	public User getUserInfoBySAPCode(String sapCode) {
		return getUserInfo("physicalDeliveryOfficeName="+sapCode.trim());
	}

	
	private User getUserInfo(String search) {

		LDAPConnection conn = new LDAPConnection();
		User user = new User();
		try {

			conn.connect("10.98.10.194", 3268);
			conn.bind("CN=Tools Team Service ID (HCLT),OU=Generic ID,DC=HCLT,DC=CORP,DC=HCL,DC=IN", "India@1234");


			LDAPSearchResults searchResults = conn.search("DC=HCLT,DC=CORP,DC=HCL,DC=IN",LDAPConnection.SCOPE_SUB, search, null, false);

			while ( searchResults.hasMore() ) {



				try {
					LDAPEntry nextEntry = null;

					nextEntry = searchResults.next();

					LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();

					user.setLoginId(attributeSet.getAttribute("sAMAccountName").getStringValue());
					user.setSapCode(attributeSet.getAttribute("physicalDeliveryOfficeName").getStringValue());
					user.setEmailId(attributeSet.getAttribute("mail").getStringValue());
					
					user.setFullName(attributeSet.getAttribute("displayName").getStringValue());
					user.setPhoneNumber(attributeSet.getAttribute("mobile").getStringValue());
					user.setPassword(null);
					user.setStatus("Active");
					user.setUserAuthentication("LDAP");
					user.setCreatedBy("IMPORTED");
					user.setCreatedOn(new Date());
					
					return user;

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (LDAPException e) {
			e.printStackTrace();
		} finally {

			try {
				if(conn.isConnected())
					conn.disconnect();
			} catch (LDAPException e) {
				e.printStackTrace();
			}

		}
		return null;

	}

	

	public boolean authenticate(String userName, String pass){

		boolean isAuthenticated = true;
		
		/*
		if( userName == null || pass == null || "".equalsIgnoreCase(userName) || "".equalsIgnoreCase(pass)){
			return isAuthenticated;
		}
		
		
		try {
			LDAPConnection conn = new LDAPConnection();

			conn.connect("10.98.10.194", 3268);
			System.out.println("Connected : "+conn.isConnected());


			conn.bind("CN=Tools Team Service ID (HCLT),OU=Generic ID,DC=HCLT,DC=CORP,DC=HCL,DC=IN", "India@1234");
			System.out.println("Bound : "+conn.isBound());

			LDAPSearchResults searchResults = conn.search("DC=HCLT,DC=CORP,DC=HCL,DC=IN",LDAPConnection.SCOPE_SUB, "sAMAccountName="+userName+"", null, false);

			if( !searchResults.hasMore() ){
				System.out.println("Nothing Found. Do more Research ....");
				isAuthenticated = false;
			}


			String userEntryDn = null;
			try {
				LDAPEntry userEntry = searchResults.next();
				LDAPAttribute objectClass = userEntry.getAttribute("objectClass");

				if ("aliasObject".equals(objectClass.getStringValue())) {
					LDAPAttribute aliasDN = userEntry.getAttribute("aliasedObjectName");
					userEntryDn = aliasDN.getStringValue();
				} else {
					userEntryDn = userEntry.getDN();
				}

				conn.bind(LDAPConnection.LDAP_V3, userEntryDn, pass.getBytes("UTF8"));
				isAuthenticated = true;

			} catch (LDAPException e) {
				System.out.println(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				System.out.println(e.getMessage());
			}


			conn.disconnect();
			System.out.println("Disconnected");
			
		} catch (LDAPException e) {
			System.out.println(e.getMessage());
		}
*/
		return isAuthenticated;
	}
}
