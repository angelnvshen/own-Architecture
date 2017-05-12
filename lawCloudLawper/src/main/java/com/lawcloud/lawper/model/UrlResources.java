package com.lawcloud.lawper.model;

import javax.persistence.*;

@Table(name = "url_resources")
public class UrlResources {
    @Id
    @Column(name = "RESOURCE_ID")
    private String resourceId;

    @Column(name = "RESOURCE_NAME")
    private String resourceName;

    @Column(name = "RESOURCE_DESC")
    private String resourceDesc;

    @Column(name = "RESOURCE_PATH")
    private String resourcePath;

    @Column(name = "ENABLE")
    private Integer enable;

    /**
     * @return RESOURCE_ID
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * @param resourceId
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * @return RESOURCE_NAME
     */
    public String getResourceName() {
        return resourceName;
    }

    /**
     * @param resourceName
     */
    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    /**
     * @return RESOURCE_DESC
     */
    public String getResourceDesc() {
        return resourceDesc;
    }

    /**
     * @param resourceDesc
     */
    public void setResourceDesc(String resourceDesc) {
        this.resourceDesc = resourceDesc;
    }

    /**
     * @return RESOURCE_PATH
     */
    public String getResourcePath() {
        return resourcePath;
    }

    /**
     * @param resourcePath
     */
    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath;
    }

    /**
     * @return ENABLE
     */
    public Integer getEnable() {
        return enable;
    }

    /**
     * @param enable
     */
    public void setEnable(Integer enable) {
        this.enable = enable;
    }
}