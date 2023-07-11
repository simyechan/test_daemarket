package com.example.daeMarket.domain.auth.controller.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CheckAuthCodeRequest {

    @NotBlank(message = "email을 입력해주세요.")
    private String email;

    @ApiModelProperty(value = "authCode", example = "4283")
    @NotBlank(message = "auth_code는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String authCode;

}
