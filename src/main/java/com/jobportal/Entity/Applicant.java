package com.jobportal.Entity;

import com.jobportal.Dto.ApplicantDTO;
import com.jobportal.Dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Applicant {
    private Long  applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private String github;
    private String linkedin;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timeStamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantDTO toDTO(){
        return new ApplicantDTO(
                this.applicantId,
                this.name,
                this.email,
                this.phone,
                this.website,
                this.github,
                this.linkedin,
                this.resume != null && this.resume.length > 0 ? Base64.getEncoder().encodeToString(this.resume) : null,
                this.coverLetter,
                this.timeStamp,
                this.applicationStatus,
                this.interviewTime
        );
    }
}
