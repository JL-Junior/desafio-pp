package org.desafio.infra.rest.client;

import jakarta.json.JsonObject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.desafio.infra.rest.dto.AuthorizationRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.accessibility.AccessibleStateSet;


@RegisterRestClient(configKey = "authorization-api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface AuthorizationRestClient {

    @POST
    @Path("/authorization")
    JsonObject authorizeTransaction(AuthorizationRequest request);
}