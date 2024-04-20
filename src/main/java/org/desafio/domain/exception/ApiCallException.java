package org.desafio.domain.exception;

import jakarta.json.JsonObject;
import lombok.Builder;
import lombok.Getter;
import org.desafio.domain.enumeration.ErrorEnum;

@Getter
public class ApiCallException extends ApplicationException {
    public ApiCallException(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
