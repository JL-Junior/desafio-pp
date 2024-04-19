package org.desafio.infra.rest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.desafio.infra.rest.dto.AuthorizationResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestResponse;


@RegisterRestClient(configKey = "authorization-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthorizationRestClient {

    @GET
    RestResponse<AuthorizationResponse> authorizeTransaction();
}