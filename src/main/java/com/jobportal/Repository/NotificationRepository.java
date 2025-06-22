package com.jobportal.Repository;

import com.jobportal.Dto.NotificationStatus;
import com.jobportal.Entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, Long> {
    public List<Notification> findByUserIdAndStatus(long userId, NotificationStatus status);
}
