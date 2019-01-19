$(function(){
	
	var staffId=$("#orderId").val();
	
    //初始化用户信息
	$.ajax({
        type: "GET",
        url: "/systemManager/system/v1/staff/searchStaff?staffId="+staffId,
        headers: {"Content-type": "application/json"},
        success: function (data) {
            if (data.resCode =="200000") {
               var staff=data.data;
               init(staff);
            } else {
                alert(data);
            }
        }
    });
	
	function init(data){
		$("#staffNo").val(data.staffNo);
		$("#email").val(data.email);
		$("#telephone").val(data.telephone);
		$("#department").val(data.department);	
		
		var roles=data.roles1;
		if(roles !=null){
			var html="";
			for(var i=0;i<roles.length;i++){
				if(roles[i].status==1){
					html +="<option value='"+roles[i].roleNo+"'>"+roles[i].roleName+"</option>";
				}
	        }
			$('#roles').append(html);
		}
	}

});	
