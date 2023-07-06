package com.example.user_management.model.request;

import com.example.user_management.entity.base.PageableRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserRequest extends PageableRequest {

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private Boolean gender;

}
