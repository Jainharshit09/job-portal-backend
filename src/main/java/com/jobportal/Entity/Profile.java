package com.jobportal.Entity;

import com.jobportal.Dto.AccountType;
import com.jobportal.Dto.Certification;
import com.jobportal.Dto.Experience;
import com.jobportal.Dto.ProfileDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Base64;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String email;
    private String name;
    private String jobTitle;
    private String company;
    private String location;
    private String  about;
    private byte[] picture;
    private Long experience;
    private AccountType accountType;
    List<String> skills;
    List<Experience> experiences;
    List<Certification> certifications;
    List<Long>savedJobs;

    public ProfileDTO toDTO(){
        return new ProfileDTO(this.id,
                this.email,
                this.name,
                this.jobTitle,
                this.company,
                this.location,
                this.about,
                this.picture!=null? Base64.getEncoder().encodeToString(this.picture):null,
                this.experience,
                this.accountType,
                this.skills,
                this.experiences,
                this.certifications,this.savedJobs);
    } 
}
