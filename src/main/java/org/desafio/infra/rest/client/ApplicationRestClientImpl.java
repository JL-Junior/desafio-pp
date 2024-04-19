package org.desafio.infra.rest.client;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.desafio.domain.exception.ApiCallException;
import org.desafio.infra.rest.dto.AuthorizationResponse;
import org.desafio.presentation.context.ApplicationContext;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.util.UUID;

@ApplicationScoped
public class ApplicationRestClientImpl implements ApplicationRestClient {

    private final AuthorizationRestClient authClient;

    private final NotificationRestClient notificationClient;

    @Inject
    public ApplicationRestClientImpl(@RestClient AuthorizationRestClient authClient,@RestClient NotificationRestClient notificationClient) {
        this.authClient = authClient;
        this.notificationClient = notificationClient;
    }

    @Override
    public void authorizeTransaction(UUID transactionId) throws ApiCallException {
        Log.info("Calling authorization service api...");

        try{
            authClient.authorizeTransaction();

        }catch (ClientWebApplicationException exception){
            Log.error("Error on call authorizations service!", exception);

            final Response response = exception.getResponse();

            throw ApiCallException
                    .builder()
                    .payload(response.readEntity(String.class))
                    .statusCode(response.getStatus())
                    .endpoint(response.getLocation().getPath())
                    .build();
        }
    }

    @Override
    public boolean notifyUser(Long userId) throws ApiCallException {
        // se falhar, retornar false
        return true;
    }
}
