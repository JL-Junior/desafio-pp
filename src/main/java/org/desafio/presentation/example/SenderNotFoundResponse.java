package org.desafio.presentation.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

public record SenderNotFoundResponse(
        @Schema(example = "1", description = "error message id")
        String id,

        @Schema(example = "Sender not found", description = "error message")
        String message,

        @Schema(description = "Error time")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp
) {
}
