package com.example.daeMarket.domain.auth.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserSignInRequest {

    @NotBlank(message = "email을 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$",
            message = "비밀번호는 6~15 자리이면서 1개 이상의 알파벳, 숫자를 포함해야합니다.")
    private String password;
}
