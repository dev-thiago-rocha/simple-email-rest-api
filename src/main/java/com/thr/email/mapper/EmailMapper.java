package com.thr.email.mapper;

import com.thr.email.domain.Email;
import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import org.springframework.stereotype.Component;

@Component
public class EmailMapper {

    public Email toEmail(SendEmailRequestDto requestDto) {
        return Email.builder()
                .destination(requestDto.getDestination())
                .sender(requestDto.getSender())
                .title(requestDto.getTitle())
                .text(requestDto.getText())
                .build();
    }

    public SendEmailResponseDto toSendEmailResponseDto(Email email) {
        return SendEmailResponseDto.builder()
                .id(email.getId())
                .status(email.getStatus())
                .build();
    }

}
