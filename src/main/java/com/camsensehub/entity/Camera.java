package com.camsensehub.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(collection = "Camera")
public class Camera {
    private String name;

    private String location;

    private String status;

    private String ipAddress;

    private String model;

    private String resolution;

    private String fieldOfView;

    private boolean fireDetection;

    private boolean smokeDetection;

    private String lastDetectedEvent;

    private String healthMetrics;

    private String owner;

    private String associatedDevices;

    private Date stampDate;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getFieldOfView() {
        return fieldOfView;
    }

    public void setFieldOfView(String fieldOfView) {
        this.fieldOfView = fieldOfView;
    }

    public boolean isFireDetection() {
        return fireDetection;
    }

    public void setFireDetection(boolean fireDetection) {
        this.fireDetection = fireDetection;
    }

    public boolean isSmokeDetection() {
        return smokeDetection;
    }

    public void setSmokeDetection(boolean smokeDetection) {
        this.smokeDetection = smokeDetection;
    }

    public String getLastDetectedEvent() {
        return lastDetectedEvent;
    }

    public void setLastDetectedEvent(String lastDetectedEvent) {
        this.lastDetectedEvent = lastDetectedEvent;
    }

    public String getHealthMetrics() {
        return healthMetrics;
    }

    public void setHealthMetrics(String healthMetrics) {
        this.healthMetrics = healthMetrics;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssociatedDevices() {
        return associatedDevices;
    }

    public void setAssociatedDevices(String associatedDevices) {
        this.associatedDevices = associatedDevices;
    }

    public Date getStampDate() {
        return stampDate;
    }

    public void setStampDate(Date stampDate) {
        this.stampDate = stampDate;
    }

    @Override
    public String toString() {
        return "Camera{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", model='" + model + '\'' +
                ", resolution='" + resolution + '\'' +
                ", fieldOfView='" + fieldOfView + '\'' +
                ", fireDetection=" + fireDetection +
                ", smokeDetection=" + smokeDetection +
                ", lastDetectedEvent='" + lastDetectedEvent + '\'' +
                ", healthMetrics='" + healthMetrics + '\'' +
                ", owner='" + owner + '\'' +
                ", associatedDevices='" + associatedDevices + '\'' +
                ", stampDate=" + stampDate +
                '}';
    }
}
