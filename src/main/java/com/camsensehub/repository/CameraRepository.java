package com.camsensehub.repository;

import com.camsensehub.entity.Camera;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface CameraRepository extends MongoRepository<Camera, String> {
    List<Camera> findByOwner(String owner);
    List<Camera> findByLocationAndStampDateBetween(String location, Date startDate, Date endDate);
    List<Camera> findByFireDetectionIsTrue();
}
