package com.thr.email.dto.email;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailRequestDto {

    @NotBlank
    @Size(max = 100)
    private String destination;

    @NotBlank
    @Size(max = 100)
    private String sender;

    @NotBlank
    @Size(max = 100)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String text;

}
