package org.desafio.domain.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.validator.CustomEmailPattern;
import org.desafio.domain.validator.CustomLength;
import org.desafio.domain.validator.CustomMin;
import org.desafio.domain.validator.CustomRequiredField;
import org.hibernate.validator.constraints.Length;

public record CreateTransactionRequest(

        @CustomRequiredField(ErrorEnum.MISSING_AMOUNT)
        @CustomMin(messageEnum = ErrorEnum.MIN_AMOUNT, value = 0.01)
        Double amount,
        Long id_sender,
        Long id_receiver,

        @CustomEmailPattern(ErrorEnum.INVALID_SENDER_EMAIL_FORMAT)
        @CustomLength(value = 256, messageEnum = ErrorEnum.SENDER_EMAIL_TOO_MUCH_CHARS)
        String email_sender,

        @CustomEmailPattern(ErrorEnum.INVALID_RECEIVER_EMAIL_FORMAT)
        @CustomLength(value = 256, messageEnum = ErrorEnum.RECEIVER_EMAIL_TOO_MUCH_CHARS)
        String email_receiver,
        Long document_sender,
        Long document_receiver
) {
}
