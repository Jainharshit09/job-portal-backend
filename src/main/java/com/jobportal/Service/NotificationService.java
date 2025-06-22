package com.jobportal.Service;

import com.jobportal.Dto.NotificationDTO;
import com.jobportal.Entity.Notification;
import com.jobportal.Expection.JobPortalExpection;

import java.util.List;


public interface NotificationService {
    public void sendNotification(NotificationDTO notificationDTO) throws JobPortalExpection;
    public List<Notification> getUnreadNotification(long userId) throws JobPortalExpection;

    void readNotification(Long id)throws JobPortalExpection;
}
