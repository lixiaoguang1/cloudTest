1、用户登录接口
http://localhost:10001/auth/
{"username":"admin","password":"123456"}
2、添加（注册）用户信息
http://localhost:10001/auth/register
{"staffNo":"test","staffName":"test","staffPwd":"123456",
"email":"arcsin721@12.com","department":"department1",
"telephone":"1111"}
//Mybatis 自动生成代码
gradle mybatisGenerator

系统登录首页
http://localhost:10001/front/toLogin


2018-12-23 
添加操作员信息
http://localhost:9000/systemManager/system/v1/staff/addStaff  
POST
{
  "staffNo":"test001",
  "staffName":"test001",
  "staffPwd":"123456"
  "email":"arcsin721@163.com",
  "department":"部门1",
  "telephone":"1234567"
}

修改操作员信息

http://localhost:9000/systemManager/system/v1/staff/updateStaff
PUT
{
  "staffNo":"test001222",
  "staffName":"test001",
  "email":"arcsin721@163.com",
  "department":"部门1",
  "telephone":"1234567",
  "orderId":"d1372811-06c1-11e9-bb84-7ce9d3bb50c2"
}

删除操作员信息
http://localhost:9000/system/v1/staff/delStaff?staffId=d1372811-06c1-11e9-bb84-7ce9d3bb50c2
http://localhost:10001/systemManager/system/v1/staff/delStaff?staffId=test100

获取操作员信息
http://localhost:9000/systemManager/system/v1/staff/searchStaff?staffId=622b52cf-8dc1-11e8-81f9-7ce9d3bb50c2
查询操作员信息
http://localhost:9000/systemManager/system/v1/staff/searchStaffs/2/1?keyword=
2018-12-23 
添加角色信息
http://localhost:9000/system/v1/role/addRole
{
  "roleNo":"common",
  "roleName":"普通操作员",
  "language":"en"
}
修改角色信息
http://localhost:9000/system/v1/role/updateRole
{
  "orderId":"6c5141e9-085b-11e9-9f57-7ce9d3bb50c2",
  "roleNo":"common",
  "roleName":"普通操作员",
  "language":"zh"
}
查询角色信息
http://localhost:9000/system/v1/role/searchRole?roleId=6c5141e9-085b-11e9-9f57-7ce9d3bb50c2
http://localhost:9000/system/v1/role/searchRoles/1/2?keyword=
删除角色信息
http://localhost:9000/system/v1/role/deleteRole?roleId=6c5141e9-085b-11e9-9f57-7ce9d3bb50c2


查看端口号：
netstat -aon|findstr "10000"
tasklist|findstr "4252"
taskkill /f /t /im yundetectservice.exe

taskkill /f /t /im tasklist|findstr "4200"


 $.ajax({
         type: "GET",
         url: "/systemManager/system/v1/role/searchRoles/-1/-1?keyword=",
         headers: {"Content-type": "application/json"},
         success: function (data) {
            console.info(data.data);
         }
     });

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




