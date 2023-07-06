package com.example.user_management.repository;

import com.example.user_management.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author thangncph26123
 */
@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, UUID> {
}
