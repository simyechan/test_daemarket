package com.example.daeMarket.domain.user.domain;

import com.example.daeMarket.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "mkt_user")
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(length = 50)
    private String email; //학교 이메일

    @Column(length = 50)
    private String name; // 이름 (성 + 이름)

    @Column(length = 15)
    private Long st_num;

    @Column(length = 50)
    private String password; // 비번 (6~15, 숫자 1, 문자 1)

    @Column(length = 5)
    private Long like_num; // 1~10까지

    @Builder
    public User(String email, String name, Long st_num, String password, Long like_num) {
        this.email = email;
        this.name = name;
        this.st_num = st_num;
        this.password = password;
        this.like_num = like_num;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
