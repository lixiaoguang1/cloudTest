$(function(){
	//加载部门信息
	$("#department").append("<option value='bumen1'>bumen1</option>");
	$("#department").append("<option value='bumen2'>bumen2</option>");
	 $.ajax({
         type: "GET",
         url: "/systemManager/system/v1/role/searchRoles/-1/-1?keyword=",
         headers: {"Content-type": "application/json"},
         success: function (data) {
             if (data.resCode =="200000") {
                var roles=data.data;
                var html="";
                for(var i=0;i<roles.length;i++){
                	html +="<option value='"+roles[i].roleNo+"'>"+roles[i].roleName+"</option>";
                }
                $('#roles').append(html);
             } else {
                 alert(data);
             }
         }
     });
	
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
	 alert($("#saveForm").serialize())
	 if($('#saveForm').valid()){
         $.ajax({
             type: "POST",
             url: "/systemManager/system/v1/staff/addStaff",
             data: $("#saveForm").serialize(),
             headers: {"Content-type": "application/x-www-form-urlencoded;charset=UTF-8"},
             success: function (data) {
            	 alert(JSON.stringify(data));
                 if (data.resCode == "200000") {
                     alert("保存成功");
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

