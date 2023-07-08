package com.example.user_management.repository;

import com.example.user_management.entity.UserToken;
import com.example.user_management.model.response.UserResponse;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, UUID> {

    @Query(value = """
            SELECT * FROM "usertoken" WHERE token = :token
            """, nativeQuery = true)
    UserToken findUserToken(@Param("token") String token);

    @Query(value = """
            SELECT * FROM "usertoken" WHERE userid = :userId
            """, nativeQuery = true)
    UserToken findUserTokenByIdUser(@Param("userId") UUID userId);

    @Query(value = """
            SELECT a.id, a.fullname, a.email, a.dateofbirth, a.phonenumber, a.gender, a.address, a.role
            FROM "users" a JOIN "usertoken" b ON a.id = b.userid
            WHERE b.token = :token
            """, nativeQuery = true)
    UserResponse findUserByToken(@Param("token") String token);

    @Modifying
    @Transactional
    void deleteUserTokenByToken(String token);
}
