package com.tiger.apigateway.entity;




import java.util.Date;
import java.util.List;


public class User {
   private String id;
   private String username;
   private String password;
   private String email;
   private String tel;
   private List<String> roles;
   private Date lastPasswordResetDate;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public List<String> getRoles() {
	return roles;
}
public void setRoles(List<String> roles) {
	this.roles = roles;
}
public Date getLastPasswordResetDate() {
	return lastPasswordResetDate;
}
public void setLastPasswordResetDate(Date lastPasswordResetDate) {
	this.lastPasswordResetDate = lastPasswordResetDate;
}
  
}
