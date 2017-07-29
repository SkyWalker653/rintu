<%@ page import="java.util.*" %>
<%@ page import="tools.automation.bean.*" %>
<%@ page import="tools.automation.utility.MenuUtility" %>



<div style="height: 85px; width: 100%;" class="themeblue">
<img src="/toolsautomation/images/btis-logo.png" height="35px" style="float: left; height: 35px;">

TOOLS GROUP 
&nbsp;&nbsp;&nbsp;

<% 
if(session.getAttribute("validUser") != null &&  session.getAttribute("validUser").getClass().equals(User.class) ){
	User user = (User)session.getAttribute("validUser"); 
	
%>
	<!-- Single button -->
	<div class="btn-group" style="float: right;">
	  <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	    <%= user.getFullName() %> <span class="caret"></span>
	  </button>
	  <ul class="dropdown-menu">
	    <li><a href="/toolsautomation/sessioncontroller/logout">Logout</a></li>
	  </ul>
	</div>
	
	<script type="text/javascript">
        var sessionTimeout = "<%= session.getMaxInactiveInterval() %>";
        $( window ).load(function() {
        	
        	setTimeout(function(){ 
              		window.location.href = "/toolsautomation/sessioncontroller/logout";
        	}, sessionTimeout*1000 );
        
        });
        
</script>
	
	
	
<% }else{ %>
	 
	<div align="right" style="position: absolute; right:5px; top: 3px;" id="logindiv">
	<form class="form-inline" action="<%= request.getContextPath() %>/sessioncontroller" method="post">
		<table>	
			<tr>
				<td>		 
					<div class="form-group">
						<label class="sr-only" for="loginID">Login Id</label>
						<input type="text" class="form-control" id="loginId" name="loginId" placeholder="Login Id">
					</div>
				</td>
				<td>	
					<div class="form-group">
						<label class="sr-only" for="password">Password</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Password" style="height: 30px;">
					</div>
	  			</td>
	  			<td>
					<button type="submit" class="btn btn-info" style="height: 30px;">Login</button>
				</td>
			</tr>
		</table>
	</form>
</div>
	
<% } %>

<hr style="margin-bottom: 0px;">


<div id='cssmenu' style="z-index: 999"> 
<ul>

<%  if(session != null 
		&& session.getAttribute("validUser") != null 
		&& session.getAttribute("dashboardmenu") != null
		&& session.getAttribute("subMenuAccess") != null
		&& session.getAttribute("dashboardAccess") != null
		&& session.getAttribute("primaryMenuAccess") != null
	){ 
	
		List<MenuDashboard> dashboardMenu = (List<MenuDashboard>)session.getAttribute("dashboardmenu");
		Set<Integer> dashboardAccess = (Set<Integer>)session.getAttribute("dashboardAccess");
		Set<Integer> primaryMenuAccess = (Set<Integer>)session.getAttribute("primaryMenuAccess");
		Set<Integer> subMenuAccess = (Set<Integer>)session.getAttribute("subMenuAccess");
		
		for(MenuDashboard menu : dashboardMenu){ 
		
			if( !dashboardAccess.contains(menu.getId())){
				continue;
			}
				
			
		
		%>
		
			<li><a href='<%= MenuUtility.checkUrl( menu.getUrl() )%>'><%=menu.getName() %></a>
				<%if(menu.getMenuPrimaries().size() > 0){ %>
				<ul>
				<% for(MenuPrimary pmenu : menu.getMenuPrimaries()){ 
					if( !primaryMenuAccess.contains(pmenu.getId())){
						continue;
					}
				
				%>
			
				<li><a href='<%=MenuUtility.checkUrl( pmenu.getUrl() )%>'><%=pmenu.getName() %></a>
				
				
				
						<%if(pmenu.getMenuSubs().size() > 0){ %>
						<ul>
						<% for(MenuSub smenu : pmenu.getMenuSubs()){ 
							
							if( !subMenuAccess.contains(smenu.getId())){
								continue;
							}
							
						%>
					
						<li><a href='<%=MenuUtility.checkUrl( smenu.getUrl() )%>'><%=smenu.getName() %></a></li>
					
					
						<%} %>
						</ul>
						
						<%} %>
						
				</li>
			
			
				<%} %>
				</ul>
				
				<%} %>
				
			</li>
		
		<%} %>
	
	<% } %>
<li style="float: right;"><a href='/toolsautomation/application/feedback' target="_blank">About Us</a>
<li style="float: right;"><a href='/toolsautomation/application/contact' target="_blank">Contact Us</a>
<li style="float: right;"><a href='/toolsautomation/application/aboutus' target="_blank">Feedback</a>
</ul>
</div>


</div> 