package com.demo.service;

import com.demo.POJO.Email;
import jakarta.mail.internet.InternetAddress;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class EmailSenderService {

    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendOtpEmail(String to, String otp) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(new InternetAddress("cantino287@gmail.com", "Cantino")); // 👈 Add this line

            helper.setTo(to);
            helper.setSubject("Your OTP Code");
            helper.setText("Your OTP code is: " + otp);

            javaMailSender.send(message);
            System.out.println("OTP email sent successfully to " + to);
        } catch (Exception e) {
            System.err.println("Failed to send OTP email: " + e.getMessage());
            throw new RuntimeException("Failed to send OTP email");
        }
    }


public void sendEmail(MimeMessage mimeMessage) {
    javaMailSender.send(mimeMessage);
}


    public MimeMessage createMimeMessage() {
        return javaMailSender.createMimeMessage();
    }

    public void sendEmailWithAttachment(String to, String subject, String text, File file) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            helper.addAttachment(file.getName(), file);

            javaMailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email with attachment: " + e.getMessage());
        }
    }

}
