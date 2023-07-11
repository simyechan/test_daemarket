package com.example.daeMarket.domain.user.controller.dto.request;

import com.example.daeMarket.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "email을 입력해주세요.")
    private String email; //학교 이메일

    @NotBlank(message = "이름을 입력해주세요.")
    private String name; // 이름 (성 + 이름)

    @NotBlank(message = "학번을 입력해주세요.")
    private Long st_num;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,15}$",
            message = "비밀번호는 6~15 자리이면서 1개 이상의 알파벳, 숫자를 포함해야합니다.")
    private String password; // 비번 (6~15, 숫자 1, 문자 1)

    private String checkedPassword;

    @NotBlank(message = "1부터 10중 좋아하는 숫자를 입력해주세요.")
    private Long like_num; // 1~10까지

}
