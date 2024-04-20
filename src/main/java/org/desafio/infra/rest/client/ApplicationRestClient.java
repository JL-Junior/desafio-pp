package org.desafio.infra.rest.client;

import org.desafio.domain.exception.ApiCallException;
import org.desafio.infra.rest.dto.AuthorizationResponse;
import org.desafio.infra.rest.dto.NotifyUserResponse;

import java.util.UUID;

public interface ApplicationRestClient {
    AuthorizationResponse authorizeTransaction(UUID transactionId) throws ApiCallException;

    NotifyUserResponse notifyUser(Long userId) throws ApiCallException;
}
