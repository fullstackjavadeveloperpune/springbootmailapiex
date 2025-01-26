package com.fullstack.controller;

import com.fullstack.model.EmailModel;
import com.fullstack.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mail")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendMail(@RequestBody EmailModel emailModel){
        emailService.sendEmail(emailModel);
        return ResponseEntity.ok("Email Sent Successfully");
    }
}
