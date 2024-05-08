package com.camsensehub.service;

import com.camsensehub.api.controller.DetectorCameraController;
import com.camsensehub.entity.Camera;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.CameraRepository;
import com.camsensehub.utils.Constants;
import com.camsensehub.utils.UtilMethods;
import com.camsensehub.utils.UtilityFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class DetectorCameraServiceImpl implements DetectorCameraService {
    private static final Logger LOG = LoggerFactory.getLogger(DetectorCameraServiceImpl.class);

    private final CameraRepository cameraRepository;

    public DetectorCameraServiceImpl(CameraRepository cameraRepository) {
        this.cameraRepository = cameraRepository;
    }

    @Override
    public GenericResponse<List<Camera>> getAllCameraDetails() {
        GenericResponse<List<Camera>> response = new GenericResponse<>();
        List<Camera> cameraDetails = new ArrayList<>();
        try {
            LOG.info("IN - getAllCameraDetails - Date: {}", new Date());
            LOG.info("fetching cameraDetails from DB start");
            cameraDetails = cameraRepository.findAll();
            LOG.info("fetching cameraDetails from DB end");
            if(!cameraDetails.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, cameraDetails);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            LOG.error("Exception in getAllCameraDetails: {}", e.toString());
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logException("getAllCameraDetails", e, LOG);
        }

        LOG.info("OUT - getAllCameraDetails - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<Camera>> getCameraDetailsByLocationAndStampDateBetween(String location, String startDate, String endDate) {
        GenericResponse<List<Camera>> response = new GenericResponse<>();
        List<Camera> cameraDetailList = new ArrayList<>();
        try {
            LOG.info("IN - getCameraDetailsByLocationAndStampDateBetween - Date: {}", new Date());
            LOG.info("Request Params: location=" + location + ",startDate=" + startDate + ",endDate=" + endDate);

            Date formattedStartDate = UtilityFunctions.convertStringToDate(startDate);
            Date formattedEndDate = UtilityFunctions.convertStringToDate(endDate);

            LOG.info("fetching cameraDetails against location: {} start", location);
            cameraDetailList = cameraRepository.findByLocationAndStampDateBetween(location, formattedStartDate, formattedEndDate);
            LOG.info("fetching cameraDetails from DB end");

            if(!cameraDetailList.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, cameraDetailList);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logException("getCameraDetailsByLocationAndStampDateBetween", e, LOG);
        }

        LOG.info("OUT - getCameraDetailsByLocationAndStampDateBetween - Date: {}", new Date());
        return response;
    }

    @Override
    public GenericResponse<List<Camera>> findCameraByOwner(String owner) {
        GenericResponse<List<Camera>> response = new GenericResponse<>();
        List<Camera> cameraDetails = new ArrayList<>();
        try {
            LOG.info("IN - findCameraByOwner - Date: {}", new Date());
            LOG.info("fetching cameraDetails from DB start");
            cameraDetails = cameraRepository.findByOwner(owner);
            LOG.info("fetching cameraDetails from DB end");

            if(!cameraDetails.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, cameraDetails);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logException("findCameraByOwner", e, LOG);
        }

        LOG.info("OUT - findCameraByOwner - Date: {}", new Date());
        return response;
    }


    @Override
    public GenericResponse<List<Camera>> findFireDetectorCameraDetails() {
        GenericResponse<List<Camera>> response = new GenericResponse<>();
        List<Camera> cameraDetails = new ArrayList<>();
        try {
            LOG.info("IN - findFireDetectorCameraDetails - Date: {}", new Date());
            LOG.info("fetching cameraDetails from DB start");
            cameraDetails = cameraRepository.findByFireDetectionIsTrue();
            LOG.info("fetching cameraDetails from DB end");

            if(!cameraDetails.isEmpty()) {
                response = new GenericResponse<>(Constants.ResponseCodes.OK, cameraDetails);
                UtilMethods.logSuccess(response, LOG);
            }
            else {
                response = new GenericResponse<>(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND);
                UtilMethods.logFailed(response, LOG);
            }
        } catch (Exception e) {
            response = new GenericResponse<>(Constants.ResponseCodes.UNABLE_TO_PROCESS);
            UtilMethods.logException("findFireDetectorCameraDetails", e, LOG);
        }

        LOG.info("OUT - findFireDetectorCameraDetails - Date: {}", new Date());
        return response;
    }
}
