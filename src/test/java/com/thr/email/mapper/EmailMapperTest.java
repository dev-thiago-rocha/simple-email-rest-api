package com.thr.email.mapper;

import com.thr.email.domain.Email;
import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;
import com.thr.email.enumerated.EmailStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith({MockitoExtension.class})
class EmailMapperTest {

    @InjectMocks
    private EmailMapper emailMapper;

    @Test
    public void toEmail_withSuccess() {
        // Arrange
        final var requestDto = SendEmailRequestDto.builder()
                .text("TEXT")
                .title("TITLE")
                .destination("DESTINATION")
                .sender("SENDER")
                .build();
        final var expected = Email.builder()
                .text(requestDto.getText())
                .title(requestDto.getTitle())
                .destination(requestDto.getDestination())
                .sender(requestDto.getSender())
                .build();

        // Act
        final Email actual = emailMapper.toEmail(requestDto);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void toSendEmailResponseDto_withSuccess() {
        // Assert
        final var email = Email.builder()
                .id(1L)
                .sender("SENDER")
                .destination("DESTINATION")
                .title("TITLE")
                .text("TEXT")
                .status(EmailStatus.SENT)
                .build();
        final var expected = SendEmailResponseDto.builder()
                .id(email.getId())
                .status(email.getStatus())
                .build();

        // Act
        final SendEmailResponseDto actual = emailMapper.toSendEmailResponseDto(email);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

}