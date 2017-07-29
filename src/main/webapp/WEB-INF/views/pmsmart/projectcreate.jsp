<div style="min-height: 600px;">

<table border="1" style="width: 100%;">
	
		<tr class="active">
			<td colspan="2" style="height: 52px;" valign="middle" >
				
				<input type="text" class="form-control" placeholder="REMEDY ID" id="incidentNumber" name="incidentNumber" style="display:inline; float:left; width: 250px; margin-left: 20px;">
					
				<span style="margin-right: 20%;">Please provide the following project details to create new Project in PMSmart</span> 
				
			</td>
		</tr>
	
	<tr>
		<td width="50%" valign="top">


		<table border="0" style="width: 90%; height: 450px; margin-left: 20px;" > 
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Information</label></td>			
			</tr>
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;SAP (Program/Project) Code</label></td>			
				<td><input type="text" class="form-control" id="projectSapCode" ></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Sub Project SAP Code</label></td>			
				<td><input type="text" class="form-control" id="projectName" ></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Customer Name</label></td>			
				<td><input type="text" class="form-control" id="pmSAPCode" onblur="fetchManager(this.value)"></td>
			</tr>
			<tr>
				<td><label for="pmName">&nbsp;&nbsp;&nbsp;&nbsp;Relationship Category</label></td>			
				<td>
					<select class="form-control">
						<option value="project">Project</option>
						<option value="program">Program</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Program / Project Name (Max Length 60)</label></td>			
				<td><input type="text" class="form-control" id="repositoryName" ></td>
			</tr>
			<tr>
				<td><label for="serviceStartDate">&nbsp;&nbsp;&nbsp;&nbsp;Planned Start Date <br> &nbsp;&nbsp;&nbsp;&nbsp;[ YYYY-MM-DD ]</label></td>			
				<td>
				
					<div class='input-group date' id='datetimepicker1'>
                    	<input type="text" class="form-control" id="serviceStartDate" >
                    	<span class="input-group-addon">
                       		<span class="glyphicon glyphicon-calendar"></span>
                    	</span>
                	
				
				</div>
				</td>
				
			</tr>
			<tr>
				<td><label for="serviceEndDate">&nbsp;&nbsp;&nbsp;&nbsp;Planned End Date <br> &nbsp;&nbsp;&nbsp;&nbsp;[ YYYY-MM-DD ] </label></td>			
				<td>
					<div class='input-group date' id='datetimepicker2'>
                    	<input type="text" class="form-control" id="serviceEndDate" >
                    	<span class="input-group-addon">
                       		<span class="glyphicon glyphicon-calendar"></span>
                    	</span>
                	</div>
				</td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Program / Project Type</label></td>			
				<td>
					<select class="form-control">
						<option value="agile">Agile</option>
						<option value="cadengineering">CAD Engineering</option>
						<option value="maintainance">Maintainance</option>
						<option value="testing">Testing</option>
						<option value="development">Development</option>
						<option value="enhancement">Enhancement</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Platform</label></td>			
				<td><input type="text" class="form-control" id="repositoryName" ></td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;Billing Type</label></td>			
				<td>
					<select class="form-control">
						<option value="Bench">Bench</option>
						<option value="RandD">R and D</option>
						<option value="Presales">Pre-Sales</option>
						<option value="MFB">Monthly Fixed Billing-MFB</option>
						<option value="MVB">Monthly Variable Billing- MVB</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="repositoryName">&nbsp;&nbsp;&nbsp;&nbsp;EVM Applicability</label></td>			
				<td>
					<select class="form-control">
						<option value="NO">NO</option>
						<option value="YES">YES</option>
					</select>
				</td>
			</tr>
			
			
			</table>
			
			<script type="text/javascript">
	            $(function () {
	                $('#datetimepicker1').datetimepicker({pickTime: false, format:'YYYY-MM-DD'});
	                $('#datetimepicker2').datetimepicker({pickTime: false, format:'YYYY-MM-DD'});
	            });
        	</script>
			
		</td>
		<td width="50%" valign="top">
			
			<table border="0" style="width: 90%;height: 400px; margin-left: 20px;" >
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">LOB</label></td>			
			</tr>
			<tr>
				<td><label for="spocSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;Level-1</label></td>			
				<td>
					<select class="form-control">
						<option value="1">Option 1</option>
						<option value="2">Option 2</option>
						<option value="3">Option 3</option>
						<option value="4">Option 4</option>
					</select>
				</td>
			</tr>
			<tr>
				<td><label for="spocName">&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;Level-2</label></td>			
				<td>
					<select class="form-control">
						<option value="1">Option 1</option>
						<option value="2">Option 2</option>
						<option value="3">Option 3</option>
						<option value="4">Option 4</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Level-3</label></td>			
				<td>
					<select class="form-control">
						<option value="1">Option 1</option>
						<option value="2">Option 2</option>
						<option value="3">Option 3</option>
						<option value="4">Option 4</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Level-4</label></td>			
				<td>
					<select class="form-control">
						<option value="1">Option 1</option>
						<option value="2">Option 2</option>
						<option value="3">Option 3</option>
						<option value="4">Option 4</option>
					</select>
				</td>
			</tr>
			
			
			
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Manager</label></td>			
			</tr>
			<tr>
				<td><label for="spocSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;SAP Code</label></td>			
				<td><input type="text" class="form-control" id="spocSAPCode" onblur="fetchSpoc(this.value)"></td>
			</tr>
			<tr>
				<td width="47%"><label for="spocName">&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;Name</label></td>			
				<td><input type="text" class="form-control" id="spocName" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Mail Id</label></td>			
				<td><input type="text" class="form-control" id="spocEmail" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Senior Manager</label></td>			
			</tr>
			<tr>
				<td><label for="spocSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;1.&nbsp;SAP Code</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverSapCode" onblur="fetchAlternateApprover(this.value)"></td>
			</tr>
			<tr>
				<td width="47%"><label for="spocName">&nbsp;&nbsp;&nbsp;&nbsp;2.&nbsp;Name</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverName" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td><label for="spocEmail">&nbsp;&nbsp;&nbsp;&nbsp;3.&nbsp;Mail Id</label></td>			
				<td><input type="text" class="form-control" id="alternateApproverEmail" readonly="readonly"></td>
			</tr>
			
			
			
		</table>

</td>
</tr>


</table>
<br>
<button class="btn btn-primary" type="button">Create New Project</button>

</div>