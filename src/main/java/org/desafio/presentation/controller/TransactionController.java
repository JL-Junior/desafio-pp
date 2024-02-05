package org.desafio.presentation.controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.desafio.domain.dto.CreateTransactionRequest;
import org.desafio.domain.usecase.CreateTransactionUseCase;
import org.desafio.presentation.api.TransactionAPI;

import java.net.URI;
import java.util.UUID;

@ApplicationScoped
public class TransactionController implements TransactionAPI {

    final CreateTransactionUseCase createTransactionUseCase;

    final UriInfo uri;

    @Inject
    public TransactionController(CreateTransactionUseCase createTransactionUseCase, UriInfo uri) {
        this.createTransactionUseCase = createTransactionUseCase;
        this.uri = uri;
    }

    @Override
    public Response createTransaction(CreateTransactionRequest request) {
        UUID id = createTransactionUseCase.createTransaction(request);

        return Response.created(URI.create(uri.getPath() + "/" + id.toString())).build();
    }

}
