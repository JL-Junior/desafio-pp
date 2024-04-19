package org.desafio.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.desafio.domain.enumeration.ErrorEnum;

public class NotFoundApplicationException extends ApplicationException {

    public NotFoundApplicationException(ErrorEnum errorEnum) {
        super(errorEnum);
    }

    public static NotFoundApplicationException of(ErrorEnum errorEnum){
        return new NotFoundApplicationException(errorEnum);
    }

}
