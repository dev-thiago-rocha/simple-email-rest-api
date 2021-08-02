package com.thr.email.service.email;

import com.thr.email.domain.Email;
import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import com.thr.email.enumerated.EmailStatus;
import com.thr.email.mapper.EmailMapper;
import com.thr.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailRepository emailRepository;
    private final EmailMapper emailMapper;
    private final JavaMailSender javaMailSender;

    @Override
    public SendEmailResponseDto sendEmail(final SendEmailRequestDto requestDto) {

        final var simpleMailMessage = getMailMessage(requestDto);
        final var email = emailMapper.toEmail(requestDto);

        try {
            javaMailSender.send(simpleMailMessage);
            email.setStatus(EmailStatus.SENT);
        } catch (MailSendException e) {
            email.setStatus(EmailStatus.ERROR);
        }

        final Email savedEmail = emailRepository.save(email);

        return emailMapper.toSendEmailResponseDto(savedEmail);
    }

    private SimpleMailMessage getMailMessage(SendEmailRequestDto requestDto) {
        final var simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(requestDto.getSender());
        simpleMailMessage.setTo(requestDto.getDestination());
        simpleMailMessage.setSubject(requestDto.getTitle());
        simpleMailMessage.setText(requestDto.getText());

        return simpleMailMessage;
    }

}
