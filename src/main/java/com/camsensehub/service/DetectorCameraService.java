package com.camsensehub.service;

import com.camsensehub.entity.Camera;
import com.camsensehub.model.response.GenericResponse;

import java.util.List;

public interface DetectorCameraService {
    public GenericResponse<List<Camera>> getAllCameraDetails();

    public GenericResponse<List<Camera>> findCameraByOwner(String owner);

    public GenericResponse<List<Camera>> getCameraDetailsByLocationAndStampDateBetween(String location, String startDate, String endDate);

    public GenericResponse<List<Camera>> findFireDetectorCameraDetails();
}
