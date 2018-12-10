package com.tiger.apigateway.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tiger.apigateway.entity.StaffInfo;
public class SecurityUser extends StaffInfo implements UserDetails
{

    private static final long serialVersionUID = 1L;
    public SecurityUser(StaffInfo staffInfo) {
        if(staffInfo != null)
        {
        	System.out.println("??????????????????????????????????");
            this.setOrderId(staffInfo.getOrderId());
            this.setStaffNo(staffInfo.getStaffNo());
          //  this.setStaffName(staffInfo.getStaffName());
            this.setEmail(staffInfo.getEmail());
           // this.setStaffPwd(staffInfo.getStaffPwd());
          //  this.setSex(user.getSex());
            this.setCreateDate(staffInfo.getCreateDate());
            this.setUsername(staffInfo.getStaffNo());
            this.setPassword(staffInfo.getStaffPwd());
            this.setStaffPwd(staffInfo.getStaffPwd());
            System.out.println("#########################################");
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        List<Role> roles = this.getRoles();
//        if(roles != null)
//        {
//            for (Role role : roles) {
//                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
//                authorities.add(authority);
//            }
//        }
        return authorities;
    }

    /**
     * 默认情况下，登录页面输入的用户名密码信息的参数名称为:username,password
     */
    @Override
    public String getPassword() {
        return super.getStaffPwd();
    }

    @Override
    public String getUsername() {
        return super.getStaffNo();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}