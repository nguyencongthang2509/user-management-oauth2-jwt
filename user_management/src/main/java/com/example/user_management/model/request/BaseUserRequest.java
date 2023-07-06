package com.example.user_management.model.request;

import com.example.user_management.infrastructure.constant.Constants;
import com.example.user_management.infrastructure.constant.EntityProperties;
import com.example.user_management.infrastructure.constant.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class BaseUserRequest {

    @NotBlank(message = "Họ và tên không được để trống")
    @Length(max = EntityProperties.LENGTH_NAME)
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Length(max = EntityProperties.LENGTH_EMAIL)
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Ngày sinh không được để trống")
    private String dateOfBirth;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Length(max = EntityProperties.LENGTH_PASSWORD, message = "Mật khẩu không vượt quá 30 ký tự")
    private String password;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Length(max = EntityProperties.LENGTH_PHONE)
    @Pattern(regexp = Constants.REGEX_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull
    private Boolean gender;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Length(max = EntityProperties.LENGTH_ADDRESS)
    private String address;

    @NotNull
    private Role role;
}
