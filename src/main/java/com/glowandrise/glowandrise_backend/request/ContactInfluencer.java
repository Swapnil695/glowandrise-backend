package com.glowandrise.glowandrise_backend.request;

import com.glowandrise.glowandrise_backend.model.Gender;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@ToString
public class ContactInfluencer {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Instagram link is required")
    private String instagramLink;
    @Min(value = 1, message = "Followers must be at least 1")
    private int followers;
    private Gender gender;
    @DecimalMin(value = "0.0", message = "Engagement rate must be greater than 0")
    private double engagementRate;
    @NotEmpty(message = "Category list cannot be empty")
    private List<String> category;
    private String youtubeLink; //optional
    private int subscribers;    //optional
    @NotBlank(message = "Contact number is required")
    private String contactNumber;
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "City is required")
    private String city;
}