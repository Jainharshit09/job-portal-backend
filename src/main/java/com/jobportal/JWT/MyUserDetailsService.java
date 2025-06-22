package com.jobportal.JWT;

import com.jobportal.Dto.UserDTO;
import com.jobportal.Expection.JobPortalExpection;
import com.jobportal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            UserDTO userDTO = userService.getUserByEmail(email);
            return new CustomUserDetails(userDTO.getId(), email,userDTO.getName(), userDTO.getPassword(), userDTO.getProfileId() ,userDTO.getAccountType(), new ArrayList<>());
        } catch (JobPortalExpection e) {
            throw new RuntimeException(e);
        }
    }
}
