package com.tiger.system.entity;

import java.io.Serializable;

public class Role implements Serializable {
    private String orderId;

    private String roleNo;

    private String roleName;

    private String language;
    
    /**
     * status:1 选中；0 未选中
     */
    private String status;

    private static final long serialVersionUID = 1L;

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    public String getRoleNo() {
        return roleNo;
    }

    public void setRoleNo(String roleNo) {
        this.roleNo = roleNo == null ? null : roleNo.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

	@Override
	public String toString() {
		return "Role [orderId=" + orderId + ", roleNo="
	     + roleNo + ", roleName=" + roleName + ", language=" + language
				+ "]";
	}
    
}