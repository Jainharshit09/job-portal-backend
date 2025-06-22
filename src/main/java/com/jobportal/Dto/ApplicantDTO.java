package com.jobportal.Dto;

import com.jobportal.Entity.Applicant;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Base64;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicantDTO {
    private Long  applicantId;
    private String name;
    @Email(message = "Invalid email format")
    private String email;
    private Long phone;
    @Pattern(regexp = "^(http://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-zA-Z]{2,6}(/)?$", message = "Invalid website URL")
    private String website;
    @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?$", message = "Invalid GitHub URL")
    private String github;
    @Pattern(regexp = "^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?$", message = "Invalid GitHub URL")
    private String linkedin;
    private String resume;
    private String coverLetter;
    private LocalDateTime timeStamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public Applicant toEntity(){
        return new Applicant(
                this.applicantId,
                this.name,
                this.email,
                this.phone,
                this.website,
                this.github,
                this.linkedin,
                this.resume!=null? Base64.getDecoder().decode(this.resume):null,
                this.coverLetter,
                this.timeStamp,
                this.applicationStatus,
                this.interviewTime
        );
    }

}
