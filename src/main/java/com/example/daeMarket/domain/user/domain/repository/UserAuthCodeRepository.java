package com.example.daeMarket.domain.user.domain.repository;

import com.example.daeMarket.domain.user.domain.UserAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthCodeRepository extends JpaRepository<UserAuthCode, String> {
}
