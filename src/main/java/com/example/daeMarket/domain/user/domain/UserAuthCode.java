package com.example.daeMarket.domain.user.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class UserAuthCode {

    @Id
    private String email;

    private String code;

    @TimeToLive
    private Long expiredAt;

    @Builder
    public UserAuthCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.expiredAt = 180L;
    }
}
