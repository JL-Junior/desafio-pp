package org.desafio.rest.exceptionhandler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.desafio.domain.dto.ErrorResponse;
import org.desafio.domain.exception.ApplicationException;

import java.sql.Date;

@Provider
public class ApplicationExceptionHandler implements ExceptionMapper<ApplicationException> {
    @Override
    public Response toResponse(ApplicationException e) {
        return Response
                .status(422)
                .entity(mapErrorResponse(e))
                .build();

    }

    private ErrorResponse mapErrorResponse(ApplicationException e) {
        return new ErrorResponse(e.getErrorEnum());
    }
}
