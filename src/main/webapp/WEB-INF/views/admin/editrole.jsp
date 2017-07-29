<%@ page import="tools.automation.bean.*" %>
<%@ page import="tools.automation.utility.DataSecurity" %>

<div style="height:30px; width: 100%; top: 40px; position: absolute; left: 0px;" >
<script type='text/javascript' src='/toolsautomation/dwr/interface/AdminService.js'></script>
<script type='text/javascript' src='/toolsautomation/dwr/interface/UserService.js'></script>

<script type="text/javascript">

function updateRole(){
	
	var dashboardList = new Array(); 
	var primaryList = new Array(); 
	var subMenuList = new Array(); 
	
	$("input:checkbox[name=dashboardmenuscheckbox]:checked").each(function(){
		dashboardList.push($(this).val());
	});
	
	$("input:checkbox[name=primarymenuscheckbox]:checked").each(function(){
		primaryList.push($(this).val());
	});
	
	$("input:checkbox[name=submenuscheckbox]:checked").each(function(){
		subMenuList.push($(this).val());
	});
	
	
	AdminService.updateRole( escape('<%=request.getParameter("id")%>') , $('#roleName').val() , $('#description').val() , $('#status').val() , dashboardList , primaryList , subMenuList , function(){
		alert("Updated");
	});
	
}



</script>
<script type="text/javascript">

$(function() {

	  $('input[type="checkbox"]').change(checkboxChanged);

	  function checkboxChanged() {
	    var $this = $(this),
	        checked = $this.prop("checked"),
	        container = $this.parent(),
	        siblings = container.siblings();

	    container.find('input[type="checkbox"]')
	    .prop({
	        indeterminate: false,
	        checked: checked
	    })
	    .siblings('label')
	    .removeClass('custom-checked custom-unchecked custom-indeterminate')
	    .addClass(checked ? 'custom-checked' : 'custom-unchecked');

	    checkSiblings(container, checked);
	  }

	  function checkSiblings($el, checked) {
	    var parent = $el.parent().parent(),
	        all = true,
	        indeterminate = false;

	    $el.siblings().each(function() {
	      return all = ($(this).children('input[type="checkbox"]').prop("checked") === checked);
	    });

	    if (all && checked) {
	      parent.children('input[type="checkbox"]')
	      .prop({
	          indeterminate: false,
	          checked: checked
	      })
	      .siblings('label')
	      .removeClass('custom-checked custom-unchecked custom-indeterminate')
	      .addClass(checked ? 'custom-checked' : 'custom-unchecked');

	      checkSiblings(parent, checked);
	    } 
	    else if (all && !checked) {
	      indeterminate = parent.find('input[type="checkbox"]:checked').length > 0;

	      parent.children('input[type="checkbox"]')
	      .prop("checked", checked)
	      .prop("indeterminate", indeterminate)
	      .siblings('label')
	      .removeClass('custom-checked custom-unchecked custom-indeterminate')
	      .addClass(indeterminate ? 'custom-indeterminate' : (checked ? 'custom-checked' : 'custom-unchecked'));

	      checkSiblings(parent, checked);
	    } 
	    else {
		      $el.parents("li").children('input[type="checkbox"]')
		      .prop({
		          indeterminate: false,
		          checked: true
		      })
		      .siblings('label')
		      .removeClass('custom-checked custom-unchecked custom-indeterminate')
		      .addClass('custom-indeterminate');
		    }
		  }
		});

</script> 

<%

DataSecurity security = new DataSecurity();

Role role = Role.findRole( Integer.parseInt( security.decryptTextURL( request.getParameter("id")  ) ) );

%>

<table style="width: 100%;">
<tr>
<td width="40%" align="center" valign="top">
<div style="background-color: #D8D8D8; height: 25px; width: 90%;">Role Information</div> <br>

