package com.example.daeMarket.domain.auth.controller;

import com.example.daeMarket.domain.auth.controller.dto.request.CheckAuthCodeRequest;
import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenRefreshResponse;
import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenResponse;
import com.example.daeMarket.domain.auth.service.CheckAuthCodeExistsService;
import com.example.daeMarket.domain.auth.service.CheckEmailExistsService;
import com.example.daeMarket.domain.auth.service.UserSignInService;
import com.example.daeMarket.domain.auth.service.UserTokenRefreshService;
import com.example.daeMarket.domain.auth.controller.dto.request.UserSignInRequest;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class AuthController {

    private final UserSignInService userSignInService;
    private final CheckAuthCodeExistsService checkAuthCodeExistsService;
    private final CheckEmailExistsService checkEmailExistsService;
    private final UserTokenRefreshService userTokenRefreshService;

    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public UserTokenResponse userSignIn(@RequestBody @Valid UserSignInRequest request) {
        return userSignInService.login(request);
    }

    @ApiOperation(value = "토큰 재발급")
    @PatchMapping("/token")
    public UserTokenRefreshResponse userTokenRefresh(@RequestHeader("Refresh-Token") String refreshToken) {
        return userTokenRefreshService.execute(refreshToken);
    }

    @ApiOperation(value = "이메일 중복 체크")
    @RequestMapping(value = "/email", method = RequestMethod.HEAD)
    public void checkEmailExist(@NotBlank @RequestParam(name = "email") String email) {
        checkEmailExistsService.execute(email);
    }

    @ApiOperation(value = "인증번호 체크")
    @RequestMapping(value = "/verification-codes", method = RequestMethod.HEAD)
    public void checkAuthCodeExists(@Valid CheckAuthCodeRequest request) {
        checkAuthCodeExistsService.execute(request);
    }
}
