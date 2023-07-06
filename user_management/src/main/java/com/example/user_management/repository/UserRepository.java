package com.example.user_management.repository;

import com.example.user_management.entity.User;
import com.example.user_management.model.request.FindUserRequest;
import com.example.user_management.model.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = """
            SELECT id, fullname, email, dateofbirth, phonenumber, gender, address, role FROM "users"
            """, countQuery = """
            SELECT COUNT(1) FROM "users"
            """, nativeQuery = true)
    Page<UserResponse> findUser(Pageable pageable, @Param("req") final FindUserRequest req);
//            WHERE (:#{#req.fullName} IS NULL OR :#{#req.fullName} ILIKE '' OR fullname ILIKE %:#{#req.fullName}%)
//            AND (:#{#req.email} IS NULL OR :#{#req.email} ILIKE '' OR email ILIKE %:#{#req.email}%)
//            AND (:#{#req.address} IS NULL OR :#{#req.address} ILIKE '' OR address ILIKE %:#{#req.address}%)
//            AND (:#{#req.phoneNumber} IS NULL OR :#{#req.phoneNumber} ILIKE '' OR phonenumber ILIKE %:#{#req.phoneNumber}%)
//            AND (:#{#req.gender} IS NULL OR :#{#req.phoneNumber} ILIKE '' OR gender = :#{#req.gender})

    @Query(value = """
            SELECT * FROM "users" WHERE email = :email
            """, nativeQuery = true)
    User findUser(@Param("email") String email);
}
