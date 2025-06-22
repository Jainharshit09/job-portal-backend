package com.jobportal.Service;

import com.jobportal.Dto.AccountType;
import com.jobportal.Dto.ProfileDTO;
import com.jobportal.Expection.JobPortalExpection;

import java.util.List;

public interface ProfileService {
    public Long createProfile(String email, String name, AccountType accountType) throws JobPortalExpection;
    public ProfileDTO getProfile(Long id) throws JobPortalExpection;
    public ProfileDTO updateProfile(ProfileDTO profileDTO) throws JobPortalExpection;

    public List<ProfileDTO> getAllProfile() throws JobPortalExpection;
}
