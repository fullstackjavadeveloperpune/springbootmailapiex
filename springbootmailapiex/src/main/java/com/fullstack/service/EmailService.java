package com.fullstack.service;

import com.fullstack.model.EmailModel;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendEmail(EmailModel emailModel) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(fromEmail);
            mimeMessageHelper.setTo(emailModel.getToEmail());
            mimeMessageHelper.setCc(emailModel.getCcEmail());
            mimeMessageHelper.setBcc(emailModel.getBccEmail());
            mimeMessageHelper.setSubject(emailModel.getEmailSubject());
            mimeMessageHelper.setText(emailModel.getEmailBody());
            FileSystemResource fileSystemResource = new FileSystemResource(new File(emailModel.getEmailAttachment()));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

            javaMailSender.send(mimeMessage);

            log.info("Mail Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}