<table  style="width: 90%;" class="table">
			
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;Role Name</label></td>			
				<td><input type="text" id="roleName" style="width: 100%;" value="<%= role.getRoleName() %>"></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Description</label></td>			
				<td><textarea cols="50" rows="10" id="description"><%= role.getDescription() %></textarea></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Status</label></td>			
				<td>
					<select id="status" style="width: 100%;">
						
						<% if("Active".equalsIgnoreCase(role.getStatus())) {%>	
							<option selected="selected" value="Active">Active</option>
							<option value="De-Active">De-Active</option>
						<% } else {%>
							<option value="Active">Active</option>
							<option selected="selected" value="De-Active">De-Active</option>
						<% } %>
					
					</select>
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center"><button type="button" onclick="updateRole()" class="btn btn-sm btn-primary">Update Role Data</button></td>			
				
			</tr>
						
			</table>

	
			
			
		</td>
		<td valign="top" >
			<div style="background-color: #D8D8D8; height: 25px; width: 90%;">Dashboard Access</div> <br>
			<div style="height: 500px; overflow: scroll;">
			
			
			
	<ul class="treeview" >
	
	<% for( MenuDashboard dashboards :  MenuDashboard.findAllMenuDashboards() ) {

		String isRoleDashboardMenuMapPresent = "";
		String isRoleDashboardMenuMapPresentcss = "custom-unchecked";
		for( RoleDashboardMenuMap map : dashboards.getRoleDashboardMenuMaps() ){
			if(map.getRole().getId().intValue() == Integer.parseInt( security.decryptTextURL( request.getParameter("id")  ) ) ){
				isRoleDashboardMenuMapPresent = "checked='checked'";
				isRoleDashboardMenuMapPresentcss = "custom-checked";
				break;
			}
		}
				%>
	  
        <li>
            <input type="checkbox" <%=isRoleDashboardMenuMapPresent %> name="dashboardmenuscheckbox" value="<%= dashboards.getId()%>" id="tall<%= dashboards.getId()%>">
            <label for="tall<%= dashboards.getId()%>" class="<%=isRoleDashboardMenuMapPresentcss%>"><%= dashboards.getName() %></label>
            
            <ul>
                 
                 <%  for( MenuPrimary primaryMenus : dashboards.getMenuPrimaries() ){
						String isRolePrimaryMenuMapPresent = "";
						String isRolePrimaryMenuMapPresentcss = "custom-unchecked";
						for( RolePrimaryMenuMap map : primaryMenus.getRolePrimaryMenuMaps() ){
							if(map.getRole().getId().intValue() == Integer.parseInt( security.decryptTextURL( request.getParameter("id")  ) ) ){
								isRolePrimaryMenuMapPresent = "checked='checked'";
								isRolePrimaryMenuMapPresentcss = "custom-checked";
								break;
							}
						}
           
                 %>
                 
                 <li>
                     <input type="checkbox" <%=isRolePrimaryMenuMapPresent %> name="primarymenuscheckbox" value="<%= primaryMenus.getId()%>" id="tall<%= dashboards.getId()%>-<%= primaryMenus.getId()%>">
                     <label for="tall<%= dashboards.getId()%>-<%= primaryMenus.getId()%>" class="<%=isRolePrimaryMenuMapPresentcss%>"><%=primaryMenus.getName() %></label>
                     <ul>
                     
                     	<%	for( MenuSub subMenus : primaryMenus.getMenuSubs() ){ 
								String isRoleSubMenuMapPresent = "";
								String isRoleSubMenuMapPresentcss = "custom-unchecked";
								for( RoleSubmenuMap map : subMenus.getRoleSubmenuMaps() ){
									if(map.getRole().getId().intValue() == Integer.parseInt( security.decryptTextURL( request.getParameter("id")  ) ) ){
										isRoleSubMenuMapPresent = "checked='checked'";
										isRoleSubMenuMapPresentcss = "custom-checked";
										break;
									}
								}
						%>
                     
                     
                         <li>
                             <input type="checkbox" <%=isRoleSubMenuMapPresent %> name="submenuscheckbox" value="<%= subMenus.getId()%>" id="tall<%= dashboards.getId()%>-<%= primaryMenus.getId()%>-<%= subMenus.getId()%>">
                             <label for="tall<%= dashboards.getId()%>-<%= primaryMenus.getId()%>-<%= subMenus.getId()%>" class="<%= isRoleSubMenuMapPresentcss %>"><%= subMenus.getName() %></label>
                         </li>
                         
                         <% } %>
                         
                     </ul>
                 </li>
                
                 
                 <% } %>
                 
            </ul>
        </li>
        <% } %>
    </ul>
			
				
			</div>
			
		
		</td>
	</tr>
	
</table>




</div>