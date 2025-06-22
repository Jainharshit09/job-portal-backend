package com.jobportal.Service;


import com.jobportal.Dto.*;
import com.jobportal.Entity.Applicant;
import com.jobportal.Entity.Job;
import com.jobportal.Expection.JobPortalExpection;
import com.jobportal.Repository.JobRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("jobService")
public class JobServiceImpl implements  JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private NotificationService notificationService;

    @Override
    public JobDTO postJob(JobDTO jobDTO) throws JobPortalExpection {
        if(jobDTO.getId()==0){
            jobDTO.setId(Utilities.getNextSequence("jobs"));
            jobDTO.setPostTime(LocalDateTime.now());
            NotificationDTO noti=new NotificationDTO();
            noti.setAction("Job Posted Successfully");
            noti.setMessage("Job Posted Successfully :"+jobDTO.getJobTitle()+"  at "+jobDTO.getCompany());
            noti.setUserId(jobDTO.getPostedBy());
            noti.setRoute("/posted-job/"+jobDTO.getId());
            notificationService.sendNotification(noti);
        }
        else{
            Job job=jobRepository.findById(jobDTO.getId()).orElseThrow(() -> new JobPortalExpection("JOB_NOT_FOUND"));
            if(job.getJobStatus().equals(JobStatus.DRAFT)||job.getJobStatus().equals(JobStatus.CLOSED)){
                jobDTO.setPostTime(LocalDateTime.now());
            }
        }

        return jobRepository.save(jobDTO.toEntity()).toDto();
    }

    @Override
    public List<JobDTO> getAllJobs() throws JobPortalExpection {
        return jobRepository.findAll().stream().map(Job::toDto).toList();
    }

    @Override
    public JobDTO getJob(Long id) throws JobPortalExpection {
        return jobRepository.findById(id).orElseThrow(() -> new JobPortalExpection("JOB_NOT_FOUND")).toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantDTO applicantDTO) throws JobPortalExpection {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobPortalExpection("JOB_NOT_FOUND"));
        List<Applicant>applicants=job.getApplicants();
        if(applicants==null){
            applicants=new ArrayList<>();
        }
       if(applicants.stream().filter((x)-> Objects.equals(x.getApplicantId(), applicantDTO.getApplicantId())).toList().size()>0){
           throw new JobPortalExpection("JOB_ALREADY_APPLIED");
       }
       applicantDTO.setApplicationStatus(ApplicationStatus.APPLIED);
       applicants.add(applicantDTO.toEntity());
       job.setApplicants(applicants);
       jobRepository.save(job);
    }

    @Override
    public List<JobDTO> getJobsPostedBy(Long id) throws JobPortalExpection {
        return jobRepository.findByPostedBy(id).stream().map(Job::toDto).toList();
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalExpection {
        Job job = jobRepository.findById(application.getId())
                .orElseThrow(() -> new JobPortalExpection("JOB_NOT_FOUND"));

        List<Applicant> applicants = job.getApplicants().stream().map(x -> {
            if (application.getApplicantID().equals(x.getApplicantId())) {
                x.setApplicationStatus(application.getApplicationStatus());
                if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
                    x.setInterviewTime(application.getInterviewTime());
                    NotificationDTO noti=new NotificationDTO();
                    noti.setAction("Interview Scheduled");
                    noti.setMessage("Your interview has been scheduled for Job Id :"+application.getApplicantID());
                    noti.setUserId(application.getApplicantID());
                    noti.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(noti);
                    } catch (JobPortalExpection e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            return x;
        }).toList();

        job.setApplicants(applicants);
        jobRepository.save(job);
    }



}
