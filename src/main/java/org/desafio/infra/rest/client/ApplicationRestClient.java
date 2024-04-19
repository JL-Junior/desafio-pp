package org.desafio.infra.rest.client;

import org.desafio.domain.exception.ApiCallException;

import java.util.UUID;

public interface ApplicationRestClient {
    void authorizeTransaction(UUID transactionId) throws ApiCallException;

    boolean notifyUser(Long userId) throws ApiCallException;
}
