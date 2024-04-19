package org.desafio.infra.rest.client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.desafio.infra.rest.dto.NotifyUserRequest;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "notification-api")
@Path("/notification")
public interface NotificationRestClient {

    @POST
    boolean notifyUser(NotifyUserRequest request, @HeaderParam("fail") boolean fail);
}
