package com.thr.email.service.email;

import com.thr.email.dto.email.SendEmailRequestDto;
import com.thr.email.dto.email.SendEmailResponseDto;

public interface EmailService {

    SendEmailResponseDto sendEmail(SendEmailRequestDto requestDto);

}
