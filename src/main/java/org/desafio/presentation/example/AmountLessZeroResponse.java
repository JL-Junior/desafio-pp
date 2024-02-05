package org.desafio.presentation.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

public record AmountLessZeroResponse(

        @Schema(example = "3", description = "error message id")
        String id,

        @Schema(example = "Amount must to be bigger than zero", description = "error message")
        String message,

        @Schema(description = "Error time")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp

){
}
