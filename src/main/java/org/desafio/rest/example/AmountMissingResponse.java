package org.desafio.rest.example;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.time.LocalDateTime;

public record AmountMissingResponse(

        @Schema(example = "4", description = "error message id")
        String id,

        @Schema(example = "Amount is missing", description = "error message")
        String message,

        @Schema(description = "Error time")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp
) {
}
