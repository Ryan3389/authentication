package com.authentication.authentication.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void sendEmail(String toUser, String emailSubject, String emailBody) throws MessagingException {
       MimeMessage emailMessage = emailSender.createMimeMessage();
       MimeMessageHelper emailHelper = new MimeMessageHelper(emailMessage, true);

       emailHelper.setTo(toUser);
       emailHelper.setSubject(emailSubject);
       emailHelper.setText(emailBody, true);

       emailSender.send(emailMessage);
    }
}
