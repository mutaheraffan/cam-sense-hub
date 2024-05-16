package com.camsensehub.service;

import com.camsensehub.entity.Camera;
import com.camsensehub.model.response.GenericResponse;
import com.camsensehub.repository.CameraRepository;
import com.camsensehub.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoDataIntegrityViolationException;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author MutaherAffan on 5/16/2024.
 * @project cam-sense-hub
 */
public class DetectorCameraServiceImplTest {
    @InjectMocks
    private DetectorCameraServiceImpl detectorCameraService;

    @Mock
    private CameraRepository cameraRepository;


    @BeforeEach
    void setUp() {
        AutoCloseable closeable = MockitoAnnotations.openMocks(this);
    }


    /* -------------------------------------- Start : getAllCameraDetails -------------------------------------- */

    @Test
    @Order(1)
    @DisplayName("getAllCameraDetails - whenSuccess - returnsProcessedOK")
    void getAllCameraDetails_whenSuccess_returnsProcessedOK() throws Exception {
        // Arrange
        Camera camera = new Camera();
        camera.setName("Camera 1");
        camera.setLocation("Building A, Floor 1");
        camera.setStatus("Online");
        camera.setIpAddress("192.168.1.100");
        camera.setModel("PTZ");
        camera.setResolution("1080p");
        camera.setFieldOfView("120 degrees");
        camera.setFireDetection(true);
        camera.setSmokeDetection(true);
        camera.setLastDetectedEvent("Fire detected");
        camera.setHealthMetrics("Temperature: 25°C, Voltage: 12V");
        camera.setOwner("Security Department");
        camera.setAssociatedDevices("Microphone, Speaker");
        camera.setStampDate(new Date());

        Camera camera2 = new Camera();
        camera2.setName("Camera 2");
        camera2.setLocation("Building B, Lobby");
        camera2.setStatus("Offline");
        camera2.setIpAddress("192.168.1.101");
        camera2.setModel("Fixed");
        camera2.setResolution("720p");
        camera2.setFieldOfView("90 degrees");
        camera2.setFireDetection(false);
        camera2.setSmokeDetection(true);
        camera2.setLastDetectedEvent("Smoke detected");
        camera2.setHealthMetrics("Temperature: 23°C, Voltage: 11.5V");
        camera2.setOwner("Facilities Department");
        camera2.setAssociatedDevices("Motion Sensor");
        camera2.setStampDate(new Date());


        Camera camera3 = new Camera();
        camera3.setName("Camera 3");
        camera3.setLocation("Parking Lot C");
        camera3.setStatus("Online");
        camera3.setIpAddress("192.168.1.102");
        camera3.setModel("Dome");
        camera3.setResolution("4K");
        camera3.setFieldOfView("180 degrees");
        camera3.setFireDetection(true);
        camera3.setSmokeDetection(false);
        camera3.setLastDetectedEvent("Fire detected");
        camera3.setHealthMetrics("Temperature: 30°C, Voltage: 12.2V");
        camera3.setOwner("Security Department");
        camera3.setAssociatedDevices("None");
        camera3.setStampDate(new Date());

        List<Camera> cameraList = new ArrayList<>();
        cameraList.addAll(Arrays.asList(camera, camera2, camera3));

        when(cameraRepository.findAll()).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getAllCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(2)
    @DisplayName("getAllCameraDetails - whenNoDataPresentInDB - returnsCameraDetailsNotFound")
    void getAllCameraDetails_whenNoDataPresentInDB_returnsCameraDetailsNotFound() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();
        when(cameraRepository.findAll()).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getAllCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(3)
    @DisplayName("getAllCameraDetails - whenThrownAnyException - returnsUnableToProcess")
    void getAllCameraDetails_whenThrownAnyException_returnsUnableToProcess() throws Exception {
        // Arrange
        when(cameraRepository.findAll()).thenReturn(null);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getAllCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    /* -------------------------------------- End : getAllCameraDetails -------------------------------------- */


    /* -------------------------------------- Start : getCameraDetailsByLocationAndStampDateBetween -------------------------------------- */

    @Test
    @Order(4)
    @DisplayName("getCameraDetailsByLocationAndStampDateBetween - whenSuccess - returnsProcessedOK")
    void getCameraDetailsByLocationAndStampDateBetween_whenSuccess_returnsProcessedOK() throws Exception {
        // Arrange
        Camera camera = new Camera();
        camera.setName("Camera 1");
        camera.setLocation("Building A, Floor 1");
        camera.setStatus("Online");
        camera.setIpAddress("192.168.1.100");
        camera.setModel("PTZ");
        camera.setResolution("1080p");
        camera.setFieldOfView("120 degrees");
        camera.setFireDetection(true);
        camera.setSmokeDetection(true);
        camera.setLastDetectedEvent("Fire detected");
        camera.setHealthMetrics("Temperature: 25°C, Voltage: 12V");
        camera.setOwner("Security Department");
        camera.setAssociatedDevices("Microphone, Speaker");
        camera.setStampDate(new Date());

        Camera camera2 = new Camera();
        camera2.setName("Camera 2");
        camera2.setLocation("Building A, Floor 1");
        camera2.setStatus("Offline");
        camera2.setIpAddress("192.168.1.101");
        camera2.setModel("Fixed");
        camera2.setResolution("720p");
        camera2.setFieldOfView("90 degrees");
        camera2.setFireDetection(false);
        camera2.setSmokeDetection(true);
        camera2.setLastDetectedEvent("Smoke detected");
        camera2.setHealthMetrics("Temperature: 23°C, Voltage: 11.5V");
        camera2.setOwner("Facilities Department");
        camera2.setAssociatedDevices("Motion Sensor");
        camera2.setStampDate(new Date());


        Camera camera3 = new Camera();
        camera3.setName("Camera 3");
        camera3.setLocation("Parking Lot C");
        camera3.setStatus("Online");
        camera3.setIpAddress("192.168.1.102");
        camera3.setModel("Dome");
        camera3.setResolution("4K");
        camera3.setFieldOfView("180 degrees");
        camera3.setFireDetection(true);
        camera3.setSmokeDetection(false);
        camera3.setLastDetectedEvent("Fire detected");
        camera3.setHealthMetrics("Temperature: 30°C, Voltage: 12.2V");
        camera3.setOwner("Security Department");
        camera3.setAssociatedDevices("None");
        camera3.setStampDate(new Date());

        List<Camera> cameraList = new ArrayList<>();
        cameraList.addAll(Arrays.asList(camera, camera2, camera3));

        when(cameraRepository.findByLocationAndStampDateBetween(anyString(), any(Date.class), any(Date.class))).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getCameraDetailsByLocationAndStampDateBetween("Parking Lot C", "2021-05-07 12:00:00", "2025-05-07 12:00:00");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(5)
    @DisplayName("getCameraDetailsByLocationAndStampDateBetween - whenNoAnyDataFoundInDB - returnsCameraDetailsNotFound")
    void getCameraDetailsByLocationAndStampDateBetween_whenNoAnyDataFoundInDB_returnsCameraDetailsNotFound() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        when(cameraRepository.findByLocationAndStampDateBetween(anyString(), any(Date.class), any(Date.class))).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getCameraDetailsByLocationAndStampDateBetween("Parking Lot C", "2021-05-07 12:00:00", "2025-05-07 12:00:00");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(6)
    @DisplayName("getCameraDetailsByLocationAndStampDateBetween - whenThrownAnyException - returnsUnableToProcess")
    void getCameraDetailsByLocationAndStampDateBetween_whenThrownAnyException_returnsUnableToProcess() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        when(cameraRepository.findByLocationAndStampDateBetween(anyString(), any(Date.class), any(Date.class))).thenReturn(null);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.getCameraDetailsByLocationAndStampDateBetween("Parking Lot C", "2021-05-07 12:00:00", "2025-05-07 12:00:00");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    /* -------------------------------------- End : getCameraDetailsByLocationAndStampDateBetween -------------------------------------- */



    /* -------------------------------------- Start : findCameraByOwner -------------------------------------- */

    @Test
    @Order(7)
    @DisplayName("findCameraByOwner - whenSuccess - returnsProcessedOK")
    void findCameraByOwner_whenSuccess_returnsProcessedOK() throws Exception {
        // Arrange
        Camera camera = new Camera();
        camera.setName("Camera 1");
        camera.setLocation("Building A, Floor 1");
        camera.setStatus("Online");
        camera.setIpAddress("192.168.1.100");
        camera.setModel("PTZ");
        camera.setResolution("1080p");
        camera.setFieldOfView("120 degrees");
        camera.setFireDetection(true);
        camera.setSmokeDetection(true);
        camera.setLastDetectedEvent("Fire detected");
        camera.setHealthMetrics("Temperature: 25°C, Voltage: 12V");
        camera.setOwner("Security Department");
        camera.setAssociatedDevices("Microphone, Speaker");
        camera.setStampDate(new Date());

        Camera camera2 = new Camera();
        camera2.setName("Camera 2");
        camera2.setLocation("Building A, Floor 1");
        camera2.setStatus("Offline");
        camera2.setIpAddress("192.168.1.101");
        camera2.setModel("Fixed");
        camera2.setResolution("720p");
        camera2.setFieldOfView("90 degrees");
        camera2.setFireDetection(false);
        camera2.setSmokeDetection(true);
        camera2.setLastDetectedEvent("Smoke detected");
        camera2.setHealthMetrics("Temperature: 23°C, Voltage: 11.5V");
        camera2.setOwner("Facilities Department");
        camera2.setAssociatedDevices("Motion Sensor");
        camera2.setStampDate(new Date());


        Camera camera3 = new Camera();
        camera3.setName("Camera 3");
        camera3.setLocation("Parking Lot C");
        camera3.setStatus("Online");
        camera3.setIpAddress("192.168.1.102");
        camera3.setModel("Dome");
        camera3.setResolution("4K");
        camera3.setFieldOfView("180 degrees");
        camera3.setFireDetection(true);
        camera3.setSmokeDetection(false);
        camera3.setLastDetectedEvent("Fire detected");
        camera3.setHealthMetrics("Temperature: 30°C, Voltage: 12.2V");
        camera3.setOwner("Security Department");
        camera3.setAssociatedDevices("None");
        camera3.setStampDate(new Date());

        List<Camera> cameraList = new ArrayList<>(Arrays.asList(camera, camera2, camera3));

        when(cameraRepository.findByOwner(anyString())).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findCameraByOwner("Security Owner");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(8)
    @DisplayName("findCameraByOwner - whenNoAnyDataFoundInDB - returnsCameraDetailsNotFound")
    void findCameraByOwner_whenNoAnyDataFoundInDB_returnsCameraDetailsNotFound() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        when(cameraRepository.findByOwner(anyString())).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findCameraByOwner("Security Owner");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(9)
    @DisplayName("findCameraByOwner - whenThrownAnyException - returnsUnableToProcess")
    void findCameraByOwner_whenThrownAnyException_returnsUnableToProcess() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        doThrow(MongoDataIntegrityViolationException.class).when(cameraRepository).findByOwner(anyString());

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findCameraByOwner("Security Owner");

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    /* -------------------------------------- End : getCameraDetailsByLocationAndStampDateBetween -------------------------------------- */


    /* -------------------------------------- Start : findFireDetectorCameraDetails -------------------------------------- */

    @Test
    @Order(10)
    @DisplayName("findFireDetectorCameraDetails - whenSuccess - returnsProcessedOK")
    void findFireDetectorCameraDetails_whenSuccess_returnsProcessedOK() throws Exception {
        // Arrange
        Camera camera = new Camera();
        camera.setName("Camera 1");
        camera.setLocation("Building A, Floor 1");
        camera.setStatus("Online");
        camera.setIpAddress("192.168.1.100");
        camera.setModel("PTZ");
        camera.setResolution("1080p");
        camera.setFieldOfView("120 degrees");
        camera.setFireDetection(true);
        camera.setSmokeDetection(true);
        camera.setLastDetectedEvent("Fire detected");
        camera.setHealthMetrics("Temperature: 25°C, Voltage: 12V");
        camera.setOwner("Security Department");
        camera.setAssociatedDevices("Microphone, Speaker");
        camera.setStampDate(new Date());

        Camera camera2 = new Camera();
        camera2.setName("Camera 2");
        camera2.setLocation("Building A, Floor 1");
        camera2.setStatus("Offline");
        camera2.setIpAddress("192.168.1.101");
        camera2.setModel("Fixed");
        camera2.setResolution("720p");
        camera2.setFieldOfView("90 degrees");
        camera2.setFireDetection(true);
        camera2.setSmokeDetection(true);
        camera2.setLastDetectedEvent("Smoke detected");
        camera2.setHealthMetrics("Temperature: 23°C, Voltage: 11.5V");
        camera2.setOwner("Facilities Department");
        camera2.setAssociatedDevices("Motion Sensor");
        camera2.setStampDate(new Date());


        Camera camera3 = new Camera();
        camera3.setName("Camera 3");
        camera3.setLocation("Parking Lot C");
        camera3.setStatus("Online");
        camera3.setIpAddress("192.168.1.102");
        camera3.setModel("Dome");
        camera3.setResolution("4K");
        camera3.setFieldOfView("180 degrees");
        camera3.setFireDetection(true);
        camera3.setSmokeDetection(false);
        camera3.setLastDetectedEvent("Fire detected");
        camera3.setHealthMetrics("Temperature: 30°C, Voltage: 12.2V");
        camera3.setOwner("Security Department");
        camera3.setAssociatedDevices("None");
        camera3.setStampDate(new Date());

        List<Camera> cameraList = new ArrayList<>(Arrays.asList(camera, camera2, camera3));

        when(cameraRepository.findByFireDetectionIsTrue()).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findFireDetectorCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.OK, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(11)
    @DisplayName("findFireDetectorCameraDetails - whenNoAnyDataFoundInDB - returnsCameraDetailsNotFound")
    void findFireDetectorCameraDetails_whenNoAnyDataFoundInDB_returnsCameraDetailsNotFound() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        when(cameraRepository.findByFireDetectionIsTrue()).thenReturn(cameraList);

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findFireDetectorCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.CAMERA_DETAILS_NOT_FOUND, response.getResponseCode(), response.getResponseDescription());
    }


    @Test
    @Order(12)
    @DisplayName("findFireDetectorCameraDetails - whenThrownAnyException - returnsUnableToProcess")
    void findFireDetectorCameraDetails_whenThrownAnyException_returnsUnableToProcess() throws Exception {
        // Arrange
        List<Camera> cameraList = new ArrayList<>();

        doThrow(MongoDataIntegrityViolationException.class).when(cameraRepository).findByFireDetectionIsTrue();

        // Act
        GenericResponse<List<Camera>> response = detectorCameraService.findFireDetectorCameraDetails();

        // Assert
        Assertions.assertEquals(Constants.ResponseCodes.UNABLE_TO_PROCESS, response.getResponseCode(), response.getResponseDescription());
    }

    /* -------------------------------------- End : findFireDetectorCameraDetails -------------------------------------- */


}
