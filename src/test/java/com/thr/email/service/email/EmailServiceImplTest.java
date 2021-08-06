package com.thr.email.service.email;

import com.thr.email.domain.Email;
import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import com.thr.email.enumerated.EmailStatus;
import com.thr.email.mapper.EmailMapper;
import com.thr.email.repository.EmailRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private EmailMapper emailMapper;

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender javaMailSender;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> simpleMailMessageCaptor;

    @Captor
    private ArgumentCaptor<Email> emailCaptor;

    @Test
    void sendEmail_withSuccess() {
        // Arrange
        final var requestDto = SendEmailRequestDto.builder()
                .text("TEXT")
                .title("TITLE")
                .destination("DESTINATION")
                .sender("SENDER")
                .build();

        final var email = Email.builder()
                .text(requestDto.getText())
                .title(requestDto.getTitle())
                .destination(requestDto.getDestination())
                .sender(requestDto.getSender())
                .build();

        final var expected = SendEmailResponseDto.builder()
                .id(email.getId())
                .status(email.getStatus())
                .build();

        final var expectedMessageMail = new SimpleMailMessage();
        expectedMessageMail.setText(email.getText());
        expectedMessageMail.setSubject(email.getTitle());
        expectedMessageMail.setTo(email.getDestination());
        expectedMessageMail.setFrom(email.getSender());

        when(emailMapper.toEmail(requestDto)).thenReturn(email);
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));
        when(emailRepository.save(email)).thenReturn(email);
        when(emailMapper.toSendEmailResponseDto(email)).thenReturn(expected);

        // Act
        final var actual = emailService.sendEmail(requestDto);

        // Assert
        verify(emailMapper).toEmail(requestDto);
        verify(javaMailSender).send(simpleMailMessageCaptor.capture());
        verify(emailRepository).save(emailCaptor.capture());
        verify(emailMapper).toSendEmailResponseDto(email);
        assertEquals(expected, actual);
        assertEquals(expectedMessageMail, simpleMailMessageCaptor.getValue());
        assertEquals(EmailStatus.SENT, emailCaptor.getValue().getStatus());
    }

    @Test
    void sendEmail_withMailSendException() {
        // Arrange
        final var requestDto = SendEmailRequestDto.builder()
                .text("TEXT")
                .title("TITLE")
                .destination("DESTINATION")
                .sender("SENDER")
                .build();

        final var email = Email.builder()
                .text(requestDto.getText())
                .title(requestDto.getTitle())
                .destination(requestDto.getDestination())
                .sender(requestDto.getSender())
                .build();

        final var expected = SendEmailResponseDto.builder()
                .id(email.getId())
                .status(EmailStatus.ERROR)
                .build();

        final var expectedMessageMail = new SimpleMailMessage();
        expectedMessageMail.setText(email.getText());
        expectedMessageMail.setSubject(email.getTitle());
        expectedMessageMail.setTo(email.getDestination());
        expectedMessageMail.setFrom(email.getSender());

        when(emailMapper.toEmail(requestDto)).thenReturn(email);
        doThrow(MailSendException.class).when(javaMailSender).send(any(SimpleMailMessage.class));
        when(emailRepository.save(email)).thenReturn(email);
        when(emailMapper.toSendEmailResponseDto(email)).thenReturn(expected);

        // Act
        final var actual = emailService.sendEmail(requestDto);

        // Assert
        verify(emailMapper).toEmail(requestDto);
        verify(javaMailSender).send(simpleMailMessageCaptor.capture());
        verify(emailRepository).save(emailCaptor.capture());
        verify(emailMapper).toSendEmailResponseDto(email);
        assertEquals(expected, actual);
        assertEquals(expectedMessageMail, simpleMailMessageCaptor.getValue());
        assertEquals(EmailStatus.ERROR, emailCaptor.getValue().getStatus());
    }
}