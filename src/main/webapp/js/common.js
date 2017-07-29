function getDataProps(aoData){
	var map = new Object();
	for(var x=0; x<aoData.length; x++){    
		var aoDataName = aoData[x].name;
		var aoDataValue = aoData[x].value;
		map[aoDataName]= aoDataValue;
	}
	return map;
}