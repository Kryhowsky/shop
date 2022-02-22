package com.kryhowsky.shop.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kryhowsky.shop.validator.PasswordValid;
import com.kryhowsky.shop.validator.group.Create;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordValid(message = "Password and ConfirmPassword should be the same", groups = Create.class)
public class UserDto extends AuditableDto {

    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank(groups = Create.class)
    @Length(min = 5, groups = Create.class)
    private String password;

    private String confirmPassword;

    private List<String> roles;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private Integer revisionNumber;
}
