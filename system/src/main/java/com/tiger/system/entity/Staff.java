package com.tiger.system.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Staff implements Serializable {
    private String orderId;

    private String staffNo;

    private String staffName;

    private String staffPwd;

    private String email;

    private String department;

    private String telephone;

    private Date createDate;
    
    private List<String> roles;
    
    private List<Role> roles1;

    private static final long serialVersionUID = 1L;

    
    public List<Role> getRoles1() {
		return roles1;
	}

	public void setRoles1(List<Role> roles1) {
		this.roles1 = roles1;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo == null ? null : staffNo.trim();
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }

    public String getStaffPwd() {
        return staffPwd;
    }

    public void setStaffPwd(String staffPwd) {
        this.staffPwd = staffPwd == null ? null : staffPwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "Staff [orderId=" + orderId + ", staffNo=" + staffNo + ", staffName=" + staffName + ", staffPwd="
				+ staffPwd + ", email=" + email + ", department=" + department + ", telephone=" + telephone
				+ ", createDate=" + createDate + ", roles=" + roles + "]";
	}
    
}