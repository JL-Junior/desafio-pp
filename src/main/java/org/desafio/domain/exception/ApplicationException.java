package org.desafio.domain.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.desafio.domain.enumeration.ErrorEnum;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class ApplicationException extends RuntimeException{

    final ErrorEnum errorEnum;
}
