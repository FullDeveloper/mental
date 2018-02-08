package com.emed.mental.auth.server.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class AuthClientService implements Serializable {
    @Id
    private Long id;

    /**
     * 客户端编号
     *
     * @mbg.generated
     */
    private Long clientId;

    /**
     * 服务编号
     *
     * @mbg.generated
     */
    private Long serviceId;

    /**
     * 描述
     *
     * @mbg.generated
     */
    private String description;

    /**
     * 创建时间
     *
     * @mbg.generated
     */
    private Date crtTime;

    /**
     * 创建人编号
     *
     * @mbg.generated
     */
    private Long crtUserId;

    /**
     * 创建人
     *
     * @mbg.generated
     */
    private String crtName;

    /**
     * 创建人主机
     *
     * @mbg.generated
     */
    private String crtHost;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Long getCrtUserId() {
        return crtUserId;
    }

    public void setCrtUserId(Long crtUserId) {
        this.crtUserId = crtUserId;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtHost() {
        return crtHost;
    }

    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", clientId=").append(clientId);
        sb.append(", serviceId=").append(serviceId);
        sb.append(", description=").append(description);
        sb.append(", crtTime=").append(crtTime);
        sb.append(", crtUserId=").append(crtUserId);
        sb.append(", crtName=").append(crtName);
        sb.append(", crtHost=").append(crtHost);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AuthClientService other = (AuthClientService) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getClientId() == null ? other.getClientId() == null : this.getClientId().equals(other.getClientId()))
            && (this.getServiceId() == null ? other.getServiceId() == null : this.getServiceId().equals(other.getServiceId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCrtTime() == null ? other.getCrtTime() == null : this.getCrtTime().equals(other.getCrtTime()))
            && (this.getCrtUserId() == null ? other.getCrtUserId() == null : this.getCrtUserId().equals(other.getCrtUserId()))
            && (this.getCrtName() == null ? other.getCrtName() == null : this.getCrtName().equals(other.getCrtName()))
            && (this.getCrtHost() == null ? other.getCrtHost() == null : this.getCrtHost().equals(other.getCrtHost()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getClientId() == null) ? 0 : getClientId().hashCode());
        result = prime * result + ((getServiceId() == null) ? 0 : getServiceId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCrtTime() == null) ? 0 : getCrtTime().hashCode());
        result = prime * result + ((getCrtUserId() == null) ? 0 : getCrtUserId().hashCode());
        result = prime * result + ((getCrtName() == null) ? 0 : getCrtName().hashCode());
        result = prime * result + ((getCrtHost() == null) ? 0 : getCrtHost().hashCode());
        return result;
    }
}