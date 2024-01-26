package org.desafio.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.desafio.domain.enumeration.ErrorEnum;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplicationException extends RuntimeException{

    ErrorEnum errorEnum;
    public static ApplicationException of(ErrorEnum errorEnum) {
        return new ApplicationException(errorEnum);
    }

}
