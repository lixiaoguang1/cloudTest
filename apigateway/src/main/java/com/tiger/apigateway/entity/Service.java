package com.tiger.apigateway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Service implements Serializable {
    private String orderId;

    private String url;

    private String method;

    private String serviceName;

    private String serviceNameCn;

    private Date createrDate;
    
    private List<String> roles;

    private static final long serialVersionUID = 1L;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getServiceNameCn() {
        return serviceNameCn;
    }

    public void setServiceNameCn(String serviceNameCn) {
        this.serviceNameCn = serviceNameCn == null ? null : serviceNameCn.trim();
    }

    public Date getCreaterDate() {
        return createrDate;
    }

    public void setCreaterDate(Date createrDate) {
        this.createrDate = createrDate;
    }
}