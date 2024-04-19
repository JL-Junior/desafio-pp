package org.desafio.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.desafio.domain.enumeration.ErrorEnum;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.Map;

public record ErrorResponse(
        String id,
        String message,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, Object> details
) {
        public ErrorResponse(ErrorEnum errorEnum){
                this(errorEnum.getCode(), errorEnum.getMessage(), LocalDateTime.now(), null);
        }

        public ErrorResponse(ErrorEnum errorEnum, Map<String, Object> details){
                this(errorEnum.getCode(), errorEnum.getMessage(), LocalDateTime.now(), details);
        }
}
