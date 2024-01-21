package org.desafio.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.desafio.domain.enumeration.ErrorEnum;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

public record ErrorResponse(
        String id,
        String message,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp
) {
        public ErrorResponse(ErrorEnum errorEnum){
                this(errorEnum.getCode(), errorEnum.getMessage(), LocalDateTime.now());
        }
}
