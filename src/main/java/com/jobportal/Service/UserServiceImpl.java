package com.jobportal.Service;

import com.jobportal.Dto.LoginDTO;
import com.jobportal.Dto.NotificationDTO;
import com.jobportal.Dto.ResponseDTO;
import com.jobportal.Dto.UserDTO;
import com.jobportal.Entity.OTP;
import com.jobportal.Expection.JobPortalExpection;
import com.jobportal.Repository.OTPRepository;
import com.jobportal.Repository.UserRepository;
import com.jobportal.Entity.User;
import com.jobportal.Utility.Data;
import com.jobportal.Utility.Utilities;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;



@Service(value = "userService")
public class UserServiceImpl implements UserService {

    public static final String USER_FOUND = "User already registered with this email.";
    public static final String USER_NOT_FOUND = "User not registered with this email.";
    public static final String INVALID_CREDENTIALS = "Invalid credentials. Please check your email and password.";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OTPRepository otpRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private NotificationService notificationService;

    @Override
    public UserDTO registerUser(UserDTO userDTO) throws JobPortalExpection {
        Optional<User> optional=userRepository.findByEmail(userDTO.getEmail());
        if(optional.isPresent()){
            throw new JobPortalExpection(USER_FOUND);
        }
        userDTO.setProfileId(profileService.createProfile(userDTO.getEmail(),userDTO.getName(),userDTO.getAccountType()));
        userDTO.setId(Utilities.getNextSequence("users"));
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user=  userDTO.toEntity();
        user=userRepository.save(user);
        return  user.toDTO();
    }

    @Override
    public UserDTO getUserByEmail(String email) throws JobPortalExpection {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new JobPortalExpection(USER_NOT_FOUND));
        return user.toDTO();
    }

    @Override
    public UserDTO loginUser(LoginDTO loginDTO) throws JobPortalExpection {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new JobPortalExpection(USER_NOT_FOUND));
        if(!passwordEncoder.matches(loginDTO.getPassword(),user.getPassword())) throw  new JobPortalExpection(INVALID_CREDENTIALS);
        return user.toDTO();
    }


    @Override
    public boolean sendOtp(String email) throws Exception {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new JobPortalExpection("User not found"));
        String generatedOtp = Utilities.genrateOtp();
        OTP otp = new OTP(email, generatedOtp, LocalDateTime.now());
        otpRepository.saveOtp(email, otp);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setFrom("Job Hook <noreply@jobhook.com>");
        messageHelper.setTo(email);
        messageHelper.setSubject("Your OTP Code");

        String emailContent = Data.getMessageBody(generatedOtp, user.getName());
        messageHelper.setText(emailContent, true);

        javaMailSender.send(mimeMessage);

        return true;
    }

    @Override
    public boolean verifyOtp(String email, String otpInput) throws JobPortalExpection {
        OTP otpEntity = otpRepository.getOtp(email);

        if (otpEntity == null) {
            throw new JobPortalExpection("OTP_NOT_FOUND");
        }

        if (!otpEntity.getOtpCode().equals(otpInput)) {
            throw new JobPortalExpection("OTP_INCORRECT");
        }

        if (isOtpExpired(otpEntity)) {
            otpRepository.removeOtp(email);
            throw new JobPortalExpection("OTP_EXPIRED");
        }

        otpRepository.removeOtp(email);
        return true;
    }

    @Override

    public ResponseDTO changePassword(LoginDTO loginDTO) throws JobPortalExpection {
        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new JobPortalExpection(USER_NOT_FOUND));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(loginDTO.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        NotificationDTO noti = new NotificationDTO();
        noti.setUserId(user.getId());
        noti.setMessage("Password Reset Successfully.");
        noti.setAction("Password Reset");

        notificationService.sendNotification(noti);
        return new ResponseDTO("Password Changed successfully.");
    }

    private boolean isOtpExpired(OTP otp) {
        LocalDateTime expirationTime = otp.getCreation().plusMinutes(5);
        return LocalDateTime.now().isAfter(expirationTime);
    }


}
