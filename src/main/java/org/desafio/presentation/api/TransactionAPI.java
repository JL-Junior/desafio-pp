package org.desafio.presentation.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafio.presentation.example.AmountMissingResponse;
import org.desafio.domain.dto.CreateTransactionRequest;

import org.desafio.presentation.example.AmountLessZeroResponse;
import org.desafio.presentation.example.SenderNotFoundResponse;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

@Path("transactions")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TransactionAPI {

    @POST
    @APIResponse(
        responseCode = "201",
        description = "Transaction created"
    )
    @APIResponse(
            responseCode = "400",
            description = "Bad request",
            content = @Content(
                    schema = @Schema(
                            implementation = AmountMissingResponse[].class
                    ))
    )
    @APIResponse(
            responseCode = "404",
            description = "Receiver or sender not found",
            content = @Content(
            schema = @Schema(
                    implementation = SenderNotFoundResponse.class
            ))
    )
    @APIResponse(
            responseCode = "422",
            description = "Validation error",
            content = @Content(
                    schema = @Schema(
                            implementation = AmountLessZeroResponse.class
                    ))
    )
    Response createTransaction(CreateTransactionRequest request);
}
