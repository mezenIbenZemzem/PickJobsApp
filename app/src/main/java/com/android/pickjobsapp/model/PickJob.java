package com.android.pickjobsapp.model;

import java.util.Date;

public class PickJob {
    private String id;
    private String channel;
    private String facilityRef;
    private Boolean hasScannableCodes;
    private String labelStatus;
    private Date lastModificationDate;
    private Date orderDate;
    private int orderArticleCount;
    private int pickedArticleCount;
    private String shortId;
    private String status;
    private int targetVersion;
    private String tenantOrderId;
    private int version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getFacilityRef() {
        return facilityRef;
    }

    public void setFacilityRef(String facilityRef) {
        this.facilityRef = facilityRef;
    }

    public Boolean getHasScannableCodes() {
        return hasScannableCodes;
    }

    public void setHasScannableCodes(Boolean hasScannableCodes) {
        this.hasScannableCodes = hasScannableCodes;
    }

    public String getLabelStatus() {
        return labelStatus;
    }

    public void setLabelStatus(String labelStatus) {
        this.labelStatus = labelStatus;
    }

    public Date getLastModificationDate() {
        return lastModificationDate;
    }

    public void setLastModificationDate(Date lastModificationDate) {
        this.lastModificationDate = lastModificationDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderArticleCount() {
        return orderArticleCount;
    }

    public void setOrderArticleCount(int orderArticleCount) {
        this.orderArticleCount = orderArticleCount;
    }

    public int getPickedArticleCount() {
        return pickedArticleCount;
    }

    public void setPickedArticleCount(int pickedArticleCount) {
        this.pickedArticleCount = pickedArticleCount;
    }

    public String getShortId() {
        return shortId;
    }

    public void setShortId(String shortId) {
        this.shortId = shortId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTargetVersion() {
        return targetVersion;
    }

    public void setTargetVersion(int targetVersion) {
        this.targetVersion = targetVersion;
    }

    public String getTenantOrderId() {
        return tenantOrderId;
    }

    public void setTenantOrderId(String tenantOrderId) {
        this.tenantOrderId = tenantOrderId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
