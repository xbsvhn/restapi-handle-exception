package com.namvh.restapihandleexception.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.*;

@ApiModel(value = "UserRequest model")
public class CreateUserRequest {
    @NotNull(message = "Full name is required")
    @NotEmpty(message = "Full name is required")
    @ApiModelProperty(
            example = "Sam Smith",
            notes = "Full name cannot be empty",
            required = true
    )
    @JsonProperty("full_name")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
    @ApiModelProperty(
            example = "sam.smith@gmail.com",
            notes = "Email cannot be empty",
            required = true
    )
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
    @ApiModelProperty(
            example = "verysecretpassword",
            notes = "Password can't be empty",
            required = true
    )
    private String password;

    @Pattern(regexp = "(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Please provide a valid phone number")
    @ApiModelProperty(
            example = "0916016972",
            notes = "Phone cannot be empty",
            required = true
    )
    private String phone;

    public CreateUserRequest() {
    }

    public CreateUserRequest(String name, String email, String password, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}