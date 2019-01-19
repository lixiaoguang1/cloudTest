$(function(){
	
	var staffId=$("#orderId").val();
	
	var departs=[
		{"orderId":"bumen1","department":"部门1"},
		{"orderId":"bumen2","department":"部门2"}];
	
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
		
		$.each(departs,function(index,value){
			if(value.orderId==data.department){
				$("#department").append("<option value='"+value.orderId+"' selected='selected'>"+value.department+"</option>");
			}else{
				$("#department").append("<option value='"+value.orderId+"'>"+value.department+"</option>");
			}
			//$("#department").val("bumen1");
			$("#department").prev().val($("#department").find("option:selected").text());
		});
		var roles=data.roles1;
		if(roles !=null){
			var html="";
			for(var i=0;i<roles.length;i++){
	        	html +="<option value='"+roles[i].roleNo+"' "+(roles[i].status==1?'selected':'')+">"+roles[i].roleName+"</option>";
	        }
			$('#roles').append(html);
		}
		//$(".addInfBtn").css('display','block');
	}
	$('#saveForm').validate({
		rules: {
			name       :{required:true},
			email      :{required:true}
        },messages:{
            name :{required:"必填"},
            email :{required:"必填"}
        }
 	});
	$('.saveBtn').click(function(){
	   if($('#saveForm').valid()){
           $.ajax({
               type: "PUT",
               url: "/systemManager/system/v1/staff/updateStaff",
               data: $("#saveForm").serialize(),
               headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
               success: function (data) {
                   if (data.resCode == "200000") {
                       alert("编辑成功");
                       pageaction();
                       closeDialog();
                   } else {
                       alert(data);
                   }
               }
           });
	   }else{
		   alert('数据验证失败，请检查！');
	   }
	});

});	
