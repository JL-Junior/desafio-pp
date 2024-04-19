package org.desafio.infra.rest.dto;

public record AuthorizationRequest(String transactionId, String fail) {
}
