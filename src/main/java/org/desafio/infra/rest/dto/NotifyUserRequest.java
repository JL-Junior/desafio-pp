package org.desafio.infra.rest.dto;

public record NotifyUserRequest(
        Long idUser,
        String message
) {
}
