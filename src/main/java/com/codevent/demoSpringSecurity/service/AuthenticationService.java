package com.codevent.demoSpringSecurity.service;

import com.codevent.demoSpringSecurity.secureAndResponse.response.JwtAuthResponse;
import com.codevent.demoSpringSecurity.secureAndResponse.secure.SignIn;
import com.codevent.demoSpringSecurity.secureAndResponse.secure.SignUp;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    JwtAuthResponse signUp(SignUp signup);

    JwtAuthResponse signIn(SignIn signIn);
}
