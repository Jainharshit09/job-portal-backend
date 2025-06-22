package com.jobportal.Service;

import com.jobportal.Dto.ApplicantDTO;
import com.jobportal.Dto.Application;
import com.jobportal.Dto.JobDTO;
import com.jobportal.Expection.JobPortalExpection;

import java.util.List;

public interface JobService {
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalExpection;

    public List<JobDTO> getAllJobs() throws JobPortalExpection;

    public JobDTO getJob(Long id) throws JobPortalExpection;

    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalExpection;

    public List<JobDTO> getJobsPostedBy(Long id)throws JobPortalExpection;

    public void changeAppStatus(Application applicantDTO) throws JobPortalExpection;
}
