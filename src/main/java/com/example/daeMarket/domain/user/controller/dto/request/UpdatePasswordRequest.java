package com.example.daeMarket.domain.user.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @ApiModelProperty(value = "새로운 비밀번호", example = "souso12345")
    @NotBlank(message = "new_password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 30, message = "password는 30글자 이하여야 합니다.")
    private String password;

    @ApiModelProperty(value = "새로운 비밀번호", example = "souso12345")
    @NotBlank(message = "new_password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 30, message = "newpassword는 30글자 이하여야 합니다.")
    private String newpassword;

}
