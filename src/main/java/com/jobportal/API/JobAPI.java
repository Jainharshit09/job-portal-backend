    package com.jobportal.API;
    
    import com.jobportal.Dto.ApplicantDTO;
    import com.jobportal.Dto.Application;
    import com.jobportal.Dto.JobDTO;
    import com.jobportal.Dto.ResponseDTO;
    import com.jobportal.Expection.JobPortalExpection;
    import com.jobportal.Service.JobService;
    import jakarta.validation.Valid;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.validation.annotation.Validated;
    import org.springframework.web.bind.annotation.*;
    
    import java.util.List;
    
    @RestController
    @CrossOrigin(origins = "*")
    @Validated
    @RequestMapping("/jobs")
    public class JobAPI {
        @Autowired
        private JobService jobService;
    
        @PostMapping("/post")
        public ResponseEntity<JobDTO> postJob(@RequestBody @Valid JobDTO jobDTO) throws JobPortalExpection {
            return new ResponseEntity<>(jobService.postJob(jobDTO), HttpStatus.CREATED);
        }
    
        @GetMapping("/getAll")
        public ResponseEntity<List<JobDTO>> getAllJobs() throws JobPortalExpection {
            return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
        }
    
        @GetMapping("/get/{id}")
        public ResponseEntity<JobDTO> getJob(@PathVariable Long id) throws JobPortalExpection {
            return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
        }
    
        @PostMapping("/apply/{id}")
        public ResponseEntity<ResponseDTO> applyJob(@PathVariable Long id, @RequestBody ApplicantDTO applicantDTO) throws JobPortalExpection {
            jobService.applyJob(id,applicantDTO);
            return new ResponseEntity<>(new ResponseDTO("Applied Successfull"), HttpStatus.OK);
        }
    
        @GetMapping("/postedBy/{id}")
        public ResponseEntity<List<JobDTO>> getJobsPostedBy(@PathVariable Long id) throws JobPortalExpection {
            return new ResponseEntity<>(jobService.getJobsPostedBy(id), HttpStatus.OK);
        }

        @PutMapping("/changeAppStatus")
        public ResponseEntity<ResponseDTO> changeAppStatus(@RequestBody Application applicantDTO) throws JobPortalExpection {
            try {
                jobService.changeAppStatus(applicantDTO);
                return new ResponseEntity<>(new ResponseDTO("Application Status Changed Successfully"), HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(new ResponseDTO("Failed to change application status"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    
    }
