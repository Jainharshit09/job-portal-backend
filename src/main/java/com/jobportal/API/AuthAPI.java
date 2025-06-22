package com.jobportal.API;

import com.jobportal.JWT.AuthRequest;
import com.jobportal.JWT.AuthResponse;
import com.jobportal.JWT.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@CrossOrigin(origins = "*")
public class AuthAPI {
    @Autowired
    private UserDetailsService userDetailsService;;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest authRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
                final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());
                final String jwt = jwtHelper.generateToken(userDetails);
                return ResponseEntity.ok(new AuthResponse(jwt));


    }

}
