package org.desafio.presentation.exceptionhandler;

import io.vertx.core.json.JsonObject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.desafio.domain.dto.ErrorResponse;
import org.desafio.domain.enumeration.ErrorEnum;
import org.desafio.domain.exception.ApiCallException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Provider
public class ApiCallExceptionHandler implements ExceptionMapper<ApiCallException> {
    @Override
    public Response toResponse(ApiCallException e) {
        return Response
                .status(500)
                .entity(mapErrorResponse(e))
                .build();
    }

    private ErrorResponse mapErrorResponse(ApiCallException e) {
        return new ErrorResponse(e.getErrorEnum());
    }
}
