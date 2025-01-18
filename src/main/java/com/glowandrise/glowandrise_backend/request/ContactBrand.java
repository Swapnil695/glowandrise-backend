package com.glowandrise.glowandrise_backend.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ContactBrand {
    @NotBlank(message = "Brand name is required")
    private String brandName;
    @NotBlank(message = "Brand website is required")
    private String websiteLink;
    @NotBlank(message = "Brand contact number is required")
    private String contactNumber;
    @NotBlank(message = "Brand email is required")
    private String email;
    private String comment;
}