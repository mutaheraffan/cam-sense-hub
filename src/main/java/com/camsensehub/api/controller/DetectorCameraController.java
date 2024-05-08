package com.camsensehub.api.controller;

import com.camsensehub.api.ApiController;
import com.camsensehub.entity.Camera;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.service.DetectorCameraService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = ApiController.CAMERA_ENDPOINT)
@CrossOrigin(origins = "${allow.origin.endpoint}", allowedHeaders = "*")
public class DetectorCameraController {
    private static final Logger LOG = LoggerFactory.getLogger(DetectorCameraController.class);

    private final DetectorCameraService detectorCameraService;

    public DetectorCameraController(DetectorCameraService detectorCameraService) {
        this.detectorCameraService = detectorCameraService;
    }


    @GetMapping(path = "/getAllCameraDetails")
    public GenericResponse<List<Camera>> getAllCameraDetails() {
        return detectorCameraService.getAllCameraDetails();
    }

    @GetMapping(path = "/getCameraDetailsByLocation")
    public GenericResponse<List<Camera>> getCameraDetailsByLocation(@RequestParam("location") String location, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        return detectorCameraService.getCameraDetailsByLocationAndStampDateBetween(location, startDate, endDate);
    }


    @GetMapping(path = "/findCameraByOwner")
    public GenericResponse<List<Camera>> findCameraByOwner(@RequestParam("owner") String owner) {
        return detectorCameraService.findCameraByOwner(owner);
    }

    @GetMapping(path = "/findFireDetectorCameraDetails")
    public GenericResponse<List<Camera>> findFireDetectorCameraDetails() {
        return detectorCameraService.findFireDetectorCameraDetails();
    }


}
