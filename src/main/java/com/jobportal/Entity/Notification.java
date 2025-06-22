package com.jobportal.Entity;

import com.jobportal.Dto.NotificationDTO;
import com.jobportal.Dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "notification")
public class Notification{
    private long id;
    private long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timeStamp;

    public NotificationDTO toDTO(){
        return new NotificationDTO(
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
