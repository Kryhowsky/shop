package com.kryhowsky.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryhowsky.shop.validator.PasswordValid;
import com.kryhowsky.shop.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordValid(message = "Password and ConfirmPassword should be the same", groups = Create.class)
public class UserDto {

    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Length(min = 5)
    private String password;

    private String confirmPassword;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
