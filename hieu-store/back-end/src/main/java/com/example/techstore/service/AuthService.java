package com.example.techstore.service;

import com.example.techstore.domain.dto.request.ForgotPasswordRequestDto;
import com.example.techstore.domain.dto.request.LoginRequestDto;
import com.example.techstore.domain.dto.request.TokenRefreshRequestDto;
import com.example.techstore.domain.dto.request.VerifyRequestDto;
import com.example.techstore.domain.dto.response.CommonResponseDto;
import com.example.techstore.domain.dto.response.LoginResponseDto;
import com.example.techstore.domain.dto.response.TokenRefreshResponseDto;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthService {

    LoginResponseDto login(LoginRequestDto request);

    TokenRefreshResponseDto refresh(TokenRefreshRequestDto request);

    CommonResponseDto logout(HttpServletRequest request,
                             HttpServletResponse response, Authentication authentication);

    CommonResponseDto forgotPassword(ForgotPasswordRequestDto requestDto);

    CommonResponseDto verifyForgotPassword(VerifyRequestDto verifyRequestDto);

}
