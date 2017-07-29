package tools.automation.utility;
import java.util.Date;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import tools.automation.bean.SvnRepository;


public class Mailer {

	static String m_message = "Feed Back Mail Sent Successfully";
	static boolean m_valid=false;	



	private static void sendNotification(String a_name, String a_emailId, String a_message) throws Exception{

		StringBuffer w_Subject = new StringBuffer();

		try{
			StringBuilder w_final = new StringBuilder("Dear User, <br><br>");
			//StringBuilder w_Subject = new StringBuilder();
			w_final.append("Your repository has been Approved and will be created soon. <br> Please find the below details :- <br><br>");

			w_final.append(a_message).append("<br><br><br> Thanks and Regards <br> Tools Group");


			w_Subject.append("New Repository Request has been approved...");

			java.util.Properties w_props = new java.util.Properties();
			w_props.put("mail.transport.protocol", "smtp");
			w_props.put("mail.smtp.host", "10.98.10.211"); //smtp port
			w_props.put("mail.smtp.sendpartial", "true");
			w_props.put("mail.smtp.port", "25");
			w_props.put("mailfrom", "sheetam.j@hcl.com");

			/*w_props.put("mail.smtp.user", "sheetam.j");
			w_props.put("mail.smtp.password", "feb@2016");*/
			w_props.put("mail.smtp.auth", "true"); 


			Session w_session = Session.getDefaultInstance(w_props, new javax.mail.Authenticator() 
			{
				protected PasswordAuthentication getPasswordAuthentication() 
				{
					return new PasswordAuthentication("sheetam.j@hcl.com","2016@june");
				}
			});


			/*javax.mail.Session w_session = javax.mail.Session.getDefaultInstance(w_props, null);*/	
			javax.mail.Message w_msg = new MimeMessage(w_session);
			w_msg.setFrom(new InternetAddress("sheetam.j@hcl.com"));
			w_msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse("sheetam.j@hcl.com", false));
			w_msg.setSubject(MimeUtility.encodeText(w_Subject.toString(), "UTF-8", "B"));
			w_msg.setSentDate(new Date());
			w_msg.setContent(w_final.toString(),"text/html");
			Transport.send(w_msg);

		}catch(Exception a_Exp){
			a_Exp.printStackTrace();
			m_message = "An Error Occured while sending. Please try again.";
		}
	}
	
	public static void createRepositoryTemplateAndSend(SvnRepository repository) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("<div role='tabpanel' class='tab-pane active' id='confirmationTab' align='center'>");

		buffer.append("<br>");

		buffer.append("<table border='1' style='width: 90%; height:200px; border: 3px;border-color: gray;border-style: groove;'>");
		buffer.append("<tbody><tr>");
		buffer.append("<td valign='top' align='left'>");
		buffer.append("<table border='0' style='width: 100%; height:200px; margin-left: 10px;'>");
		buffer.append("<tbody><tr><td style='width: 30%;'>Project SAP Code :</td><td>  <span id='confirmProjectSAPCode'>"+repository.getProjectSapCode()+"</span>  </td></tr>");
		buffer.append("<tr><td>Project Name :</td><td> <span id='confirmProjectName'>"+repository.getProjectName()+"</span> </td></tr>");
		buffer.append("<tr><td>PM's SAP Code :</td><td> <span id='confirmPmSAPCode'>"+repository.getProjectManager().getSapCode()+"</span>  </td></tr>");
		buffer.append("<tr><td>PM's Name :</td><td> <span id='confirmPmName'>"+repository.getProjectManager().getFullName()+"</span>  </td></tr>");

		buffer.append("<tr><td>Alternate Approver SAP Code :</td><td> <span id='confirmAltApproverSAPCode'>"+repository.getAlternateApprover().getSapCode()+"</span> </td></tr>");
		buffer.append("<tr><td>Alternate Approver Name :</td><td> <span id='confirmAltApproverName'>"+repository.getAlternateApprover().getFullName()+"</span>  </td></tr>");
		buffer.append("<tr><td>Alternate Approver Email :</td><td> <span id='confirmAltApproverEmail'>"+repository.getAlternateApprover().getEmailId()+"</span>  </td></tr>");
		buffer.append("</tbody></table>");
		buffer.append("</td>");
		buffer.append("<td valign='top' align='left'>");

		buffer.append("<table border='0' style='width: 100%; height:200px;  margin-left: 10px;'>");
		buffer.append("<tbody><tr><td style='width: 30%;'>Repository Name :</td><td> <span id='confirmRepositoryName'>"+repository.getRepositoryName()+"</span> </td></tr>");
		buffer.append("<tr><td>No of CM tool users :</td><td> <span id='confirmCMUserCount'>"+repository.getCmtoolUserCount()+"</span>   </td></tr>");
		buffer.append("<tr><td>Service Start Date :</td><td> <span id='confirmServiceStartDate'>"+repository.getServiceStartDate()+"</span>   </td></tr>");
		buffer.append("<tr><td>Service End Date :</td><td> <span id='confirmServiceEndDate'>"+repository.getServiceEndDate()+"</span>   </td></tr>");

		buffer.append("<tr><td>SPOC's SAP Code :</td><td> <span id='confirmSpocSAPCode'>"+repository.getSpoc().getSapCode()+"</span> </td></tr>");
		buffer.append("<tr><td>SPOC's Name :</td><td> <span id='confirmSpocName'>"+repository.getSpoc().getFullName()+"</span>  </td></tr>");
		buffer.append("<tr><td>SPOC's Email :</td><td> <span id='confirmSpocEmail'>"+repository.getSpoc().getEmailId()+"</span>  </td></tr>");
		buffer.append("</tbody></table> ");
		buffer.append("</td>");

		buffer.append("</tr>");
		buffer.append("</tbody></table> ");


		buffer.append("</div>");



		try {

			sendNotification("Sheetam" , "sheetam.j@hcl.com" , buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void createRepositoryApproveTemplateAndSend( SvnRepository repository ) {

		StringBuffer buffer = new StringBuffer();

		buffer.append("<div role='tabpanel' class='tab-pane active' id='confirmationTab' align='center'>");

		buffer.append("<br>");

		buffer.append("<table border='1' style='width: 90%; height:200px; border: 3px;border-color: gray;border-style: groove;'>");
		buffer.append("<tbody><tr>");
		buffer.append("<td valign='top' align='left'>");
		buffer.append("<table border='0' style='width: 100%; height:200px; margin-left: 10px;'>");
		buffer.append("<tbody><tr><td style='width: 30%;'>Project SAP Code :</td><td>  <span id='confirmProjectSAPCode'>"+repository.getProjectSapCode()+"</span>  </td></tr>");
		buffer.append("<tr><td>Project Name :</td><td> <span id='confirmProjectName'>"+repository.getProjectName()+"</span> </td></tr>");
		buffer.append("<tr><td>PM's SAP Code :</td><td> <span id='confirmPmSAPCode'>"+repository.getProjectManager().getSapCode()+"</span>  </td></tr>");
		buffer.append("<tr><td>PM's Name :</td><td> <span id='confirmPmName'>"+repository.getProjectManager().getFullName()+"</span>  </td></tr>");

		buffer.append("<tr><td>Alternate Approver SAP Code :</td><td> <span id='confirmAltApproverSAPCode'>"+repository.getAlternateApprover().getSapCode()+"</span> </td></tr>");
		buffer.append("<tr><td>Alternate Approver Name :</td><td> <span id='confirmAltApproverName'>"+repository.getAlternateApprover().getFullName()+"</span>  </td></tr>");
		buffer.append("<tr><td>Alternate Approver Email :</td><td> <span id='confirmAltApproverEmail'>"+repository.getAlternateApprover().getEmailId()+"</span>  </td></tr>");
		buffer.append("</tbody></table>");
		buffer.append("</td>");
		buffer.append("<td valign='top' align='left'>");

		buffer.append("<table border='0' style='width: 100%; height:200px;  margin-left: 10px;'>");
		buffer.append("<tbody><tr><td style='width: 30%;'>Repository Name :</td><td> <span id='confirmRepositoryName'>"+repository.getRepositoryName()+"</span> </td></tr>");
		buffer.append("<tr><td>No of CM tool users :</td><td> <span id='confirmCMUserCount'>"+repository.getCmtoolUserCount()+"</span>   </td></tr>");
		buffer.append("<tr><td>Service Start Date :</td><td> <span id='confirmServiceStartDate'>"+repository.getServiceStartDate()+"</span>   </td></tr>");
		buffer.append("<tr><td>Service End Date :</td><td> <span id='confirmServiceEndDate'>"+repository.getServiceEndDate()+"</span>   </td></tr>");

		buffer.append("<tr><td>SPOC's SAP Code :</td><td> <span id='confirmSpocSAPCode'>"+repository.getSpoc().getSapCode()+"</span> </td></tr>");
		buffer.append("<tr><td>SPOC's Name :</td><td> <span id='confirmSpocName'>"+repository.getSpoc().getFullName()+"</span>  </td></tr>");
		buffer.append("<tr><td>SPOC's Email :</td><td> <span id='confirmSpocEmail'>"+repository.getSpoc().getEmailId()+"</span>  </td></tr>");
		buffer.append("</tbody></table> ");
		buffer.append("</td>");

		buffer.append("</tr>");
		buffer.append("</tbody></table> ");


		buffer.append("</div>");



		try {

			sendNotification("Sheetam" , "sheetam.j@hcl.com" , buffer.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
