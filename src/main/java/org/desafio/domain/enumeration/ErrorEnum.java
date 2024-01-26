package org.desafio.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {
    DEFAULT_VALIDATION_ERROR("000", "Default Validation Error"),
    MISSING_AMOUNT("001", "Amount field is missing"),
    MIN_AMOUNT("002", "Amount can't be less than 0.01"),
    SENDER_EMAIL_TOO_MUCH_CHARS("003", "Sender email has invalid length"),
    RECEIVER_EMAIL_TOO_MUCH_CHARS("004", "Receiver email has invalid length"),
    NO_RECEIVER_DATA("005", "Has no receiver data"),
    NO_SENDER_DATA("006", "Has no sender data"),
    TOO_MUCH_RECEIVER_ID_FIELDS("007", "Too much receiver id fields"),
    TOO_MUCH_SENDER_ID_FIELDS("008", "Too much sender id fields"),
    INVALID_SENDER_EMAIL_FORMAT("009", "Invalid sender email format"),
    INVALID_RECEIVER_EMAIL_FORMAT("010", "Invalid receiver email format"),
    SAME_RECEIVER_AND_SENDER_DATA("011","Same receiver and sender identifier value" ),
    SENDER_NOT_FOUND("012", "Sender not found"),
    RECEIVER_NOT_FOUND("013", "Receiver not found"),
    SENDER_TYPE_CANT_PAY("014", "Sender type can't do payments"),
    RECEIVER_TYPE_CANT_RECEIVE("015", "Receiver type can't receive payments"),
    INSUFFICIENT_FUNDS("016", "Insufficient funds");

    private final String code;
    private final String message;

}
