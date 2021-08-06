package com.thr.email.controller;

import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import com.thr.email.enumerated.EmailStatus;
import com.thr.email.service.email.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class EmailControllerTest {

    @InjectMocks
    private EmailController emailController;

    @Mock
    private EmailService emailService;

    @Test
    void sendEmail_withSuccess() {
        // Arrange
        final var requestDto = SendEmailRequestDto.builder()
                .title("TITLE")
                .text("TEXT")
                .sender("SENDER")
                .destination("DESTINATION")
                .build();

        final var expected = SendEmailResponseDto.builder()
                .id(1L)
                .status(EmailStatus.SENT)
                .build();

        when(emailService.sendEmail(requestDto)).thenReturn(expected);

        // Act
        final SendEmailResponseDto actual = emailController.sendEmail(requestDto);

        // Assert
        verify(emailService).sendEmail(requestDto);
        assertEquals(expected, actual);
    }
}