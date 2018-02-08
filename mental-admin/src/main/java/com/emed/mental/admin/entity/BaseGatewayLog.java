package com.emed.mental.admin.entity;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class BaseGatewayLog implements Serializable {
    @Id
    private Long id;

    /**
     * 请求路径
     *
     * @mbg.generated
     */
    private String uri;

    /**
     * 请求方式
     *
     * @mbg.generated
     */
    private String method;

    /**
     * 菜单名称
     *
     * @mbg.generated
     */
    private String menu;

    /**
     * 请求时间
     *
     * @mbg.generated
     */
    private Date crtTime;

    /**
     * 请求人编号
     *
     * @mbg.generated
     */
    private Long crtUser;

    /**
     * 请求人名称
     *
     * @mbg.generated
     */
    private String crtName;

    /**
     * 请求人主机
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public Long getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(Long crtUser) {
        this.crtUser = crtUser;
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
        sb.append(", uri=").append(uri);
        sb.append(", method=").append(method);
        sb.append(", menu=").append(menu);
        sb.append(", crtTime=").append(crtTime);
        sb.append(", crtUser=").append(crtUser);
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
        BaseGatewayLog other = (BaseGatewayLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUri() == null ? other.getUri() == null : this.getUri().equals(other.getUri()))
            && (this.getMethod() == null ? other.getMethod() == null : this.getMethod().equals(other.getMethod()))
            && (this.getMenu() == null ? other.getMenu() == null : this.getMenu().equals(other.getMenu()))
            && (this.getCrtTime() == null ? other.getCrtTime() == null : this.getCrtTime().equals(other.getCrtTime()))
            && (this.getCrtUser() == null ? other.getCrtUser() == null : this.getCrtUser().equals(other.getCrtUser()))
            && (this.getCrtName() == null ? other.getCrtName() == null : this.getCrtName().equals(other.getCrtName()))
            && (this.getCrtHost() == null ? other.getCrtHost() == null : this.getCrtHost().equals(other.getCrtHost()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUri() == null) ? 0 : getUri().hashCode());
        result = prime * result + ((getMethod() == null) ? 0 : getMethod().hashCode());
        result = prime * result + ((getMenu() == null) ? 0 : getMenu().hashCode());
        result = prime * result + ((getCrtTime() == null) ? 0 : getCrtTime().hashCode());
        result = prime * result + ((getCrtUser() == null) ? 0 : getCrtUser().hashCode());
        result = prime * result + ((getCrtName() == null) ? 0 : getCrtName().hashCode());
        result = prime * result + ((getCrtHost() == null) ? 0 : getCrtHost().hashCode());
        return result;
    }
}