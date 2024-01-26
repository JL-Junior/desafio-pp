package org.desafio.rest.exceptionhandler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.desafio.domain.dto.ErrorResponse;
import org.desafio.domain.exception.ApplicationException;
import org.desafio.domain.exception.NotFoundApplicationException;

@Provider
public class NotFoundApplicationHandler implements ExceptionMapper<NotFoundApplicationException> {
    @Override
    public Response toResponse(NotFoundApplicationException e) {
        return Response
                .status(404)
                .entity(mapErrorResponse(e))
                .build();
    }

    private ErrorResponse mapErrorResponse(ApplicationException e) {
        return new ErrorResponse(e.getErrorEnum());
    }
}
