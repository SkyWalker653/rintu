<%@ page import="tools.automation.bean.*" %>
<%@ page import="java.util.*" %>
<div class="row">
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail">
      <div class="alert alert-info" role="alert">PMSmart</div>
      <div class="caption" style="text-align: left;">
         <p>Total Projects : </p>
         <p>Total Users : </p>
         <p>Hit Count : </p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail">
      <div class="alert alert-info" role="alert">SVN</div>
      <div class="caption" style="text-align: left;">
         <p>Total Projects : </p>
         <p>Total Users : </p>
         <p>Hit Count : </p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail">
      <div class="alert alert-info" role="alert">TestLink</div>
      <div class="caption" style="text-align: left;">
         <p>Total Projects : </p>
         <p>Total Users : </p>
         <p>Hit Count : </p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail">
      <div class="alert alert-info" role="alert">Bugzilla</div>
      <div class="caption" style="text-align: left;">
         <p>Total Projects : </p>
         <p>Total Users : </p>
         <p>Hit Count : </p>
      </div>
    </div>
  </div>
  
  <div class="col-sm-6 col-md-2">
    <div class="thumbnail">
      <div class="alert alert-info" role="alert">Total Count</div>
      <div class="caption" style="text-align: left;">
      	 <p>Total Projects : </p>
         <p>Total Users : </p>
         <p>Hit Count : </p>
      </div>
    </div>
  </div>
  
</div>
 
   
    
 
<div class="alert alert-info" role="alert" style="margin-bottom: 0px; padding: 0px; color:black; font-weight:bold;  background-image: url('/toolsautomation/images/home/boxbg.jpg'); background-repeat: no-repeat; background-size: 100% 100%;" >Notification :-</div>
<div class="dashboardnotification">
<br>
	<table class="table-bordered table-striped" style="width: 95%">
	<tr>
		<th width="15%">Date</th>
		<th width="15%">Application</th>
		<th width="20%">Notification</th>
		<th width="50%">Message</th>
	</tr>
	<% List<Notification> notifications = Notification.findFirst30NotificationByUser( ((User)session.getAttribute("validUser")).getId()); 
	
	for(Notification notification : notifications){%>
		
		<tr>
			<td><%= notification.getDateTime().getTime() %></td>
			<td><%= notification.getNotificationType() %></td>
			<td><%= notification.getNotification() %></td>
			<td><%= notification.getDetails() %></td>
		</tr>
	<% } %>
	
	</table>
</div>
