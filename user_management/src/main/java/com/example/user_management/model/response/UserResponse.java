package com.example.user_management.model.response;

import java.sql.Date;
import java.util.UUID;

public interface UserResponse {

    UUID getId();

    String getFullname();

    String getEmail();

    Date getDateofbirth();

    String getPhonenumber();

    Boolean getGender();

    String getAddress();

    Integer getRole();
}
