<div align="center">
<table border="1" style="width: 90%;">
	
		<tr class="active">
			<td colspan="2" style="height: 52px;" valign="middle" >
				
				<input type="text" class="form-control" placeholder="REMEDY ID" id="incidentNumber" name="incidentNumber" style="display:inline; float:left; width: 250px; margin-left: 20px;">
					
				<span style="margin-left: 10%;">Please provide the following project details to Migrate</span>
				
			</td>
		</tr>
	
	<tr>
		<td width="50%" valign="top">


		<table border="0" style="width: 90%; height: 400px; margin-left: 20px;" >
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Information</label></td>			
			</tr>
			<tr>
				<td width="47%"><label for="projectCode">&nbsp;&nbsp;&nbsp;&nbsp;Project Code</label></td>			
				<td><input type="text" class="form-control" id="projectSapCode" ></td>
			</tr>
			<tr>
				<td><label for="projectName">&nbsp;&nbsp;&nbsp;&nbsp;Project Name</label></td>			
				<td><input type="text" class="form-control" id="projectName" ></td>
			</tr>
			<tr>
				<td><label for="pmSAPCode">&nbsp;&nbsp;&nbsp;&nbsp;Manager SAP Code</label></td>			
				<td><input type="text" class="form-control" id="pmSAPCode" onblur="fetchManager(this.value)"></td>
			</tr>
			<tr>
				<td><label for="pmName">&nbsp;&nbsp;&nbsp;&nbsp;Manager Name</label></td>			
				<td><input type="text" class="form-control" id="projectManager" readonly="readonly"></td>
			</tr>
			
			<tr>
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Alternate Approver</label></td>			
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
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Information</label></td>			
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
				<td colspan="2" style="background-color: #D8D8D8; height: 25px;"><label for="exampleInputName2">Project Spoc for SVN</label></td>			
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
			
		</table>
		
		<script type="text/javascript">
			function changeCMTassSourceValue(f){
				$('#cmtassSource7').val('Other - '+f.value);
			}
		</script>

</td>
</tr>
</table>

<br><button class="btn btn-primary" type="button" onclick="createNewRepository()">Submit Request</button>

</div>