package com.jobportal.Service;


import com.jobportal.Dto.LoginDTO;
import com.jobportal.Dto.ResponseDTO;
import com.jobportal.Dto.UserDTO;
import com.jobportal.Expection.JobPortalExpection;


public interface UserService {
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalExpection;

    public UserDTO getUserByEmail(String email) throws JobPortalExpection;
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalExpection;

    public boolean sendOtp(String email) throws Exception;

    public boolean verifyOtp(String email,String otp) throws JobPortalExpection;

    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalExpection;
}
