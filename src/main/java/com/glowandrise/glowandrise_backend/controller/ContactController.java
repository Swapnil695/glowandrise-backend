package com.glowandrise.glowandrise_backend.controller;

import com.glowandrise.glowandrise_backend.request.ContactBrand;
import com.glowandrise.glowandrise_backend.request.ContactInfluencer;
import com.glowandrise.glowandrise_backend.response.ContactResponse;
import com.glowandrise.glowandrise_backend.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/glowandrise/api/v1/contact")
@Slf4j
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/influencer")
    public ResponseEntity<ContactResponse> contactInfluencer(@Valid @RequestBody ContactInfluencer contactInfluencer) {
        log.info("Influencer contact request: {}", contactInfluencer);
        ContactResponse contactResponse = new ContactResponse();
        if (Objects.isNull(contactInfluencer)) {
            contactResponse.setResult(false);
            contactResponse.setMessage("Bad Request!");
            return ResponseEntity.badRequest().body(contactResponse);
        }
        int result = contactService.contactInfluencer(contactInfluencer);
        if (result != 1) {
            contactResponse.setResult(false);
            contactResponse.setMessage("Technical issue, try again later!");
            return ResponseEntity.internalServerError().body(contactResponse);
        }
        contactResponse.setMessage("SUCCESS");
        contactResponse.setResult(true);
        return ResponseEntity.accepted().body(contactResponse);
    }

    @PostMapping("/brand")
    public ResponseEntity<?> contactBrand(@Valid @RequestBody ContactBrand contactBrand) {
        log.info("Brand contact request: {}", contactBrand);
        ContactResponse contactResponse = new ContactResponse();
        if (Objects.isNull(contactBrand)) {
            contactResponse.setResult(false);
            contactResponse.setMessage("Bad Request!");
            return ResponseEntity.badRequest().body(contactResponse);
        }
        int result = contactService.contactBrand(contactBrand);
        if (result != 1) {
            contactResponse.setResult(false);
            contactResponse.setMessage("Technical issue, try again later!");
            return ResponseEntity.internalServerError().body(contactResponse);
        }
        contactResponse.setMessage("SUCCESS");
        contactResponse.setResult(true);
        return ResponseEntity.accepted().body(contactResponse);
    }

    @GetMapping("/test")
    public String test() {
        log.info("Hello");
        return "success";
    }
}