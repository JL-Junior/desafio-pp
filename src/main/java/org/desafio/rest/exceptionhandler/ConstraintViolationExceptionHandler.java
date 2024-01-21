package org.desafio.rest.exceptionhandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.desafio.domain.dto.ErrorResponse;
import org.desafio.domain.enumeration.ErrorEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    private final List<ErrorEnum> errors = Arrays.asList(ErrorEnum.values());

    @Override
    public Response toResponse(ConstraintViolationException e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(mapErrorMessage(e))
                .build();
    }

    private List<ErrorResponse> mapErrorMessage(ConstraintViolationException e) {

        return e.getConstraintViolations()
                .stream()
                .map(violation -> mapErrorResponse(violation))
                .toList();

    }

    private ErrorResponse mapErrorResponse(ConstraintViolation<?> violation) {
        ErrorEnum error = errors.stream()
                .filter(errorEnum -> errorEnum.getCode().equals(violation.getMessage()))
                .findAny()
                .orElseThrow();

        return new ErrorResponse(error);
    }
}
