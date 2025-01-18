package com.glowandrise.glowandrise_backend.service.impl;

import com.glowandrise.glowandrise_backend.request.ContactBrand;
import com.glowandrise.glowandrise_backend.request.ContactInfluencer;
import com.glowandrise.glowandrise_backend.service.ContactService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public int contactBrand(ContactBrand brand) {
        try {
            sendEmailForBrand(brand);
            return 1; // Success
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0; // Failure
        }
    }

    @Override
    public int contactInfluencer(ContactInfluencer influencer) {
        try {
            sendEmailForInfluencer(influencer);
            return 1; // Success
        } catch (MessagingException e) {
            e.printStackTrace();
            return 0; // Failure
        }
    }

    private void sendEmailForInfluencer(ContactInfluencer influencer) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo("mayur.valkunde@glowandrise.com"); // Replace with the recipient's email
        messageHelper.setSubject("New Influencer Contact Request");
        messageHelper.setText(buildEmailContentForInfluencer(influencer), true); // true enables HTML content

        javaMailSender.send(message);
    }

    private void sendEmailForBrand(ContactBrand brand) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo("mayur.valkunde@glowandrise.com"); // Replace with the recipient's email
        messageHelper.setSubject("New Brand Contact Request");
        messageHelper.setText(buildEmailContentForBrand(brand), true); // true enables HTML content

        javaMailSender.send(message);
    }

    private String buildEmailContentForInfluencer(ContactInfluencer influencer) {
        String categories = String.join(", ", influencer.getCategory());
        return "<html>" +
                "<body>" +
                "<h2 style=\"color: #4CAF50;\">New Influencer Contact Request</h2>" +
                "<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; width: 100%;\">" +
                "<tr>" +
                "<th style=\"text-align: left;\">Field</th>" +
                "<th style=\"text-align: left;\">Value</th>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Name</strong></td>" +
                "<td>" + influencer.getName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Instagram Link</strong></td>" +
                "<td><a href=\"" + influencer.getInstagramLink() + "\">" + influencer.getInstagramLink() + "</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Followers</strong></td>" +
                "<td>" + influencer.getFollowers() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Gender</strong></td>" +
                "<td>" + influencer.getGender().name() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Engagement Rate</strong></td>" +
                "<td>" + String.format("%.2f", influencer.getEngagementRate()) + "%</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Categories</strong></td>" +
                "<td>" + categories + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>YouTube Link</strong></td>" +
                "<td><a href=\"" + influencer.getYoutubeLink() + "\">" + influencer.getYoutubeLink() + "</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Subscribers</strong></td>" +
                "<td>" + influencer.getSubscribers() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Contact Number</strong></td>" +
                "<td>" + influencer.getContactNumber() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Email</strong></td>" +
                "<td>" + influencer.getEmail() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>City</strong></td>" +
                "<td>" + influencer.getCity() + "</td>" +
                "</tr>" +
                "</table>" +
                "</body>" +
                "</html>";
    }

    private String buildEmailContentForBrand(ContactBrand brand) {
        return "<html>" +
                "<body>" +
                "<h2 style=\"color: #4CAF50;\">New Brand Contact Request</h2>" +
                "<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\" style=\"border-collapse: collapse; width: 100%;\">" +
                "<tr>" +
                "<th style=\"text-align: left;\">Field</th>" +
                "<th style=\"text-align: left;\">Value</th>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Brand Name</strong></td>" +
                "<td>" + brand.getBrandName() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Website Link</strong></td>" +
                "<td><a href=\"" + brand.getWebsiteLink() + "\">" + brand.getWebsiteLink() + "</a></td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Contact Number</strong></td>" +
                "<td>" + brand.getContactNumber() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Email</strong></td>" +
                "<td>" + brand.getEmail() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td><strong>Comment</strong></td>" +
                "<td>" + brand.getComment() + "</td>" +
                "</tr>" +
                "</table>" +
                "</body>" +
                "</html>";
    }
}
