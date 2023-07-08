package com.example.user_management.entity;

import com.example.user_management.infrastructure.constant.EntityProperties;
import com.example.user_management.infrastructure.constant.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;

import java.sql.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "fullname", length = EntityProperties.LENGTH_NAME)
    @Nationalized
    private String fullName;

    @Column(name = "email", length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "dateofbirth")
    private Date dateOfBirth;

    @Column(name = "password", length = EntityProperties.LENGTH_PASSWORD)
    private String password;

    @Column(name = "phonenumber", length = EntityProperties.LENGTH_PHONE)
    private String phoneNumber;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address", length = EntityProperties.LENGTH_ADDRESS)
    @Nationalized
    private String address;

    @Column(name = "role", nullable = false)
    private Role role;

}
