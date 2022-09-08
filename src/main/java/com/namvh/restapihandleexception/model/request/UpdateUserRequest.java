package com.namvh.restapihandleexception.model.request;

import org.hibernate.validator.constraints.URL;

import javax.validation.Valid;
import javax.validation.constraints.*;

public class UpdateUserRequest {
    @NotNull(message = "Full name is required")
    @NotEmpty(message = "Full name is required")
//    @ApiModelProperty(
//            example="Sam Smith",
//            notes="Full name cannot be empty",
//            required=true
//    )
//    @JsonProperty("full_name")
    private String name;

    @NotNull(message = "Email is required")
    @NotEmpty(message = "Email is required")
    @Email(message = "Please provide a valid email")
//    @ApiModelProperty(
//            example="sam.smith@gmail.com",
//            notes="Email cannot be empty",
//            required=true
//    )
    private String email;

    @NotNull(message = "Password is required")
    @NotEmpty(message = "Password is required")
    @Size(min = 4, max = 20, message = "Pasword must be between 4 and 20 characters")
//    @ApiModelProperty(
//            example="verysecretpassword",
//            notes="Password can't be empty",
//            required=true
//    )
    private String password;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "Please provide a valid phone number")
//    @ApiModelProperty(
//            example="0916016972",
//            notes="Phone cannot be empty",
//            required=true
//    )
    private String phone;

    @Valid
    @URL(regexp="(https?:\\/\\/.*\\.(?:png|jpg))", message="Avatar must be an url image")
//    @ApiModelProperty(
//            example="https://ssl.gstatic.com/images/branding/product/1x/avatar_circle_blue_512dp.png",
//            notes="Avatar must be an url image",
//            required=false
//    )
    private String avatar;

    public UpdateUserRequest() {
    }

    public UpdateUserRequest(String name, String email, String password, String phone, String avatar) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
