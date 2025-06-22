package com.jobportal.Service;

import com.jobportal.Dto.AccountType;
import com.jobportal.Dto.ProfileDTO;
import com.jobportal.Entity.Profile;
import com.jobportal.Expection.JobPortalExpection;
import com.jobportal.Repository.ProfileRepository;
import com.jobportal.Utility.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("profileService")
public class ProfileServiceImpl implements  ProfileService{
    @Autowired
    private ProfileRepository profileRepository;
    @Override
    public Long createProfile(String email, String name, AccountType accountType) throws JobPortalExpection {
        Profile profile=new Profile();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setName(name);
        profile.setAccountType(accountType);
        profile.setSkills(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profile.setExperiences(new ArrayList <>());
        profileRepository.save(profile);
        return profile.getId();
    }

    @Override
    public ProfileDTO getProfile(Long id) throws JobPortalExpection {
         return profileRepository.findById(id).orElseThrow(()->new JobPortalExpection("PROFILE_NOT_FOUND")).toDTO();
    }

    @Override
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalExpection {
         profileRepository.findById(profileDTO.getId()).orElseThrow(()->new JobPortalExpection("PROFILE_NOT_FOUND"));
         profileRepository.save(profileDTO.toEntity());
         return profileDTO;
    }

    @Override  
    public List<ProfileDTO> getAllProfile() throws JobPortalExpection {
        return profileRepository.findAll().stream().map(Profile::toDTO).toList();
    }
}
