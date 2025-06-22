package com.jobportal.Dto;

import com.jobportal.Entity.Job;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    @Id
    private Long id;
    private String JobTitle;
    private String company;
    private List<ApplicantDTO> applicants;
    private  String about;
    private String experience;
    private  String jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillsRequired;
    private JobStatus jobStatus;
    private Long postedBy;

    public Job toEntity(){
        return new Job(
                this.id,
                this.JobTitle,
                this.company,
                this.applicants!=null?this.applicants.stream().map(ApplicantDTO::toEntity).toList():null,
                this.about,
                this.experience,
                this.jobType,
                this.location,
                this.packageOffered,
                this.postTime,
                this.description,
                this.skillsRequired,
                this.jobStatus,
                this.postedBy
                );
    }
}
