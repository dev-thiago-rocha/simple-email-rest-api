package com.thr.email.dto.email;

import com.thr.email.enumerated.EmailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailResponseDto {
    private Long id;
    private EmailStatus status;
}
