package com.jobportal.Dto;

import com.jobportal.Entity.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {
    private long id;
    private long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timeStamp;

    public Notification toEntity() {
        return new Notification(
                this.id,
                this.userId,
                this.message,
                this.action,
                this.route,
                this.status,
                this.timeStamp
        );
    }
}
