package com.thr.email.controller;

import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import com.thr.email.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public SendEmailResponseDto sendEmail(@Valid @RequestBody SendEmailRequestDto requestDto) {
        return emailService.sendEmail(requestDto);
    }

}
