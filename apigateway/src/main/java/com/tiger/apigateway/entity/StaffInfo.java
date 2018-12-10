package com.tiger.apigateway.entity;



import java.util.Date;
import java.util.List;

public class StaffInfo {
    private String orderId;

    private String staffNo;

    private String staffName;

    private String email;

    private String department;

    private String telephone;

    private Date createDate;
    
    private String staffPwd;
    
    
    private String username;
    private String password;
    private Date lastPasswordResetDate;
    private List<String> roles;
    
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

	public String getStaffPwd() {
		return staffPwd;
	}

	public void setStaffPwd(String staffPwd) {
		this.staffPwd = staffPwd;
	}

	public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	@Override
	public String toString() {
		return "StaffInfo [orderId=" + orderId + ", staffNo=" + staffNo + ", staffName=" + staffName + ", email="
				+ email + ", department=" + department + ", telephone=" + telephone + ", createDate=" + createDate
				+ ", staffPwd=" + staffPwd + ", username=" + username + ", password=" + password
				+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", roles=" + roles + "]";
	}
    
    
}