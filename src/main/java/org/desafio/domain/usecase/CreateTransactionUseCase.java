package org.desafio.domain.usecase;

import jakarta.validation.Valid;
import org.desafio.domain.dto.CreateTransactionRequest;

import java.util.UUID;

public interface CreateTransactionUseCase {

    UUID createTransaction(@Valid CreateTransactionRequest request);
}
