console.log("Attend Module");
var attendService = (function(){
	return {name:"AAAA"};
	
	function getList(param, callback, error){
		var m_num = param.m_num;
		
		$.getJSON("/attend/"+m_num+".json",
			function(data){
				if(callback){
					callback(data);
				}
		}).fail(function(xhr, status, error){
			if(error){
				error();
			}
		});
	}
	
	
	return {
		getList:getList
	};
})()