package com.example.daeMarket.domain.user.controller;

import com.example.daeMarket.domain.auth.controller.dto.response.UserTokenResponse;
import com.example.daeMarket.domain.user.controller.dto.request.UpdatePasswordRequest;
import com.example.daeMarket.domain.user.controller.dto.request.UserSignUpRequest;
import com.example.daeMarket.domain.user.domain.repository.UserRepository;
import com.example.daeMarket.domain.user.service.UpdatePasswordService;
import com.example.daeMarket.domain.user.service.UserLogoutService;
import com.example.daeMarket.domain.user.service.UserSignUpService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final UserSignUpService userSignUpService;
    private final UpdatePasswordService updatePasswordService;

    private final UserLogoutService userLogoutService;

    @ApiOperation(value = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @ApiOperation(value = "비밀번호 변경")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/passwordUpdate")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @ApiOperation(value = "로그아웃")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout() {
        userLogoutService.execute();
    }
}
